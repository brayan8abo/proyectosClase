package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class resultadosEuroMillones extends AppCompatActivity {

    private Button btnSalir;
    private TextView seleccionNumStar, numStarsWin, numJuegos;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        btnSalir = findViewById(R.id.btnSalir);
        numStarsWin = findViewById(R.id.numStarsWin);
        numJuegos = findViewById(R.id.numJuegos);

        //recogemos los numeros y estrellas seleccionados
        ArrayList<Integer> numerosSeleccionados = getIntent().getIntegerArrayListExtra("numerosSeleccionados");
        ArrayList<Integer> estrellasSeleccionadas = getIntent().getIntegerArrayListExtra("estrellasSeleccionadas");


        //mostramos los numeros y estrellas seleccionados
        seleccionNumStar = findViewById(R.id.seleccionNumStar);
        String numStarSelected = "Has elegio los números: " + numerosSeleccionados.toString() + "\n\nHas elegito las estrellas: " + estrellasSeleccionadas.toString();
        seleccionNumStar.setText(numStarSelected);

        //generamos los numeros y estrellas aleatorias
        ArrayList<Integer> numerosGanadores = generacionNum();
        ArrayList<Integer> estrellasGanadoras = generacionEstrellas();
        numStarsWin.setText("Los numeros ganadores son: " + numerosGanadores.toString() + "\n\nLas estrellas ganadoras son: " + estrellasGanadoras.toString());

        int aciertosNumeros = contarAciertos(numerosSeleccionados, numerosGanadores);
        int aciertosEstrellas = contarAciertos(estrellasSeleccionadas, estrellasGanadoras);
        TextView aciertos = findViewById(R.id.aciertos);
        aciertos.setText("Has acertado " + aciertosNumeros + " numeros y " + aciertosEstrellas + " estrellas");


        int ganacias = calcularGanancias(contarAciertos(numerosSeleccionados, numerosGanadores),
                contarAciertos(estrellasSeleccionadas, estrellasGanadoras));
        TextView ganancias = findViewById(R.id.ganancias);

        if (ganacias > 0) {
            ganancias.setTextColor(ContextCompat.getColor(this, R.color.ganaciaPositiva)); // Verde
            ganancias.setText("Has ganado: " + ganacias + "€, Enhorabuena!");
        } else {
            ganancias.setTextColor(ContextCompat.getColor(this, R.color.ganaciaNegativa)); // Rojo
            ganancias.setText("Hoy no hemos tenido suerte");
        }



        contadorVecesJugadas ContadorVecesJugadas = null;
        int numeroJugadas = ContadorVecesJugadas.recuperarJuegos(this);
        ContadorVecesJugadas.incrementarJuegos(this);
        numJuegos.setText("Has jugado: " + numeroJugadas + " veces");


        //listener del boton salir para finalizar la app
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
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
            int estrellas = estrellasRdn.nextInt(12) + 1;
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

    public static int calcularGanancias(int numerosAcertados, int estrellasAcertadas) {
        if (numerosAcertados == 5 && estrellasAcertadas == 2) return 100;
        if (numerosAcertados == 5 && estrellasAcertadas == 1) return 95;
        if (numerosAcertados == 5 && estrellasAcertadas == 0) return 80;
        if (numerosAcertados == 4 && estrellasAcertadas == 2) return 75;
        if (numerosAcertados == 4 && estrellasAcertadas == 1) return 60;
        if (numerosAcertados == 3 && estrellasAcertadas == 2) return 50;
        if (numerosAcertados == 4 && estrellasAcertadas == 0) return 40;
        if (numerosAcertados == 2 && estrellasAcertadas == 2) return 30;
        if (numerosAcertados == 3 && estrellasAcertadas == 1) return 20;
        if (numerosAcertados == 3 && estrellasAcertadas == 0) return 10;
        if (numerosAcertados == 1 && estrellasAcertadas == 2) return 5;
        if (numerosAcertados == 2 && estrellasAcertadas == 1) return 3;
        return 0;
    }
}


