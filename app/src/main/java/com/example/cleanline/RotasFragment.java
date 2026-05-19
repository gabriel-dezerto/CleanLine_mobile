package com.example.cleanline;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cleanline.model.Rotas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        recyclerRotas.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        atualizarRotas();
    }

    private void atualizarRotas(){
        SharedPreferences pref = requireActivity().getSharedPreferences("CLEAN_LINE", Context.MODE_PRIVATE);
        Set<String> concluidos = pref.getStringSet("setores_concluidos", new HashSet<>());

        List<Rotas> baseRotas = new ArrayList<>();

        baseRotas.add(new Rotas("Venda", "1666738673", 1));
        baseRotas.add(new Rotas("TI", "1675784673", 2));
        baseRotas.add(new Rotas("RH", "1395870976", 3));
        baseRotas.add(new Rotas("Financeiro", "1661122769", 4));
        baseRotas.add(new Rotas("Administrativo", "1662030545", 5));

        listaRotas = new ArrayList<>();
        for (Rotas rota : baseRotas){
            if (!concluidos.contains(String.valueOf(rota.getIdSetor()))){
                listaRotas.add(rota);
            }
        }

        if (listaRotas.isEmpty()){
            pref.edit().remove("setores_concluidos").apply();

            Toast.makeText(getContext(), "Todas as vistorias concluídas! Ciclo reiniciado.", Toast.LENGTH_LONG).show();

            atualizarRotas();
        } else {
            rotasAdapter = new RotasAdapter(listaRotas);
            recyclerRotas.setAdapter(rotasAdapter);
        }
    }
}