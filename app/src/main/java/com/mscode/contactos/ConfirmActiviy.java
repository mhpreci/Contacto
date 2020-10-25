package com.mscode.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActiviy extends AppCompatActivity {

    Button btnEditar;
    String name ;
    String phone ;
    String email ;
    String description ;
    String birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_activiy);

        Bundle parametros = getIntent().getExtras();
        name = parametros.getString("name");
        phone = parametros.getString("phone");
        email = parametros.getString("Email");
        description = parametros.getString("description");
        birth = parametros.getString("birth");

        TextView tvNombre =(TextView)  findViewById(R.id.txtName);
        TextView tvTelefono =(TextView)  findViewById(R.id.txtPhone);
        TextView tvEmail =(TextView)  findViewById(R.id.txtEmail);
        TextView tvDescripcion =(TextView)  findViewById(R.id.txtDescription);
        TextView tvFechaNacimiento =(TextView)  findViewById(R.id.lblBirthText);
        Button btnEditar =(Button)  findViewById(R.id.btnEdit);

        tvNombre.setText(name);
        tvTelefono.setText(phone);
        tvDescripcion.setText(description);
        tvEmail.setText(email);
        tvFechaNacimiento.setText(birth);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario contacto = new Usuario(name,birth, phone,email,description);

                Intent intent = new Intent(ConfirmActiviy.this, MainActivity.class);
                intent.putExtra("nameEdit",contacto.getNombre());
                intent.putExtra("phoneEdit",contacto.getTelefono());
                intent.putExtra("emailEdit",contacto.getEmail());
                intent.putExtra("descriptionEdit",contacto.getNombre());
                intent.putExtra("BirthEdit",contacto.getFechaNacimiento());
                startActivity(intent);

            }
        });
    }
}