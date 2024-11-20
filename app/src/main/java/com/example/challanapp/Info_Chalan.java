package com.example.challanapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Info_Chalan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // Configuración del Spinner de Habilidades
        Spinner habilidadesSpinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<CharSequence> habilidadesAdapter = ArrayAdapter.createFromResource(this,
                R.array.habilidades_array, android.R.layout.simple_spinner_item);
        habilidadesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        habilidadesSpinner.setAdapter(habilidadesAdapter);

// Configuración del Spinner de Ubicación
        Spinner ubicacionSpinner = findViewById(R.id.skillsSpinner);
        ArrayAdapter<CharSequence> ubicacionAdapter = ArrayAdapter.createFromResource(this,
                R.array.estados_array, android.R.layout.simple_spinner_item);
        ubicacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ubicacionSpinner.setAdapter(ubicacionAdapter);




        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_chalan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}