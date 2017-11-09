package com.example.ben.qrcode;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.Result;

import java.util.Calendar;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private int msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate", "onCreate");
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        // Prints the scan format (qrcode, pdf417 etc.)
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        msg = Integer.parseInt(rawResult.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //mScannerView.resumeCameraPreview(scanner.this);
                startActivity(new Intent(scanner.this,MainActivity.class));
            }
        });
        builder.setNegativeButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert();
            }
        });
        builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                remove();
            }
        });
        builder.setMessage("Product # :" + rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

    }

    public void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("Sales", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                search();
                startActivity(new Intent(scanner.this,viewSales.class));
            }
        });
        builder.setNegativeButton("Inventory", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                search();
                startActivity(new Intent(scanner.this,viewInventory.class));
            }
        });
        builder.setMessage("View Sales database or Inventory");
        AlertDialog alert1 = builder.create();
        alert1.show();
    }

    public void search(){
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("search", msg);
        editor.commit();
    }

    public void remove(){
        System.out.println("msg: " + msg);
        inventoryDB db = new inventoryDB(this);
        salesDB db1 = new salesDB(this);
        List<product> gallery = db.getAllproducts();
        int i;
        for (i =0; i<=gallery.size()-1; i++){
            int y = Calendar.getInstance().get(Calendar.YEAR);
            int m = Calendar.getInstance().get(Calendar.MONTH)+1;
            int d = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            if (gallery.get(i).getNum() == msg){
                db.updateProducts(new product(gallery.get(i).getId(),gallery.get(i).getNum(),gallery.get(i).getName(),(gallery.get(i).getQuantity()-1), gallery.get(i).getCost()));
                db1.addSales(new sold(1,gallery.get(i).getNum(),gallery.get(i).getName(),1,gallery.get(i).getCost(), y+"/"+m+"/"+d));
            }

        }
        startActivity(new Intent(scanner.this,viewInventory.class));
    }

}
