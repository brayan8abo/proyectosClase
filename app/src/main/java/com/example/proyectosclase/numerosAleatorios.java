package com.example.proyectosclase;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class numerosAleatorios extends AppCompatActivity {


    private EditText numero;
    private int numAleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numeros_aleatorios);

        numero = findViewById(R.id.numero);
        numAleatorio = (int) (Math.random() * 100001);
        String cadena = String.valueOf(numAleatorio);
        Toast notificacion = Toast.makeText(this, cadena, Toast.LENGTH_LONG);
        notificacion.show();
    }

    public void controlar(View view) {
        String valorIngresado = numero.getText().toString();
        int valor = Integer.parseInt(valorIngresado);
        if (valor == numAleatorio) {
            Toast notificacion = Toast.makeText(this, "Muy bien recordaste el número mostrado.", Toast.LENGTH_LONG);
            notificacion.show();
        } else {
            Toast notificacion = Toast.makeText(this, "Lo siento pero no es el número que mostré.", Toast.LENGTH_LONG);
            notificacion.show();
        }
    }
}


