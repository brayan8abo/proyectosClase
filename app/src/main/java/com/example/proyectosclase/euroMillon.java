package com.example.proyectosclase;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class euroMillon extends AppCompatActivity {

    private Button btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.euro_millon);
        setTitle("Euro Millon");

        btnSalir = findViewById(R.id.btnSalir);

        btnSalir.setOnClickListener(view -> {
            finish();
        });

    }
}
