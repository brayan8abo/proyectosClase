package com.example.proyectosclase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class calculadoraSpinner extends AppCompatActivity {
    private Spinner spinner1;
    private EditText et1, et2;
    private TextView resultado, resultado2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora_spinner);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        resultado = findViewById(R.id.resultado);
        resultado2 = findViewById(R.id.resultado2);
        spinner1 = findViewById(R.id.spinner);
        String[] opciones = {"sumar", "restar", "multiplicar", "dividir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);
    }

    //Este método se ejecutará cuando se presione el botón
    public void operar(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();
        int nro1 = Integer.parseInt(valor1);
        int nro2 = Integer.parseInt(valor2);
        String selec = spinner1.getSelectedItem().toString();
        if (selec.equals("sumar")) {
            int suma = nro1 + nro2;
            String resu = String.valueOf(suma);
            resultado2.setText(resu);
        } else if (selec.equals("restar")) {
            int resta = nro1 - nro2;
            String resu = String.valueOf(resta);
            resultado2.setText(resu);
        } else if (selec.equals("multiplicar")) {
            int multi = nro1 * nro2;
            String resu = String.valueOf(multi);
            resultado2.setText(resu);
        } else if (selec.equals("dividir")) {
            int divi = nro1 / nro2;
            String resu = String.valueOf(divi);
            resultado2.setText(resu);
        }
    }
}