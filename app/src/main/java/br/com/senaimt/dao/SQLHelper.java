package br.com.senaimt.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String BANCO_DADOS = "meuCarro";
    private static int VERSAO = 1;

    public SQLHelper(Context context){
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Banco", "iniciando...");
        // Criar as tabelas
       // String sqlInsert = "CREATE TABLE carro(id INTEGER)";
        db.execSQL("CREATE TABLE carro(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "marca TEXT, ano INTEGER, modelo TEXT" +
                ")");
        Log.d("Tabela", "<<<<<<<<<<<CRIADA>>>>>>>>>>>...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
