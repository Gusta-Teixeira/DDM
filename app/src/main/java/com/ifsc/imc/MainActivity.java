package com.ifsc.imc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvResult, tvAcele, tvGiros;
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResultado);
        tvAcele = findViewById(R.id.tvAcele);
        tvGiros = findViewById(R.id.tvGiros);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Sensor sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor sensorGiroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorManager.registerListener(this, sensorLuz, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorGiroscopio, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            tvResult.setText("Luminosidade: " + Float.toString(sensorEvent.values[0]));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            tvAcele.setText("Aceleromêtro: " + Float.toString(sensorEvent.values[0]));
        }else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            tvGiros.setText("Giroscópio: " + Float.toString(sensorEvent.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}