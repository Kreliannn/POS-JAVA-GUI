/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posjavagui;

/**
 *
 * @author U
 */




public class Transaction {
     private int id;
     private String transaction_id;
     private int date;
     private int total;
     private int cash;
     private int payment_change;
     
     public Transaction(int id, String transaction_id, int date, int total, int cash, int payment_change) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.date = date;
        this.total = total;
        this.cash = cash;
        this.payment_change = payment_change;
    }
     
    

    // Getters and Setters (Optional)
    public int getId() {
        return id;
    }

    public String getTransactionId() {
        return transaction_id;
    }

    public int getDate() {
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
