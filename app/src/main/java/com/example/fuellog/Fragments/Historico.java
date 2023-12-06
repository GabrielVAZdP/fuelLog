package com.example.fuellog.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuellog.Classes.Adapters.AdapterHistorico;
import com.example.fuellog.Classes.Modelos.Consumo;
import com.example.fuellog.R;
import com.example.fuellog.databinding.FragmentAbastecimentoBinding;

import java.util.ArrayList;

public class Historico extends Fragment {

    private RecyclerView recyclerView;
    private AdapterHistorico adapter;
    private Consumo consumo;
    private ArrayList<Consumo> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historico, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Inicializar o adapter passando o contexto
        adapter = new AdapterHistorico(getContext());
        recyclerView.setAdapter(adapter);

        consumo = new Consumo(getContext());
        arrayList = consumo.getListConsumo(null);

        // Atualizar os dados do adapter com a lista de consumo
        adapter.setData(arrayList);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}