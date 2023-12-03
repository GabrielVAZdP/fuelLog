package com.example.fuellog.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fuellog.Classes.Modelos.Consumo;
import com.example.fuellog.R;
import com.example.fuellog.databinding.FragmentConsumoAtualBinding;

public class ConsumoAtual extends Fragment {

    private FragmentConsumoAtualBinding binding;
    double consumoAtual;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        Consumo consumo = new Consumo(getContext());
        consumoAtual = consumo.calcularConsumo();

        binding = FragmentConsumoAtualBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textoConsumo = view.findViewById(R.id.textConsumo);
        textoConsumo.setText("Consumo Atual: " + consumoAtual + " Km/L");


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}