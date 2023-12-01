package com.example.fuellog.Classes.Modelos;

import android.content.Context;
import android.database.Cursor;

import com.example.fuellog.Classes.BancoDeDados.DatabaseManager;

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

    DatabaseManager dbManager;

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

    public Consumo(Context context) {
        dbManager = new DatabaseManager(context);

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

    public double calcularConsumo() {
        dbManager.open();

        double consumo = 0;

        int kmAnterior = 0;
        int qtdAbasAnterior = 0;
        int tqCheioAbasAnterior = 0;
        int prctAbasAnterior = 0;
        int kmAtual = 0;
        int qtdAbasAtual = 0;
        int tqCheioAbasAtual = 0;
        int prctAbasAtual = 0;
        int tamTanque = 0;

        tamTanque = 40; //dbManager.getTamTanque();

        Cursor cursor = dbManager.getDataConsumo();

        if (cursor != null && cursor.moveToFirst()) {
            do {

                if (cursor.isFirst()) {
                   kmAtual = cursor.getInt(cursor.getColumnIndex("kmAtualAbas"));
                   qtdAbasAtual = cursor.getInt(cursor.getColumnIndex("qtdAbas"));
                   tqCheioAbasAtual = cursor.getInt(cursor.getColumnIndex("tqCheioAbas"));
                   prctAbasAtual = cursor.getInt(cursor.getColumnIndex("prctAbas"));

                } else if (cursor.getColumnIndex("kmAtualAbas") != -1) {
                   kmAnterior = cursor.getInt(cursor.getColumnIndex("kmAtualAbas"));
                   qtdAbasAnterior = cursor.getInt(cursor.getColumnIndex("qtdAbas"));
                   tqCheioAbasAnterior = cursor.getInt(cursor.getColumnIndex("tqCheioAbas"));
                   prctAbasAnterior = cursor.getInt(cursor.getColumnIndex("prctAbas"));

                }

            } while (cursor.moveToNext());

            cursor.close();
            if (tqCheioAbasAnterior == 1 && tqCheioAbasAtual == 1) {
                return consumo = ((kmAtual - kmAnterior) / (tamTanque - qtdAbasAtual));

            } else if (prctAbasAtual != 0 && prctAbasAnterior != 0) {
                return consumo = ((kmAtual - kmAnterior)/ ((tamTanque*prctAbasAtual/100) - ((tamTanque*prctAbasAnterior/100))));

            } else if (tqCheioAbasAnterior == 1 && prctAbasAtual != 0) {
                return consumo;
            }

        }

        return 0;

    }

}
