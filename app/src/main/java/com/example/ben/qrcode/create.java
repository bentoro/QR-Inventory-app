package com.example.ben.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        Button create = (Button) findViewById(R.id.btn_create);
        create.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addnew();
                printDb();
                startActivity(new Intent(create.this,MainActivity.class));
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(create.this,MainActivity.class));
            }
        });

    }

    public void addnew(){
        TextView num = (TextView) findViewById(R.id.number);
        String nr = num.getText().toString();
        int number = Integer.parseInt(nr);
        TextView name = (TextView) findViewById(R.id.name);
        String nm = name.getText().toString();
        TextView quantity = (TextView) findViewById(R.id.quantity);
        String quant = quantity.getText().toString();
        int qt = Integer.parseInt(quant);
        TextView cost = (TextView) findViewById(R.id.cost);
        String cst = cost.getText().toString();
        int ct = Integer.parseInt(cst);
        inventoryDB db = new inventoryDB(this);
        String co = cost.getText().toString();

        db.addProducts(new product(1,number,nm,qt,ct));
    }

    public void printDb(){
        inventoryDB db = new inventoryDB(this);
        List<product> gallery = db.getAllproducts();
        for (product product : gallery) {
            String log = "Id: "+ product.getId() + " ,#: " + product.getNum() + " ,name: " + product.getName() + " quantity: " + product.getQuantity()+" ,cost: " + product.getCost();
            Log.d("products: : ", log);
        }
    }
}
