package com.example.fuellog.Classes.BancoDeDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccess {

    DatabaseManager dbManager;
    SQLiteDatabase db;

    public DatabaseAccess(Context context) {
       dbManager = new DatabaseManager(context);
       db = dbManager.getReadableDatabase();

    }

    public double calcularConsumo() {

        double consumo = 0;

        String[] colunas = {"tamTanque"};
        String consumoFormatado = "";

        double kmAnterior = 0;
        double qtdAbasAnterior = 0;
        int tqCheioAbasAnterior = 0;
        int prctAbasAnterior = 0;
        double kmAtual = 0;
        double qtdAbasAtual = 0;
        int tqCheioAbasAtual = 0;
        int prctAbasAtual = 0;
        int tamTanque = 0;

        int countRowsInTable = dbManager.countRowsInTable("VEICULO");
        Cursor cursor = dbManager.selectFromTable("VEICULO", colunas , null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            tamTanque = cursor.getInt(cursor.getColumnIndex("tamTanque"));

        }

        cursor = dbManager.selectAllFromTable("ABASTECIMENTO", null, null, "data DESC", 2);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                if (cursor.isFirst()) {
                    kmAtual = cursor.getDouble(cursor.getColumnIndex("kmAtual"));
                    qtdAbasAtual = cursor.getDouble(cursor.getColumnIndex("litros"));
                    tqCheioAbasAtual = cursor.getInt(cursor.getColumnIndex("tanqueCheio"));
                    prctAbasAtual = cursor.getInt(cursor.getColumnIndex("porcentagem"));

                } else if (cursor.getColumnIndex("kmAtual") != -1) {
                    kmAnterior = cursor.getDouble(cursor.getColumnIndex("kmAtual"));
                    qtdAbasAnterior = cursor.getDouble(cursor.getColumnIndex("litros"));
                    tqCheioAbasAnterior = cursor.getInt(cursor.getColumnIndex("tanqueCheio"));
                    prctAbasAnterior = cursor.getInt(cursor.getColumnIndex("porcentagem"));

                }

            } while (cursor.moveToNext());

            cursor.close();


            if (tqCheioAbasAnterior == 1 && tqCheioAbasAtual == 1) {
                consumo = ((kmAtual - kmAnterior) / qtdAbasAtual);


            } else if (prctAbasAtual != 0 && prctAbasAnterior != 0) {
                consumo = ((kmAtual - kmAnterior)/ ((tamTanque*prctAbasAtual/100) - ((tamTanque*prctAbasAnterior/100))));

            } else if (tqCheioAbasAnterior == 1 && prctAbasAtual != 0) {
                consumo = ((kmAtual - kmAnterior)/ tamTanque - (tamTanque*prctAbasAtual/100));

            } else {
                consumo = ((kmAtual - kmAnterior)/ qtdAbasAtual - (tamTanque*prctAbasAnterior/100));

            }

            consumoFormatado = String.format("%.1f", consumo);
            return Double.parseDouble(consumoFormatado);

        }

        return 0;

    }

}
