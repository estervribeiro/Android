package br.senai.sp.agenda.agendacontatos;

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

import br.senai.sp.agenda.R;
import br.senai.sp.agenda.dao.ContatoDAO;
import br.senai.sp.agenda.modelo.Contato;

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

        // ************ Definindo um menu de contexto para a listView
        registerForContextMenu(listaContatos);
        //Criando um menu de contexto na list view que é um objeto, o metodo da litView chama
        //o metodo onCreateCOntextMenu , quando o menu é criado ele dispara um evento para criar
        //o contexto do menu que é o on Create ContextMenu


        //********* listener para o meu contexto
        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato) listaContatos.getItemAtPosition(position);

                //intent para abrir essa activity a partir da main activity, e eu naos ei pq mas eu quero abrir a activity de cadastro
                Intent cadastro = new Intent(MainActivity.this, CadastroContatoActivity.class);

                //passando um contato para a intent
                cadastro.putExtra("contato", contato);
                //serializando o contato para passar ele, ele é um objeto
                //serializar é pegar o objeto e decompor ele em bits em menor particula e quando ele for para
                //eu descerializo ele para ele retomar como um objeto por inteiro

                //abrindo a activity
                startActivity(cadastro);
            }
        });
    }

    // ****************** inflando o meunu de contexto


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //responsavel por passar quem é o menu


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
        inflar.inflate(R.menu.menu_context_lista_contatos);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //usamos o final pq o metodo esta usando um valor que esta em outro metodo
        final ContatoDAO dao = new ContatoDAO(MainActivity.this);

        //usamos o adapter para pegar o que esta no item que foi selecionado e pegar a informação do menuContext
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // pegando o item do contato na posição info
        final Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Colocar um titulo na caixa de dialogo
        builder.setTitle("EXCLUINDO CONTATO");

        //mensagem exibida na caixa de dialogo
        builder.setMessage("Você deseja excluir o contato" + contato.getNome() + "?");

        builder.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dao.excluir(contato);

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
        Toast.makeText(this, "Aqui é o onResume", Toast.LENGTH_LONG).show();

        //chamando o carregar lista de contatos
        carregarLista();

        super.onResume();
    }

    //************ Usando esse metodo para carregar toda a lista do array
    private void carregarLista(){

        //matriz dos contatos que vão ser exibidos no listView
        ContatoDAO dao = new ContatoDAO(this);

        //usando o array que esta no DAO
        List<Contato> contatos = dao.getContatos();

        dao.close();

        //Criando o Adpter que carrega os contatos da lisView

        ArrayAdapter<Contato> ListaContatosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);

        listaContatos.setAdapter(ListaContatosAdapter);
        //a lista de contatos esta recebendo os contatos

    }

    @Override
    protected void onPause() { super.onPause(); }
}
