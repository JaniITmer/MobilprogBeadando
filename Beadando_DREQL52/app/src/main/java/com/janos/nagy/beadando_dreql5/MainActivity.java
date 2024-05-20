package com.janos.nagy.beadando_dreql5;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SajatAdapter adapter;
    private EditText editText;
    private Button buttonShowText;
    private Button buttonSensor;
    private Button buttonSwitch;
    private NavController navController;
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new SajatAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Új kód: RecyclerView kezdetben láthatatlan
        recyclerView.setVisibility(View.GONE);

        editText = findViewById(R.id.editText);
        buttonShowText = findViewById(R.id.buttonShowText);
        buttonSwitch = findViewById(R.id.buttonSwitch);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

        buttonShowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        buttonSensor = findViewById(R.id.buttonSensor);
        buttonSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.sensorFragment);
            }
        });

        buttonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (navController.getCurrentDestination().getId() == R.id.fragment1) {
                    navController.navigate(R.id.action_fragment1_to_fragment2);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    navController.navigate(R.id.action_fragment2_to_fragment1);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });

        sharedViewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                adapter.updateData(items);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}