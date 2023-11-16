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
import com.example.fuellog.R;
import com.example.fuellog.databinding.FragmentAbastecimentoBinding;

public class Historico extends Fragment {

    private RecyclerView recyclerView;
    private AdapterHistorico adapter;
    private FragmentAbastecimentoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historico, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new AdapterHistorico();
        recyclerView.setAdapter(adapter);

        // Adicione os dados à lista (por exemplo, uma lista de registros)
        // adapter.setData(dataList);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}