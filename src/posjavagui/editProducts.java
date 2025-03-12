/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posjavagui;

import posjavagui.backend.Product;
import posjavagui.backend.dbHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;


/**
 *
 * @author U
 */
public class editProducts extends javax.swing.JFrame {

    /**
     * Creates new form editProducts
     */
    public editProducts() {
        initComponents();
        
        header.setFont(new Font("Arial", Font.BOLD, 18));
        
        dbHelper db = new dbHelper();
        List<Product> products = db.getProducts();
        
        menu.setLayout(new GridLayout(0, 4, 10, 10)); // Adjust columns as needed
       

        for (Product product : products) 
        {
            // create jpanel for container
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS)); // Vertical layout
            productPanel.setPreferredSize(new Dimension(150, 300)); // Adjusted height
            productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border
            
            //create button  para sa status ng product
            JButton label = new JButton();
            label.setPreferredSize(new Dimension(300,30));
            label.setMinimumSize(new Dimension(300, 30));
            label.setMaximumSize(new Dimension(300, 30));
            label.setBackground(Color.WHITE);
            
            // i cehck kung ilan nalang ang stocks at baguhin ang status ng product based sa stocks 
            if(product.getStocks() > 10)
            {
                label.setText("High Stocks");
                label.setForeground(Color.GREEN);
            }
            else if(product.getStocks() == 0)
            {
                label.setText("Out Of Stocks");
                label.setForeground(Color.RED);
            }
            else
            {
                label.setText("Low Stocks");
                label.setForeground(Color.ORANGE);
            }
            
            
        
            // picture ng products
           ImageIcon icon = new ImageIcon(getClass().getResource("/posjavagui/assets/" + product.getImg()));

            // Resize the image
            Image img = icon.getImage();  
            Image newImg = img.getScaledInstance(200, 100, Image.SCALE_SMOOTH); // Adjust width & height
            ImageIcon scaledIcon = new ImageIcon(newImg);

            JLabel imageLabel = new JLabel(scaledIcon);


            // Product Name
            JLabel nameLabel = new JLabel("Name: " + product.getName());

            // Stock Label
            JLabel stockLabel = new JLabel("Stock: ");

            // Price Label
            JLabel priceLabel = new JLabel("Price: ");

            // Quantity TextField
            JTextField priceField = new JTextField(1); // Small text field for quantity
            priceField.setText(Integer.toString(product.getPrice()));
            
            JTextField stocksField = new JTextField(1);
            stocksField.setText(Integer.toString(product.getStocks()));
            
            JTextField nameField = new JTextField(1); // Small text field for quantity
            nameField.setText(product.getName());
            
            // Save Button
            JButton saveButton = new JButton("save");
            saveButton.setPreferredSize(new Dimension(300,30));
            saveButton.setMinimumSize(new Dimension(300, 30));
            saveButton.setMaximumSize(new Dimension(300, 30));
            saveButton.setBackground(new Color(100, 200, 100));
            saveButton.setForeground(Color.WHITE);
            
            JButton removeButton = new JButton("remove");
            removeButton.setPreferredSize(new Dimension(300,30));
            removeButton.setMinimumSize(new Dimension(300, 30));
            removeButton.setMaximumSize(new Dimension(300, 30));
            removeButton.setBackground(new Color(220, 80, 80));
            removeButton.setForeground(Color.WHITE);



            // pag pinindot ang save button eto ang mag rurun na code
            saveButton.addActionListener(e -> {
                // kunin lahat ng information ng product na binili
                String name = nameField.getText();
                int price = Integer.parseInt(!priceField.getText().equals("") ? priceField.getText() : "0");
                int stocks = Integer.parseInt(!stocksField.getText().equals("") ? stocksField.getText() : "0");
                int id = product.getId();
                
                dbHelper myDb = new dbHelper();
                
                // i update ang value ng products sa database
                if(myDb.updateProduct(name, price, stocks, id))
                {
                    editProducts page = new editProducts();
                    this.dispose(); 
                    page.setLocationRelativeTo(null); 
                    page.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "something went wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            
            // pag pinindot ang remove button eto ang mag rurun na code
            removeButton.addActionListener((e) -> {
                int id = product.getId();
                dbHelper myDb = new dbHelper();
                
                // i remove ang product sa database
                myDb.removeProduct(id);
                
                // i restart ang jframe
                editProducts editProductPage = new editProducts();
                this.dispose(); 
                editProductPage.setLocationRelativeTo(null); 
                editProductPage.setVisible(true);
                
            });

            // Add components to panel
            productPanel.add(label);
            productPanel.add(imageLabel); // Add image first
            
            productPanel.add(nameLabel);
            productPanel.add(nameField);
            
            productPanel.add(priceLabel);
            productPanel.add(priceField);
            
            productPanel.add(stockLabel);
            productPanel.add(stocksField);
            
            productPanel.add(saveButton);
            productPanel.add(removeButton);

            // Add product panel to menu
            menu.add(productPanel);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        header = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        header.setText("EDIT PRODUCTS");

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        menu.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(227, 227, 227)
                        .addComponent(header)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(header))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // bumalik sa shop page
        this.dispose(); 
        shopFrame shopPage = new shopFrame();
        shopPage.setLocationRelativeTo(null);
        shopPage.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editProducts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menu;
    // End of variables declaration//GEN-END:variables
}
