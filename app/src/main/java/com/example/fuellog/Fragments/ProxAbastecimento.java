package com.example.fuellog.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fuellog.Classes.BancoDeDados.DatabaseAccess;
import com.example.fuellog.Classes.Modelos.Previsao;
import com.example.fuellog.R;
import com.example.fuellog.databinding.FragmentProxAbastecimentoBinding;


public class ProxAbastecimento extends Fragment {

    private FragmentProxAbastecimentoBinding binding;
    Previsao previsao;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentProxAbastecimentoBinding.inflate(inflater, container, false);
        previsao = new Previsao(getContext());
        previsao = previsao.getPrevisao();

        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView previsaoKm = view.findViewById(R.id.previsaoKm);
        TextView previsaoDia = view.findViewById(R.id.previsaoDia);
        TextView mediaDia = view.findViewById(R.id.mediaDia);
        TextView mediaKm = view.findViewById(R.id.mediaKm);

        previsaoKm.setText(String.valueOf(previsao.getKmPrevisto()) + " km");
        previsaoDia.setText(String.valueOf(previsao.getDataPrevista()) + " dia(s)");
        mediaDia.setText(String.valueOf(previsao.getMediaDia()) + " dia(s)");
        mediaKm.setText(String.valueOf(previsao.getMediaKm()) + " km");



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}