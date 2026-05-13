package com.example.cleanline;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormsActivity extends AppCompatActivity {
    private String currentPhotoPath;
    private Button btnFoto, btnEnviar;
    private TextView tvStatusFoto;
    private EditText etNota;

    // Array para facilitar busca dos RadioGroups
    private RadioGroup[] rgs = new RadioGroup[8];

    private final ActivityResultLauncher<Uri> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.TakePicture(),
            result ->{
                if (result){
                    tvStatusFoto.setText("Foto capturada com sucesso!");
                    tvStatusFoto.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                }
            }
    );

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

        btnFoto = findViewById(R.id.btnTirarFoto);
        btnEnviar = findViewById(R.id.btnEnviarForm);
        tvStatusFoto = findViewById(R.id.tvStatusFoto);
        etNota = findViewById(R.id.etNota);

        for (int i = 0; i < 8; i++){
            String idName = "rgPergunta" + (i + 1);
            int resID = getResources().getIdentifier(idName, "id", getPackageName());
            rgs[i] = findViewById(resID);
        }

        btnFoto.setOnClickListener(v -> tirarFoto());
        btnEnviar.setOnClickListener(v -> enviarVistoria());
    }

    private void tirarFoto(){
        try {
            File photoFile = createImageFile();
            Uri photoUri = FileProvider.getUriForFile(this, "com.example.cleanline.fileprovider", photoFile);
            cameraLauncher.launch(photoUri);
        } catch (IOException e) {
            Toast.makeText(this, "Erro ao criar arquivo de imagem", Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile("VISTORIA_" + timeStamp, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


}