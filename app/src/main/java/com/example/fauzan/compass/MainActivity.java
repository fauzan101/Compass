    package com.example.fauzan.compass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity implements SensorEventListener{
        private SensorManager sensorManager;
        private Sensor compass;
        private ImageView image;
        private TextView compassAngle;
        private float currentDegree = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.imageViewCompass);
        compassAngle = (TextView)findViewById(R.id.angle);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        compass = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if(compass != null){
            sensorManager.registerListener(this, compass, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        compassAngle.setText("Heading: " + Float.toString(degree) + " degrees");
        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // how long the animation will take place
        ra.setDuration(210);
        // set the animation after the end of the reservation status
        ra.setFillAfter(true);
        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
