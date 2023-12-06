package com.example.fuellog.Classes.BancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FuelLog.db";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USUARIOS);
        db.execSQL(SQL_CREATE_TABLE_VEICULO);
        db.execSQL(SQL_CREATE_TABLE_ABASTECIMENTO);
        db.execSQL(SQL_CREATE_TABLE_CONSUMO);
        setUpDatabase(db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor selectFromTable(String tableName, String[] columns, String selection, String[] selectionArgs, String orderBy, String limit) {

        SQLiteDatabase db = this.getReadableDatabase();

        // Consulta com selectionArgs
        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, null, null, orderBy, limit);


        return cursor;
    }

    public Cursor selectAllFromTable(String tableName, String selection, String[] selectionArgs, String orderBy, Integer limit) {

        SQLiteDatabase db = getReadableDatabase();

        // Consulta com LIMIT
        String limitClause = (limit == null) ? null: String.valueOf(limit);

        // Consulta sem especificar as colunas
        Cursor cursor = db.query(tableName, null, selection, selectionArgs, null, null, orderBy, limitClause);


        return cursor;
    }

    public int countRowsInTable(String tableName) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tableName, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();

        return count;
    }

    public void delete(String tableName) {
        SQLiteDatabase db = getReadableDatabase();

        db.delete(tableName, null, null);
    }



    public void inserirDadosUsuario(String nome, String nacionalidade, String dtNascimento,
                                    String sexo, String cpf, String email, String telefone, int senha) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("nacionalidade", nacionalidade);
        values.put("dtNascimento", dtNascimento);
        values.put("sexo", sexo);
        values.put("cpf", cpf);
        values.put("email", email);
        values.put("telefone", telefone);
        values.put("senha", senha);

        // Insira os dados na tabela
        db.insert("USUARIOS", null, values);


    }

    public void inserirDadosVeiculo(String marca, String modelo, String ano, String tipoCombustivel,
                                    int tamTanque, int km, String placa) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("marca", marca);
        values.put("modelo", modelo);
        values.put("ano", ano);
        values.put("tipoCombustivel", tipoCombustivel);
        values.put("tamTanque", tamTanque);
        values.put("km", km);
        values.put("placa", placa);

        // Insira os dados na tabela
        db.insert("VEICULO", null, values);


    }

    public void inserirDadosAbastecimento(int idUsuario, int idVeiculo, String data, int kmAtual, double valor,
                                          int litros, int tanqueCheio, String tipoCombustivel, int porcentagem) {


        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("idUsuario", idUsuario);
        values.put("idVeiculo", idVeiculo);
        values.put("data", data);
        values.put("kmAtual", kmAtual);
        values.put("valor", valor);
        values.put("litros", litros);
        values.put("tanqueCheio", tanqueCheio);
        values.put("tipoCombustivel", tipoCombustivel);
        values.put("porcentagem", porcentagem);

        // Insira os dados na tabela
        db.insert("ABASTECIMENTO", null, values);


    }

    public void inserirDadosConsumo(int idUsuario, int idVeiculo,  String dataCalculo, double valorFinal,
                                    String tipoCombustivel, String tipo, int progressBar) {


        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("idUsuario", idUsuario);
        values.put("idVeiculo", idVeiculo);
        values.put("dataCalculo", dataCalculo);
        values.put("valorFinal", valorFinal);
        values.put("tipoCombustivel", tipoCombustivel);
        values.put("tipo", tipo);
        values.put("progressBar", progressBar);

        // Insira os dados na tabela
        db.insert("CONSUMO", null, values);


    }

    public void setUpDatabase(SQLiteDatabase db) {

//        excluirTabela("USUARIOS");
//        excluirTabela("VEICULO");
//        excluirTabela("ABASTECIMENTO");
//        excluirTabela("CONSUMO");

        db.execSQL(SQL_CREATE_TABLE_USUARIOS);
        db.execSQL(SQL_CREATE_TABLE_VEICULO);
        db.execSQL(SQL_CREATE_TABLE_ABASTECIMENTO);
        db.execSQL(SQL_CREATE_TABLE_CONSUMO);

        if (countRowsInTable("USUARIOS") == 0) {
            inserirDadosUsuario("Gabriel", "Brasileiro", "1999901", "Masculino",
                    "06309873199", "gabriel.vzpaula@gmail.com", "62992489754", 123456);

        }

        if (countRowsInTable("VEICULO") == 0) {
            inserirDadosVeiculo("Mitsubishi", "Lancer", "2015/2015", "G",
                    55, 100000, "LRU9B16");

        }

        if (countRowsInTable("ABASTECIMENTO") == 0) {
            inserirDadosAbastecimento(1, 1, "20231015", 100000, 250.6, 40, 1, "G", 0);
            inserirDadosAbastecimento(1, 1, "20231101", 100100, 110.8, 18, 1, "G", 0);

        }

    }

    public void excluirTabela(String tabela) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tabela);
        db.close();
    }

    private static final String SQL_CREATE_TABLE_USUARIOS =
            "CREATE TABLE IF NOT EXISTS USUARIOS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT, nacionalidade TEXT, dtNascimento TEXT, " +
                    "sexo TEXT, cpf INTEGER, email TEXT," +
                    "telefone INTEGER, senha INTEGER)";

    private static final String SQL_CREATE_TABLE_VEICULO =
            "CREATE TABLE IF NOT EXISTS VEICULO (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "marca TEXT, modelo TEXT, ano TEXT, " +
                    "tipoCombustivel TEXT, tamTanque INTEGER, km INTEGER," +
                    "placa TEXT)";

    private static final String SQL_CREATE_TABLE_ABASTECIMENTO =
            "CREATE TABLE IF NOT EXISTS ABASTECIMENTO (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idUsuario INTEGER, idVeiculo INTEGER, data TEXT, " +
                    "kmAtual INTEGER, valor REAL, litros INTEGER," +
                    "tanqueCheio INTEGER, tipoCombustivel TEXT, porcentagem INTEGER)";

    private static final String SQL_CREATE_TABLE_CONSUMO =
            "CREATE TABLE IF NOT EXISTS CONSUMO (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idUsuario INTEGER, idVeiculo INTEGER, dataCalculo TEXT, " +
                    "valorFinal REAL, tipoCombustivel TEXT, tipo TEXT, progressBar INTEGER)";

}
