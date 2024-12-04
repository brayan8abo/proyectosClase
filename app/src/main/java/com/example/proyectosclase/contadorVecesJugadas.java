package com.example.proyectosclase;

import android.content.Context;
import android.content.SharedPreferences;

public class contadorVecesJugadas {

    //genero unas constantes de las preferencias
    private static final String JUEGO = "EuroMillones";
    private static final String CONTADOR = "VecesJugadas";

    //genero un metodo para recuperar el contador de las veces que jugamos
    public static int recuperarJuegos(Context context) {
        SharedPreferences preferencias = context.getSharedPreferences(JUEGO, Context.MODE_PRIVATE);
        //recuperamos el contador de las veces que jugamos
        return preferencias.getInt(CONTADOR, 0);
    }

    //genero un metodo para incrementar el contador de las veces que jugamos
    public static void incrementarJuegos(Context context) {
        SharedPreferences preferencias = context.getSharedPreferences(JUEGO, Context.MODE_PRIVATE);
        int vecesJugadas = preferencias.getInt(CONTADOR, 0);
        //incrementamos el contador
        vecesJugadas++;
        preferencias.edit().putInt(CONTADOR, vecesJugadas).apply();

    }

    //genero un metodo para resetear el contador de las veces que jugamos, en caso de que deseemos tener un contador en ceros (0)
    public static void resetearJuegos(Context context) {
        SharedPreferences preferencias = context.getSharedPreferences(JUEGO, Context.MODE_PRIVATE);
        preferencias.edit().putInt(CONTADOR, 0).apply(); // Resetea el contador a 0
    }
}

