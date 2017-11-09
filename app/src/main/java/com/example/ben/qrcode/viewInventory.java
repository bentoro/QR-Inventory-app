package com.example.ben.qrcode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class viewInventory extends AppCompatActivity {
    private int productnum;
    private boolean search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);
        //inventoryDB db = new inventoryDB(this);
        /*db.addProducts(new product(1,1001,"Product 1",100,100));
        db.addProducts(new product(1,1002,"Product 3",100,100));
        db.addProducts(new product(1,1003,"Product 4",100,100));
        db.addProducts(new product(1,1004,"Product 5",100,100));
        db.addProducts(new product(1,1005,"Product 7",100,100));
        db.addProducts(new product(1,1006,"Product 8",100,100));
        db.addProducts(new product(1,1007,"Product 9",100,100));
        db.addProducts(new product(1,1008,"Product 10",100,100));
        db.addProducts(new product(1,1009,"Product 11",100,100));
        db.addProducts(new product(1,1010,"Product 12",100,100));
        db.addProducts(new product(1,1011,"Product 13",100,100));*/
        //db.deleteDB();
        printDb();
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        if(settings.contains("search")){
            productnum=settings.getInt("search",0);
            search = true;

        }
        display();
        addListenerOnButton();

    }

    public void addListenerOnButton() {
        Button back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewInventory.this, MainActivity.class));
                SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();
                editor.commit();
            }
        });
    }

    private void display(){
        ListView list = (ListView) findViewById(R.id.ListView);
        inventoryDB db = new inventoryDB(this);
        List<product> products;
        if(search == true){
            products = db.search(productnum);
        } else {
            products = db.getAllproducts();
        }

        MyCustomAdapter adapter = new MyCustomAdapter(this,products);
        ListView view = (ListView) findViewById(R.id.ListView);
        view.setAdapter(adapter);


    }

    public void printDb(){
        inventoryDB db = new inventoryDB(this);
        List<product> gallery = db.getAllproducts();
        for (product product : gallery) {
            String log = "Id: "+ product.getId() + " ,#: " + product.getNum() + " ,name: " + product.getName() + " quantity: " + product.getQuantity()+" ,cost: " + product.getCost();
            Log.d("products: : ", log);
        }
    }

    public void printDb1(){
        salesDB db = new salesDB(this);
        List<sold> gallery = db.getAllSales();
        for (sold sold : gallery) {
            String log = "Id: "+ sold.getId() + " ,#: " + sold.getNum() + " ,name: " + sold.getName() + " quantity: " + sold.getQuantity()+" ,cost: " + sold.getCost();
            Log.d("products: : ", log);
        }
    }

}
