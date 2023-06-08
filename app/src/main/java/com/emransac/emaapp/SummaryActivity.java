package com.emransac.emaapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emransac.emaapp.Adapters.ProductAdapter;
import com.emransac.emaapp.Adapters.SummaryAdapter;
import com.emransac.emaapp.Entity.Product;
import com.emransac.emaapp.Entity.Summary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Summary summary;
    private SummaryAdapter summaryAdapter;
    public static ArrayList<Summary> SumaryArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);


        for(int i=0;i<5;i++){
            summary= new Summary("https://productosbatan.com/247-medium_default/canela-molida-frasco-x-50-gr.jpg","1","emmel","H.T TOTTUS_PORON AJI COL.PANCA PICANTE FRASCO X 70 GRS ","34","26","2023-05-12");
            SumaryArrayList.add(summary);
        }

        SummaryAdapter adapter = new SummaryAdapter(this, SumaryArrayList);
        adapter.setOnItemClickListener(new SummaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Acciones a realizar cuando se hace clic en un elemento
                Toast.makeText(SummaryActivity.this,SumaryArrayList.get(position).getTienda(), Toast.LENGTH_SHORT).show();



            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_summary);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public void retrieveData(String url){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SumaryArrayList.clear();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String exito = jsonObject.getString("exito");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");
                            if(exito.equals("1")){
                                //Log.d("SIZE: ", String.valueOf(jsonArray.length()));
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id_producto");
                                    String nombre = object.getString("nombre");
                                    String imagen = object.getString("IMAGENES");
                                    Log.d("Retrival "," id: "+id+ "Nombre: "+nombre +"img: "+imagen);
                                    summary= new Summary("",id,nombre,"","","",imagen);
                                    SumaryArrayList.add(summary);
                                    summaryAdapter.notifyDataSetChanged();
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
                Toast.makeText(SummaryActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}