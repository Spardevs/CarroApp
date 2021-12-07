package br.com.senaimt.carroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.senaimt.dao.SQLHelper;

public class EditarCarroActivity extends AppCompatActivity {

    // 1- Carregar o SQL Helper
    SQLHelper helper;

    TextView txtMarca;
    TextView txtModelo;
    TextView txtAno;

    Button btnEditar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_carro);
        // 2 - Carregar o context
        helper = new SQLHelper(this);
       // Recupera o valor passado da outra activity
        Intent intent = getIntent();
        long id = intent.getLongExtra("ID", 0);

        txtAno = findViewById(R.id.txtAno);
        txtMarca = findViewById(R.id.txtMarca);
        txtModelo = findViewById(R.id.txtModelo);

        btnEditar = findViewById(R.id.btnEditar);
        Log.d("CARRO", "ID DO CARRO " + id);
        //3 - Carregar o modo de leitura
        SQLiteDatabase db =  helper.getReadableDatabase();
        // 4 - realiza consulta
        Cursor cursor = db.rawQuery("SELECT * FROM CARRO WHERE id = "+id, null);
        cursor.moveToFirst();
        String marca = cursor.getString(1);
        int ano = cursor.getInt(2);
        String modelo = cursor.getString(3);
        cursor.close();
        txtMarca.setText(marca);
        txtAno.setText(String.valueOf(ano));
        txtModelo.setText(modelo);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id", id);
                values.put("marca", txtMarca.getText().toString());
                values.put("modelo", txtModelo.getText().toString());
                values.put("ano", txtAno.getText().toString());
             String[] args = {String.valueOf(id)};
             long result = db.update("carro", values, "id=?", args);
                Toast toast;
            if(result != -1){
                 toast = Toast.makeText(getBaseContext(), "Sucesso!", Toast.LENGTH_SHORT);
            }else{
                 toast = Toast.makeText(getBaseContext(), "Erro!", Toast.LENGTH_SHORT);
             }
             toast.show();
              db.close();
              Intent intent = new Intent(getApplicationContext(), ListarCarroActivity.class);
              startActivity(intent);
            }
        });
    }
}