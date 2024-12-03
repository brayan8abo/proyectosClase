package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Random;

public class resultadosEuroMillones extends AppCompatActivity {


    //declaramos las variables usadas en el xml
    private Button btnSalir, btnReset;
    private TextView seleccionNumStar, numStarsWin, numJuegos, aciertos;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        btnSalir = findViewById(R.id.btnSalir);
        numStarsWin = findViewById(R.id.numStarsWin);
        numJuegos = findViewById(R.id.numJuegos);
        seleccionNumStar = findViewById(R.id.seleccionNumStar);
        aciertos = findViewById(R.id.aciertos);
        btnReset = findViewById(R.id.btnReset);

        //recogemos los numeros y estrellas seleccionados que la pasamos de la actividad anterior
        ArrayList<Integer> numerosSeleccionados = getIntent().getIntegerArrayListExtra("numerosSeleccionados");
        ArrayList<Integer> estrellasSeleccionadas = getIntent().getIntegerArrayListExtra("estrellasSeleccionadas");


        //mostramos los numeros y estrellas seleccionados
        String numStarSelected = "Has elegio los números: " + numerosSeleccionados.toString() + "\nHas elegito las estrellas: " + estrellasSeleccionadas.toString();
        seleccionNumStar.setText(numStarSelected);

        //generamos los numeros y estrellas aleatorias contando que deben ir desde 1 50 y de 1 a 12
        ArrayList<Integer> numerosGanadores = generacionNum();
        ArrayList<Integer> estrellasGanadoras = generacionEstrellas();
        numStarsWin.setText("Los numeros ganadores son: " + numerosGanadores.toString() + "\nLas estrellas ganadoras son: " + estrellasGanadoras.toString());

        //le pasamos a las variables los metodos creados para contar los aciertos, tanto de los numeros como las estrellas
        //en este caso pasandole los numeros seleccionados y los numeros ganadores que se generan aleatoriamente
        int aciertosNumeros = contarAciertos(numerosSeleccionados, numerosGanadores);
        int aciertosEstrellas = contarAciertos(estrellasSeleccionadas, estrellasGanadoras);


        //le pasamos a un textView lo que hemos sacado, en aciertos
        aciertos.setText("Has acertado " + aciertosNumeros + " numeros y " + aciertosEstrellas + " estrellas");


        //hacemos uno del metodo calcular ganancia
        int ganacias = calcularPorcentajeGanancia(contarAciertos(numerosSeleccionados, numerosGanadores), contarAciertos(estrellasSeleccionadas, estrellasGanadoras));
        TextView ganancias = findViewById(R.id.ganancias);


        //contar las veces de juego
        contadorVecesJugadas ContadorVecesJugadas = null;
        int numeroJugadas = ContadorVecesJugadas.recuperarJuegos(this);
        ContadorVecesJugadas.incrementarJuegos(this);
        numJuegos.setText("Has jugado: " + numeroJugadas + " veces");

        int porcentajeGanancias = contarAciertos(numerosSeleccionados, numerosGanadores);
        long premioDelBote = bote(porcentajeGanancias);

        if (porcentajeGanancias > 0) {
            ganancias.setTextColor(ContextCompat.getColor(this, R.color.ganaciaPositiva));
            ganancias.setText("Has ganado: " + String.valueOf(premioDelBote) + "€, Enhorabuena!");
        } else {
            ganancias.setTextColor(ContextCompat.getColor(this, R.color.ganaciaNegativa));
            ganancias.setText("Hoy no hemos tenido suerte");
        }


        //listener del boton salir para finalizar la app
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contadorVecesJugadas.resetearJuegos(resultadosEuroMillones.this);

                numJuegos.setText("Has jugado: 0 veces");

                Toast.makeText(resultadosEuroMillones.this, "Has reiniciado el juego", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static ArrayList<Integer> generacionNum() {
        ArrayList<Integer> numerosGanadores = new ArrayList<>();
        Random numerosRdn = new Random();
        while (numerosGanadores.size() < 5) {
            int numeros = numerosRdn.nextInt(5) + 1;
            if (!numerosGanadores.contains(numeros)) {
                numerosGanadores.add(numeros);
            }
        }
        return numerosGanadores;
    }

    public static ArrayList<Integer> generacionEstrellas() {
        ArrayList<Integer> estrellasGanadoras = new ArrayList<>();
        Random estrellasRdn = new Random();
        while (estrellasGanadoras.size() < 2) {
            int estrellas = estrellasRdn.nextInt(2) + 1;
            if (!estrellasGanadoras.contains(estrellas)) {
                estrellasGanadoras.add(estrellas);
            }
        }
        return estrellasGanadoras;
    }

    public static int contarAciertos(ArrayList<Integer> seleccion, ArrayList<Integer> ganadores) {
        int aciertos = 0;
        for (int num : seleccion) {
            if (ganadores.contains(num)) {
                aciertos++;
            }
        }
        return aciertos;
    }


    public static int calcularPorcentajeGanancia(int aciertosNumeros, int aciertosEstrellas) {
        // Si aciertas los 5 números y las 2 estrellas
        if (aciertosNumeros == 5 && aciertosEstrellas == 2) {
            return 100; // 100% si aciertas 5 números y 2 estrellas
        }

        // Si aciertas 5 números y 1 estrella
        if (aciertosNumeros == 5 && aciertosEstrellas == 1) {
            return 90; // 90% si aciertas 5 números y 1 estrella
        }

        // Si aciertas 5 números y 0 estrellas
        if (aciertosNumeros == 5 && aciertosEstrellas == 0) {
            return 80; // 80% si aciertas 5 números y 0 estrellas
        }

        // Si aciertas 4 números y 2 estrellas
        if (aciertosNumeros == 4 && aciertosEstrellas == 2) {
            return 75; // 75% si aciertas 4 números y 2 estrellas
        }

        // Si aciertas 4 números y 1 estrella
        if (aciertosNumeros == 4 && aciertosEstrellas == 1) {
            return 60; // 60% si aciertas 4 números y 1 estrella
        }

        // Si aciertas 3 números y 2 estrellas
        if (aciertosNumeros == 3 && aciertosEstrellas == 2) {
            return 50; // 50% si aciertas 3 números y 2 estrellas
        }

        // Si aciertas 4 números y 0 estrellas
        if (aciertosNumeros == 4 && aciertosEstrellas == 0) {
            return 40; // 40% si aciertas 4 números y 0 estrellas
        }

        // Si aciertas 2 números y 2 estrellas
        if (aciertosNumeros == 2 && aciertosEstrellas == 2) {
            return 30; // 30% si aciertas 2 números y 2 estrellas
        }

        // Si aciertas 3 números y 1 estrella
        if (aciertosNumeros == 3 && aciertosEstrellas == 1) {
            return 20; // 20% si aciertas 3 números y 1 estrella
        }

        // Si aciertas 3 números y 0 estrellas
        if (aciertosNumeros == 3 && aciertosEstrellas == 0) {
            return 10; // 10% si aciertas 3 números y 0 estrellas
        }

        // Si aciertas 1 número y 2 estrellas
        if (aciertosNumeros == 1 && aciertosEstrellas == 2) {
            return 5; // 5% si aciertas 1 número y 2 estrellas
        }

        // Si aciertas 2 números y 1 estrella
        if (aciertosNumeros == 2 && aciertosEstrellas == 1) {
            return 3; // 3% si aciertas 2 números y 1 estrella
        }

        // Si no aciertas ninguna de las combinaciones anteriores
        return 0; // 0% en cualquier otro caso
    }

    public static long bote(int porcentaje) {
        long bote = 15000000L;
        return (long) (15000000 * (100 / 100.0));
    }

}



