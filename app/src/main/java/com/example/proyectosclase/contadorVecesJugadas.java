package com.example.proyectosclase;

import android.content.Context;
import android.content.SharedPreferences;

public class contadorVecesJugadas {

    private static final String JUEGO = "EuroMillones";
    private static final String CONTADOR = "VecesJugadas";

    public static int recuperarJuegos(Context context) {
        SharedPreferences preferencias = context.getSharedPreferences(JUEGO, Context.MODE_PRIVATE);
        return preferencias.getInt(CONTADOR, 0);
    }

    public static void incrementarJuegos(Context context) {
        SharedPreferences preferencias = context.getSharedPreferences(JUEGO, Context.MODE_PRIVATE);
        int vecesJugadas = preferencias.getInt(CONTADOR, 0);
        vecesJugadas++;
        preferencias.edit().putInt(CONTADOR, vecesJugadas).apply();

    }
}
