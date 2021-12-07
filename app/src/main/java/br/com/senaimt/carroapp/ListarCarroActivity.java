package br.com.senaimt.carroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.senaimt.adapters.CarroAdapter;
import br.com.senaimt.dao.SQLHelper;
import br.com.senaimt.model.Carro;

public class ListarCarroActivity extends AppCompatActivity {

    CarroAdapter carroAdapter;
    ListView listView;
    ArrayList<Carro> carros;
    SQLHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_carro);

        helper = new SQLHelper(this);
        carros = listarCarros();
        carroAdapter = new CarroAdapter(carros, this);
        listView = findViewById(R.id.listView);

        listView.setAdapter(carroAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditarCarroActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

    }

    public ArrayList<Carro> listarCarros(){
      carros = new ArrayList<>();
      SQLiteDatabase db = helper.getReadableDatabase();
      Cursor cursor = db.rawQuery("SELECT * FROM carro", null);
      cursor.moveToFirst();

      for(int a = 0; a < cursor.getCount(); a++) {
          int id = cursor.getInt(0);
          String marca = cursor.getString(1);
          int ano = cursor.getInt(2);
          String modelo = cursor.getString(3);
          Carro carro = new Carro(id, marca, ano, modelo);
          carros.add(carro);
          cursor.moveToNext();
          Log.d("CARROOOOO ", ""+ id + " - "+ marca + " - " +  modelo);
      }

       return carros;
    }


}