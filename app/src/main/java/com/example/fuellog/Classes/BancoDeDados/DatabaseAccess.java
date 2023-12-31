package com.example.fuellog.Classes.BancoDeDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fuellog.Classes.Modelos.Abastecimento;
import com.example.fuellog.Classes.Modelos.Consumo;
import com.example.fuellog.Classes.Modelos.Previsao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

        int progressBar = 0;
        String consumoFormatado = "";
        String tipo = "APROXIMADO";
        double consumoCalculado = 0;

        Cursor cursor = dbManager.selectAllFromTable("ABASTECIMENTO", null, null, "id DESC", 2);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                if (cursor.isFirst()) {
                    atual.setIdUsuario(cursor.getInt(cursor.getColumnIndex("idUsuario")));
                    atual.setIdVeiculo(cursor.getInt(cursor.getColumnIndex("idVeiculo")));
                    atual.setKmAtual(cursor.getInt(cursor.getColumnIndex("kmAtual")));
                    atual.setQuantidadeLitros(cursor.getInt(cursor.getColumnIndex("litros")));
                    atual.setPercentualTanque(cursor.getInt(cursor.getColumnIndex("porcentagem")));
                    atual.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
                    atual.setTipoCombustivel(cursor.getString(cursor.getColumnIndex("tipoCombustivel")));
                    atual.setTanqueCheio(cursor.getInt(cursor.getColumnIndex("tanqueCheio")));


                } else if (cursor.getColumnIndex("kmAtual") != -1) {
                    anterior.setIdUsuario(cursor.getInt(cursor.getColumnIndex("idUsuario")));
                    anterior.setIdVeiculo(cursor.getInt(cursor.getColumnIndex("idVeiculo")));
                    anterior.setKmAtual(cursor.getInt(cursor.getColumnIndex("kmAtual")));
                    anterior.setQuantidadeLitros(cursor.getInt(cursor.getColumnIndex("litros")));
                    anterior.setPercentualTanque(cursor.getInt(cursor.getColumnIndex("porcentagem")));
                    anterior.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
                    anterior.setTipoCombustivel(cursor.getString(cursor.getColumnIndex("tipoCombustivel")));
                    anterior.setTanqueCheio(cursor.getInt(cursor.getColumnIndex("tanqueCheio")));

                }

            } while (cursor.moveToNext());

            cursor.close();


            if (anterior.getTanqueCheio() == 1 && atual.getTanqueCheio() == 1) {
                consumoCalculado = ((atual.getKmAtual() - anterior.getKmAtual()) / atual.getQuantidadeLitros());
                tipo = "EXATO";

            } else if (anterior.getTanqueCheio() != 1 && atual.getTanqueCheio() != 1) {
                consumoCalculado = ((atual.getKmAtual() - anterior.getKmAtual())/ ((tamTanque()*anterior.getPercentualTanque()/100) - ((tamTanque()*atual.getPercentualTanque()/100) - (atual.getQuantidadeLitros()))));

            } else if (anterior.getTanqueCheio() == 1 && atual.getTanqueCheio() != 1) {
                consumoCalculado = ((atual.getKmAtual() - anterior.getKmAtual())/ (tamTanque() - ((tamTanque()*atual.getPercentualTanque()/100) - (atual.getQuantidadeLitros()))));

            } else {
                consumoCalculado = ((atual.getKmAtual() - anterior.getKmAtual())/ ((tamTanque()*anterior.getPercentualTanque()/100) - (tamTanque() - atual.getQuantidadeLitros())));

            }

            if (consumoCalculado < 0) {
                consumoCalculado = consumoCalculado * -1;
            } else if (consumoCalculado == 0) {
                consumoCalculado = 1;
            }

            consumoFormatado = String.format(Locale.US, "%.1f", consumoCalculado);
            progressBar = calcularProgressBar(atual.getTipoCombustivel(), consumoCalculado);

            Consumo consumo = new Consumo(atual.getIdUsuario(), atual.getIdVeiculo(), getDataAtualString(), atual.getTipoCombustivel(), tipo, Double.parseDouble(consumoFormatado), progressBar);
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

    public ArrayList<Consumo> getListConsumo(Integer limit) {

        ArrayList<Consumo> arrayList= new ArrayList<Consumo>();

        Cursor cursor = dbManager.selectAllFromTable("CONSUMO", null, null, "id DESC", limit);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Consumo consumo = new Consumo();
                consumo.setIdUsuario(cursor.getInt(cursor.getColumnIndex("idUsuario")));
                consumo.setIdVeiculo(cursor.getInt(cursor.getColumnIndex("idVeiculo")));
                consumo.setData(formatarData(cursor.getString(cursor.getColumnIndex("dataCalculo"))));
                consumo.setTipoCombustivel(cursor.getString(cursor.getColumnIndex("tipoCombustivel")));
                consumo.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                consumo.setConsumoFinal(cursor.getDouble(cursor.getColumnIndex("valorFinal")));
                consumo.setProgressBar(cursor.getInt(cursor.getColumnIndex("progressBar")));

                arrayList.add(consumo);

            }
            while (cursor.moveToNext()) ;
        }

        cursor.close();

        return arrayList;

    }

    public Previsao getPrevisaoAbastecimento() {

        Previsao previsao = new Previsao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate dataInicial;

        String[] colunas = {"data", "kmAtual"};
        ArrayList<Integer> mediaKm = new ArrayList();
        ArrayList<String> mediaDia = new ArrayList();


            Cursor cursor = dbManager.selectFromTable("ABASTECIMENTO", colunas , null, null, "data ASC", null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    mediaKm.add(cursor.getInt(cursor.getColumnIndex("kmAtual")));
                    mediaDia.add(cursor.getString(cursor.getColumnIndex("data")));

                } while (cursor.moveToNext());
            }

            cursor.close();


            previsao.setMediaKm((mediaKm.get(mediaKm.size()-1) - mediaKm.get(0))/(mediaKm.size()-1));
            previsao.setMediaDia(LocalDate.parse(mediaDia.get(0), formatter).until(LocalDate.parse(mediaDia.get(mediaDia.size()-1), formatter)).getDays());
            previsao.setDataCalculo(getDataAtual());
            previsao.setDataPrevista(LocalDate.parse(mediaDia.get(mediaDia.size()-1), formatter).until(LocalDate.parse(getDataAtualPrevisao(), formatter)).getDays());
            previsao.setKmPrevisto(mediaKm.get(mediaKm.size()-1) + previsao.getMediaKm());

            if (previsao.getDataPrevista() < 0)
                previsao.setDataPrevista(0);

            return previsao;

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

    private String getDataAtualString() {
        // Obter a data atual
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        // Definir o formato desejado para a data (ano, mês, dia)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        // Formatar a data como uma string
        return dateFormat.format(date);

    }

    private String formatarData(String data) {
        // Formato original da string
        DateTimeFormatter formatoOriginal = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Formato desejado
        DateTimeFormatter formatoDesejado = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Parse da string para um objeto LocalDate
        LocalDate dataF = LocalDate.parse(data, formatoOriginal);

        // Formatar a data no novo formato
        String dataFormatada = dataF.format(formatoDesejado);

        return dataFormatada;
    }

    private String getDataAtualPrevisao() {
        // Obter a data atual
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        // Definir o formato desejado para a data (ano, mês, dia)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        // Formatar a data como uma string
        return dateFormat.format(date);

    }

    private void salvarConsumo(Consumo consumo) {
        dbManager.inserirDadosConsumo(consumo.getIdUsuario(), consumo.getIdVeiculo(), consumo.getData(),
                consumo.getConsumoFinal(), consumo.getTipoCombustivel(), consumo.getTipo(), consumo.getProgressBar());

    }

    public void registrarAbastecimento(Abastecimento abastecimento) {
        dbManager.inserirDadosAbastecimento(abastecimento.getIdUsuario(), abastecimento.getIdVeiculo(), getDataAtualPrevisao(), abastecimento.getKmAtual(),
                abastecimento.getValor(), abastecimento.getQuantidadeLitros(), abastecimento.getTanqueCheio(), abastecimento.getTipoCombustivel(), abastecimento.getPercentualTanque());
    }

    public int kmAnteriorAbastecimento() {
        Cursor cursor = dbManager.selectAllFromTable("ABASTECIMENTO", null, null, "data DESC", 1);

        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("kmAtual"));
        }

        return Integer.MAX_VALUE;

    }

    public int tamTanque() {

        String[] colunas = {"tamTanque"};
        Cursor cursor = dbManager.selectFromTable("VEICULO", colunas , null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("tamTanque"));

        }
        else {
            return 0;
        }

    }

}
