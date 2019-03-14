package br.senai.sp.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.agenda.modelo.Contato;

public class ContatoDAO extends SQLiteOpenHelper{



    //Criamos um construtor para essa classe para sabermos o nome do banco **toda classe tem construtor essa é execessao

    // Quando criado um novo ContatoDAO essa é a primeira classe a ser chamada
    //O construtor chama a classe mãe e abre o banco


    //O contexto é obrigatório, quando o FilmeDAOé chamdo tem que falar qual activity vai chamar
        public ContatoDAO(Context context){
        super(context, "db_contato", null, 1);
    }
    // ******* Escrevendo o metodo na classe mãe
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Só se entra no onCreate UMA VEZ


        //criando a tabela com os dados
        String sql = "CREATE TABLE tbl_contato(" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "logradouro TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "endereco_linkedin TEXT NOT NULL) ";
        //executando o banco(sql)
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salvar (Contato contato){
            //tornando o db em um banco de dados para que ocorra o salvamento
            SQLiteDatabase db = getWritableDatabase();

            ContentValues dados = getContentValues(contato);

            //inserindo a tbl_contato dados.put no banco
        db.insert("tbl_contato", null, dados);

    }

    public void excluir (Contato contato){

            //apagando dados do banco
        SQLiteDatabase db = getWritableDatabase();

        //passando o id do contato como parametro para a exclusao
        String[] params = {String.valueOf(contato.getId())};
        // tbm transformei o id em string com o toString

        //deletando, excluindo da tabela de contatos, passar o id, e passar o params de String
        db.delete("tbl_contato", "id = ?", params);

    }

    public void atualizar (Contato contato){

            SQLiteDatabase db = getWritableDatabase();

            String [] params = {String.valueOf(contato.getId())};

            db.update("tbl_contato", getContentValues(contato), "id = ?", params);
    }

    // ContentvValues é criado para conter os valores
    private ContentValues getContentValues(Contato contato) {
            ContentValues dados = new ContentValues();

            // colocando os valores nos contentValues
        dados.put("nome", contato.getNome());
        dados.put("logradouro", contato.getLogradouro());
        dados.put("email", contato.getEmail());
        dados.put("telefone", contato.getTelefone());
        dados.put("endereco_linkedin", contato.getEnderecoLinkedin());
        return dados;
    }

    //******** metodo usado para fazer a lista de filmes
    public List<Contato> getContatos(){

     //comando usado para o ler o banco e fazer o select
     SQLiteDatabase db = getReadableDatabase();

     String sql = "SELECT * FROM tbl_contato";


     //usado para rodar um comando no banco e trazer o resultado
        Cursor c = db.rawQuery(sql, null);
        //navegando nas linhas

        List<Contato> contatos = new ArrayList<>();

        //a cada volta do while o cursor vai descer uma linha
        while (c.moveToNext()){

            Contato contato = new Contato();

            contato.setId(c.getInt(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setLogradouro(c.getString(c.getColumnIndex("logradouro")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contato.setEnderecoLinkedin(c.getString(c.getColumnIndex("endereco_linkedin")));

            contatos.add(contato);

        }


        return contatos;


    }


}
