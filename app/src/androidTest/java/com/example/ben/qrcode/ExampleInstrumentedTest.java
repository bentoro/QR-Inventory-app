package com.example.ben.qrcode;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        inventoryDB db = new inventoryDB(appContext);

        int productnum = 3002;
        String productname = "Unit Test 1";
        int quantity = 100;
        int cost = 1000;

        //db.addProducts(new product(1,productnum,productname,quantity,cost));

        List<product> products = db.search(productnum);
        for(int i = 0; i<=products.size()-1;i++){
            assertEquals(productnum, products.get(i).getNum());
            assertEquals(productname, products.get(i).getName());
            assertEquals(quantity, products.get(i).getQuantity());
            assertEquals(cost, products.get(i).getCost());
        }
        salesDB db1 = new salesDB(appContext);
        int i;
        for (i =0; i<=products.size()-1; i++){
            int y = Calendar.getInstance().get(Calendar.YEAR);
            int m = Calendar.getInstance().get(Calendar.MONTH)+1;
            int d = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            if (products.get(i).getNum() == productnum){
                db.updateProducts(new product(products.get(i).getId(),products.get(i).getNum(),products.get(i).getName(),(products.get(i).getQuantity()-1), products.get(i).getCost()));
                db1.addSales(new sold(1,products.get(i).getNum(),products.get(i).getName(),1,products.get(i).getCost(), y+"/"+m+"/"+d));
            }

        }
        products = db.search(productnum);
        for(i = 0; i<=products.size()-1;i++){
            assertEquals(productnum, products.get(i).getNum());
            assertEquals(productname, products.get(i).getName());
            assertEquals((99), products.get(i).getQuantity());
            assertEquals(cost, products.get(i).getCost());
        }

        List<sold> sales = db1.search(productnum);
        for(i = 0; i<=sales.size()-1;i++){
            assertEquals(productnum, sales.get(i).getNum());
            assertEquals(productname, sales.get(i).getName());
            assertEquals(1, sales.get(i).getQuantity());
            assertEquals(cost, sales.get(i).getCost());


        }
        products = db.search(productnum);
        db.updateProducts(new product(products.get(0).getId(),products.get(0).getNum(),products.get(0).getName(),(quantity), products.get(0).getCost()));



    }
}
