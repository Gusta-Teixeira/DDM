package com.ifsc.imc;

import android.Manifest;
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
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvLongitude, tvLatitude;
    Button btGerar;
    LocationManager lm;
    MapView mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvLongitude = findViewById(R.id.tvLongitude);
        tvLatitude = findViewById(R.id.tvLatitude);
        btGerar = findViewById(R.id.button);
        mapa = findViewById(R.id.map);
        Configuration.getInstance().setUserAgentValue(getPackageName());

        mapa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                track = false;
                return false;
            }
        });

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        btGerar.setOnClickListener(view -> getlocalizacao());
    }

    public void getlocalizacao(){
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)  ||
           (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) ||
           (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {

            Location location;
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

//            if (location != null){
//                tvLongitude.setText("Longitude: " + Double.toString(location.getLongitude()));
//                tvLatitude.setText("Latitude: " +Double.toString(location.getLatitude()));
//            }else {
//                tvLatitude.setText("Não foi possível encontrar a localização");
//            }

        }else {
            ActivityCompat.requestPermissions(this,  new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return;
        }
    }
    boolean track = true;
    public void showLocalizacao(double latitude,double longitude){
        GeoPoint userLocation = new GeoPoint(latitude, longitude);
        mapa.getController().setZoom(18);

        if (track){
            mapa.getController().setCenter(userLocation);
        }

        Marker marcador = new Marker(mapa);
        marcador.setPosition(userLocation);
        marcador.setTitle("Você está aqui");

        mapa.getOverlays().clear();
        mapa.getOverlays().add(marcador);
        mapa.invalidate();
    }

    public final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            tvLongitude.setText("Longitude: " + Double.toString(location.getLongitude()));
            tvLatitude.setText("Latitude: " +Double.toString(location.getLatitude()));
            showLocalizacao(location.getLatitude(), location.getLongitude());
        }
    };
}