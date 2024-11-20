package com.example.challanapp;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Catalogo extends AppCompatActivity {
    private static final String URL = "jdbc:postgresql://articulosconstruccion.cxom6eo64yqc.us-east-2.rds.amazonaws.com:5432/articulosConstruccion?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private static final String USER = "daniel";
    private static final String PASSWORD = "12345678";



    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }
        return connection;
    }

    public static ArrayList<String> obtenerAtriculos() {
        String selectSQL = "SELECT producto, precio, lugardeorigen, unidaddemedida FROM articulo";
        ArrayList<String> articulosList = new ArrayList<>();

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String producto = resultSet.getString("producto");
                int precio = resultSet.getInt("precio"); // Usa getInt para el tipo integer
                String lugar = resultSet.getString("lugardeorigen");
                String unidad = resultSet.getString("unidaddemedida");

                articulosList.add("Producto: " + producto + "\n" +
                        "Precio: " + precio + "\n" +
                        "Lugar de origen: " + lugar + "\n" +
                        "Unidad de Medida: " + unidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            articulosList = null;
        }

        return articulosList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_catalogo);

        ListView listView = findViewById(R.id.listaIngredientes);
        Button HomeButton= findViewById(R.id.homeButton);

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Catalogo.this, Home_Usuarios.class);
                startActivity(intent);

            }
        });
        // Hilo para recuperar los ingredientes desde la base de datos
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> articulosList = obtenerAtriculos();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (articulosList != null && !articulosList.isEmpty()) {
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(Catalogo.this,
                                    android.R.layout.simple_list_item_1, articulosList);
                            listView.setAdapter(adapter);
                        } else if (articulosList == null) {
                            Toast.makeText(Catalogo.this, "Error al obtener los artículos", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Catalogo.this, "No hay artículos disponibles", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}