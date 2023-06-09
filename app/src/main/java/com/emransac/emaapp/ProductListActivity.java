package com.emransac.emaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emransac.emaapp.Adapters.ProductAdapter;
import com.emransac.emaapp.Entity.Product;
import com.emransac.emaapp.Ubication.GpsTracker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity implements ProductAdapter.itemClickListener, ProductAdapter.TextInputListener {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private Product producto;

    private SearchView searchView;
    public static ArrayList<Product> productArrayList = new ArrayList<>();
    ArrayList<String> sucursales = new ArrayList<>();
    TextView txtTienda,txt_count,txt_total;

    String sucursal_name;
    int suma;

    GpsTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        try { //Request Permission if not permitted
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // supermercados franco productos-sucursales 0 - 3
        sucursales.add("https://emaransac.com/android/productos_emmel.php");
        sucursales.add("https://emaransac.com/android/productos_lambramani.php");
        sucursales.add("https://emaransac.com/android/productos_ktristan.php");
        sucursales.add("https://emaransac.com/android/productos_kmayorista.php");
        // supermercados peruanos plazavea 4
        sucursales.add("https://emaransac.com/android/productos_plazavea.php");
        // tottus suppermercados 5
        sucursales.add("https://emaransac.com/android/productos_tottus.php");
        // metros uspermercados 6
        sucursales.add("https://emaransac.com/android/productos_metro.php");
        // metro 7
        sucursales.add("https://emaransac.com/android/productos_super.php");

        txtTienda = findViewById(R.id.title);
        txt_count = findViewById(R.id.counter);
        txt_total = findViewById(R.id.counter_now);

        searchView = findViewById(R.id.search_view);
        searchView.clearFocus();

        FloatingActionButton btn1 = findViewById(R.id.btn1);
        FloatingActionButton btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDetalle();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanner();
            }
        });


        /*

        aqui va ese codigo raro
         */


        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            //retrieveProduct(url2);
            String sucursal = bundle.getString("sucursal");
            String sucursal_ = sucursal.replace("»", "");
            Toast.makeText(ProductListActivity.this,sucursal_,Toast.LENGTH_LONG).show();
            String strSinEspacios = sucursal_.replaceAll("\\s+", "");
            sucursal_name = strSinEspacios;
            //productArrayList.clear();
            if(strSinEspacios.equals("Emmel")){
                retrieveData(sucursales.get(0));
            }
            if(strSinEspacios.equals("Lambramani")){
                retrieveData(sucursales.get(1));
            }
            if(strSinEspacios.equals("KostoTritan")){
                retrieveData(sucursales.get(2));
            }
            if(strSinEspacios.equals("KostoMayorista")){
                retrieveData(sucursales.get(3));
            }
            if(strSinEspacios.equals("PlazaVea-Ejército")){
                retrieveData(sucursales.get(4));
            }
            if(strSinEspacios.equals("PlazaVea-LaMarina")){
                retrieveData(sucursales.get(4));
            }
            if(strSinEspacios.equals("Tottus-Ejército")){
                retrieveData(sucursales.get(5));
            }
            if(strSinEspacios.equals("Tottus-Porrongoche")){
                retrieveData(sucursales.get(5));
            }
            if(strSinEspacios.equals("Tottus-Parra")){
                retrieveData(sucursales.get(5));
            }
            if(strSinEspacios.equals("Metro-Aviación")){
                retrieveData(sucursales.get(6));
            }
            if(strSinEspacios.equals("Metro-Ejército")){
                retrieveData(sucursales.get(6));
            }
            if(strSinEspacios.equals("Metro-Lambramani")){
                retrieveData(sucursales.get(6));
            }
            if(strSinEspacios.equals("Metro-Hunter")){
                retrieveData(sucursales.get(6));
            }
            if(strSinEspacios.equals("Super-Pierola")){
                retrieveData(sucursales.get(7));
            }
            if(strSinEspacios.equals("Super-Portal")){
                retrieveData(sucursales.get(7));
            }
            else {
                Log.d("Error el seleccionar la tienda ",strSinEspacios);
            }
            txtTienda.setText("Productos "+sucursal_);

            recyclerView = findViewById(R.id.recycler_view);
            adapter = new ProductAdapter(this,productArrayList,this,this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(ProductListActivity.this,"It is empty",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onItemClick(int position) {
        //Toast.makeText(MainActivity.this,"Item click"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTextInputClicked(String nombre, String inventario, String pedido) {
        try { //Request Permission if not permitted
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("tienda: ", sucursal_name);
        String a_lat = "0";
        String a_lon = "0";
        a_lat = getLocs(1);
        a_lon = getLocs(2);
        String count = suma+1-recyclerView.getAdapter().getItemCount()+"";

        //Toast.makeText(this, "Lat: "+a_lat+" Log: "+a_lon, Toast.LENGTH_SHORT).show();
        //Toast.makeText(ProductListActivity.this," I: "+inventario+" P: "+pedido + "count: "+recyclerView.getAdapter().getItemCount()+"Lat: "+a_lat+" Log: "+a_lon,Toast.LENGTH_SHORT).show();
        Log.d("INSERTAR","Suc: "+sucursal_name+" "+"Prod: "+nombre+" Inv: "+inventario+" Ped: "+pedido +" Lat: "+a_lat+" Log: "+a_lon);
        txt_count.setText(count);
    }

    public void retrieveData(String url){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        productArrayList.clear();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String exito = jsonObject.getString("exito");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");
                            if(exito.equals("1")){
                                //Log.d("SIZE: ", String.valueOf(jsonArray.length()));
                                txt_total.setText(String.valueOf(jsonArray.length()));
                                suma = jsonArray.length();
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id_producto");
                                    String nombre = object.getString("nombre");
                                    String imagen = object.getString("IMAGENES");
                                    Log.d("Retrival "," id: "+id+ "Nombre: "+nombre +"img: "+imagen);
                                    producto= new Product(id,nombre,"","","",imagen);
                                    productArrayList.add(producto);
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
                Toast.makeText(ProductListActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void mostrarDetalle() {
        String a_lat = "0";
        String a_lon = "0";
        a_lat = getLocs(1);
        a_lon = getLocs(2);
        Toast.makeText(this, "Lat: "+a_lat+" Log: "+a_lon, Toast.LENGTH_SHORT).show();
    }
    public void scanner() {
        Toast.makeText(this, "Este es el scanner", Toast.LENGTH_SHORT).show();
    }

    public String getLocs(int ID) { //Get Current Lat and Lon 1=lat, 2=lon
        String asd_lat = "";
        String asd_lon = "";
        gpsTracker = new GpsTracker(ProductListActivity.this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            asd_lat = String.valueOf(latitude);
            asd_lon = String.valueOf(longitude);
        } else {
            gpsTracker.showSettingsAlert();
        }
        if (ID == 1) {
            return asd_lat;
        } else if (ID == 2) {
            return asd_lon;
        } else {
            return "0";
        }
    }
}

        /*

         //recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        for(int i=0;i<5;i++){
            producto= new Product(Integer.toString(i),"Canela molida para sobres"+i,"","","","https://sibarita.pe/wp-content/uploads/2021/07/SIB008.jpg");
            productArrayList.add(producto);
        }
*/