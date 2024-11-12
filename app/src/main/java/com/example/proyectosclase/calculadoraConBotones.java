package com.example.proyectosclase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class calculadoraConBotones extends AppCompatActivity {

    private TextView resultado, resultado2;
    private ImageButton imgBtnSuma, imgBtnResta, imgBtnMulti, imgBtnDivi;
    private EditText et1, et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora_botones);

        resultado = findViewById(R.id.resultado);
        resultado2 = findViewById(R.id.resultado2);
        imgBtnSuma = findViewById(R.id.imgBtnSuma);
        imgBtnResta = findViewById(R.id.imgBtnResta);
        imgBtnMulti = findViewById(R.id.imgBtnMulti);
        imgBtnDivi = findViewById(R.id.imgBtnDivi);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        imgBtnSuma = findViewById(R.id.imgBtnSuma);
        imgBtnResta = findViewById(R.id.imgBtnResta);
        imgBtnMulti = findViewById(R.id.imgBtnMulti);
        imgBtnDivi = findViewById(R.id.imgBtnDivi);

        // Asigna un OnClickListener para cada botón
        imgBtnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("+");
            }
        });

        imgBtnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("-");
            }
        });

        imgBtnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("*");
            }
        });

        imgBtnDivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("/");
            }
        });
    }

    // Método para realizar la operación
    private void realizarOperacion(String operacion) {
        // Obtiene los valores de los EditText y convierte a double
        int numero1 = Integer.parseInt(et1.getText().toString());
        int numero2 = Integer.parseInt(et2.getText().toString());
        int suma = 0;

        switch (operacion) {
            case "+":
                suma = numero1 + numero2;
                break;
            case "-":
                suma = numero1 - numero2;
                break;
            case "*":
                suma = numero1 * numero2;
                break;
            case "/":
                if (numero2 != 0) {
                    suma = numero1 / numero2;
                } else {
                    resultado2.setText("Error: División por cero");
                    return;
                }
                break;
        }

        // Muestra el resultado en el TextView
        resultado2.setText(String.valueOf(suma));

    }
}

