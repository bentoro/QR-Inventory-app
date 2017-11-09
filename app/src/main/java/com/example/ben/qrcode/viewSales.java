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

public class viewSales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int productnum;
        boolean search = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sales);
        salesDB db = new salesDB(this);
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

        printDb();
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        if(settings.contains("search")){
            productnum=settings.getInt("search",0);
            search = true;

        }
        display();
        addListenerOnButton();
        display();
    }

    public void addListenerOnButton() {
        Button back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewSales.this, MainActivity.class));
                SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();
                editor.commit();
            }
        });
    }

    private void display() {
        ListView list = (ListView) findViewById(R.id.ListView);
        salesDB db = new salesDB(this);
        List<sold> sales = db.getAllSales();
        SalesAdapter adapter = new SalesAdapter(this, sales);
        ListView view = (ListView) findViewById(R.id.ListView);
        view.setAdapter(adapter);
    }

    public void printDb() {
        salesDB db = new salesDB(this);
        List<sold> gallery = db.getAllSales();
        for (sold sold : gallery) {
            String log = "Id: " + sold.getId() + " ,#: " + sold.getNum() + " ,name: " + sold.getName() + " quantity: " + sold.getQuantity() + " ,cost: " + sold.getCost();
            Log.d("products: : ", log);
        }
    }
}
