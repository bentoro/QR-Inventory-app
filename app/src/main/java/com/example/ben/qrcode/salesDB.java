package com.example.ben.qrcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class salesDB extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "sales";
    // Contacts table name
    private static final String TABLE = "sales_table";
    // imgGallerys Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NUM = "number";
    private static final String KEY_NAME = "name";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_COST = "cost";
    private static final String KEY_DATE = "date";

    public salesDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GALLERY_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NUM + " INTEGER, " + KEY_NAME + " TEXT, " + KEY_QUANTITY + " INTEGER," + KEY_COST + " INTEGER,"+ KEY_DATE + " TEXT"+ ")";
        db.execSQL(CREATE_GALLERY_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Creating tables again
        onCreate(db);
    }

    public void addSales(sold sold) {
        //OPENING DATABASE
        SQLiteDatabase db = this.getWritableDatabase();
        //MAPS TABLES COLUMNS
        ContentValues values = new ContentValues();
        values.put(KEY_NUM, sold.getNum());
        values.put(KEY_NAME, sold.getName());
        values.put(KEY_QUANTITY, sold.getQuantity());
        values.put(KEY_COST, sold.getCost());
        values.put(KEY_DATE, sold.getDate());
        // Inserting Row
        db.insert(TABLE, null, values);
        db.close(); // Closing database connection
    }


    public List<sold> getAllSales() {
        List<sold> imgGalleryList = new ArrayList<sold>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                sold sold = new sold();
                sold.setId(Integer.parseInt(cursor.getString(0)));
                sold.setNum(Integer.parseInt(cursor.getString(1)));
                sold.setName(cursor.getString(2));
                sold.setQuantity(Integer.parseInt(cursor.getString(3)));
                sold.setCost(Integer.parseInt(cursor.getString(4)));
                sold.setDate(cursor.getString(5));
                imgGalleryList.add(sold);
            } while (cursor.moveToNext());
        }
        return imgGalleryList;
    }

    public int updateSales(sold sold) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NUM, sold.getNum());
        values.put(KEY_NAME, sold.getName());
        values.put(KEY_QUANTITY, sold.getQuantity());
        values.put(KEY_COST, sold.getCost());
        values.put(KEY_DATE, sold.getDate());
        // updating row
        return db.update(TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(sold.getId())});
    }
    /*public void deleteDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);
        db.close();

    }*/


    public List<sold> search(int productnum){
        SQLiteDatabase db = this.getWritableDatabase();
        List<sold> gallery = getAllSales();
        List<sold> filtered = new ArrayList<sold>();
        int i;
        for (i =0; i<=gallery.size()-1; i++){
            int y = Calendar.getInstance().get(Calendar.YEAR);
            int m = Calendar.getInstance().get(Calendar.MONTH)+1;
            int d = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            if (gallery.get(i).getNum() == productnum){
                filtered.add(new sold(gallery.get(i).getId(),gallery.get(i).getNum(),gallery.get(i).getName(),(gallery.get(i).getQuantity()), gallery.get(i).getCost(),gallery.get(i).getDate()));
            }
        }
        return filtered;
    }
}