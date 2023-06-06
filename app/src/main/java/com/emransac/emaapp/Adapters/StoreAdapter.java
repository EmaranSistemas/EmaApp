package com.emransac.emaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.emransac.emaapp.Entity.Stores;
import com.emransac.emaapp.R;

import java.util.List;

public class StoreAdapter  extends ArrayAdapter<Stores> {

    Context context;
    List<Stores> StoreArray;

    //Nuestro constructor recibe objetos de tipo lista de tiendas
    public StoreAdapter(@NonNull Context context, List<Stores> StoreArray) {
        super(context, R.layout.store_list);
        this.context = context;
        this.StoreArray = StoreArray;
    }

    //Nuestra funcion inflater para enviar valores desde esta la base de datos a la vista .xml
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list,null,true);

        TextView tvID = view.findViewById(R.id.txt_store_id);
        TextView tvName = view.findViewById(R.id.txt_store_name);

        tvID.setText(StoreArray.get(position).getId());
        tvName.setText(StoreArray.get(position).getName());

        return view;
    }
}
