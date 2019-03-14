package br.senai.sp.catlogodefilmes;

import android.widget.EditText;
import android.widget.RatingBar;

import br.senai.sp.modelo.Filme;

public class CadastroFilmeHelper {
    //tem os atributos dos objetos do formula´rio


    private EditText txtTitulo;
    private EditText txtDiretor;
    private EditText txtDataLancamento;
    private EditText txtDuracao;
    private EditText txtGenero;
    private RatingBar nota;
    private Filme filme;


    //Criando um helper, pois quando precisar pegar e colocar o que digitou e usa essa classe

    //Esse construtor é utilizado para quando a classe for chamada ele já cria e pega todos os elementos das Edits
    public CadastroFilmeHelper(CadastroActivity activity){
        //no construtor da classe eu chamos todos os objetos e tenho acesso


        txtTitulo = activity.findViewById(R.id.txt_titulo);
        txtDiretor = activity.findViewById(R.id.txt_diretor);
        txtDataLancamento = activity.findViewById(R.id.txt_data_lancamento);
        txtDuracao = activity.findViewById(R.id.txt_duracao);
        txtGenero = activity.findViewById(R.id.txt_genero);
        nota = activity.findViewById(R.id.rate_nota);
        filme = new Filme();
        //aqui no construtor eu crio o novo filme
    }


    //PEGAR TODAS AS INFORMAÇÕES DO FORMULÁRIO PARA SALVAR
    public Filme getFilme(){

        filme.setTitulo(txtTitulo.getText().toString());
        filme.setDiretor(txtDiretor.getText().toString());
        filme.setDataLancamento(txtDataLancamento.getText().toString());
        filme.setDuracao(txtDuracao.getText().toString());
        filme.setGenero(txtGenero.getText().toString());
        filme.setNota(nota.getProgress());


        return filme;

    }

    //PREENCHER FORMULÁRIO
    public void preencherFormulario(Filme filme) {

        txtTitulo.setText(filme.getTitulo());
        txtDataLancamento.setText(filme.getDataLancamento());
        txtDiretor.setText(filme.getDiretor());
        txtGenero.setText(filme.getGenero());
        txtDuracao.setText(filme.getDuracao());
        nota.setProgress(filme.getNota());
        this.filme = filme;
        //eu to dizendo que é pra pegar o filme e fazer receber o filme que passamos
    }
}
