package br.senai.sp.agenda.agendacontatos;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.senai.sp.agenda.dao.ContatoDAO;
import br.senai.sp.agenda.modelo.Contato;

public class CadastroContatoActivity extends AppCompatActivity {


    private CadastroContatoHelper helper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_cadastro);


        //passando essa activity para a  helper como parametro * a helper pega o que esta na activity e cria uma
        //ligaçao entre a activity e o layout e ela retorna o que esta escrito nas views para o contato
        helper = new CadastroContatoHelper(this);

        //Pegando a intent em que o filme foi serializado
        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");

        if (contato != null) {
            helper.preencherFormulario(contato);
        }
    }

    //****** CRIANDO O MENU


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //menu vazio da activity

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro, menu);

        // inflnado um menu usando o outro

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // *** metodo que recebe o item selecionado

        switch (item.getItemId()) {
            case R.id.menu_salvar:

                Contato contato = helper.getContato();
                ContatoDAO dao = new ContatoDAO(this);
                // this >>> a activity cadastro esta chamando o dao e retorna o contexto dela para o DAO


                if (contato.getId() == 0) {
                    //chamando o metodo salvar do DAO
                    dao.salvar(contato);
                } else {
                    //chamando o metodo atualizar do DAO
                    dao.atualizar(contato);
                }

                //fechando o banco
                dao.close();
                break;

            case R.id.menu_deletar:
                Toast.makeText(CadastroContatoActivity.this, "Excuindo", Toast.LENGTH_LONG).show();
                break;

            default:
                Toast.makeText(CadastroContatoActivity.this, "Nada", Toast.LENGTH_LONG).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}