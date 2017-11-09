package com.example.ben.qrcode;

public class sold {
    private int productnum;
    private String productname;
    private int quantity;
    private int cost;
    private String date;
    private int id;

    public sold(){

    }

    public sold(int id, int productnum, String productname, int quantity, int cost,  String date){
        this.id = id;
        this.productnum = productnum;
        this.productname = productname;
        this.quantity = quantity;
        this.cost = cost;
        this.date = date;
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

    public String getDate(){
        return this.date;
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

    public void setDate(String date){
        this.date = date;
    }

    public void setId(int id){
        this.id = id;
    }

    public String toString(){
        return  "   " + productnum + "          " + productname + "       " + quantity + "                 " + cost + "            " + date;
    }
}