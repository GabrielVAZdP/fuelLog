package com.example.fuellog.Classes.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    // Nome do banco de dados
    private static final String DATABASE_NAME = "SeuBancoDeDados.db";

    // Versão do banco de dados
    private static final int DATABASE_VERSION = 1;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executar comandos SQL para criar tabelas
        db.execSQL(SQL_CREATE_TABLE_USUARIOS);
        db.execSQL(SQL_CREATE_TABLE_VEICULO);
        db.execSQL(SQL_CREATE_TABLE_ABASTECIMENTO);
        db.execSQL(SQL_CREATE_TABLE_CONSUMO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualizar esquema do banco de dados, se necessário
    }

    // Comandos SQL para criar tabelas
    private static final String SQL_CREATE_TABLE_USUARIOS =
            "CREATE TABLE IF NOT EXISTS USUARIOS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nomeUsu TEXT, naciUso TEXT, dtNascUsu DATE, " +
                    "sexoUsu TEXT, cpfUsu INTEGER, emailUsu TEXT," +
                    "telUsu INTEGER, senhaUsu INTEGER)";

    private static final String SQL_CREATE_TABLE_VEICULO =
            "CREATE TABLE IF NOT EXISTS VEICULO (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "marcVeic TEXT, modeloVeic TEXT, anoVeic TEXT, " +
                    "tipoCombVeic TEXT, tamTanqVeic INTEGER, kmVeic INTEGER," +
                    "placaVeic TEXT)";

    private static final String SQL_CREATE_TABLE_ABASTECIMENTO =
            "CREATE TABLE IF NOT EXISTS ABASTECIMENTO (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idUsuario INTEGER, idVeiculo INTEGER, dataAbas DATE, " +
                    "kmAtualAbas INTEGER, valorAbas REAL, qtdAbas INTEGER," +
                    "tqCheioAbas INTEGER, tipoCombAbas TEXT, prctAbas INTEGER)";

    private static final String SQL_CREATE_TABLE_CONSUMO =
            "CREATE TABLE IF NOT EXISTS CONSUMO (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idUsuario INTEGER, idVeiculo INTEGER, dataCalculoCons DATE, " +
                    "valorFinalCons REAL, tipoCons INTEGER, qtdAbasUlt REAL," +
                    "qtdAbasPenult REAL, kmAbasUlt INTEGER, kmAbasPenult INTEGER," +
                    "prcAtual INTEGER)";

}
