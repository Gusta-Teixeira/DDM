package com.ifsc.imc;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLinearLayout extends AppCompatActivity {

    private EditText editNome, editEmail;
    private RadioGroup radioGroupFormacao;
    private RadioButton radioTecnico, radioGraduacao, radioPos;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        radioGroupFormacao = findViewById(R.id.radioGroupFormacao);
        radioTecnico = findViewById(R.id.radioTecnico);
        radioGraduacao = findViewById(R.id.radioGraduacao);
        radioPos = findViewById(R.id.radioPos);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString();
                String email = editEmail.getText().toString();

                String formacao = "";
                int selectedId = radioGroupFormacao.getCheckedRadioButtonId();

                if (selectedId == R.id.radioTecnico) {
                    formacao = "Curso Técnico";
                } else if (selectedId == R.id.radioGraduacao) {
                    formacao = "Graduação";
                } else if (selectedId == R.id.radioPos) {
                    formacao = "Pós-Graduação";
                }

                String mensagem = "Dados enviados:\n" +
                        "Nome: " + nome + "\n" +
                        "E-mail: " + email + "\n" +
                        "Formação: " + formacao;

                Toast.makeText(ActivityLinearLayout.this,
                        mensagem,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
