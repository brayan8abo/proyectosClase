package com.example.proyectosclase;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class euroMillon extends AppCompatActivity {


    // definimos variables usadas en xml
    private Button btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.euro_millon);
        setTitle("Euro Millon");

        // buscamos los elementos del xml
        btnSalir = findViewById(R.id.btnSalir);


        // Listener del boton salir para fin
        btnSalir.setOnClickListener(view -> {
            finish();
        });

    }
}
