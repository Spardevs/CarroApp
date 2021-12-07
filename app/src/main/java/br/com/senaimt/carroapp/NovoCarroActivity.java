package br.com.senaimt.carroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.senaimt.dao.SQLHelper;

public class NovoCarroActivity extends AppCompatActivity {

    Button btnSalvar;
    EditText txtMarca;
    EditText txtModelo;
    EditText txtAno;

    SQLHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_carro);

        helper = new SQLHelper(this);

        btnSalvar = findViewById(R.id.btnEditar);
        txtMarca = findViewById(R.id.txtMarca);
        txtModelo = findViewById(R.id.txtModelo);
        txtAno = findViewById(R.id.txtAno);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put("marca", txtMarca.getText().toString());
                valores.put("modelo", txtModelo.getText().toString());
                valores.put("ano", txtAno.getText().toString());

       long resultado =  db.insert("carro", null, valores);
        if(resultado != -1) {
            txtMarca.getText().clear();
            txtModelo.getText().clear();
            txtAno.getText().clear();
            Toast.makeText(getBaseContext(), "Sucesso", Toast.LENGTH_SHORT).show();
           }else{
            Toast.makeText(getBaseContext(), "Erro", Toast.LENGTH_SHORT).show();
           }
        }
      });
    }
}