/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posjavagui;
import posjavagui.backend.Product;
import posjavagui.backend.Transaction;
import posjavagui.backend.dbHelper;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
/**
 *
 * @author U
 */
public class shopFrame extends javax.swing.JFrame {

    /**
     * Creates new form shopFrame
     */
    public shopFrame() {
        initComponents();
        
        // resizze logo
        scaleImage();
                
        dbHelper db = new dbHelper();
        List<Product> productsDrinks = db.getProducts();
        
        // set grid layout
        menuFood.setLayout(new GridLayout(0, 1, 10, 10));
        menuDrinks.setLayout(new GridLayout(0, 1, 10, 10));
        menuDesert.setLayout(new GridLayout(0, 1, 10, 10)); 
        menuBeverage.setLayout(new GridLayout(0, 1, 10, 10));
        
        // iterate sa lahat ng products 
        for (Product product : productsDrinks) 
        {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS)); // Vertical layout
            productPanel.setPreferredSize(new Dimension(150, 220)); // Adjusted height
            productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border
            
           ImageIcon icon = new ImageIcon(getClass().getResource("/posjavagui/assets/" + product.getImg()));

            // Resize the image
            Image img = icon.getImage();  
            Image newImg = img.getScaledInstance(200, 120, Image.SCALE_SMOOTH); // Adjust width & height
            ImageIcon scaledIcon = new ImageIcon(newImg);

            JLabel imageLabel = new JLabel(scaledIcon);


            // Product Name
            JLabel nameLabel = new JLabel("Name: " + product.getName());

            // Stock Label
            JLabel stockLabel = new JLabel("Stock: " + product.getStocks());

            // Price Label
            JLabel priceLabel = new JLabel("Price: ₱" + product.getPrice());

            // Quantity TextField
            JTextField quantityField = new JTextField(1); // Small text field for quantity
            
            
            // cart Button
            JButton buyButton = new JButton("add to cart");
            buyButton.setPreferredSize(new Dimension(300,30));
            buyButton.setMinimumSize(new Dimension(300, 30));
            buyButton.setMaximumSize(new Dimension(300, 30));

            



            // eto ang mag rurun pag pinindot ang add to cart button
            buyButton.addActionListener(e -> {
                String quantityText = quantityField.getText();
                if (!quantityText.isEmpty() && quantityText.matches("\\d+")) {
                    int quantity = Integer.parseInt(quantityText);
                    if (quantity > 0 && quantity <= product.getStocks()) {
                        // kunin ang inforamation ng product
                        String item = product.getName();
                        int price = product.getPrice();
                        int total = price * quantity;
                        String category = product.getCategory();
                        int id = product.getId();
                        
                        // i dagdag sa cart ang product na binili
                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                        model.addRow(new Object[]{id, item, category, quantity, total});

                        int totalVariableConvert = Integer.parseInt(totalVariable.getText());

                        totalVariable.setText(String.valueOf(totalVariableConvert + total)); 
                        
                        quantityField.setText("");
                        
                        // i update ang ttotal product
                        updateTotal();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a valid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Add components to panel
            productPanel.add(imageLabel); // Add image first
            productPanel.add(nameLabel);
            productPanel.add(stockLabel);
            productPanel.add(priceLabel);
            productPanel.add(quantityField);
            productPanel.add(buyButton);
            
            // i cehck ang category ng product at i add ang products kung saaan ba syang category
            if(product.getCategory().equals("food"))
            {
                menuFood.add(productPanel);
            }
            else if(product.getCategory().equals("drinks"))
            {
                menuDrinks.add(productPanel);
            }
            else if(product.getCategory().equals("dessert"))
            {
                menuDesert.add(productPanel);
            }
            else if(product.getCategory().equals("beverage"))
            {
                menuBeverage.add(productPanel);
            }
        }
        
        

   
    }
    
    // i update ang total ng product
    public void updateTotal()
    {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount(); // Get total rows
        
        int total = 0;

        for (int i = 0; i < rowCount; i++) {
            Object value = model.getValueAt(i, 4); // 2 is the index of the third column
            System.out.println(value); // Print or store the value
            total += Integer.parseInt(value.toString());;
        }
        
        totalVariable.setText(String.valueOf(total));
    }
    
    public void addCart(String name, int Price)
    {
        
    }
    
    private void scaleImage() {
        ImageIcon icon = new ImageIcon("src/posjavagui/assets/logo.jpg"); 
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaledImg));
    }


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        dashboard = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        menuFood = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        payButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        totalVariable = new javax.swing.JLabel();
        removeCart = new javax.swing.JButton();
        payment = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        menuDesert = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        menuBeverage = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        menuDrinks = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ITEM", "TYPE", "Qnty", "PRICE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jButton1.setText("Add Products");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit Products");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        logoutBtn.setText("logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        jButton3.setText("Add Accounts");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        dashboard.setText("Dashboard");
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });

        jButton4.setText("History");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Sales Report");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addGap(92, 92, 92))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        menuFood.setBackground(new java.awt.Color(204, 204, 204));
        menuFood.setToolTipText("");

        javax.swing.GroupLayout menuFoodLayout = new javax.swing.GroupLayout(menuFood);
        menuFood.setLayout(menuFoodLayout);
        menuFoodLayout.setHorizontalGroup(
            menuFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );
        menuFoodLayout.setVerticalGroup(
            menuFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(menuFood);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("CART");

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        payButton.setText("PAY");
        payButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("TOTAL: ");

        totalVariable.setText("0");

        removeCart.setText("remove");
        removeCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCartActionPerformed(evt);
            }
        });

        jLabel1.setText("payment: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(payButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(payment)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalVariable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                        .addComponent(removeCart)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(totalVariable)
                    .addComponent(removeCart, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payment, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        menuDesert.setBackground(new java.awt.Color(204, 204, 204));
        menuDesert.setToolTipText("");

        javax.swing.GroupLayout menuDesertLayout = new javax.swing.GroupLayout(menuDesert);
        menuDesert.setLayout(menuDesertLayout);
        menuDesertLayout.setHorizontalGroup(
            menuDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        menuDesertLayout.setVerticalGroup(
            menuDesertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(menuDesert);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setText("FOOD");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setText("DRINKS");

        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        menuBeverage.setBackground(new java.awt.Color(204, 204, 204));
        menuBeverage.setToolTipText("");

        javax.swing.GroupLayout menuBeverageLayout = new javax.swing.GroupLayout(menuBeverage);
        menuBeverage.setLayout(menuBeverageLayout);
        menuBeverageLayout.setHorizontalGroup(
            menuBeverageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        menuBeverageLayout.setVerticalGroup(
            menuBeverageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(menuBeverage);

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        menuDrinks.setBackground(new java.awt.Color(204, 204, 204));
        menuDrinks.setToolTipText("");

        javax.swing.GroupLayout menuDrinksLayout = new javax.swing.GroupLayout(menuDrinks);
        menuDrinks.setLayout(menuDrinksLayout);
        menuDrinksLayout.setHorizontalGroup(
            menuDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );
        menuDrinksLayout.setVerticalGroup(
            menuDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );

        jScrollPane6.setViewportView(menuDrinks);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setText("DESSERT");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setText("BEVERAGE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(53, 53, 53)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel8)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(137, 137, 137)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane4))
                .addGap(118, 118, 118))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // tangalin ang item sa cart
    private void removeCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCartActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow(); // Get selected row

        if (selectedRow != -1) { // If a row is selected
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to remove.");
        }
        
        updateTotal();
    }//GEN-LAST:event_removeCartActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addProductsFrame addProductPage = new addProductsFrame();
        this.dispose(); 
        addProductPage.setLocationRelativeTo(null);
        addProductPage.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        editProducts editProductsPage = new editProducts();
        this.dispose(); 
        editProductsPage.setLocationRelativeTo(null);
        editProductsPage.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // i open ang login page
        loginFrame loginPage = new loginFrame();
        this.dispose(); 
        loginPage.setLocationRelativeTo(null);
        loginPage.setVisible(true);
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         // i open ang add account page
        this.dispose(); 
        addAccountFrame addAccountPage = new addAccountFrame();
        addAccountPage.setLocationRelativeTo(null); 
        addAccountPage.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
         // i open ang dashboard page
        this.dispose(); 
         dashboardFrame dashboardPage = new dashboardFrame();
         dashboardPage.setLocationRelativeTo(null);
         dashboardPage.setVisible(true);
    }//GEN-LAST:event_dashboardActionPerformed
    
    // mag rurun to pag pinindot na ni cashier ang pay button
    private void payButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payButtonActionPerformed
      
        dbHelper myDb = new dbHelper();
        
        String transaction_id = UUID.randomUUID().toString();
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.toString();
        int transactionTotal = Integer.parseInt(totalVariable.getText());
        int cash = Integer.parseInt(payment.getText());
        
        // i check if sakto or sobrta yung binayad ni customer. pag kulang mag alerrt ng error
        if(cash < transactionTotal)
        {
            JOptionPane.showMessageDialog(null, "payment is lessthan bill", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount(); // Get total rows
       
        // i dagdag sa soldProducts lahat ng product na nasa cart
        for (int i = 0; i < rowCount; i++) {
            int product_id = Integer.parseInt(model.getValueAt(i, 0).toString());
            String name = model.getValueAt(i, 1).toString(); // 0 is the index of the third column
            String type = model.getValueAt(i, 2).toString(); // 1 is the index of the third column
            int qty = Integer.parseInt(model.getValueAt(i, 3).toString()); // 2 is the index of the third column
            int total = Integer.parseInt(model.getValueAt(i, 4).toString()); // 3 is the index of the third column
            myDb.insertSoldProduct(qty, product_id, transaction_id);
            myDb.updateStocks(qty, product_id);
        }
        
        // kunin ang sukli
        int change = cash - transactionTotal;
        
        myDb.addTransaction(transaction_id, date, transactionTotal, cash, change);
        
        // i open ang receipt page
        receiptFrame receiptPage = new receiptFrame(new Transaction(transaction_id, date, transactionTotal, cash, change));
        receiptPage.setLocationRelativeTo(null);
         this.dispose(); 
        receiptPage.setVisible(true);
        
    }//GEN-LAST:event_payButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // open receipt history page
        this.dispose(); 
         historyFrame historyPage = new historyFrame();
         historyPage.setLocationRelativeTo(null);
         historyPage.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // open report sales page
        this.dispose(); 
         salesReport reportPage = new salesReport();
         reportPage.setLocationRelativeTo(null);
         reportPage.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(shopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(shopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(shopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(shopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new shopFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dashboard;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel menuBeverage;
    private javax.swing.JPanel menuDesert;
    private javax.swing.JPanel menuDrinks;
    private javax.swing.JPanel menuFood;
    private javax.swing.JButton payButton;
    private javax.swing.JTextField payment;
    private javax.swing.JButton removeCart;
    private javax.swing.JLabel totalVariable;
    // End of variables declaration//GEN-END:variables
}
