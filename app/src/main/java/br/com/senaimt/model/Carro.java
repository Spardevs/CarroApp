package br.com.senaimt.model;

public class Carro {

    private int id;
    private String marca;
    private int ano;
    private String modelo;

    public Carro() {
    }

    public Carro(int id, String marca, int ano, String modelo) {
        this.id = id;
        this.marca = marca;
        this.ano = ano;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return  marca + " - " + modelo + " - " + ano;
    }
}
