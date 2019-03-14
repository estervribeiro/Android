package br.senai.sp.catalogodefilmes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        listaFilmes = findViewById(R.id.list_filmes);
        // ***** Vetor de filmes *******

       // abrir o banco de dados
        //rodas uma query de consulta
        //retomar um arrayList



        btNovoFilme = findViewById(R.id.btn_novo_filme);
        btNovoFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                Intent abrirCadastro = new Intent(MainActivity.this, CadastroActvity.class);
                startActivity(abrirCadastro);
            }
        });

        //**** menu de contexto ***//

        registerForContextMenu(listaFilmes);

        listaFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme filme = (Filme) listaFilmes.getItemAtPosition(position);

                Intent cadastro = new Intent(MainActivity.this, CadastroActvity.class);
                cadastro.putExtra("filme", filme);
                startActivity(cadastro);


                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_lista_filmes, menu);



        /*
        MenuItem deletar = menu.add("Excluir");
        MenuItem editar = menu.add("Editar");
        MenuItem vizualisar = menu.add("vizuzlixar");
        super.onCreateContextMenu(menu, v, menuInfo);

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Toast.makeText(MainActivity.this, "Deletar", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        */

    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Filme filme = (Filme) listaFilmes.getItemAtPosition(info.position);
        new AlertDialog.Builder(this)
                .setTitle("Deletando Filme")
                .setMessage("Tem certeza que deseja deletar o filme " + filme.getTitulo() +  "?")
                .setPositiveButton("sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FilmeDAO dao = new FilmeDAO(MainActivity.this);



                                Toast.makeText(MainActivity.this, filme.getTitulo(), Toast.LENGTH_LONG).show();

                                dao.excluir(filme);

                                dao.close();

                                carregarLista();
                            }
                        })
                .setNegativeButton("não", null)
                .show();






        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        carregarLista();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();
        super.onPause();
    }


    private void carregarLista(){

        FilmeDAO dao = new FilmeDAO(this);
        List<Filme> filmes = dao.getFilmes();
        dao.close();

        //*** Criar um Adapter para carregar um vetor na listView ****///

        ArrayAdapter<Filme> listaFilmesAdapter  = new ArrayAdapter<Filme>(this , android.R.layout.simple_list_item_1, filmes);

        listaFilmes.setAdapter(listaFilmesAdapter);

    }
}
