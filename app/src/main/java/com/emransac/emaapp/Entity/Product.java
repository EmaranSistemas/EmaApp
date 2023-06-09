package com.emransac.emaapp.Entity;

public class Product {
    String id,nombre,pedido,inventario,cod_ref,img,cod_barras;
    public Product(){
        //empty contructor needed
    }

    public Product(String id,String cod_ref,String cod_barras, String nombre,String inventario,String pedido,String img){
        this.id = id;
        this.nombre = nombre;
        this.pedido = pedido;
        this.inventario = inventario;
        this.img = img;
        this.cod_ref = cod_ref;
        this.cod_barras = cod_barras;
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

    public String getCod_ref(){
        return cod_ref;
    }

    public String getCod_barras(){
        return cod_barras;
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
    public String setCod_ref(){
        return this.cod_ref;
    }
    public String setImg(){
        return this.img;
    }
}

