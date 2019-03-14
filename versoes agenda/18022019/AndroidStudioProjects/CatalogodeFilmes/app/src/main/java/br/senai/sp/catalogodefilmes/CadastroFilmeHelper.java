package br.senai.sp.catalogodefilmes;

import android.widget.EditText;
import android.widget.RatingBar;

import br.senai.sp.modelo.Filme;

public class CadastroFilmeHelper {

    private EditText txtTitulo;
    private EditText txtDiretor;
    private EditText txtDataLancamento;
    private EditText txtDuracao;
    private EditText txtGenero;
    private RatingBar nota;

    public CadastroFilmeHelper(CadastroActvity actvity){
        txtTitulo = actvity.findViewById(R.id.txt_titulo);
        txtDiretor = actvity.findViewById(R.id.txt_diretor);
        txtDataLancamento = actvity.findViewById(R.id.txt_data);
        txtDuracao = actvity.findViewById(R.id.txt_duracao);
        txtGenero = actvity.findViewById(R.id.txt_genero);
        nota = actvity.findViewById(R.id.rate_nota);
    }

    public Filme getFilme(){

        Filme filme = new Filme();

        filme.setDataLancamento(txtDataLancamento.getText().toString());
        filme.setDiretor(txtDiretor.getText().toString());
        filme.setGenero(txtGenero.getText().toString());
        filme.setDuracao(txtDuracao.getText().toString());
        filme.setTitulo(txtTitulo.getText().toString());
        filme.setNota((int)nota.getProgress());

        return filme;

    }

}
