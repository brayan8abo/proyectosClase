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

public class seleccionNumeros extends AppCompatActivity {

    private GridLayout gridNumeros, gridEstrellas;

    private ArrayList<Integer> numerosSeleccionados = new ArrayList<>();
    private ArrayList<Integer> estrellasSeleccionadas = new ArrayList<>();

    private void configurarGrid(GridLayout grid, int max, ArrayList<Integer> seleccionados, int limite, String numero, boolean isStar) {
        //Array con los nombres de los botones que llevara el grid
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
        //array con los nombres de las estrellas que llevara el grid
        String[] estrellas = {
                "estrella_uno", "estrella_dos", "estrella_tres", "estrella_cuatro", "estrella_cinco",
                "estrella_seis", "estrella_siete", "estrella_ocho", "estrella_nueve", "estrella_diez",
                "estrella_once", "estrella_doce"
        };


        //aca lo que hacemos es que en un bucle recorremos el grid y lo llenamos con los botones
        for (int i = 1; i <= max; i++) {
            ImageButton btn = new ImageButton(this);

            //hacemos un if ternario para comprobar si es estrella o numero, teniendo condicionales de tamaño para estrellas 12 y numeros 50
            String nombreTipo = isStar ? "estrella" : "numero";
            if (isStar) {
                // para estrellas, usamos el array de estrellas
                if (i <= 12) {
                    nombreTipo = estrellas[i - 1];
                } else {
                    continue;
                }
            } else {
                // para números, usamos el array numeros
                if (i <= 50) {
                    nombreTipo = numeros[i - 1];
                } else {
                    //si llegamos a darle jugar y no tenemos ningun numero ni estrella seleccionada nos saltara el mensaje que debemos seleccionar
                    continue;
                }
            }

            // obtener el ID del recurso usando el nombre generado, pasandole como recurso la carpeta drawable donde estan todos las imagenes
            @SuppressLint("DiscouragedApi") int idImagenes = getResources().getIdentifier(nombreTipo, "drawable", getPackageName());

            //a cada imagen le asignamos un ID
            if (idImagenes == 0) {
                System.out.println("Error: Imagen no encontrada para " + nombreTipo);
                continue; // Salta esta iteración si no se encuentra el recurso
            }

            // configurar la imagen y el tag del botón, pasandole un ID generado previamente
            btn.setImageResource(idImagenes);
            btn.setTag(i);//asignamos el tag con el numero que le corresponde


            //asignamos un listener donde le pasamos el tag para hacer el uso del boton que deseemos, tambien haciendo control en que si es numero o estrella
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int numero = (int) v.getTag();
                    //comprobamos si el boton esta seleccionado, de ser asi, lo quitamos
                    if (seleccionados.contains(numero)) {
                        seleccionados.remove(Integer.valueOf(numero));
                        btn.setBackgroundResource(0);

                        //comprobamos si el array tiene la capacidad llena, y de ser incompleta, procedemos a llenar con la seleccio, y pasamos el DRAWABLE de seleccionar
                    } else if (seleccionados.size() < limite) {
                        seleccionados.add(numero);
                        btn.setBackgroundResource(R.drawable.seleccionar);
                    } else {
                        //mensaje para notificar que no se pueden elegir mas números
                        Toast.makeText(seleccionNumeros.this, "No puedes seleccionar más de " + limite, Toast.LENGTH_SHORT).show();
                    }
                }
            });


            //configuracion del grid para que los botones se acoplen directamente en unos espacios
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

        gridNumeros = findViewById(R.id.gridNumeros);
        gridEstrellas = findViewById(R.id.gridEstrellas);

        //le pasamos al grid, el array numeros, el array estrellas, los numeros y estrellas que a seleccionado, los limites, los (tipos de nombre) y un boolean donde determina si es estrella o numero
        configurarGrid(gridNumeros, 50, numerosSeleccionados, 5, "numero_", false);
        configurarGrid(gridEstrellas, 12, estrellasSeleccionadas, 2, "estrella_", true);


        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //comprobamos si los numeros seleccionados y estrellas selecciondas cumplan con los requisitos para hacer el siguiente paso
                if (numerosSeleccionados.size() == 5 && estrellasSeleccionadas.size() == 2) {
                    //llamamos intent que es la que se encarga de que cuando haga la comprobacion ejecute el siguiente activity
                    Intent intent = new Intent(seleccionNumeros.this, resultadosEuroMillones.class);

                    //pasamos los arrays para poder tener conocimiento de los datos o informacion ingresada en la otra actividad y poder manejar la informacion
                    intent.putIntegerArrayListExtra("numerosSeleccionados", numerosSeleccionados);
                    intent.putIntegerArrayListExtra("estrellasSeleccionadas", estrellasSeleccionadas);
                    startActivity(intent);
                } else {
                    //si llegaras a darle jugar y no tenemos ningun numero ni estrella seleccionada nos saltara el mensaje que debemos seleccionar
                    Toast.makeText(seleccionNumeros.this, "Selecciona 5 números y 2 estrellas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
