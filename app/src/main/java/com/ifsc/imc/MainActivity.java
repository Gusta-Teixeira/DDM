package com.ifsc.imc;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button btInsere;
    EditText edTexto;
    ListView lvLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btInsere = findViewById(R.id.button);
        edTexto = findViewById(R.id.edTexto);
        lvLista = findViewById(R.id.lvLista);

        db = openOrCreateDatabase("notas", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas(bdID INTEGER PRIMARY KEY AUTOINCREMENT, bdTEXTO TEXT)");

        //db.execSQL("DELETE FROM notas");

        btInsere.setOnClickListener(view -> {
            insereNota(edTexto.getText().toString().trim());
        });
        carregaLv();

    }

    public String insereNota(String txt){
        ContentValues cv = new ContentValues();
        cv.put("bdTEXTO", txt);
        db.insert("notas", null, cv);
        carregaLv();
        return "Inserido";
    }

    public void carregaLv(){
            Cursor cursor = db.rawQuery("SELECT * FROM notas", null);
            cursor.moveToFirst();
        ArrayList<String> notas = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            int column = cursor.getColumnIndex("bdTEXTO");
            notas.add(cursor.getString(column));
            cursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                notas);
        lvLista.setAdapter(adapter);
    }
}