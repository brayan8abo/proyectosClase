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
    private String[] arrayUser = {"Brayan@gmail.com", "Pablo@gmail.com", "Noel@gmail.com", "Carolina@gmail.com", "Juan Jose@gmail.com", "Alejandra@gmail.com", "Cristian@gmail.com","Ivan@gmail.com"};
    private String[] arrayPass = {"12345", "11111", "22222", "33333", "44444", "55555", "66666","77777"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.euro_millon_registro);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = user.getText().toString();
                String password = pass.getText().toString();

                boolean isUser = false;
                for (int i = 0; i < arrayUser.length; i++) {
                    if (arrayUser[i].equals(usuario) && arrayPass[i].equals(password)) {
                        isUser = true;
                        break;
                    }
                }
                if (isUser) {
                    setContentView(R.layout.euro_millon);
                } else {
                    Toast.makeText(euroMillonRegistro.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    user.setText("");
                    pass.setText("");
                }
            }
        });
    }
}
