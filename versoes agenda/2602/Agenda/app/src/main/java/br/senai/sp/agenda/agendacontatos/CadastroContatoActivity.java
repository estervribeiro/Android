package br.senai.sp.agenda.agendacontatos;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.senai.sp.agenda.R;
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

        if(contato != null){
            helper.preencherFormulario(contato);
        }
    }

    //****** CRIANDO O MENU



}
