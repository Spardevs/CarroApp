package br.com.senaimt.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.com.senaimt.model.Carro;

public class CarroDAO {
    SQLHelper helper;

    public CarroDAO(Context context){
        helper = new SQLHelper(context);
    }

    public long inserirCarro(Carro carro){

        return 0;
    }

    public Carro getCarroPorId(long id){

        return new Carro();
    }

    public long excluirCarro(long id){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] criterio = {String.valueOf(id)};
        return db.delete("carro", "id=?", criterio);

    }
    public ArrayList<Carro> listarCarros(){
        ArrayList<Carro> carros = new ArrayList<>();
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
        }
        return carros;
    }
}
