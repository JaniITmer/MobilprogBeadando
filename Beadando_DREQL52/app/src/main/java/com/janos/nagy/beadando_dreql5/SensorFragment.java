package com.janos.nagy.beadando_dreql5;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.List;

public class SensorFragment extends Fragment {

    private TextView sensorInfoTextView;
    private SensorManager sensorManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);
        sensorInfoTextView = view.findViewById(R.id.sensorInfoTextView);
        sensorManager = (SensorManager) requireActivity().getSystemService(requireActivity().SENSOR_SERVICE);
        displaySensorInfo();
        return view;
    }

    private void displaySensorInfo() {
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorInfo = new StringBuilder();
        for (Sensor sensor : sensorList) {
            sensorInfo.append("Name: ").append(sensor.getName()).append("\n");
            sensorInfo.append("Vendor: ").append(sensor.getVendor()).append("\n");
            sensorInfo.append("Version: ").append(sensor.getVersion()).append("\n");
            sensorInfo.append("Type: ").append(sensor.getType()).append("\n\n");
        }
        sensorInfoTextView.setText(sensorInfo.toString());
    }
}