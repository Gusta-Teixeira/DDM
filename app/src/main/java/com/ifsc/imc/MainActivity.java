package com.ifsc.imc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btCalcula;
    EditText edPeso, edAltura;
    String wTexto;
    float wIMC=0, wAltura=0;
    ImageView imgPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btCalcula    = findViewById(R.id.button);
        edPeso       = findViewById(R.id.edPeso);
        edAltura     = findViewById(R.id.edAltura);
        imgPrincipal = findViewById(R.id.imgGeral);

        imgPrincipal.setImageResource(R.drawable.perfil);

        btCalcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wAltura = (Float.valueOf(edAltura.getText().toString())) / 100;
                wIMC = Float.valueOf(edPeso.getText().toString()) / (wAltura * wAltura);

                Intent i = new Intent(getApplicationContext(), MsgActivity.class);
                i.putExtra("IMC", Float.toString(wIMC));
                i.putExtra("Peso", edPeso.getText().toString());
                i.putExtra("Altura", Float.toString(wAltura));
                startActivity(i);
            }
        });
    }
}