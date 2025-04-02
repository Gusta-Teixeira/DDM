package com.ifsc.imc;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MsgActivity extends AppCompatActivity {
    EditText edPeso, edAltura;
    TextView tvIMC;
    ImageView imgResult;
    Float imc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_msg);
        edPeso = findViewById(R.id.edPeso);
        edAltura = findViewById(R.id.edAltura);
        tvIMC = findViewById(R.id.tvIMC);
        imgResult = findViewById(R.id.imgResult);

        Bundle bundle = getIntent().getExtras();
        imc = Float.valueOf(bundle.getString("IMC"));

        if (imc < 18.5){
            imgResult.setImageResource(R.drawable.abaixopeso);
        } else if (imc >= 18.6 && imc <= 24.9) {
            imgResult.setImageResource(R.drawable.normal);
        } else if (imc >= 25 && imc <= 29.9) {
            imgResult.setImageResource(R.drawable.sobrepeso);
        } else if (imc >= 30 && imc <= 34.9) {
            imgResult.setImageResource(R.drawable.obesidade1);
        } else if (imc >= 35 && imc <= 39.9) {
            imgResult.setImageResource(R.drawable.obesidade2);
        } else
            imgResult.setImageResource(R.drawable.obesidade3);

        edPeso.setText(bundle.getString("Peso") + "Kg");
        edAltura.setText(bundle.getString("Altura") + "M");
        tvIMC.setText(bundle.getString("IMC"));
    }
}