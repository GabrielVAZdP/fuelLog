package com.example.fuellog.Classes.Modelos;

import android.content.Context;

import com.example.fuellog.Classes.BancoDeDados.DatabaseAccess;

public class Previsao {

    String dataCalculo;
    int dataPrevista;
    int kmPrevisto;
    int mediaDia;
    int mediaKm;

    DatabaseAccess databaseAccess;

    public Previsao(Context context) {
        databaseAccess = new DatabaseAccess(context);
    }

    public Previsao() {

    }

    public String getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(String dataCalculo) {
        this.dataCalculo = dataCalculo;
    }

    public int getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(int dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public int getKmPrevisto() {
        return kmPrevisto;
    }

    public void setKmPrevisto(int kmPrevisto) {
        this.kmPrevisto = kmPrevisto;
    }

    public int getMediaDia() {
        return mediaDia;
    }

    public void setMediaDia(int mediaDia) {
        this.mediaDia = mediaDia;
    }

    public int getMediaKm() {
        return mediaKm;
    }

    public void setMediaKm(int mediaKm) {
        this.mediaKm = mediaKm;
    }

    public Previsao getPrevisao() {
        return databaseAccess.getPrevisaoAbastecimento();
    }

}
