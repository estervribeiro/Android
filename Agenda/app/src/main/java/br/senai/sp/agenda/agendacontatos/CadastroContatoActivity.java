package br.senai.sp.agenda.agendacontatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.senai.sp.agenda.R;
import br.senai.sp.agenda.dao.ContatoDAO;
import br.senai.sp.agenda.modelo.Contato;

public class CadastroContatoActivity extends AppCompatActivity {


    private CadastroContatoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        final Contato contato = helper.getContato();
        final ContatoDAO dao = new ContatoDAO(this);

        switch (item.getItemId()) {
            case R.id.menu_salvar:
                // this >>> a activity cadastro esta chamando o dao e retorna o contexto dela para o DAO
                if(helper.validar(CadastroContatoActivity.this)==true) {

                    if (contato.getId() == 0) {
                        //chamando o metodo salvar do DAO
                        dao.salvar(contato);
                        Toast.makeText(CadastroContatoActivity.this, contato.getNome() + " salvo com sucesso!!", Toast.LENGTH_LONG).show();

                    } else {
                        //chamando o metodo atualizar do DAO
                        dao.atualizar(contato);
                        Toast.makeText(CadastroContatoActivity.this, contato.getNome() + " atualizado com sucesso!!", Toast.LENGTH_LONG).show();


                    }

                    //fechando o banco
                    dao.close();
                    finish();
                }
                break;

            case R.id.menu_del:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                //Colocar um titulo na caixa de dialogo
                builder.setTitle("EXCLUINDO CONTATO");

                //mensagem exibida na caixa de dialogo
                builder.setMessage("Você deseja excluir o contato " + contato.getNome() + " ?");

                builder.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dao.excluir(contato);

                        Toast.makeText(CadastroContatoActivity.this, contato.getNome()+" excluido com sucesso!!", Toast.LENGTH_LONG).show();

                        dao.close();
                        finish();

                    }
                });
                builder.setNegativeButton("Não", null);

                builder.create().show();

                dao.close();
                break;

            default:
                Toast.makeText(CadastroContatoActivity.this, "Nada", Toast.LENGTH_LONG).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}