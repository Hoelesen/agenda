package br.ifro.vilhena.agenda.model;

import java.io.Serializable;

public class Contato implements Serializable {

    public Contato() {

    }

    private int id;
    private String nome;
    private String email;
    private String endetreco;
    private String telefone;
    private String caminhoFoto;

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndetreco() {
        return endetreco;
    }

    public void setEndetreco(String endetreco) {
        this.endetreco = endetreco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
