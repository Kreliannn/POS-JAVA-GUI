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
                    rs.getInt("product_stocks")  
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    public Boolean addProduct(Product myProduct)
    {
        String query = "insert into products(product_name, product_img, price, product_stocks) values (?,?,?,?)";
        
        try{
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setString(1, myProduct.getName());
             stmt.setString(2, myProduct.getImg());
             stmt.setInt(3, myProduct.getPrice());
             stmt.setInt(4, myProduct.getStocks());
             stmt.executeUpdate();
             return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

