package com.example.cleanline;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cleanline.model.Rotas;

import java.util.ArrayList;
import java.util.List;

public class RotasFragment extends Fragment {

    private RecyclerView recyclerRotas;
    private RotasAdapter rotasAdapter;
    private List<Rotas> listaRotas;

    public RotasFragment() {
        super(R.layout.fragment_rotas);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerRotas = view.findViewById(R.id.recyclerRotas);

        listaRotas = new ArrayList<>();

        listaRotas.add(new Rotas("Venda", "1666738673", 1));
        listaRotas.add(new Rotas("TI", "1675784673", 2));
        listaRotas.add(new Rotas("RH", "1395870976", 3));
        listaRotas.add(new Rotas("Financeiro", "1661122769", 4));
        listaRotas.add(new Rotas("Administrativo", "1662030545", 5));

        rotasAdapter = new RotasAdapter(listaRotas);

        recyclerRotas.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerRotas.setAdapter(rotasAdapter);

    }
}