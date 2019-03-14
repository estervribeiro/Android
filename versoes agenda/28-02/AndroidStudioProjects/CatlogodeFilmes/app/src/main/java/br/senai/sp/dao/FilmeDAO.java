package br.senai.sp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.modelo.Filme;

public class FilmeDAO extends SQLiteOpenHelper{

    //Toda classe tem um construtor, mas essa temos que criar por que não sabemos o nome do banco

    //Quando eu for criar um novo filmeDAO, obrigatoriamente essa classe é chamada PRIMEIRO
    //O meu construtor ta chamando da classe mãe e abri o banco
    public FilmeDAO(Context context) {
        super(context, "db_filme", null, 1);
    }
    //o contexto é obrigatorio, se eu chamar o filmeDAO tem que falar qual activity vai chamar

    @Override//O MÉTODO ESTÁ SENDO SOBRE ESCRITO NA CLASSE MAE
    public void onCreate(SQLiteDatabase db) {
        //ELE SÓ ENTRA NO onCreat UMA VEZ

        String sql = "CREATE TABLE tbl_filme (" +
                "id INTEGER PRIMARY KEY, " +
                "titulo TEXT NOT NULL, " +
                "diretor TEXT NOT NULL, " +
                "genero TEXT NOT NULL, " +
                "data_lancamento TEXT NOT NULL, " +
                "duracao TEXT NOT NULL, " +
                "nota INTEGER NOT NULL)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salvar(Filme filme) {

        //O db agora é um banco de dados para gravar
        SQLiteDatabase db = getWritableDatabase();


        ContentValues dados = getContentValues(filme);

        // aqui insere no banco tbl_filme os dados.put   //o null mão importa agora, pois não vamos usar
        db.insert("tbl_filme", null, dados);

    }

    public void excluir (Filme filme){

        //apagar algo do banco é escrever
        SQLiteDatabase db = getWritableDatabase();

        //vou passar como parametro o id do filme
        String [] params = {String.valueOf(filme.getId())};
        //transformando o id em string

        //deletar do banco
        //Excluir da tabela filme, onde o id é tal, passando o parametro de string
        db.delete("tbl_filme","id = ?", params);

        //params = parameters
    }



    public void atualizar(Filme filme){

        SQLiteDatabase db = getWritableDatabase();

        String [] params = {String.valueOf(filme.getId())};


        db.update("tbl_filme", getContentValues(filme), "id = ?", params);

    }

    //ContentValues foi criado para conter os valores
    @NonNull
    private ContentValues getContentValues(Filme filme) {
        //ContentValues foi criado para conter os valores
        ContentValues dados = new ContentValues();

        //aqui coloca os valores no ContentValues
        dados.put("titulo", filme.getTitulo());
        dados.put("diretor", filme.getDiretor());
        dados.put("genero", filme.getGenero());
        dados.put("data_lancamento", filme.getDataLancamento());
        dados.put("duracao", filme.getDuracao());
        dados.put("nota", filme.getNota());
        return dados;
    }


    //MÉTODO PARA FAZER A LISTA DE FILMES
    public List<Filme> getFilmes() {

        //comando do SQL para ler o banco, fazer SELECT
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM tbl_filme";

        //ele roda um comando sql no banco e retorna um resultado
        Cursor c = db.rawQuery(sql, null);
        //o CURSOR é para navegar pelas linhas descendo e subindo

        List<Filme> filmes = new ArrayList<>();

        //a cada volta o cursor vai descer uma linha
        while(c.moveToNext()){

            Filme filme = new Filme();

            ////quando o curso tiver na primeira linha ele vai pegar o inteiro da coluna id
            filme.setId(c.getInt(c.getColumnIndex("id")));
            filme.setTitulo(c.getString(c.getColumnIndex("titulo")));
            filme.setDiretor(c.getString(c.getColumnIndex("diretor")));
            filme.setGenero(c.getString(c.getColumnIndex("genero")));
            filme.setDataLancamento(c.getString(c.getColumnIndex("data_lancamento")));
            filme.setDuracao(c.getString(c.getColumnIndex("duracao")));
            filme.setNota(c.getInt(c.getColumnIndex("nota")));

            //adicionando o filme na lista filmes
            filmes.add(filme);
        }

        return filmes;
    }
}
