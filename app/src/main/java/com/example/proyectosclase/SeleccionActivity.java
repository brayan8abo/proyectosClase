package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SeleccionActivity extends AppCompatActivity {

    private ArrayList<Integer> numerosSeleccionados = new ArrayList<>();
    private ArrayList<Integer> estrellasSeleccionadas = new ArrayList<>();

    private void configurarGrid(GridLayout grid, int max, ArrayList<Integer> seleccionados, int limite, String numero, boolean isStar) {
        // Lista de nombres de los números y las estrellas
        String[] numeros = {
                "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez",
                "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho",
                "diecinueve", "veinte", "veintiuno", "veintidos", "veintitres", "veinticuatro",
                "veinticinco", "veintiseis", "veintisiete", "veintiocho", "veintinueve", "treinta",
                "treinta_y_uno", "treinta_y_dos", "treinta_y_tres", "treinta_y_cuatro", "treinta_y_cinco",
                "treinta_y_seis", "treinta_y_siete", "treinta_y_ocho", "treinta_y_nueve", "cuarenta",
                "cuarenta_y_uno", "cuarenta_y_dos", "cuarenta_y_tres", "cuarenta_y_cuatro", "cuarenta_y_cinco",
                "cuarenta_y_seis", "cuarenta_y_siete", "cuarenta_y_ocho", "cuarenta_y_nueve", "cincuenta"
        };

        String[] estrellas = {
                "estrella_uno", "estrella_dos", "estrella_tres", "estrella_cuatro", "estrella_cinco",
                "estrella_seis", "estrella_siete", "estrella_ocho", "estrella_nueve", "estrella_diez",
                "estrella_once", "estrella_doce"
        };

        for (int i = 1; i <= max; i++) {
            ImageButton btn = new ImageButton(this);

            // Generar el nombre del recurso basado en si es una estrella o un número
            String resourceName;
            if (isStar) {
                // Para estrellas, usamos el array 'estrellas'
                if (i <= 12) { // Aseguramos que no exceda el rango de estrellas disponibles
                    resourceName = estrellas[i - 1]; // "estrella_uno", "estrella_dos", ..., "estrella_doce"
                } else {
                    continue; // Si el índice está fuera de rango, lo omitimos
                }
            } else {
                // Para números, usamos el array 'numeros'
                if (i <= 50) { // Aseguramos que no exceda el rango de números disponibles
                    resourceName = numeros[i - 1]; // "uno", "dos", ..., "cincuenta"
                } else {
                    continue; // Si el índice está fuera de rango, lo omitimos
                }
            }

            // Obtener el ID del recurso usando el nombre generado
            int imageResId = getResources().getIdentifier(resourceName, "mipmap", getPackageName());

            if (imageResId == 0) {
                System.out.println("Error: Imagen no encontrada para " + resourceName);
                continue; // Salta esta iteración si no se encuentra el recurso
            }

            // Configurar la imagen y el tag del botón
            btn.setImageResource(imageResId);
            btn.setTag(i);

            // Configurar evento de clic
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int numero = (int) v.getTag();
                    if (seleccionados.contains(numero)) {
                        // Deseleccionar
                        seleccionados.remove(Integer.valueOf(numero));
                        btn.setBackgroundResource(0); // Quitar fondo
                    } else if (seleccionados.size() < limite) {
                        // Seleccionar
                        seleccionados.add(numero);
                        btn.setBackgroundResource(R.mipmap.seleccion); // Fondo seleccionado
                    } else {
                        Toast.makeText(SeleccionActivity.this, "No puedes seleccionar más de " + limite, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // Ajustar estilo y añadir el botón al GridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 250; // Ancho
            params.height = 160; // Alto
            btn.setLayoutParams(params);
            btn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            btn.setBackgroundResource(0);

            grid.addView(btn);
        }
    }

    // Método para convertir un número a su forma en palabras
   /* private String convertNumberToWord(int number) {
        String[] words = {
                "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez",
                "once", "doce", "trece", "catorce", "quince", "dieciseis", "diecisiete", "dieciocho", "diecinueve", "veinte",
                "veintiuno", "veintidos", "veintitres", "veinticuatro", "veinticinco", "veintiseis", "veintisiete", "veintiocho", "veintinueve", "treinta",
                "treinta_y_uno", "treinta_y_dos", "treinta_y_tres", "treinta_y_cuatro", "treinta_y_cinco", "treinta_y_seis", "treinta_y_siete", "treinta_y_ocho", "treinta_y_nueve", "cuarenta",
                "cuarenta_y_uno", "cuarenta_y_dos", "cuarenta_y_tres", "cuarenta_y_cuatro", "cuarenta_y_cinco", "cuarenta_y_seis", "cuarenta_y_siete", "cuarenta_y_ocho", "cuarenta_y_nueve", "cincuenta"
        };
        return words[number - 1]; // Ajuste para índices basados en 0
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) GridLayout gridNumeros = findViewById(R.id.gridNumeros);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) GridLayout gridEstrellas = findViewById(R.id.gridEstrellas);

        // Configurar los grids
        // Configurar los grids
        configurarGrid(gridNumeros, 50, numerosSeleccionados, 5, "numero_", false);
        configurarGrid(gridEstrellas, 12, estrellasSeleccionadas, 2, "estrella_", true);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnConfirmar = findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numerosSeleccionados.size() == 5 && estrellasSeleccionadas.size() == 2) {
                    Intent intent = new Intent(SeleccionActivity.this, ResultadoActivity.class);
                    intent.putIntegerArrayListExtra("numerosSeleccionados", numerosSeleccionados);
                    intent.putIntegerArrayListExtra("estrellasSeleccionadas", estrellasSeleccionadas);
                    startActivity(intent);
                } else {
                    Toast.makeText(SeleccionActivity.this, "Selecciona 5 números y 2 estrellas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
