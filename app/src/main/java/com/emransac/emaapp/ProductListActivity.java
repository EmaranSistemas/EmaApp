package com.emransac.emaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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
    TextView txtTienda;

    String sucursal_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
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
        searchView = findViewById(R.id.search_view);

        searchView.clearFocus();

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
        Log.d("tienda: ", sucursal_name);
        Toast.makeText(ProductListActivity.this,"N: "+nombre+" I: "+inventario+" P: "+pedido + "count: "+recyclerView.getAdapter().getItemCount(),Toast.LENGTH_SHORT).show();
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
                                Log.d("SIZE: ", String.valueOf(jsonArray.length()));
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
}

        /*

         //recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        for(int i=0;i<5;i++){
            producto= new Product(Integer.toString(i),"Canela molida para sobres"+i,"","","","https://sibarita.pe/wp-content/uploads/2021/07/SIB008.jpg");
            productArrayList.add(producto);
        }
*/