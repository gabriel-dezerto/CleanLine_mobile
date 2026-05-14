package com.example.cleanline;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.cleanline.service.ApiService;
import com.example.cleanline.service.RetroFitClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private void enviarVistoria(){
        // Validação da foto
        if (currentPhotoPath == null){
            Toast.makeText(this, "Tire a foto antes de enviar!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validação da nota
        String notaStr = etNota.getText().toString();
        if (notaStr.isEmpty()) {
            Toast.makeText(this, "Informe a nota do setor", Toast.LENGTH_SHORT).show();
            return;
        }

        // Pegar ID do Supervisor logado
        SharedPreferences pref = getSharedPreferences("CLEAN_LINE", Context.MODE_PRIVATE);
        int idSuper = pref.getInt("supervisor_id", -1);

        // Preparar dados multipart
        RequestBody rbIdSuper = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(idSuper));
        RequestBody rbIdSetor = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody rbPontuacao = RequestBody.create(MediaType.parse("text/plain"), notaStr);

        // Capturar texto dos RadioButtons selecionados
        RequestBody[] qBodies = new RequestBody[8];
        for (int i = 0; i < 8; i++){
            int selectedId = rgs[i].getCheckedRadioButtonId();
            if (selectedId == -1){
                Toast.makeText(this, "Responda a pergunta " + (i + 1), Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton rb = findViewById(selectedId);
            qBodies[i] = RequestBody.create(MediaType.parse("text/plain"), rb.getText().toString());
        }

        // Preparar a imagem
        File file = new File(currentPhotoPath);
        RequestBody reqImage = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part bodyImagem = MultipartBody.Part.createFormData("Image", file.getName(), reqImage);

        // Chamada API
        ApiService api = RetroFitClient.getApiService();
        api.enviarVistoria(rbIdSuper, rbIdSetor, rbPontuacao, bodyImagem,
                qBodies[0], qBodies[1], qBodies[2], qBodies[3],
                qBodies[4], qBodies[5], qBodies[6], qBodies[7]
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(FormsActivity.this, "Vistoria enviada com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(FormsActivity.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FormsActivity.this, "Falha na rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}