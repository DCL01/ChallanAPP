package com.example.challanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class login extends AppCompatActivity {

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

    public static boolean verificarUsuario(String nombreUsuario, String contraseña) {
        boolean usuarioValido = false; // Inicializa como falso
        String query = "SELECT 1 FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, contraseña);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuarioValido = true; // Usuario y contraseña correctos
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar el usuario: " + e.getMessage());
        }
        return usuarioValido; // Devuelve verdadero si es válido, falso si no
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        Button crearNueva = findViewById(R.id.CrearCuentaButton);
        EditText usuarioEditText = findViewById(R.id.UserEditText);
        EditText contraseñaEditText = findViewById(R.id.PasswordEditText);
        Button ingresarButton = findViewById(R.id.LoginButton);
        Button conectar = findViewById(R.id.ContrasenaOlvidadaButton);

        crearNueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Crear_Cuenta.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection connection = null;
                        try {
                            connection = DriverManager.getConnection(URL, USER, PASSWORD);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(login.this, "Conexión exitosa", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (SQLException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(login.this, "Error de conexión: " + e.getMessage(), Toast.LENGTH_LONG).show();
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

        ingresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = usuarioEditText.getText().toString().trim();
                String contraseña = contraseñaEditText.getText().toString().trim();

                if (usuario.isEmpty() || contraseña.isEmpty()) {
                    Toast.makeText(login.this, "Por favor, ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return; // No continuar si los campos están vacíos
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean usuarioValido = verificarUsuario(usuario, contraseña); // Verifica si el usuario es válido
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (usuarioValido) {
                                    Intent intent = new Intent(login.this, Home_Usuarios.class); // Redirigir a Ingresa
                                    startActivity(intent);
                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                } else {
                                    Toast.makeText(login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
}