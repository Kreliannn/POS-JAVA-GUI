/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posjavagui.backend;

import posjavagui.backend.dbHelper;
import posjavagui.backend.SoldProduct;
import java.util.List;

/**
 *
 * @author U
 */




public class Transaction {
     private String transaction_id;
     private String date;
     private int total;
     private int cash;
     private int payment_change;
     
     //paglalagay ng value sa lahat ng variable
     public Transaction(String transaction_id, String date, int total, int cash, int payment_change) {
        this.transaction_id = transaction_id;
        this.date = date;
        this.total = total;
        this.cash = cash;
        this.payment_change = payment_change;
    }
     
     // kukunin lahat ng products na naibenta sa transaction
     public List<SoldProduct> getSoldProducts()
     {
         dbHelper myDb = new dbHelper();
         return myDb.getSoldProducts(transaction_id);
     }
     
    

    // Getters and Setters (Optional)

    public String getTransactionId() {
        return transaction_id;
    }

    public String getDate() {
        return date;
    }

    public int getTotal() {
        return total;
    }

    public int getCash() {
        return cash;
    }

    public int getPaymentChange() {
        return payment_change;
    }
}
