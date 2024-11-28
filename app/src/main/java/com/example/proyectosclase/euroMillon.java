package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class euroMillon extends AppCompatActivity {


    // definimos variables usadas en xml
    GridLayout gridNumeros;
    Button btnSalir;
    ArrayList<Integer> numerosSeleccionados = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.euro_millon);
        setTitle("Euro Millon");

        // buscamos los elementos del xml
        btnSalir = findViewById(R.id.btnSalir);
        gridNumeros = findViewById(R.id.gridNumeros);

        // Establecer el número de columnas del GridLayout
        gridNumeros.setColumnCount(5); // Puedes ajustar el número de columnas según tus necesidades
        int[] numImagenes = {
                R.mipmap.uno, R.mipmap.dos, R.mipmap.tres, R.mipmap.cuatro, R.mipmap.cinco, R.mipmap.seis,
                R.mipmap.siete, R.mipmap.ocho, R.mipmap.nueve, R.mipmap.diez, R.mipmap.once, R.mipmap.doce,
                R.mipmap.trece, R.mipmap.catorce, R.mipmap.quince, R.mipmap.dieciseis, R.mipmap.diecisiete, R.mipmap.dieciocho,
                R.mipmap.diecinueve, R.mipmap.veinte, R.mipmap.veintiuno, R.mipmap.veintidos, R.mipmap.veintitres, R.mipmap.veinticuatro,
                R.mipmap.veinticinco, R.mipmap.veintiseis, R.mipmap.veintisiete, R.mipmap.veintiocho, R.mipmap.veintinueve, R.mipmap.treinta,
                R.mipmap.treinta_y_uno, R.mipmap.treinta_y_dos, R.mipmap.treinta_y_tres, R.mipmap.treinta_y_cuatro, R.mipmap.treinta_y_cinco, R.mipmap.treinta_y_seis,
                R.mipmap.treinta_y_siete, R.mipmap.treinta_y_ocho, R.mipmap.treinta_y_nueve, R.mipmap.cuarenta, R.mipmap.cuarenta_y_uno, R.mipmap.cuarenta_y_dos,
                R.mipmap.cuarenta_y_tres, R.mipmap.cuarenta_y_cuatro, R.mipmap.cuarenta_y_cinco, R.mipmap.cuarenta_y_seis, R.mipmap.cuarenta_y_siete, R.mipmap.cuarenta_y_ocho,
                R.mipmap.cuarenta_y_nueve, R.mipmap.cincuenta};


        // Llamar al método para generar los ImageButtons
        imagenesNumeros(gridNumeros, 50, numerosSeleccionados, numImagenes);

        // Listener del boton salir para fin
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void imagenesNumeros(GridLayout gridN, int contador, ArrayList<Integer> seleccion, int[] imagenes) {
        for (int i = 0; i < contador; i++) {
            // Crear un nuevo ImageButton
            ImageButton btnNumeros = new ImageButton(this);
            btnNumeros.setId(View.generateViewId()); // Generar un ID único para el botón
            btnNumeros.setImageResource(imagenes[i]); // Establecer la imagen del botón
            btnNumeros.setBackgroundColor(0); // Eliminar el fondo
            btnNumeros.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // Configurar el escalado de la imagen
            btnNumeros.setTag(i + 1); // Establecer el número como el tag del botón (puedes usarlo luego si lo necesitas)

            // Configuración del layout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 200; // Establecer el tamaño del botón (puedes ajustar este valor)
            params.height = 200;
            params.setMargins(10, 10, 10, 10); // Establecer márgenes entre los botones
            btnNumeros.setLayoutParams(params);

            // Establecer el OnClickListener para cada botón
            btnNumeros.setOnClickListener(view -> {
                int numero = (int) view.getTag();
                // Aquí puedes añadir la lógica para lo que debe ocurrir al hacer clic en el botón
                // Por ejemplo, agregarlo a una lista de números seleccionados
                seleccion.add(numero);
            });

            // Añadir el botón al GridLayout
            gridN.addView(btnNumeros);
        }
    }


    public void seleccionNumero(ArrayList<Integer> numeroSeleccionado, int numero, int numPermitido, ImageButton btns) {
        if (numeroSeleccionado.contains(numero)) {
            numeroSeleccionado.remove(numero);
            btns.setBackgroundColor(0);
        } else {
            if (numeroSeleccionado.size() < numPermitido) {
                numeroSeleccionado.add(numero);
                btns.setBackgroundColor(getResources().getColor(R.color.rojo));
            } else {
                Toast.makeText(this, "SOLO SE PERMITEN 5 NÚMEROS", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
