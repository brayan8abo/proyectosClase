package com.example.proyectosclase;



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

public class seleccionNumeros extends AppCompatActivity {

    private GridLayout gridNumeros, gridEstrellas;

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
            String nombreTipo = isStar ? "estrella" : "numero";
            if (isStar) {
                // Para estrellas, usamos el array 'estrellas'
                if (i <= 12) { // Aseguramos que no exceda el rango de estrellas disponibles
                    nombreTipo = estrellas[i - 1]; // "estrella_uno", "estrella_dos", ..., "estrella_doce"
                } else {
                    continue; // Si el índice está fuera de rango, lo omitimos
                }
            } else {
                // Para números, usamos el array 'numeros'
                if (i <= 50) { // Aseguramos que no exceda el rango de números disponibles
                    nombreTipo = numeros[i - 1]; // "uno", "dos", ..., "cincuenta"
                } else {
                    continue; // Si el índice está fuera de rango, lo omitimos
                }
            }

            // Obtener el ID del recurso usando el nombre generado
            int idImagenes = getResources().getIdentifier(nombreTipo, "drawable", getPackageName());

            if (idImagenes == 0) {
                System.out.println("Error: Imagen no encontrada para " + nombreTipo);
                continue; // Salta esta iteración si no se encuentra el recurso
            }

            // Configurar la imagen y el tag del botón
            btn.setImageResource(idImagenes);
            btn.setTag(i);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int numero = (int) v.getTag();
                    if (seleccionados.contains(numero)) {
                        seleccionados.remove(Integer.valueOf(numero));
                        btn.setBackgroundResource(0); // Quitar fondo
                    } else if (seleccionados.size() < limite) {
                        seleccionados.add(numero);
                        btn.setBackgroundResource(R.drawable.seleccionar);
                    } else {
                        Toast.makeText(seleccionNumeros.this, "No puedes seleccionar más de " + limite, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 250; // Ancho
            params.height = 160; // Alto
            btn.setLayoutParams(params);
            btn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            btn.setBackgroundResource(0);

            grid.addView(btn);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        GridLayout gridNumeros = findViewById(R.id.gridNumeros);
        GridLayout gridEstrellas = findViewById(R.id.gridEstrellas);

        // Configurar los grids
        // Configurar los grids
        configurarGrid(gridNumeros, 50, numerosSeleccionados, 5, "numero_", false);
        configurarGrid(gridEstrellas, 12, estrellasSeleccionadas, 2, "estrella_", true);


        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numerosSeleccionados.size() == 5 && estrellasSeleccionadas.size() == 2) {
                    Intent intent = new Intent(seleccionNumeros.this, resultadosEuroMillones.class);
                    intent.putIntegerArrayListExtra("numerosSeleccionados", numerosSeleccionados);
                    intent.putIntegerArrayListExtra("estrellasSeleccionadas", estrellasSeleccionadas);
                    startActivity(intent);
                } else {
                    Toast.makeText(seleccionNumeros.this, "Selecciona 5 números y 2 estrellas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
