package com.example.ben.qrcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class inventoryDB extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "inventory";
    // Contacts table name
    private static final String TABLE = "inventory_table";
    // imgGallerys Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NUM = "number";
    private static final String KEY_NAME = "name";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_COST = "cost";

    public inventoryDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GALLERY_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NUM + " INTEGER, " + KEY_NAME + " TEXT, " + KEY_QUANTITY + " INTEGER," + KEY_COST + " INTEGER" + ")";
        db.execSQL(CREATE_GALLERY_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Creating tables again
        onCreate(db);
    }
    public void addProducts(product product) {
        //OPENING DATABASE
        SQLiteDatabase db = this.getWritableDatabase();
        //MAPS TABLES COLUMNS
        ContentValues values = new ContentValues();
        values.put(KEY_NUM, product.getNum());
        values.put(KEY_NAME, product.getName());
        values.put(KEY_QUANTITY, product.getQuantity());
        values.put(KEY_COST, product.getCost());
        // Inserting Row
        db.insert(TABLE, null, values);
        db.close(); // Closing database connection
    }

    public List<product> getAllproducts() {
        List<product> imgGalleryList = new ArrayList<product>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                product product = new product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setNum(Integer.parseInt(cursor.getString(1)));
                product.setName(cursor.getString(2));
                product.setQuantity(Integer.parseInt(cursor.getString(3)));
                product.setCost(Integer.parseInt(cursor.getString(4)));
                imgGalleryList.add(product);
            } while (cursor.moveToNext());
        }
        return imgGalleryList;
    }

    public int updateProducts(product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NUM, product.getNum());
        values.put(KEY_NAME, product.getName());
        values.put(KEY_QUANTITY, product.getQuantity());
        values.put(KEY_COST, product.getCost());
        // updating row
        return db.update(TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
    }
    public void deleteDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        db.close();

    }

    public List<product> search(int productnum){
        SQLiteDatabase db = this.getWritableDatabase();
        List<product> gallery = getAllproducts();
        List<product> filtered = new ArrayList<product>();
        int i;
        for (i =0; i<=gallery.size()-1; i++){
            int y = Calendar.getInstance().get(Calendar.YEAR);
            int m = Calendar.getInstance().get(Calendar.MONTH)+1;
            int d = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            if (gallery.get(i).getNum() == productnum){
                filtered.add(new product(gallery.get(i).getId(),gallery.get(i).getNum(),gallery.get(i).getName(),(gallery.get(i).getQuantity()), gallery.get(i).getCost()));
            }
        }
        return filtered;
    }
}