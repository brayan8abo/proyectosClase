package com.example.proyectosclase;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        // Recibir los datos enviados desde SeleccionActivity
        ArrayList<Integer> numerosSeleccionados = getIntent().getIntegerArrayListExtra("numerosSeleccionados");
        ArrayList<Integer> estrellasSeleccionadas = getIntent().getIntegerArrayListExtra("estrellasSeleccionadas");

        // Mostrar los datos (puedes personalizar esto)
        TextView resultadoTextView = findViewById(R.id.resultadoTextView);
        String mensaje = "Números seleccionados: " + numerosSeleccionados.toString() +
                "\nEstrellas seleccionadas: " + estrellasSeleccionadas.toString();
        resultadoTextView.setText(mensaje);
    }
}
