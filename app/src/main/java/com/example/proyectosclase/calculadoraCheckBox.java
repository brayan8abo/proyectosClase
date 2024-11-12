package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class calculadoraCheckBox extends AppCompatActivity {

    private EditText et1, et2;
    private TextView resultado, resultado2;
    private CheckBox chBox1, chBox2, chBox3, chBox4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora_checkbox);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        chBox1 = findViewById(R.id.chBox1);
        chBox2 = findViewById(R.id.chBox2);
        chBox3 = findViewById(R.id.chBox3);
        chBox4 = findViewById(R.id.chBox4);
        resultado = findViewById(R.id.resultado);
        resultado2 = findViewById(R.id.resultado2);
    }

    public void sumar(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();
        int nro1 = Integer.parseInt(valor1);
        int nro2 = Integer.parseInt(valor2);
        String resu = "";
        if (chBox1.isChecked() == true) {
            int suma = nro1 + nro2;
            resu = "La suma es: " + suma;
        }
        if (chBox2.isChecked() == true) {
            int resta = nro1 - nro2;
            resu = "La resta es: " + resta;
        }
        if (chBox3.isChecked() == true) {
            int multi = nro1 * nro2;
            resu = "La multiplicacion es: " + multi;
        }
        if (chBox4.isChecked() == true) {
            int div = nro1 / nro2;
            resu = "La division es: " + div;
        }
        resultado2.setText(resu);


    }
}
