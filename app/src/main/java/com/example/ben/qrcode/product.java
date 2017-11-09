package com.example.ben.qrcode;

/**
 * Created by Ben on 2017-11-07.
 */

public class product {
    private int productnum;
    private String productname;
    private int quantity;
    private int cost;
    private int id;

    public product(){

    }

    public product(int id, int productnum, String productname, int quantity, int cost){
        this.id = id;
        this.productnum = productnum;
        this.productname = productname;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getId(){
        return this.id;
    }

    public int getNum(){
        return this.productnum;
    }

    public String getName(){
        return this.productname;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public int getCost(){
        return this.cost;
    }

    public void setNum(int productnum){
        this.productnum = productnum;
    }

    public void setName(String productname){
        this.productname = productname;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public void setId(int id){
        this.id = id;
    }

    public String toString(){
        return  "   " + productnum + "         " + productname + "       " + quantity + "    " + cost;
    }
}
