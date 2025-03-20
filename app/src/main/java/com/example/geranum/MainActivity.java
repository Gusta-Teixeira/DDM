package com.example.geranum;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import java.util.random.RandomGenerator;

public class MainActivity extends AppCompatActivity {
    EditText edMin, edMax;
    Button btGera;
    TextView tvResult;
    int min=0, max=0, resultado=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edMin    = findViewById(R.id.edMin);
        edMax    = findViewById(R.id.edMax);
        btGera   = findViewById(R.id.btGera);
        tvResult = findViewById(R.id.txResult);

        Random random = new Random();

        btGera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min = Integer.parseInt(edMin.getText().toString());
                max = Integer.parseInt(edMax.getText().toString());

                resultado = random.nextInt((max - min) + 1) + min;

                tvResult.setText(Integer.toString(resultado));
            }
        });
    }
}