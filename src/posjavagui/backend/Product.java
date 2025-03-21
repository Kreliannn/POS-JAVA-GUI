
package posjavagui.backend;



public class Product {
    private int product_id;
    private String product_name;
    private String product_img;
    private int price;
    private int product_stocks;
    private String product_category;
    
    //paglalagay ng value sa lahat ng variable
    public Product(int product_id, String product_name, String product_img, int price, int product_stocks, String product_category) 
    {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_img = product_img;
        this.price = price;
        this.product_stocks = product_stocks;
        this.product_category = product_category;
    }
    
    public String getName()
    {
        return this.product_name;
    }
    
    public String getImg()
    {
        return this.product_img;
    }
    
    public String getCategory()
    {
        return this.product_category;
    }
    
    public int getId()
    {
        return this.product_id;
    }
    
    public int getPrice()
    {
        return this.price;
    }
    
    public int getStocks()
    {
        return this.product_stocks;
    }
}
