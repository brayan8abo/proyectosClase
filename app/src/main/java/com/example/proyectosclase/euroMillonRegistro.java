package com.example.proyectosclase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class euroMillonRegistro extends AppCompatActivity {


    /*
        Definimos las variables o objetos usados en el xml, tambien usando como variable un array con los usuarios y contraseñas
    * */
    private EditText user, pass;
    ;
    private Button btnEntrar;
    private String[] arrayUser = {"Brayan@gmail.com", "Pablo@gmail.com", "Noel@gmail.com", "Carolina@gmail.com", "JuanJose@gmail.com", "Alejandra@gmail.com", "Cristian@gmail.com", "Ivan@gmail.com"};
    private String[] arrayPass = {"12345", "11111", "22222", "33333", "44444", "55555", "66666", "77777"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hacemos un setContentView de euroMillonRegistro ya que ahí es donde se implemente el logeo de usuario
        setContentView(R.layout.euro_millon_registro);


        // buscamos las variables o objetos usados en el xml
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        btnEntrar = findViewById(R.id.btnEntrar);


        // hacemos un Listener para el boton entrar
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = user.getText().toString();
                String password = pass.getText().toString();


                //Hacemos una comprobación con el usuario y si este existe en el array igual que su contraseña ingresara
                // pero enviando un comando de iniciar euroMillon con los numeros y estrellas
                boolean isUser = false;
                for (int i = 0; i < arrayUser.length; i++) {
                    //acaba debemos tener en cuenta los usuarios guardados
                    /*
                     *   todo: "Brayan@gmail.com",1-5 "Pablo@gmail.com", 1-1 "Noel@gmail.com" 2-2,
                     *    "Carolina@gmail.com" 3-3, "JuanJose@gmail.com" 4-4,
                     *    "Alejandra@gmail.com"5-5, "Cristian@gmail.com"6-6, "Ivan@gmail.com7-7"};
                     *
                     * todo: y las contraseñas correspondientes "12345", "11111", "22222", "33333",
                     *  "44444", "55555", "66666", "77777"
                     * */


                    if (arrayUser[i].equals(usuario) && arrayPass[i].equals(password)) {
                        isUser = true;
                        break;
                    }
                }
                if (isUser == true) {
                    //Enviamos el comando de iniciar la seleccion de numeros
                    Intent seleccion = new Intent(euroMillonRegistro.this, seleccionNumeros.class);
                    startActivity(seleccion);
                    finish();
                } else {
                    //Toast para mostrar el mensaje de usuario o contraseña incorrecta, y asi mismo setear a limpio y tener que volver a ingresar datos
                    Toast.makeText(euroMillonRegistro.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    user.setText("");
                    pass.setText("");
                }
            }
        });
    }
}
