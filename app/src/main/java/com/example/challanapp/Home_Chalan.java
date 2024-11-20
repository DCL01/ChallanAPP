package com.example.challanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home_Chalan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        Button CrearProyectoButton= findViewById(R.id.buttonProjects);
        Button EditarPerfilButton= findViewById(R.id.buttonSettings);
        Button VerProductosButton= findViewById(R.id.buttonCatalog);

        CrearProyectoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Home_Chalan.this, Home_Chalan.class);
                startActivity(intent);

            }
        });

        EditarPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Home_Chalan.this, Info_Chalan.class);
                startActivity(intent);

            }
        });

        VerProductosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Home_Chalan.this, Catalogo.class);
                startActivity(intent);

            }
        });


        setContentView(R.layout.activity_home_chalan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}