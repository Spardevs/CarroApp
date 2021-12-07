package br.com.senaimt.carroapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import br.com.senaimt.dao.SQLHelper;

public class MainActivity extends AppCompatActivity {

    SQLHelper helper;
    Button btnNovo;
    Button btnListar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new SQLHelper(this);

        btnNovo = findViewById(R.id.btnNovo);
        btnListar = findViewById(R.id.btnListar);

        btnNovo.setOnClickListener(v -> {
 Intent intent = new Intent(getApplicationContext(), NovoCarroActivity.class);
  startActivity(intent);
        });

        btnListar.setOnClickListener(v -> {
    Intent intent =  new Intent(getApplicationContext(), ListarCarroActivity.class);
    startActivity(intent);
        });


    }
}