package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class botonLlamando extends AppCompatActivity {

    private TextView tv1;
    private ImageButton imgBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boton_llamando);

        tv1 = findViewById(R.id.tv1);


    }

    public void llamar(View view) {
        tv1.setText("Llamando...");

    }

}

