package com.example.fuellog.Classes.Modelos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fuellog.Classes.BancoDeDados.DatabaseAccess;
import com.example.fuellog.Classes.BancoDeDados.DatabaseManager;

import java.util.ArrayList;

public class Consumo {

    private int id;
    private int idUsuario;
    private int idVeiculo;
    private String data;
    private String tipoCombustivel;
    private String tipo;
    private double consumoFinal;
    private int progressBar;

    DatabaseAccess databaseAccess;

    public Consumo(int idUsuario, int idVeiculo, String data, String tipoCombustivel, String tipo, double consumoFinal, int progressBar) {
        this.idUsuario = idUsuario;
        this.idVeiculo = idVeiculo;
        this.data = data;
        this.tipoCombustivel = tipoCombustivel;
        this.tipo = tipo;
        this.consumoFinal = consumoFinal;
        this.progressBar = progressBar;
    }

    public Consumo(Context context) {
        databaseAccess = new DatabaseAccess(context);

    }

    public Consumo() {

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

    public String getTipoCombustivel() {
        return this.tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getConsumoFinal() {
        return consumoFinal;
    }

    public void setConsumoFinal(double consumoFinal) {
        this.consumoFinal = consumoFinal;
    }

    public int getProgressBar() {
        return this.progressBar;
    }

    public void setProgressBar(int progressBar) {
        this.progressBar = progressBar;
    }

    public double calcularConsumo() {
        return databaseAccess.calcularConsumo();
    }

    public ArrayList<Consumo> getListConsumo(Integer limit) {
        return databaseAccess.getListConsumo(limit);
    }

}
