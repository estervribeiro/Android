package br.senai.sp.catalogodefilmes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class CadastroActvity extends AppCompatActivity {

    private  CadastroFilmeHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        helper = new CadastroFilmeHelper(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_filmes, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_salvar:

                Filme filme = helper.getFilme();

                FilmeDAO dao = new FilmeDAO(this);
                dao.salvar(filme);
                dao.close();
                Toast.makeText(this, filme.getTitulo() + " gravado com sucesso!", Toast.LENGTH_LONG).show();
                finish();

                //abrir o banco
                //query de insert
                //fechar banco


                break;
            case R.id.menu_del:
                Toast.makeText(CadastroActvity.this, "Excluir", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_config:
                Toast.makeText(CadastroActvity.this, "configurações", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
