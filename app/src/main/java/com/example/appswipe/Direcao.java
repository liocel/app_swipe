package com.example.appswipe;

public class Direcao {

    private int id;
    private String direcao;

    public Direcao(){

    }

    public Direcao(String direcao) {
        this.direcao = direcao;
    }

    public Direcao(int id, String direcao) {
        this.id = id;
        this.direcao = direcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    @Override
    public String toString() {
        return direcao;
    }
}
