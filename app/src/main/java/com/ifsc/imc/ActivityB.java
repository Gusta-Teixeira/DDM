package com.ifsc.imc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.planeta_selecionado);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        Planeta planeta = (Planeta) b.getSerializable("planeta");

        TextView tv = findViewById(R.id.tvNome);
        tv.setText(planeta.nome);

        ImageView img = findViewById(R.id.imgPlanetaSelecionado);
        img.setImageResource(planeta.foto);


    }
}
