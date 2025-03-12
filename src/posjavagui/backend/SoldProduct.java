/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posjavagui.backend;

/**
 *
 * @author U
 */
public class SoldProduct {
    private int id;
    private int qty;
    private String name;
    private int price;
    private String category;
    private int total;
    
    //pag lalagay ng value sa lahat ng variable
    public SoldProduct(int id, int qty,  String name, int price, String category) {
        this.id = id;
        this.qty = qty;
        this.name = name;
        this.price = price;
        this.category = category;
        this.total = price * qty;
    }

    // Getters and Setters (Optional)
    public int getId() {
        return id;
    }

    public int getQty() {
        return qty;
    }

    public int getTotal() {
        return total;
    }

  

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
