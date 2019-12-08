package com.example.currencyconverter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Lab3 extends AppCompatActivity implements SensorEventListener, LocationListener {

    private SensorManager senSensorManger;
    private Sensor senAccelerometer;

    LocationManager locationManager;

    private Button startAndStop;
    private Button compass;

    private TextView xValue;
    private TextView yValue;
    private TextView zValue;

    private TextView orientation;
    private TextView coordinates;



    private boolean InformationObtained;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3);

        InformationObtained = false;

        startAndStop = (Button) findViewById(R.id.start_and_stop);
        startAndStop.setOnClickListener(StartAndStopButtonListener);

        compass = (Button) findViewById(R.id.compass);
        compass.setOnClickListener(CompasspButtonListener);

        xValue = (TextView) findViewById(R.id.x_value);
        yValue = (TextView) findViewById(R.id.y_value);
        zValue = (TextView) findViewById(R.id.z_value);

        coordinates = (TextView) findViewById(R.id.coordinates);
        orientation = (TextView) findViewById(R.id.orientation);

        senSensorManger = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    View.OnClickListener StartAndStopButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (senAccelerometer == null) {
                Toast.makeText(Lab3.this, getString(R.string.no_sensor), Toast.LENGTH_LONG).show();
            }

            if (InformationObtained) {
                startAndStop.setText(getString(R.string.start));
                senSensorManger.unregisterListener(Lab3.this, senAccelerometer);
                InformationObtained = false;
            } else {
                senSensorManger.registerListener(Lab3.this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                startAndStop.setText(getString(R.string.stop));
                InformationObtained = true;
            }
        }
    };

    View.OnClickListener CompasspButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Lab3.this, Compass.class);

            startActivity(intent);
        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            double x = event.values[0];
            double y = event.values[1];
            double z = event.values[2];
            xValue.setText(String.valueOf(x));
            yValue.setText(String.valueOf(y));
            zValue.setText(String.valueOf(z));

            //right down
            if (x < -8) {
                orientation.setText("right down");
            }
            //left down
            else if (x > 8) {
                orientation.setText("left down");
            }
            //top down
            else if (y < -8) {
                orientation.setText("top down");
            }
            //bottom down
            else if (y > 8) {
                orientation.setText("bottom down");
            }
            //screen down
            else if (z < -8) {
                orientation.setText("screen down");
            }
            //screen up
            else if (z > 8) {
                orientation.setText("screen up");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (senAccelerometer != null) {
            senSensorManger.unregisterListener(Lab3.this, senAccelerometer);
        }

        //
        if (ActivityCompat.checkSelfPermission(Lab3.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(Lab3.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        this.locationManager.removeUpdates(Lab3.this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (senAccelerometer != null && InformationObtained) {
            senSensorManger.registerListener(Lab3.this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        //
        if (ActivityCompat.checkSelfPermission(Lab3.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(Lab3.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, Lab3.this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location!=null){
            coordinates.setText(getString(R.string.Latitude_text) + " "
            + location.getLatitude() + " " + getString(R.string.Longitude_text) + " " + location.getLongitude());

            System.out.println(location.getLatitude());
            System.out.println(location.getLongitude());
        }

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
