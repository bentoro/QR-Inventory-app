package com.example.ben.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

    }

    public void addListenerOnButton() {
        Button create = (Button) findViewById(R.id.btn_create);
        create.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,create.class));

            }
        });
        Button inv = (Button) findViewById(R.id.btn_inv);
        inv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,viewSales.class));
            }
        });
        Button sales = (Button) findViewById(R.id.btn_sales);
        sales.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,viewInventory.class));
            }
        });
        Button scan = (Button) findViewById(R.id.btn_scan);
        scan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,scanner.class));
            }
        });
    }

}
