package posjavagui;
import java.sql.*;
import java.util.*;

public class PosJavaGui {

  
    public static void main(String[] args) {
        // pag open ng cashier login pag ni run ang program
       loginFrame loginPage = new loginFrame();
       loginPage.setLocationRelativeTo(null); 
       loginPage.setVisible(true); 
    }
    
}
