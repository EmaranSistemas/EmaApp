package com.emransac.emaapp.Entity;

import com.emransac.emaapp.SummaryActivity;

import java.util.ArrayList;

public class Summary {
    String  id,tienda,producto, stock,diferencia,img,fecha;
    public Summary(SummaryActivity summaryActivity, ArrayList<Summary> sumaryArrayList, SummaryActivity activity, SummaryActivity summaryActivity1){
        //empty contructor needed
    }

    public Summary(String img,String id,String tienda,String producto,String stock, String diferencia,String fecha){
        this.id = id;
        this.tienda = tienda;
        this.producto = producto;
        this.stock = stock;
        this.diferencia = diferencia;
        this.img = img;
        this.fecha = fecha;
    }

    public String getId(){
        return id;
    }

    public String getTienda(){
        return tienda;
    }

    public String getProducto(){
        return producto;
    }

    public String getStock(){
        return stock;
    }

    public String getDiferencia(){
        return diferencia;
    }

    public String getImg(){
        return img;
    }

    public String getFecha(){
        return fecha;
    }
}
