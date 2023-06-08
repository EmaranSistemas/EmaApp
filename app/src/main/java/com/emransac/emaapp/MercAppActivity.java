package com.emransac.emaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emransac.emaapp.Adapters.StoreAdapter;
import com.emransac.emaapp.Adapters.ViewPagerAdapter;
import com.emransac.emaapp.Entity.Stores;
import com.emransac.emaapp.Ubication.GpsTracker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MercAppActivity extends AppCompatActivity {
    ListView listView;
    StoreAdapter adapter;

    public static ArrayList<Stores> TiendasArraylist = new ArrayList<>();
    String url = "https://emaransac.com/android/mostrar_tiendas.php";
    Stores stores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merc_app);

        /*
        GPS TRACKER
         */

        listView = findViewById(R.id.myListView);
        adapter = new StoreAdapter(this, TiendasArraylist);
        listView.setAdapter(adapter);


        FloatingActionButton btn1 = findViewById(R.id.summaryList);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MercAppActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                if (TiendasArraylist.get(position).getName().equals("FRANCO SUPERMERCADOS")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                    CharSequence[] dialogItem = {"» Emmel", "» Lambramani", "» Kosto Tritan", "» Kosto Mayorista"};
                    builder.setTitle(TiendasArraylist.get(position).getName());
                    builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            switch (i) {
                                case 0:
                                    //Toast.makeText(MercAppActivity.this,"Emmel", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent.putExtra("sucursal", dialogItem[0].toString());
                                    intent.putExtra("id", 0);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent1.putExtra("sucursal",dialogItem[1].toString());
                                    intent1.putExtra("id", 1);
                                    startActivity(intent1);
                                    break;
                                case 2:
                                    Intent intent2 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent2.putExtra("sucursal",dialogItem[2].toString());
                                    intent2.putExtra("id",2);
                                    startActivity(intent2);
                                    break;
                                case 3:
                                    Intent intent3 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent3.putExtra("sucursal",dialogItem[3].toString());
                                    intent3.putExtra("id",3);
                                    startActivity(intent3);
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
                // end if
                if (TiendasArraylist.get(position).getName().equals("SUPERMERCADOS PERUANOS")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                    CharSequence[] dialogItem = {"» Plaza Vea - Ejército", "» Plaza Vea - La Marina"};
                    builder.setTitle(TiendasArraylist.get(position).getName());
                    builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            switch (i) {
                                case 0:
                                    //Toast.makeText(MercAppActivity.this,"Emmel", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent.putExtra("sucursal",dialogItem[0].toString());
                                    intent.putExtra("id",4);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent1.putExtra("sucursal",dialogItem[1].toString());
                                    intent1.putExtra("id",4);
                                    startActivity(intent1);
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
                if (TiendasArraylist.get(position).getName().equals("HIPERMERCADOS TOTTUS")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                    CharSequence[] dialogItem = {"» Tottus - Ejército", "» Tottus - Porrongoche", "» Tottus - Parra"};
                    builder.setTitle(TiendasArraylist.get(position).getName());
                    builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            switch (i) {
                                case 0:
                                    //Toast.makeText(MercAppActivity.this,"Emmel", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent.putExtra("sucursal",dialogItem[0].toString());
                                    intent.putExtra("id",5);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent1.putExtra("sucursal",dialogItem[1].toString());
                                    intent1.putExtra("id",5);
                                    startActivity(intent1);
                                    break;
                                case 2:
                                    Intent intent2 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent2.putExtra("sucursal",dialogItem[2].toString());
                                    intent2.putExtra("id",5);
                                    startActivity(intent2);
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
                if (TiendasArraylist.get(position).getName().equals("CENCOSUD RETAIL")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                    CharSequence[] dialogItem = {"» Metro - Aviación", "» Metro - Ejército", "» Metro - Lambramani", "» Metro - Hunter"};
                    builder.setTitle(TiendasArraylist.get(position).getName());
                    builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            switch (i) {
                                case 0:
                                    //Toast.makeText(MercAppActivity.this,"Emmel", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent.putExtra("sucursal",dialogItem[0].toString());
                                    intent.putExtra("id",6);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent1.putExtra("sucursal",dialogItem[1].toString());
                                    intent1.putExtra("id",6);
                                    startActivity(intent1);
                                    break;
                                case 2:
                                    Intent intent2 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent2.putExtra("sucursal",dialogItem[2].toString());
                                    intent2.putExtra("id",6);
                                    startActivity(intent2);
                                    break;
                                case 3:
                                    Intent intent3 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent3.putExtra("sucursal",dialogItem[3].toString());
                                    intent3.putExtra("id",6);
                                    startActivity(intent3);
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
                if (TiendasArraylist.get(position).getName().equals("R-INTERNACIONALES - EL SUPER")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                    CharSequence[] dialogItem = {"» Super - Pierola", "» Super - Portal"};
                    builder.setTitle(TiendasArraylist.get(position).getName());
                    builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            switch (i) {
                                case 0:
                                    //Toast.makeText(MercAppActivity.this,"Emmel", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent.putExtra("sucursal",dialogItem[0].toString());
                                    intent.putExtra("id",7);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(MercAppActivity.this, ProductListActivity.class);
                                    intent1.putExtra("sucursal",dialogItem[1].toString());
                                    intent1.putExtra("id",7);
                                    startActivity(intent1);
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
                //end if
            }

        });
        retrieveData();
    }


    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        TiendasArraylist.clear();
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
                                    TiendasArraylist.add(stores);
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