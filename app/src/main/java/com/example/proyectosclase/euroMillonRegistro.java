package com.example.proyectosclase;

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
    private String[] arrayUser = {"Brayan@gmail.com", "Pablo@gmail.com", "Noel@gmail.com", "Carolina@gmail.com", "Juan Jose@gmail.com", "Alejandra@gmail.com", "Cristian@gmail.com", "Ivan@gmail.com"};
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
                    if (arrayUser[i].equals(usuario) && arrayPass[i].equals(password)) {
                        isUser = true;
                        break;
                    }
                }
                if (isUser == true) {
                    setContentView(R.layout.euro_millon);
                } else {
                    //Toast para mostrar el mensaje de usuario o contraseña incorrecta
                    Toast.makeText(euroMillonRegistro.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    user.setText("");
                    pass.setText("");
                }
            }
        });
    }
}
