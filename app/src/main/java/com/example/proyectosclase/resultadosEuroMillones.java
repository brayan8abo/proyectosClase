package com.example.proyectosclase;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    private Button btnSalir, btnReset, btnNuevoJuego;
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
        btnNuevoJuego = findViewById(R.id.btnNuevoJuego);

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


        //contar las veces de juego usando la clase donde implementamos un SharedPreferences
        contadorVecesJugadas ContadorVecesJugadas = null;
        int numeroJugadas = ContadorVecesJugadas.recuperarJuegos(this);
        ContadorVecesJugadas.incrementarJuegos(this);
        numJuegos.setText("Has jugado: " + numeroJugadas + " veces");


        //calculamos las ganacias que va tener el usuario y le pasamos el bote, pasandole el porcentaje
        int porcentajeGanancias = calcularGanancias(aciertosNumeros, aciertosEstrellas);
        int premioDelBote = bote(porcentajeGanancias);



        //hacemos un control de ganacias para hacer un pequeño cambio de color dependiendo de si hemos ganado o no
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
                //finalizamos la app
                finishAffinity();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contadorVecesJugadas.resetearJuegos(resultadosEuroMillones.this);
                //setemaos el numero de juegos a 0 con el listener
                numJuegos.setText("Has jugado: 0 veces");

                Toast.makeText(resultadosEuroMillones.this, "Has reiniciado el juego", Toast.LENGTH_SHORT).show();
            }
        });
        //hacemos un listener para el boton nuevo juego, que nos redirige a la pestaña de seleccion
        btnNuevoJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(resultadosEuroMillones.this, seleccionNumeros.class);
                startActivity(intent);
            }
        });
    }


    //un metodo para generar los numeros aleatoriamente que nos da un numero entre el 1 y el 50 y seguido los vamos metiendo en nuestro array
    public static ArrayList<Integer> generacionNum() {
        ArrayList<Integer> numerosGanadores = new ArrayList<>();
        Random numerosRdn = new Random();
        while (numerosGanadores.size() < 5) {
            int numeros = numerosRdn.nextInt(50) + 1;
            if (!numerosGanadores.contains(numeros)) {
                numerosGanadores.add(numeros);
            }
        }
        //nos retorna los numeros ganadores
        return numerosGanadores;
    }


    //metodo para generar las estrellas aleatoriamente de entre 1 y 12, y posteriormente las agrega al array
    public static ArrayList<Integer> generacionEstrellas() {
        ArrayList<Integer> estrellasGanadoras = new ArrayList<>();
        Random estrellasRdn = new Random();
        while (estrellasGanadoras.size() < 2) {
            int estrellas = estrellasRdn.nextInt(12) + 1;
            if (!estrellasGanadoras.contains(estrellas)) {
                estrellasGanadoras.add(estrellas);
            }
        }
        //retornamos las estrellas ganadoras
        return estrellasGanadoras;
    }


    //metodo para contar los aciertos que le pasamos los numeros selecionados y los numeros ganadores que fueron anteriormente generados aleatoriamente
    public static int contarAciertos(ArrayList<Integer> seleccion, ArrayList<Integer> ganadores) {
        int aciertos = 0;
        for (int num : seleccion) {
            if (ganadores.contains(num)) {
                aciertos++;
            }
        }
        return aciertos;
    }


    //metodo para calcular las ganancias dadas como requisito por el profesor, teniendo en cuenta el numero de estrellas y numeros acertados
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

    //metodo para generar un bote
    public static int bote(int porcentajeGanancias) {
        int bote = 15000000; // bote de 15 millones por que no queremos pobres

        return bote * porcentajeGanancias / 100;
    }

}



