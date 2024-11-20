package com.example.challanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Crear_Proyecto extends AppCompatActivity {








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_proyecto);




        Button HomeButton= findViewById(R.id.backToHomeButton);
        Button CreateProjectButton= findViewById(R.id.createProjectButton);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Crear_Proyecto.this, Home_Usuarios.class);
                startActivity(intent);

            }
        });

        CreateProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Crear_Proyecto.this, Crear_Proyecto.class);
                startActivity(intent);

            }
        });

        // Configuración del Spinner para Tipos de Proyecto
        Spinner projectTypeSpinner = findViewById(R.id.projectTypeSpinner);
        ArrayAdapter<CharSequence> projectTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.habilidades_array, android.R.layout.simple_spinner_item);
        projectTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        projectTypeSpinner.setAdapter(projectTypeAdapter);

// Configuración del Spinner para Chalanes
//        Spinner chalanSpinner = findViewById(R.id.chalanSpinner);
// Aquí deberías tener un array o lista de nombres de chalanes que llenarán este adaptador
//        ArrayAdapter<String> chalanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, chalanNames);
//        chalanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//       chalanSpinner.setAdapter(chalanAdapter);

// Configuración del Spinner para Estados
        Spinner locationSpinner = findViewById(R.id.locationSpinner);
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this,
                R.array.estados_array, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}