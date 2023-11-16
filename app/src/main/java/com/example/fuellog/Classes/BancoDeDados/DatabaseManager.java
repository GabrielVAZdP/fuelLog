package com.example.fuellog.Classes.BancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

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

    //

    public long insertUsuario(String nomeUsu,String naciUso, String dtNascUsu,
                              String sexoUsu, String cpfUsu, String emailUsu,
                              String telUsu, int senhaUsu) {

        ContentValues values = new ContentValues();
        values.put("nomeUsu", nomeUsu);      // String
        values.put("naciUso", naciUso);      // String
        values.put("dtNascUsu", dtNascUsu);  // String (representando uma data)
        values.put("sexoUsu", sexoUsu);      // String
        values.put("cpfUsu", Long.parseLong(cpfUsu));        // Long
        values.put("emailUsu", emailUsu);    // String
        values.put("telUsu", Long.parseLong(telUsu));        // Long
        values.put("senhaUsu", senhaUsu);    // Integer

        return database.insert("Usuarios", null, values);
    }

    // Consultar todos os dados da tabela
    public Cursor getAllData() {
        String[] columns = {"id", "nomeUsu"};
        return database.query("USUARIOS", columns, null, null, null, null, null);
    }

    // Atualizar dados na tabela
    public int updateData(int id, String novoNome) {
        ContentValues values = new ContentValues();
        values.put("nome", novoNome);
        return database.update("TabelaExemplo", values, "id=?", new String[]{String.valueOf(id)});
    }

    // Deletar dados da tabela
    public int deleteData(String id) {
        return database.delete("USUARIOS", "nomeUsu=?", new String[]{String.valueOf(id)});
    }

    public void exportarBancoDeDados(Context context) {
        try {
            File currentDB = context.getDatabasePath("SeuBancoDeDados.db");
            File backupDB = new File(Environment.getExternalStorageDirectory(), "BackupBancoDeDados");

            if (currentDB.exists()) {
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                // Notifique o usuário sobre o local do backup
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
