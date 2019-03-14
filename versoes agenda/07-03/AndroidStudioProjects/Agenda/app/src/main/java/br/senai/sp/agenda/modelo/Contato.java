package br.senai.sp.agenda.modelo;

import java.io.Serializable;

public class Contato implements Serializable {

    private int id;
    private String nome;
    private String logradouro;
    private String telefone;
    private String email;
    private String enderecoLinkedin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnderecoLinkedin() {
        return enderecoLinkedin;
    }

    public void setEnderecoLinkedin(String enderecoLinkedin) {
        this.enderecoLinkedin = enderecoLinkedin;
    }

    @Override
    public String toString() {
        return Contato.this.id+" - " + Contato.this.nome;
    }
}
