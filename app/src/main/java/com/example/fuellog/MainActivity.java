package com.example.fuellog;

import android.database.Cursor;
import android.os.Bundle;

import com.example.fuellog.Classes.BancoDeDados.DatabaseManager;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fuellog.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        DatabaseManager dbManager = new DatabaseManager(this);
        dbManager.open();
        //insertUsuario(dbManager);
        //lerUsuario(dbManager);
        dbManager.exportarBancoDeDados(this);
        dbManager.deleteData("Gabriel");
        dbManager.close();

    }

    private void insertUsuario(DatabaseManager dbManager) {
        long newRowId = dbManager.insertUsuario("Gabriel", "Brasileiro", "1999-09-01", "Masculino", "06309873199", "gabriel.vzpaula@gmail.com", "62992489754", 123456);
    }

    private void lerUsuario(DatabaseManager dbManager) {
        // Chame o método getAllData e obtenha um Cursor
        Cursor cursor = dbManager.getAllData();

        // Faça algo com o Cursor, como exibir os dados em um ListView ou RecyclerView
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nome = cursor.getString(cursor.getColumnIndex("nomeUsu"));
                // Faça algo com os dados
            } while (cursor.moveToNext());
        } else {

        }

        // Certifique-se de fechar o Cursor quando não precisar mais dele
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            DatabaseManager dbManager = new DatabaseManager(this);
            dbManager.open();
            insertUsuario(dbManager);
            lerUsuario(dbManager);
            dbManager.close();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}