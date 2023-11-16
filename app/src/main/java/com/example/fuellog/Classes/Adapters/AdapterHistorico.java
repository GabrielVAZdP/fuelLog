package com.example.fuellog.Classes.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fuellog.R;

public class AdapterHistorico extends RecyclerView.Adapter<AdapterHistorico.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar o layout do item da lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Vincular dados aos elementos do layout do item da lista
        // Os dados podem ser obtidos a partir de uma lista de registros
    }

    @Override
    public int getItemCount() {
        // Retornar o número total de itens na lista
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Declare elementos do layout do item da lista aqui

        public ViewHolder(View itemView) {
            super(itemView);
            // Inicialize os elementos do layout do item da lista aqui
        }
    }
}