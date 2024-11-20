package com.example.challanapp;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Crear_Cuenta extends AppCompatActivity {
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
    public static boolean crearUsuario(String nombreUsuario, String contraseña) {
        boolean exito = false;
        String query = "INSERT INTO usuarios (usuario, contrasena, direccion) VALUES (?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, contraseña);
            preparedStatement.setString(3, "usuario"); // Establecer tipo_usuario como "usuario"

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                exito = true; // Usuario creado exitosamente
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
        }
        return exito;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_cuenta);EditText usuarioEditText = findViewById(R.id.usernameEditText);
        EditText contraseñaEditText = findViewById(R.id.passwordEditText);
        Button crearButton = findViewById(R.id.CreateButton);
        Button regresarButton = findViewById(R.id.loginButton);

        Spinner accountTypeSpinner = findViewById(R.id.accountTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.account_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        crearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = usuarioEditText.getText().toString().trim();
                String contraseña = contraseñaEditText.getText().toString().trim();





                if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
                    Toast.makeText(Crear_Cuenta.this, "Por favor, ingrese nombre de usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return; // No continuar si los campos están vacíos
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean exito = crearUsuario(nombreUsuario, contraseña);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (exito) {
                                    Toast.makeText(Crear_Cuenta.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                                    finish(); // Cerrar la actividad y volver a la anterior
                                } else {
                                    Toast.makeText(Crear_Cuenta.this, "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String URL = "jdbc:postgresql://articulosconstruccion.cxom6eo64yqc.us-east-2.rds.amazonaws.com:5432/articulosConstruccion?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
                        String USER = "daniel";
                        String PASSWORD = "12345678";
                        Connection connection = null;
                        try {
                            connection = DriverManager.getConnection(URL, USER, PASSWORD);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Crear_Cuenta.this, "Conexión exitosa", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (SQLException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Crear_Cuenta.this, "Error de conexión: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        });
    }
}