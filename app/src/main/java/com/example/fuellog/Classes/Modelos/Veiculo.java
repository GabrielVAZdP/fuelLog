package com.example.fuellog.Classes.Modelos;

public class Veiculo {

    private int id;
    private String marca;
    private String modelo;
    private String ano;
    private String tipoComb;
    private int tamanhoTanque;
    private int km;
    private String placa;

    private Veiculo() {

    }

    public Veiculo(String marca, String modelo, String ano, String tipoComb, int tamanhoTanque, int km, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.tipoComb = tipoComb;
        this.tamanhoTanque = tamanhoTanque;
        this.km = km;
        this.placa = placa;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTipoComb() {
        return tipoComb;
    }

    public void setTipoComb(String tipoComb) {
        this.tipoComb = tipoComb;
    }

    public int getTamanhoTanque() {
        return tamanhoTanque;
    }

    public void setTamanhoTanque(int tamanhoTanque) {
        this.tamanhoTanque = tamanhoTanque;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}

