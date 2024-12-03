package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Random;

public class resultadosEuroMillones extends AppCompatActivity {


    //declaramos las variables usadas en el xml
    private Button btnSalir;
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
        int ganacias = calcularGanancias(contarAciertos(numerosSeleccionados, numerosGanadores), contarAciertos(estrellasSeleccionadas, estrellasGanadoras));
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
    public static int bote(int porcentaje){
        int bote = 1500000;
        return (int) (bote * (porcentaje / 100.0));
    }
    public static void mostrarBote(TextView textoBote, int aciertosNumeros, int aciertosEstrellas) {
        // Llamada a calcularPorcentajeBote para obtener el porcentaje según los aciertos
        int porcentaje = bote(TextView textoBote, TextView aciertosNumeros, aciertosEstrellas);

        // Calcular el valor del bote
        int bote = bote(porcentaje);

        // Mostrar el resultado en el TextView
        if (porcentaje > 0) {
            textoBote.setText("¡Has ganado " + bote + "€ en el Bote!");
        } else {
            textoBote.setText("No has ganado el Bote esta vez.");
        }
    }


}


