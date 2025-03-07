package posjavagui;
import java.sql.*;
import java.util.*;

public class PosJavaGui {

  
    public static void main(String[] args) {
        /*
        try{
             Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pos_schema",
                "root",
                "impoyski0501"
            );
             
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from products");
            
            while(resultSet.next())
            {
                System.out.println("product_name: " + resultSet.getString("product_name"));
                System.out.println("product_price: " + resultSet.getInt("price"));
                System.out.println("product_stock: " + resultSet.getInt("product_stocks"));
            }
             
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        
        dbHelper db = new dbHelper();
        
        List<Product> products = db.getProducts();

        for (Product p : products) {
            System.out.println(p.getName());
        }
    }
    
}
