package com.emransac.emaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emransac.emaapp.Adapters.StoreAdapter;
import com.emransac.emaapp.Entity.Stores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MercAppActivity extends AppCompatActivity {
/*
    ListView listView;
    StoreAdapter adapter;
    Stores tienda;
    public static ArrayList<Stores> tiendasArrayList = new ArrayList<>();
    String mostrar_tiendas = "https://emaransac.com/android/mostrar_tiendas.php";

    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merc_app);
/*
        listView = findViewById(R.id.ListaMercados);
        adapter = new StoreAdapter(MercAppActivity.this,tiendasArrayList);
        listView.setAdapter(adapter);
        retrieveData(mostrar_tiendas);
        */
    }

    /*
    public void retrieveData(String url){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                tiendasArrayList.clear();
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String exito = jsonObject.getString("exito");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if(exito.equals("1")){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String nombre = object.getString("nombre");
                            Log.d("data:",nombre);

                            tienda = new Stores(id,nombre);
                            tiendasArrayList.add(tienda);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MercAppActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }*/
}