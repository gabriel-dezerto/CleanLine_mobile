package com.example.cleanline;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormsActivity extends AppCompatActivity {

    RadioButton rbP1Exc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rbP1Exc = findViewById(R.id.rbP1Exc);

        RadioGroup radioGroup = findViewById(R.id.rgPergunta1);

        if (radioGroup.getCheckedRadioButtonId() != -1) {
            // Um botão foi selecionado
            RadioButton radioButtonSelecionado = findViewById(radioGroup.getCheckedRadioButtonId());
            String textoSelecionado = radioButtonSelecionado.getText().toString();
        } else {
            // Nenhum botão foi selecionado
        }

    }
}