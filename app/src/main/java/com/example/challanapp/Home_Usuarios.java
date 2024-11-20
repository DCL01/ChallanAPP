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

public class Home_Usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_usuarios);


        Button CrearProyectoButton= findViewById(R.id.Crear_Page_Button);
        Button VerChallanButton= findViewById(R.id.Ver_Challan_Page_Button);
        Button EditarPerfilButton= findViewById(R.id.Editar_Perfil_Chalan_button);
        Button VerProductosButton= findViewById(R.id.Ver_Productos_Button);



        CrearProyectoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Home_Usuarios.this, Ver_Proyectos.class);
                startActivity(intent);

            }
        });



        VerChallanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Home_Usuarios.this, Lista_Chalan.class);
                startActivity(intent);

            }
        });



        EditarPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Home_Usuarios.this, Info_Usuario.class);
                startActivity(intent);

            }
        });



        VerProductosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Home_Usuarios.this, Catalogo.class);
                startActivity(intent);

            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}