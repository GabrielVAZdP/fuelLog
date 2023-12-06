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
import java.util.ArrayList;

public class ConsumoAtual extends Fragment {

    private FragmentConsumoAtualBinding binding;
    Consumo consumo;
    ArrayList<Consumo> arrayList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        consumo = new Consumo(getContext());
        arrayList = consumo.getListConsumo(1);

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

        if (!arrayList.isEmpty()) {
            textoConsumo.setText("Consumo Atual: " + arrayList.get(0).getConsumoFinal() + " Km/L");
            textoDataCalculoResultado.setText(arrayList.get(0).getData());
            textoTipoCalculoResultado.setText(arrayList.get(0).getTipo());
            progressBar.setProgress(arrayList.get(0).getProgressBar());

            if (arrayList.get(0).getTipoCombustivel().equals("G")) {
                textoCombustivel.setText("Gasolina");
            }

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}