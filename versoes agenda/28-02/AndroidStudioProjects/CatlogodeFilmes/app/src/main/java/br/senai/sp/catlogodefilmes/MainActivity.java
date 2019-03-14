package br.senai.sp.catlogodefilmes;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class MainActivity extends AppCompatActivity {

    private ListView listaFilmes;
    private Button btNovoFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //***Associo o objeto ListView do JAVA À VIEW LIST VIEW DO LAYOUT XML
        listaFilmes = findViewById(R.id.list_filmes);
        btNovoFilme = findViewById(R.id.bt_novo_filme);

        Toast.makeText(this, "Estou no onCreat", Toast.LENGTH_SHORT).show();

        btNovoFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Tostadinhas", Toast.LENGTH_LONG).show();

                //Por estar no contexto do botão tem que colocar a classe em que está e a activity que eu quero que abra
                Intent abrirCadastro = new Intent(MainActivity.this, CadastroActivity.class);

                //Abri a activity
                startActivity(abrirCadastro);
            }
        });

        //*** DEFINIÇÃO DE UM MENU DE CONTEXTO PARA A LISTVIEW
        registerForContextMenu(listaFilmes);
        //registrar um contexto para o menu//*** PASSA O CONTEXTO DA VIEW , passa a LIST VIEW QUE É UM OBJETO
        //CRIA UM MENU DE CONTEXTO NA LISTVIEW E INTERNAMENTE ESSE MÉTODO CHAMA O MÉTODO onCreateContextMenu
        //QUANDO O MENU É CRIADO ELE DISPARA UM EVENTO PARA CRIAR O CONTEXTO DO MENU QUE É O onCreateContextMenu


        //****LISTENER PARA O ITEM DO MENU CONTEXTO
        listaFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme filme = (Filme) listaFilmes.getItemAtPosition(position);

                //intenção de abrir essa activy a partir da main activity eu quero abrir a cadastro main
                Intent cadastro = new Intent(MainActivity.this, CadastroActivity.class);

                //dentro da inteção ela recebe um filme
                cadastro.putExtra("filme", filme);
                //PARA EU PASSAR O FILME EU TENHO QUE SERIALIZAR ELE, PORQUE ELE AINDA É OBJETO
                //serializar é pegar o objeto e decompor ele em bits e quando ele for decerializado no destino final tem que estar por completo
                //serializar é decompor na menor particula

                //abrir a activity
                startActivity(cadastro);
            }
        });
    }


    //**** METODO CRIADO PARA INFLAR O MENU DE CONTEXTO
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    //*** ele passa quem é o menu()


        //*** COLOCANDO UM ITEM NO MENU
        /*MenuItem deletar = menu.add("Excluir");
        MenuItem editar = menu.add("Editar");

        //NO BOTÃO DELETAR EU COLOCO UM OUVINTE NO ITEM DE MENU
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Toast.makeText(MainActivity.this, "Excluir", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);*/

        MenuInflater inflar = getMenuInflater();
        inflar.inflate(R.menu.menu_context_lista_filmes, menu);

    }


    //MÉTODO É CHAMADO QUANDO O ITEM É CLICADO
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //tem final porque o metodo dialog ta usando um valor de fora de seu método
        final FilmeDAO dao = new FilmeDAO(MainActivity.this);

        //o adapter ele lê o que está no item e pega a informação do menuContext criado
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //pegar o item do filme na posição da info
        final Filme filme = (Filme) listaFilmes.getItemAtPosition(info.position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

            //colocando um título na caixa de diálogo
            builder.setTitle("Excluir Filme");

            //mensagem da caixa de diálogo
            builder.setMessage("Confirma a exclusão do filme " + filme.getTitulo() + "?");

            builder.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                dao.excluir(filme);

                Toast.makeText(MainActivity.this, "Excluído com sucesso", Toast.LENGTH_SHORT).show();

                dao.close();

                carregarLista();
                    }
            });
            builder.setNegativeButton("Não",null);

            builder.create().show();



        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "Estou no onResume", Toast.LENGTH_SHORT).show();

        //NO ON RESUME CHAMA O CARREGAR LISTA
        carregarLista();

        super.onResume();
    }

    //***MÉTODO UTILIZADO PARA CARREGAR TODA A LISTA NO ARRAY
    private void carregarLista(){

        // *** MATRIZ DE FILMES QUE SERÃO EXIBIDOS NO ListView
        FilmeDAO dao = new FilmeDAO(this);

        //vamos usar o arraylist que vem do dao
        List<Filme> filmes = dao.getFilmes();

        //FECHAR O BANCO
        dao.close();

        // *** Criação do Adapter que carrega os filmes na ListView
        // O primeiro parâmetro é qual activity será utilizado
        //*** utilizando um layout pronto (simple_list_item_1) - padrão
        ArrayAdapter<Filme> ListaFilmesAdapter = new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1,filmes);


        listaFilmes.setAdapter(ListaFilmesAdapter);
        //a lista filmes recebe os filmes

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
