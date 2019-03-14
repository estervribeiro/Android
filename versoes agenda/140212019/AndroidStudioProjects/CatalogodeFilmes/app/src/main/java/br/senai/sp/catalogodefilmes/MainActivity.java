package br.senai.sp.catalogodefilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    private Button btnVoltar;



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
