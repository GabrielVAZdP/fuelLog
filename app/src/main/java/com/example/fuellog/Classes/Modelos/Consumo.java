package com.example.fuellog.Classes.Modelos;

public class Consumo {

    private int id;
    private int idUsuario;
    private int idVeiculo;
    private String data;
    private double valor;
    private String tipoCombustivel;
    private double quantidadeLitrosUlt;
    private double quantidadeLitrosPenult;
    private int kmAtualUlt;
    private int kmAtualPenult;
    private int percentualAtual;

    public Consumo(int idUsuario, int idVeiculo, String data, double valor, String tipoCombustivel, double quantidadeLitrosUlt, double quantidadeLitrosPenult, int kmAtualUlt, int kmAtualPenult, int percentualAtual) {
        this.idUsuario = idUsuario;
        this.idVeiculo = idVeiculo;
        this.data = data;
        this.valor = valor;
        this.tipoCombustivel = tipoCombustivel;
        this.quantidadeLitrosUlt = quantidadeLitrosUlt;
        this.quantidadeLitrosPenult = quantidadeLitrosPenult;
        this.kmAtualUlt = kmAtualUlt;
        this.kmAtualPenult = kmAtualPenult;
        this.percentualAtual = percentualAtual;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public double getQuantidadeLitrosUlt() {
        return quantidadeLitrosUlt;
    }

    public void setQuantidadeLitrosUlt(double quantidadeLitrosUlt) {
        this.quantidadeLitrosUlt = quantidadeLitrosUlt;
    }

    public double getQuantidadeLitrosPenult() {
        return quantidadeLitrosPenult;
    }

    public void setQuantidadeLitrosPenult(double quantidadeLitrosPenult) {
        this.quantidadeLitrosPenult = quantidadeLitrosPenult;
    }

    public int getKmAtualUlt() {
        return kmAtualUlt;
    }

    public void setKmAtualUlt(int kmAtualUlt) {
        this.kmAtualUlt = kmAtualUlt;
    }

    public int getKmAtualPenult() {
        return kmAtualPenult;
    }

    public void setKmAtualPenult(int kmAtualPenult) {
        this.kmAtualPenult = kmAtualPenult;
    }

    public int getPercentualAtual() {
        return percentualAtual;
    }

    public void setPercentualAtual(int percentualAtual) {
        this.percentualAtual = percentualAtual;
    }
}
