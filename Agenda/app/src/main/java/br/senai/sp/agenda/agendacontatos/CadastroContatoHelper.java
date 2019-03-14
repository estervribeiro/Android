package br.senai.sp.agenda.agendacontatos;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.senai.sp.agenda.R;
import br.senai.sp.agenda.modelo.Contato;

public class CadastroContatoHelper {

    //guarda os atributos dos onjetos do formulario



    // instanciando as varievies que vão ser utilizadas localmente
    private EditText txtNome;
    private EditText txtLogradouro;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtEnderecoLinkedin;
    private Contato contato;
    private TextInputLayout validarNome;
    private TextInputLayout validarTel;
    private TextInputLayout validarLogradouro;
    private TextInputLayout validarLinkedin;
    private TextInputLayout validarEmail;


    //*****Criando um helper para ele resgatar o que foi digitado, para isso que serve essa classe

    //******criando um construtor para quando a classe for chamada ele ja cria e pega todos os elementos dos EDITS
    public CadastroContatoHelper(CadastroContatoActivity activity){

        //*** colocando as variaveis da validao para validar
        validarNome = activity.findViewById(R.id.validar_nome);
        validarLogradouro = activity.findViewById(R.id.validar_endereco);
        validarTel = activity.findViewById(R.id.validar_telefone);
        validarLinkedin = activity.findViewById(R.id.validar_linkedin);
        validarEmail = activity.findViewById(R.id.validar_email);


        //******no construtor da classe estiu chamando os objetos para que eu tenha acesso
        txtNome = activity.findViewById(R.id.txt_nome);
        txtLogradouro = activity.findViewById(R.id.txt_logradouro);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        txtEnderecoLinkedin = activity.findViewById(R.id.txt_enderecoLinkedin);
        contato = new Contato();
        //*****criando um novo contato no construtor

    }

    ///****** FUNÇÂO para validar os campos
    public boolean validar (Context activity){

        boolean validado = true;

        if(txtNome.getText().toString().isEmpty()){
            validarNome.setErrorEnabled(true);
            validarNome.setError("Nome Sobrenome");
            validado = false;
        }else{
            validarEmail.setErrorEnabled(false);
        }if(txtEmail.getText().toString().isEmpty()){
            validarEmail.setErrorEnabled(true);
            validarEmail.setError("contato@contato.com");
            validado = false;
        }else{
            validarLogradouro.setErrorEnabled(false);
        }if(txtLogradouro.getText().toString().isEmpty()){
            validarLogradouro.setErrorEnabled(true);
            validarLogradouro.setError("Rua x, 0 - Jd.x - cidade - XX");
            validado = false;
        }else{
            validarTel.setErrorEnabled(false);
        }if(txtTelefone.getText().toString().isEmpty()){
            validarTel.setErrorEnabled(true);
            validarTel.setError("(DD)XXXX-XXXX");
            validado = false;
        }else{
            validarLinkedin.setErrorEnabled(false);
        }if(txtEnderecoLinkedin.getText().toString().isEmpty()){
            validarLinkedin.setErrorEnabled(true);
            validarLinkedin.setError("linkedin@linkedin.com");
            validado = false;
        }else{
            validarNome.setErrorEnabled(false);
        }

        return validado;

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

