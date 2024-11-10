package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class calculadorard extends AppCompatActivity {

    private EditText et1, et2;
    private TextView resultado, resultado2;
    private RadioButton rdBtnSumar, rdBtnRestar, rdBtnMultiplicar, rdBtnDivision;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadorard);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        rdBtnSumar = findViewById(R.id.rdBtnSumar);
        rdBtnRestar = findViewById(R.id.rdBtnRestar);
        rdBtnDivision = findViewById(R.id.rdBtnDivision);
        rdBtnMultiplicar = findViewById(R.id.rdBtnMultiplicar);
        resultado = findViewById(R.id.resultado);
        resultado2 = findViewById(R.id.resultado2);
    }

    public void operaciones(View view) {
        int num1 = Integer.parseInt(et1.getText().toString());
        int num2 = Integer.parseInt(et2.getText().toString());
        int res = 0;

        if (rdBtnSumar.isChecked()) {
            res = num1 + num2;
        } else if (rdBtnRestar.isChecked()) {
            res = num1 - num2;
        } else if (rdBtnMultiplicar.isChecked()) {
            res = num1 * num2;
        } else if (rdBtnDivision.isChecked()) {
            if (num2 != 0) {
                res = num1 / num2;
            } else {
                resultado2.setText("Error: División por cero");
                return;
            }
        }

        resultado2.setText(String.valueOf(res));
    }
}
