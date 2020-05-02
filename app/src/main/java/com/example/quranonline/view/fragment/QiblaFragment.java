package com.example.quranonline.view.fragment;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.quranonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class QiblaFragment extends BaseFragment implements SensorEventListener {
    @BindView(R.id.imv_compass)
    ImageView imvCompass;
    private float currentDegree = 0f;
    private SensorManager sensorManager;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_qibla, container, false);
        ButterKnife.bind(this , root);
        sensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);


        // Inflate the layout for this fragment
        return root;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        RotateAnimation ra = new RotateAnimation(-currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(120);
        ra.setFillAfter(true);
        imvCompass.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
