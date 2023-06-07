package com.emransac.emaapp.Entity;

public class Product {
    String id,nombre,pedido,inventario,stock,img;
    public Product(){
        //empty contructor needed
    }

    public Product(String id,String nombre,String inventario,String pedido, String stock,String img){
        this.id = id;
        this.nombre = nombre;
        this.pedido = pedido;
        this.inventario = inventario;
        this.img = img;
        this.stock = stock;
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getInventario(){
        return inventario;
    }

    public String getPedido(){
        return pedido;
    }

    public String getStock(){
        return stock;
    }

    public String getImg(){
        return img;
    }


    public String setId(){
        return this.id;
    }
    public String setNombre(){
        return this.nombre;
    }
    public String setStock(){
        return this.stock;
    }
    public String setImg(){
        return this.img;
    }
}

