package com.example.cleanline;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleanline.model.Supervisor;
import com.example.cleanline.service.ApiService;
import com.example.cleanline.service.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {

    private TextView tvNome, tvEmail, tvTelefone, tvCPF, tvCidade, tvEstado, tvBairro, tvLogradouro, tvNumero;

    public PerfilFragment() {
        super(R.layout.fragment_perfil);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNome = view.findViewById(R.id.tvPerfilNome);
        tvEmail = view.findViewById(R.id.tvPerfilEmail);
        tvTelefone = view.findViewById(R.id.tvPerfilTelefone);
        tvCPF = view.findViewById(R.id.tvPerfilCPF);
        tvCidade = view.findViewById(R.id.tvPerfilCidade);
        tvEstado = view.findViewById(R.id.tvPerfilEstado);
        tvBairro = view.findViewById(R.id.tvPerfilBairro);
        tvLogradouro = view.findViewById(R.id.tvPerfilLogradouro);
        tvNumero = view.findViewById(R.id.tvPerfilNumero);

        SharedPreferences pref = requireActivity().getSharedPreferences("CLEAN_LINE", Context.MODE_PRIVATE);
        int idSuper = pref.getInt("supervisor_id", -1);

        if (idSuper != -1) {
            carregarDadosDoServidor(idSuper);
        } else {
            Toast.makeText(getContext(), "Supervisor não identificado localmente.", Toast.LENGTH_SHORT).show();
        }
    }

    private void carregarDadosDoServidor(int id){
        ApiService api = RetroFitClient.getApiService();
        api.obterPerfil(id).enqueue(new Callback<Supervisor>() {
            @Override
            public void onResponse(Call<Supervisor> call, Response<Supervisor> response) {
                if (response.isSuccessful() && response.body() != null){
                    Supervisor supervisor = response.body();

                    tvNome.setText(supervisor.getNome());
                    tvEmail.setText(supervisor.getEmail());
                    tvTelefone.setText(supervisor.getTel());
                    tvCPF.setText(supervisor.getCPF());
                    tvCidade.setText(supervisor.getCidade());
                    tvEstado.setText(supervisor.getEstado());
                    tvBairro.setText(supervisor.getBairro());
                    tvLogradouro.setText(supervisor.getLogradouro());
                    tvNumero.setText(String.valueOf(supervisor.getN()));
                }
                else {
                    Toast.makeText(getContext(), "Erro ao carregar os dados do perfil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Supervisor> call, Throwable t) {
                Toast.makeText(getContext(), "Falha de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}