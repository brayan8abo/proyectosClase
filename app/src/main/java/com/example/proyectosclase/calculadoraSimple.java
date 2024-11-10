package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class calculadoraSimple extends AppCompatActivity {

    private EditText et1, et2;
    private TextView resultado, resultado2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculadora_simple);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        resultado = findViewById(R.id.resultado);
        resultado2 = findViewById(R.id.resultado2);

    }

    public void sumar(View view) {
        int num1 = Integer.parseInt(et1.getText().toString());
        int num2 = Integer.parseInt(et2.getText().toString());
        int res = num1 + num2;
        resultado2.setText("\n " + res);

    }
}