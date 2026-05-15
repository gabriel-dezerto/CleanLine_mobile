package com.example.cleanline;

import android.app.ComponentCaller;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NfcActivity extends AppCompatActivity {

    Button btnCancelar;

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private String nfcEsperado;
    private int idSetor;
    private String nomeSetor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nfc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nfcEsperado = getIntent().getStringExtra("NFC_ESPERADO");
        idSetor = getIntent().getIntExtra("ID_SETOR", -1);
        nomeSetor = getIntent().getStringExtra("NOME_SETOR");

        btnCancelar = findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(v -> {
            finish();
        });

        // Inicializa o adaptador NFC
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null){
            Toast.makeText(this, "NFC não disponível neste dispositivo", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepara a Intent para que o sistema saiba que este app quer processar a tag
        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Ativa o modo de prioridade
        if (nfcAdapter != null){
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Desativa a prioridade ao sair da tela
        if (nfcAdapter != null){
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction()) || NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            byte[] tagIdBytes = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);

            String tagLidaDecimal = convertBytesToDecimal(tagIdBytes);

            if (tagLidaDecimal != null && tagLidaDecimal.equals(nfcEsperado)){
                Toast.makeText(this, "Acesso autorizado ao setor: " + nomeSetor, Toast.LENGTH_SHORT).show();

                Intent mudarTela = new Intent(this, FormsActivity.class);
                mudarTela.putExtra("ID_SETOR", idSetor);
                mudarTela.putExtra("NOME_SETOR", nomeSetor);
                startActivity(mudarTela);
                finish();
            }
            else {
                Toast.makeText(this, "Tag inválida! Este NFC não pertence ao setor " + nomeSetor, Toast.LENGTH_LONG).show();
            }
        }
    }

    private String convertBytesToDecimal(byte[] bytes){
        if (bytes == null) return null;
        long result = 0;
        for (int i = bytes.length - 1; i >= 0; i--){
            result = (result << 8) + (bytes[i] & 0xFF);
        }
        return String.valueOf(result);
    }
}