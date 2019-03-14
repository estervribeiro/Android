package br.senai.sp.agenda.agendacontatos;

import android.widget.EditText;

import br.senai.sp.agenda.R;
import br.senai.sp.agenda.modelo.Contato;

public class CadastroContatoHelper {

    //guarda os atributos dos onjetos do formulario


    private EditText txtNome;
    private EditText txtLogradouro;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtEnderecoLinkedin;
    private Contato contato;

    //*****Criando um helper para ele resgatar o que foi digitado, para isso que serve essa classe

    //******criando um construtor para quando a classe for chamada ele ja cria e pega todos os elementos dos EDITS
    public CadastroContatoHelper(CadastroContatoActivity activity){


        //******no construtor da classe estiu chamando os objetos para que eu tenha acesso

        txtNome = activity.findViewById(R.id.txt_nome);
        txtLogradouro = activity.findViewById(R.id.txt_logradouro);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        txtEnderecoLinkedin = activity.findViewById(R.id.txt_enderecoLinkedin);
        contato = new Contato();
        //*****criando um novo contato no construtor

    }

    //*****Pegando os dados do formulario e para salvar eles
    public Contato getContato() {

            contato.setNome(txtNome.getText().toString());
            contato.setTelefone(txtTelefone.getText().toString());
            contato.setEmail(txtEmail.getText().toString());
            contato.setLogradouro(txtLogradouro.getText().toString());
            contato.setEnderecoLinkedin(txtEnderecoLinkedin.getText().toString());

        return contato;
    }


    //****** Prenchendo formulario
    public void preencherFormulario (Contato contato){
        txtNome.setText(contato.getNome());
        txtLogradouro.setText(contato.getLogradouro());
        txtTelefone.setText(contato.getTelefone());
        txtEmail.setText(contato.getEmail());
        txtEnderecoLinkedin.setText(contato.getEnderecoLinkedin());
        this.contato = contato;
        // os dados que passamos aqui o novo contato recebe

    }
}

