package br.senai.sp.catlogodefilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class CadastroActivity extends AppCompatActivity {

    private CadastroFilmeHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //eu vou passar essa activity como parametro
        helper = new CadastroFilmeHelper(this);
        /*helper - no construtor ele pega a activity e lá ele vai criar a ligação entre
          a activity e o layout e ele vai fazer o que ta escrito nas view e retorna para o filme*/


        //Pegar a intent que eu serializei o filme
        Intent intent = getIntent();
        Filme filme = (Filme) intent.getSerializableExtra("filme");

        if(filme != null){
            helper.preencherFormulario(filme);
        }


    }

    //***MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //*** esse menu é o menu da activity e ele está vazio

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_filmes, menu);
        //*** e eu vou inflar esse menu com o meu menu

        return super.onCreateOptionsMenu(menu);
    }

    //***DAS OPÇÕES QUAL FOI O ITEM SELECIONADO
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //***Ele recebe o item selecionado

        //item.getItemId();
        switch (item.getItemId()){
            case R.id.menu_salvar:



                Filme filme = helper.getFilme();
                FilmeDAO dao = new FilmeDAO(this);
                //this - O cadastro activity ta chamando o dao e retorna ao cadastro activity como contexto para o filmeDAO

                if(filme.getId() == 0){
                    dao.salvar(filme);

                }else{
                    dao.atualizar(filme);
                }

                //chamar o metodo salvar no dao


                //fechar o banco
                dao.close();
                //Toast.makeText(CadastroActivity.this,filme.getTitulo(), Toast.LENGTH_LONG).show();
                finish();


                //abrir o banco
                //query insert
                //fechar o banco
                break;

            case R.id.menu_del:
                Toast.makeText(CadastroActivity.this,"Excluir", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_configuracoes:
                Toast.makeText(CadastroActivity.this,"Configurações", Toast.LENGTH_LONG).show();
                break;

                default:
                Toast.makeText(CadastroActivity.this,"Nada", Toast.LENGTH_LONG).show();
                break;
        }



        return super.onOptionsItemSelected(item);
    }

}
