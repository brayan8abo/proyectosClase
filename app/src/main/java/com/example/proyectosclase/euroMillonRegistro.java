package com.example.proyectosclase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class euroMillonRegistro extends AppCompatActivity {

    private EditText user;
    private EditText pass;
    private Button btnEntrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.euro_millon_registro);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        Button btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = user.getText().toString();
                String password = pass.getText().toString();
                if (usuario.equals("Brayan") && password.equals("12345")) {
                    setContentView(R.layout.euro_millon);
                } else {
                    Toast.makeText(euroMillonRegistro.this, "Usuario o contrasena incorrecta", Toast.LENGTH_SHORT).show();
                    user.setText("");
                    pass.setText("");
                    setContentView(R.layout.euro_millon_registro);

                }

            }
        });

    }

}