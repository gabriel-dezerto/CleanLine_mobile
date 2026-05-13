package com.example.cleanline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cleanline.model.Supervisor;
import com.example.cleanline.service.ApiService;
import com.example.cleanline.service.LoginRequest;
import com.example.cleanline.service.RetroFitClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    TextInputEditText etEmail, etSenha;
    TextInputLayout tilEmail, tilSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);

        tilEmail = findViewById(R.id.tilEmail);
        tilSenha = findViewById(R.id.tilSenha);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();

                tilEmail.setError(null);
                tilSenha.setError(null);

                if(email.isEmpty()){
                    tilEmail.setError("Informe o email");
                    return;
                }

                if(!email.contains("@")){
                    tilEmail.setError("Email inválido");
                    return;
                }

                if(senha.isEmpty()){
                    tilSenha.setError("Informe a senha");
                    return;
                }

                efetuarLogin(email, senha);
            }
        });
    }

    private void efetuarLogin(String email, String senha){
        LoginRequest request = new LoginRequest(email, senha);
        ApiService apiService = RetroFitClient.getApiService();

        apiService.login(request).enqueue(new Callback<Supervisor>() {
            @Override
            public void onResponse(Call<Supervisor> call, Response<Supervisor> response) {
                if (response.isSuccessful() && response.body() != null){
                    int idSupervisor = response.body().getId();

                    SharedPreferences pref = getSharedPreferences("CLEAN_LINE", Context.MODE_PRIVATE);
                    pref.edit().putInt("supervisor_id", idSupervisor).apply();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Supervisor> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}