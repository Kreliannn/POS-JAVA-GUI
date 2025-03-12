/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posjavagui.backend;
import posjavagui.backend.SoldProduct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author U
 */
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

    // kukunin lahat ng products sa database
    public List<Product> getProducts() {
        
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                // pag seset ng value sa product
                Product product = new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("product_img"),
                    rs.getInt("price"),
                    rs.getInt("product_stocks"),
                    rs.getString("product_category")
                );
                
                // pag add ng products sa products array
                productList.add(product);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList; // i reeturn lahat ng na add na products
    }
    
    //kukunin lahat ng nabentang products sa database based sa transaction id
    public List<SoldProduct> getSoldProducts(String transaction_id) {
        List<SoldProduct> productList = new ArrayList<>();
        String query = "SELECT * FROM soldProduct join products on soldProduct.product_id = products.product_id where soldProduct.transaction_id = " + "'" + transaction_id + "'";
        System.out.println(query);
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                // pag seset ng value sa product
                SoldProduct product = new SoldProduct(
                    rs.getInt("id"),
                    rs.getInt("qty"),
                    rs.getString("product_name"),
                    rs.getInt("price"),
                    rs.getString("product_category")
                );
                
                // pag add ng products sa products array
                productList.add(product);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList; // i reeturn lahat ng na add na products
    }
    
    
    //i uupdate and stocks ng product (restock)
    public void updateStocks(int qty, int id)
    {
        String query = "update products set product_stocks = ? where product_id = ?";
        
        // kunin yung current stocks ng product at bawasan ito based sa quantity na binili ng customer
        int currentStocks = getStocksCount(id);
        int newStocks = currentStocks - qty;

        try{
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, newStocks);
             stmt.setInt(2, id);
             stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //kukunin kung ilan yung stocks and product based sa id nito
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
    
    //mag dadagdag ng product sa menu
    public Boolean addProduct(Product myProduct)
    {
        String query = "insert into products(product_name, product_img, price, product_stocks, product_category) values (?,?,?,?,?)";
        
        try{
             // pag seset up ng product na idadagdag
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setString(1, myProduct.getName());
             stmt.setString(2, myProduct.getImg());
             stmt.setInt(3, myProduct.getPrice());
             stmt.setInt(4, myProduct.getStocks());
             stmt.setString(5, myProduct.getCategory());
             
             // pag execute sa sql query, pag add ng product sa dsatabase
             stmt.executeUpdate();
             return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //i uupdate ang product 
    public boolean updateProduct(int price, int stocks, int id)
    {
         String query = "UPDATE products SET price = ?, product_stocks = ? WHERE product_id = ?";
        
        try{
            // pag seset up ng product na iuupdate
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, price);
             stmt.setInt(2, stocks);
             stmt.setInt(3, id);
             
              // pag execute sa sql query, pag update ng product sa dsatabase
             stmt.executeUpdate();
             return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //dedelete ang product sa menu
    public void removeProduct(int id)
    {
        String query = "delete from products where product_id = ?";
        
        try{
            // pag reremove ng product sa database
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, id);  
             stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //mag dagdag ng account sa cashier log in
    public void addAccount(String username, String password)
    {
        String query = "insert into accounts (username, password) values(?,?)";
        
        try{
            // pag seset up ng account na i dadagdag
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setString(1, username);  
             stmt.setString(2, password);  
             
             // pag execute ng sql qury para i add ang account
             stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //kunin kung ilan na ba ang bilang ng products na binebenta
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
 
    //kunin kung ilan na ang total cashier accounts
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
    
    //kunin kung ilan na ang kabuuang kita ng restaurant
    public int getTotalSales()
    {
        String query = "select sum(total) as totalSales from transactions";
         try{
               PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(); 

                if (rs.next()) {
                    return rs.getInt("totalSales"); 
                }
             
             return 0; // change
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } 
    }
    
    //kunin kung ilang piraso na ang naibbeenta
    public int getItemSold()
    {
        String query = "select sum(qty) as qty from soldProduct";
         try{
               PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(); 

                if (rs.next()) {
                    return rs.getInt("qty"); 
                }
             
             return 0; // change
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } 
    }
    
    // i check kung tama ba ang user login
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
    
    // i store sa database ang mga product na nabiili ng customer
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
    
    // kunin lahat ng transaction data para sa resibo
    public List<Transaction> getTransactions()
    {
        List<Transaction> myTransaction = new ArrayList<>();
        
        String query = "SELECT * FROM transactions";
        System.out.println(query);
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                // pag seset ng value sa transaction
                Transaction transaction = new Transaction(
                    rs.getString("transaction_id"),
                    rs.getString("date"),  
                    rs.getInt("total"),
                    rs.getInt("cash"),
                    rs.getInt("payment_change")
                );
                
                // pag add ng transaction sa array
                myTransaction.add(transaction);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myTransaction; // pag return sa lahat ng transaction sa array
        
    }
    
    // i save ang transaction sa database
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
}
