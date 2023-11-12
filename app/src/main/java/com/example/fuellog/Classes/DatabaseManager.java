package com.example.fuellog.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    private DataBase dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DataBase(context);
    }

    // Abrir o banco de dados para escrita
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Fechar o banco de dados
    public void close() {
        dbHelper.close();
    }

    // Inserir dados na tabela
    public long insertData(String nomeTabela, String data) {
        ContentValues values = new ContentValues();
        values.put(nomeTabela, data);
        return database.insert(nomeTabela, null, values);
    }

    // Consultar todos os dados da tabela
    public Cursor getAllData() {
        String[] columns = {"id", "nome"};
        return database.query("TabelaExemplo", columns, null, null, null, null, null);
    }

    // Atualizar dados na tabela
    public int updateData(int id, String novoNome) {
        ContentValues values = new ContentValues();
        values.put("nome", novoNome);
        return database.update("TabelaExemplo", values, "id=?", new String[]{String.valueOf(id)});
    }

    // Deletar dados da tabela
    public int deleteData(int id) {
        return database.delete("TabelaExemplo", "id=?", new String[]{String.valueOf(id)});
    }
}
