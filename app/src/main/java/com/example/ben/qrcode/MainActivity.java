package com.example.ben.qrcode;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        Button view = (Button) findViewById(R.id.btn_view);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                alert();
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

    public void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Database");
        builder.setPositiveButton("Sales", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this,viewSales.class));
            }
        });
        builder.setNegativeButton("Inventory", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this,viewInventory.class));
            }
        });
        builder.setMessage("Select which database you would like to view.");
        AlertDialog alert1 = builder.create();
        alert1.show();
    }

}
