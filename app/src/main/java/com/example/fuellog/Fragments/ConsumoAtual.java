package com.example.fuellog.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fuellog.Classes.Modelos.Consumo;
import com.example.fuellog.R;
import com.example.fuellog.databinding.FragmentConsumoAtualBinding;

import java.sql.Date;

public class ConsumoAtual extends Fragment {

    private FragmentConsumoAtualBinding binding;
    Consumo consumo;
    double consumoFinal;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        consumo = new Consumo(getContext());
        consumo = consumo.getUltimoConsumo();

        binding = FragmentConsumoAtualBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textoConsumo = view.findViewById(R.id.textConsumo);
        TextView textoCombustivel = view.findViewById(R.id.tipoCombustivelResutado);
        TextView textoDataCalculoResultado = view.findViewById(R.id.dataCalculoResultado);
        TextView textoTipoCalculoResultado = view.findViewById(R.id.tipoCalculoResultado);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        textoConsumo.setText("Consumo Atual: " + consumo.getConsumoFinal() + " Km/L");
        textoDataCalculoResultado.setText(consumo.getData());
        textoTipoCalculoResultado.setText(consumo.getTipo());
        progressBar.setProgress(consumo.getProgressBar());

        if (consumo.getTipoCombustivel().equals("G")) {
            textoCombustivel.setText("Gasolina");
        }



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}