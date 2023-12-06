package com.example.fuellog.Classes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuellog.Classes.Modelos.Consumo;
import com.example.fuellog.R;

import java.util.ArrayList;

public class AdapterHistorico extends RecyclerView.Adapter<AdapterHistorico.ViewHolder> {

    private Context context;
    private ArrayList<Consumo> arrayList;

    public AdapterHistorico(Context context) {
        this.context = context;
        this.arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar o layout do item da lista
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obter o objeto Consumo na posição específica do ArrayList
        Consumo consumoAtual = arrayList.get(position);

        // Definir os valores nos TextViews
        holder.dataCalculo.setText(consumoAtual.getData());
        holder.mediaCalculo.setText(String.valueOf(consumoAtual.getConsumoFinal()) + " Km/L");
        holder.tipoCalculoHist.setText(consumoAtual.getTipo());
    }

    @Override
    public int getItemCount() {
        // Retornar o número total de itens na lista
        return arrayList.size();
    }

    public void setData(ArrayList<Consumo> data) {
        // Atualizar o conjunto de dados e notificar o RecyclerView
        arrayList.clear();
        arrayList.addAll(data);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dataCalculo;
        TextView mediaCalculo;
        TextView tipoCalculoHist;

        public ViewHolder(View itemView) {
            super(itemView);
            dataCalculo = itemView.findViewById(R.id.dataCalculoHist);
            mediaCalculo = itemView.findViewById(R.id.mediaCalculadaHist);
            tipoCalculoHist = itemView.findViewById(R.id.tipoCalculoHist);
        }
    }
}
