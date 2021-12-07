package br.com.senaimt.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.senaimt.carroapp.ListarCarroActivity;
import br.com.senaimt.carroapp.R;
import br.com.senaimt.dao.CarroDAO;
import br.com.senaimt.model.Carro;

public class CarroAdapter extends BaseAdapter {

    ArrayList<Carro> carros;
    Context context;

    public CarroAdapter(ArrayList<Carro> carros, Context context) {
        this.carros = carros;
        this.context = context;
    }

    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int position) {
        return carros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return carros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        }
        TextView campoTexto = convertView.findViewById(R.id.texto_linha);
   Carro carro = (Carro) getItem(position);
   campoTexto.setText(carro.getMarca() + " | " + carro.getModelo() +  " | " + carro.getAno());

        ImageView btnExcluir = convertView.findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CarroAdapter", "Clicou no botão excluir." + carro.getId());
                CarroDAO dao = new CarroDAO(parent.getContext());
                long result = dao.excluirCarro(carro.getId());
                Toast toast;
                if(result != -1){
                 toast = Toast.makeText(parent.getContext(), "Sucesso!", Toast.LENGTH_SHORT);
                 carros.clear();
                 carros = dao.listarCarros();
                 notifyDataSetChanged();
              }else{
              toast = Toast.makeText(parent.getContext(), "Deu erro!", Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });
        return convertView;
    }


}
