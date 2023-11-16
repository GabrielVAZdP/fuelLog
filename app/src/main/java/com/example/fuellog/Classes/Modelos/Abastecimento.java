package com.example.fuellog.Classes.Modelos;

public class Abastecimento {

    private int id;
    private int idUsuario;
    private int idVeiculo;
    private String data;
    private int kmAtual;
    private double valor;
    private double quantidadeLitros;
    private boolean tanqueCheio;
    private String tipoCombustivel;
    private int percentualTanque;

    public Abastecimento() {
    }

    public Abastecimento(int idUsuario, int idVeiculo, String data, int kmAtual, double valor, double quantidadeLitros, boolean tanqueCheio, String tipoCombustivel) {
        this.idUsuario = idUsuario;
        this.idVeiculo = idVeiculo;
        this.data = data;
        this.kmAtual = kmAtual;
        this.valor = valor;
        this.quantidadeLitros = quantidadeLitros;
        this.tanqueCheio = tanqueCheio;
        this.tipoCombustivel = tipoCombustivel;
    }

    public Abastecimento(int idUsuario, int idVeiculo, String data, int kmAtual, double valor, boolean tanqueCheio, String tipoCombustivel, int percentualTanque) {
        this.idUsuario = idUsuario;
        this.idVeiculo = idVeiculo;
        this.data = data;
        this.kmAtual = kmAtual;
        this.valor = valor;
        this.tanqueCheio = tanqueCheio;
        this.tipoCombustivel = tipoCombustivel;
        this.percentualTanque = percentualTanque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(int kmAtual) {
        this.kmAtual = kmAtual;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getQuantidadeLitros() {
        return quantidadeLitros;
    }

    public void setQuantidadeLitros(double quantidadeLitros) {
        this.quantidadeLitros = quantidadeLitros;
    }

    public boolean isTanqueCheio() {
        return tanqueCheio;
    }

    public void setTanqueCheio(boolean tanqueCheio) {
        this.tanqueCheio = tanqueCheio;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public int getPercentualTanque() {
        return percentualTanque;
    }

    public void setPercentualTanque(int percentualTanque) {
        this.percentualTanque = percentualTanque;
    }
}
