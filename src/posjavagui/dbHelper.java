package posjavagui;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class dbHelper {
    private Connection conn;

    // Constructor - Establishes Connection
    public dbHelper() {
        try {
            conn = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/pos_schema",
            "root",
            "impoyski0501"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all products
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("product_img"),
                    rs.getInt("price"),
                    rs.getInt("product_stocks"),
                    rs.getString("product_category")
                );
                
                
                productList.add(product);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    public List<SoldProduct> getSoldProducts(String transaction_id) {
        List<SoldProduct> productList = new ArrayList<>();
        String query = "SELECT * FROM soldProduct join products on soldProduct.product_id = products.product_id where soldProduct.transaction_id = " + "'" + transaction_id + "'";
        System.out.println(query);
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                SoldProduct product = new SoldProduct(
                    rs.getInt("id"),
                    rs.getInt("qty"),
                    rs.getString("product_name"),
                    rs.getInt("price"),
                    rs.getString("product_category")
                );
                
                
                productList.add(product);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    
    public void updateStocks(int qty, int id)
    {
        String query = "update products set product_stocks = ? where product_id = ?";
        
        int currentStocks = getStocksCount(id);
        int newStocks = currentStocks - qty;
        
        System.out.println("current: " + currentStocks);
        System.out.println("newStocks: " + newStocks); 
        
        try{
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, newStocks);
             stmt.setInt(2, id);
             stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getStocksCount(int id)
    {
        String query = "select * from products where product_id = " + "'" + id + "'";
         try{
               PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(); 

                if (rs.next()) {
                    return rs.getInt("product_stocks"); 
                }
             
             return 0; // change
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } 
    }
    
    
    public Boolean addProduct(Product myProduct)
    {
        String query = "insert into products(product_name, product_img, price, product_stocks, product_category) values (?,?,?,?,?)";
        
        try{
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setString(1, myProduct.getName());
             stmt.setString(2, myProduct.getImg());
             stmt.setInt(3, myProduct.getPrice());
             stmt.setInt(4, myProduct.getStocks());
             stmt.setString(5, myProduct.getCategory());
             stmt.executeUpdate();
             return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean updateProduct(int price, int stocks, int id)
    {
         String query = "UPDATE products SET price = ?, product_stocks = ? WHERE product_id = ?";
        
        try{
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, price);
             stmt.setInt(2, stocks);
             stmt.setInt(3, id);
             stmt.executeUpdate();
             return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void removeProduct(int id)
    {
        String query = "delete from products where product_id = ?";
        
        try{
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, id);  
             stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addAccount(String username, String password)
    {
        String query = "insert into accounts (username, password) values(?,?)";
        
        try{
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setString(1, username);  
             stmt.setString(2, password);  
             stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getProductsCount()
    {
        String query = "select count(product_id) as productCount from products";
         try{
               PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(); 

                if (rs.next()) {
                    return rs.getInt("productCount"); 
                }
             
             return 0; // change
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } 
    }
    
    public int getItemSold()
    {
        return 0;
    }
    
    public int getAccounts()
    {
      String query = "select count(account_id) as accountCount from accounts";
         try{
               PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(); 

                if (rs.next()) {
                    return rs.getInt("accountCount"); 
                }
             
             return 0; // change
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } 
    }
    
    public int getTotalSales()
    {
        return 0;
    }
    
    
    public boolean checkAccountExist(String username, String password)
    {
        String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a record exists, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public void insertSoldProduct(int qty, int product_id, String transaction_id)
    {
        String query = "insert into soldProduct (qty, product_id, transaction_id) values(?,?,?)";
        
        try{
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, qty);  
             stmt.setInt(2, product_id);  
             stmt.setString(3, transaction_id); 
             stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addTransaction(String transaction_id, String date, int total, int cash, int change)
    {
        String query = "insert into transactions (transaction_id, date, total, cash, payment_change) values(?,?,?,?,?)";
        
        try{
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setString(1, transaction_id);  
             stmt.setString(2, date);
             stmt.setInt(3, total);  
             stmt.setInt(4, cash); 
             stmt.setInt(5, change);  
             stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    

    // Close connection (Optional)
    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

