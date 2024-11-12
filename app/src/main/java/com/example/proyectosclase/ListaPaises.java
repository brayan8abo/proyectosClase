package com.example.proyectosclase;


import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListaPaises extends AppCompatActivity {

    private String[] paises = {"Argentina", "Chile", "Paraguay", "Bolivia", "Peru", "Ecuador", "Brasil", "Colombia", "Venezuela", "Uruguay"};
    private String[] habitantes = {"40000000", "17000000", "6500000", "10000000", "30000000", "14000000", "183000000", "44000000", "29000000", "3500000"};
    private TextView tv1;
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_paises);
        tv1 = findViewById(R.id.resultado);
        lv1 = findViewById(R.id.lista);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                tv1.setText("Población de: " + lv1.getItemAtPosition(i) + " es [" + habitantes[i]+"]");
            }
        });
    }
}

