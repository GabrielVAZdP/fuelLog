package com.example.fuellog.Classes.BancoDeDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fuellog.Classes.Modelos.Abastecimento;
import com.example.fuellog.Classes.Modelos.Consumo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseAccess {

    DatabaseManager dbManager;
    SQLiteDatabase db;

    public DatabaseAccess(Context context) {
       dbManager = new DatabaseManager(context);
       db = dbManager.getReadableDatabase();

    }

    public double calcularConsumo() {

        Abastecimento atual = new Abastecimento();
        Abastecimento anterior = new Abastecimento();

        int tamTanque = 0;
        int progressBar = 0;
        String[] colunas = {"tamTanque"};
        String consumoFormatado = "";
        String tipo = "APROXIMADO";
        double consumoCalculado = 0;


        Cursor cursor = dbManager.selectFromTable("VEICULO", colunas , null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            tamTanque = cursor.getInt(cursor.getColumnIndex("tamTanque"));

        }

        cursor = dbManager.selectAllFromTable("ABASTECIMENTO", null, null, "data DESC", 2);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                if (cursor.isFirst()) {
                    atual.setIdUsuario(cursor.getInt(cursor.getColumnIndex("idUsuario")));
                    atual.setIdVeiculo(cursor.getInt(cursor.getColumnIndex("idVeiculo")));
                    atual.setKmAtual(cursor.getInt(cursor.getColumnIndex("kmAtual")));
                    atual.setQuantidadeLitros(cursor.getDouble(cursor.getColumnIndex("litros")));
                    atual.setPercentualTanque(cursor.getInt(cursor.getColumnIndex("porcentagem")));
                    atual.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
                    atual.setTipoCombustivel(cursor.getString(cursor.getColumnIndex("tipoCombustivel")));

                    if(cursor.getInt(cursor.getColumnIndex("tanqueCheio")) == 1)
                        atual.setTanqueCheio(true);
                    else
                        atual.setTanqueCheio(false);


                } else if (cursor.getColumnIndex("kmAtual") != -1) {
                    anterior.setIdUsuario(cursor.getInt(cursor.getColumnIndex("idUsuario")));
                    anterior.setIdVeiculo(cursor.getInt(cursor.getColumnIndex("idVeiculo")));
                    anterior.setKmAtual(cursor.getInt(cursor.getColumnIndex("kmAtual")));
                    anterior.setQuantidadeLitros(cursor.getDouble(cursor.getColumnIndex("litros")));
                    anterior.setPercentualTanque(cursor.getInt(cursor.getColumnIndex("porcentagem")));
                    anterior.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
                    anterior.setTipoCombustivel(cursor.getString(cursor.getColumnIndex("tipoCombustivel")));

                    if(cursor.getInt(cursor.getColumnIndex("tanqueCheio")) == 1)
                        anterior.setTanqueCheio(true);
                    else
                        anterior.setTanqueCheio(false);


                }

            } while (cursor.moveToNext());

            cursor.close();


            if (anterior.isTanqueCheio() && atual.isTanqueCheio()) {
                consumoCalculado = ((atual.getKmAtual() - anterior.getKmAtual()) / atual.getQuantidadeLitros());
                tipo = "EXATO";

            } else if (!anterior.isTanqueCheio() && !atual.isTanqueCheio()) {
                consumoCalculado = ((atual.getKmAtual() - anterior.getKmAtual())/ ((tamTanque*atual.getPercentualTanque()/100) - (tamTanque*anterior.getPercentualTanque()/100)));

            } else if (anterior.isTanqueCheio() && !atual.isTanqueCheio()) {
                consumoCalculado = ((atual.getKmAtual() - anterior.getKmAtual())/ (tamTanque - (tamTanque*atual.getPercentualTanque()/100)));

            } else {
                consumoCalculado = ((atual.getKmAtual() - anterior.getKmAtual())/ (atual.getQuantidadeLitros() - (tamTanque*anterior.getPercentualTanque()/100)));

            }

            consumoFormatado = String.format("%.1f", consumoCalculado);
            progressBar = calcularProgressBar(atual.getTipoCombustivel(), consumoCalculado);

            Consumo consumo = new Consumo(atual.getIdUsuario(), atual.getIdVeiculo(), getDataAtual(), atual.getTipoCombustivel(), tipo, Double.parseDouble(consumoFormatado), progressBar);
            salvarConsumo(consumo);

            return consumo.getConsumoFinal();

        }

        return 0;

    }

    public int calcularProgressBar(String tipoCombustivel, double consumo) {

        if (tipoCombustivel.equals("G")) {

            if (consumo < 5) {
                return 20;
            } else if (consumo < 7) {
                return 40;
            } else if (consumo < 9) {
                return 60;
            } else if (consumo < 11) {
                return 80;
            } else {
                return 100;
            }

        } else if (tipoCombustivel.equals("E")) {

            if (consumo < 3) {
                return 20;
            } else if (consumo < 5) {
                return 40;
            } else if (consumo < 7) {
                return 60;
            } else if (consumo < 9) {
                return 80;
            } else {
                return 100;
            }

        } else {
            return 0;
        }

    }

    public Consumo getUltimoConsumo() {

        Consumo consumo = new Consumo();

        Cursor cursor = dbManager.selectAllFromTable("CONSUMO", null, null, "dataCalculo DESC", 1);

        if (cursor != null && cursor.moveToFirst()) {
            consumo.setIdUsuario(cursor.getInt(cursor.getColumnIndex("idUsuario")));
            consumo.setIdVeiculo(cursor.getInt(cursor.getColumnIndex("idVeiculo")));
            consumo.setData(cursor.getString(cursor.getColumnIndex("dataCalculo")));
            consumo.setTipoCombustivel(cursor.getString(cursor.getColumnIndex("tipoCombustivel")));
            consumo.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
            consumo.setConsumoFinal(cursor.getDouble(cursor.getColumnIndex("valorFinal")));
            consumo.setProgressBar(cursor.getInt(cursor.getColumnIndex("progressBar")));

        }

        cursor.close();

        return consumo;

    }

    public void setUpDatabase() {
        dbManager.setUpDatabase(db);
    }

    private String getDataAtual() {
        // Obter a data atual
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        // Definir o formato desejado para a data (ano, mês, dia)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Formatar a data como uma string
        return dateFormat.format(date);

    }

    private void salvarConsumo(Consumo consumo) {
        dbManager.inserirDadosConsumo(consumo.getIdUsuario(), consumo.getIdVeiculo(), consumo.getData(), consumo.getConsumoFinal(), consumo.getTipoCombustivel(), consumo.getTipo(), consumo.getProgressBar());

    }

}
