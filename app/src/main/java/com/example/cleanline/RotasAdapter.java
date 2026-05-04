package com.example.cleanline;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanline.model.Rotas;

import java.util.List;

public class RotasAdapter extends RecyclerView.Adapter<RotasAdapter.RotasViewHolder> {

    private List<Rotas> listaRotas;

    public RotasAdapter(List<Rotas> listaRotas){
        this.listaRotas = listaRotas;
    }

    @NonNull
    @Override
    public RotasAdapter.RotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rotas, parent, false);

        return new RotasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RotasAdapter.RotasViewHolder holder, int position) {
        Rotas rotas = listaRotas.get(position);

        holder.txtSetor.setText(rotas.getSetor());

    }

    @Override
    public int getItemCount() {
        return listaRotas.size();
    }

    public class RotasViewHolder extends RecyclerView.ViewHolder{
        TextView txtSetor;

        public RotasViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSetor = itemView.findViewById(R.id.txtSetor);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();

                if(position != RecyclerView.NO_POSITION){
                    Rotas rota = listaRotas.get(position);

                    Intent intent = new Intent(v.getContext(), NfcActivity.class);

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
