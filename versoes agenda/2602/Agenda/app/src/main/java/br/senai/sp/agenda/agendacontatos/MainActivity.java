package br.senai.sp.agenda.agendacontatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import br.senai.sp.agenda.R;

public class MainActivity extends AppCompatActivity {

    private ListView listaContatos;
    private Button btNovoContato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associando a listView do java a listView do xml
        listaContatos = findViewById(R.id.list_contatos);
        btNovoContato = findViewById(R.id.bt_novo_contato);

        Toast.makeText(this, "Aqui é o onCreate", Toast.LENGTH_LONG).show();


        //Quando clicado no botao novo contato uma intent (abrirCadastro) sera chamada com a activity que eu quero que abra, dps eu starto a açao na activity
        btNovoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Tostadinhas", Toast.LENGTH_LONG).show();

                //colocar o botao na classe em que esta a activity que eu quero que abra quando eu aciona-lo
                Intent abrirCadastro = new Intent(MainActivity.this, CadastroContatoActivity.class);

                //comando para abrir a activity desejada
                startActivity(abrirCadastro);

            }
        });







    }
}
