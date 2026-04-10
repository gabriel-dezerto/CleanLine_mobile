package com.example.cleanline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);

                startActivity(intent);
            }
        });
    }
}