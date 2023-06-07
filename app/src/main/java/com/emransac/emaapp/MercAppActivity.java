package com.emransac.emaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    ListView listView;
    StoreAdapter adapter;
    public static ArrayList<Stores> employeeArrayList = new ArrayList<>();
    String url = "https://emaransac.com/android/mostrar_tiendas.php";
    Stores stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merc_app);
        listView = findViewById(R.id.myListView);
        adapter = new StoreAdapter(this, employeeArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"Emmel", "Lambramani", "Kosto Tritan", "Kosto Mayorista"};
                builder.setTitle(employeeArrayList.get(position).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                //startActivity(new Intent(getApplicationContext(),detalles.class.putExtra("position",position));
                                break;
                            case 1:
                                //startActivity(new Intent(getApplicationContext(),editar.class).putExtra("position",position));
                                break;
                            case 2:
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        retrieveData();
    }


    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        employeeArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String exito = jsonObject.getString("exito");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");
                            if(exito.equals("1")){
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id");
                                    String nombre = object.getString("nombre");
                                    stores = new Stores(id,nombre);
                                    employeeArrayList.add(stores);
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
    }


}