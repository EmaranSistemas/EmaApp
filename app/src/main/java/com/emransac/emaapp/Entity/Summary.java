package com.emransac.emaapp.Entity;

import com.emransac.emaapp.SummaryActivity;

import java.util.ArrayList;

public class Summary {
    String  id,tienda,producto, inventario,pedido,img,fecha;
    public Summary(SummaryActivity summaryActivity, ArrayList<Summary> sumaryArrayList, SummaryActivity activity, SummaryActivity summaryActivity1){
        //empty contructor needed
    }

    public Summary(String img,String id,String tienda,String producto,String inventario, String pedido,String fecha){
        this.id = id;
        this.tienda = tienda;
        this.producto = producto;
        this.inventario = inventario;
        this.pedido = pedido;
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

    public String getInventario(){
        return inventario;
    }

    public String getPedido(){
        return pedido;
    }

    public String getImg(){
        return img;
    }

    public String getFecha(){
        return fecha;
    }
}
