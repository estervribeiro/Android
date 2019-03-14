package br.senai.sp.modelo;

import android.widget.EditText;

import java.io.Serializable;

//implementa a classe Serializable, o objeto tem que ser transformado em binário
public class Filme implements Serializable{

    //**precisa ter um id pq o o banco vai criar automatico
    private int id;
    private String titulo;
    private String diretor;
    private String dataLancamento;
    private String duracao;
    private String genero;
    private int nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    //metodo para retornar o nome do filme
    @Override
    public String toString() {
        return this. id + " - " + this.titulo;
    }
    //esse metodo é da classe mãe e ela retorna a referencia do objeto
    //e quando eu sobre escrevi o metodo eu coloquei parar retornar o nome do titulo
}
