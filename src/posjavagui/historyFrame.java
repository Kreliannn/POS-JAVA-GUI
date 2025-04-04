/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posjavagui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import posjavagui.backend.dbHelper;
import posjavagui.backend.Transaction;
import posjavagui.backend.SoldProduct;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author U
 */
public class historyFrame extends javax.swing.JFrame {

    /**
     * Creates new form historyFrame
     */
    public historyFrame() {
        initComponents();
        
        dbHelper myDb = new dbHelper();
        
        List<Transaction> transactions = myDb.getTransactions();
        
        container.setLayout(new GridLayout(0, 1, 10, 10));
        
        // mag iterate sa lahat ng transactions
        for (Transaction transaction : transactions) {
            
            // mag add ng container para sa bawat transactions
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
            productPanel.setPreferredSize(new Dimension(220, 300)); // Adjusted size
            productPanel.setBackground(Color.WHITE);
            productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Thicker border for receipt look

            // Set monospaced font for a receipt-like appearance
            Font receiptFont = new Font("Courier New", Font.PLAIN, 12);
            
            
            JLabel id = new JLabel("Receipt ID: " + transaction.getTransactionId());
            JLabel total = new JLabel("Total: ₱" + transaction.getTotal());
            JLabel cash = new JLabel("Cash: ₱" + transaction.getCash());
            JLabel change = new JLabel("Change: ₱" + transaction.getPaymentChange());
            JLabel date = new JLabel("Date: " + transaction.getDate());

            // Apply font and center align
            JLabel[] labels = {id, total, cash, change, date};
            for (JLabel label : labels) {
                label.setFont(receiptFont);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
            }

            // Table columns
            String[] columns = {"Item", "Qty", "₱"};
            DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

            // Add sold products to table
            List<SoldProduct> products = transaction.getSoldProducts();
            for (SoldProduct product : products) {
                tableModel.addRow(new Object[]{product.getName(), product.getQty(), product.getTotal()});
            }

            // Create JTable and remove grid lines for cleaner look
            JTable table = new JTable(tableModel);
            table.setFont(receiptFont);
            table.setRowHeight(20);
            table.setEnabled(false); // Prevent editing
            table.setShowGrid(false);
            table.setIntercellSpacing(new Dimension(0, 0));

            // Set table and header backgrounds to white
            table.setBackground(Color.WHITE);
            table.setForeground(Color.BLACK);
            table.getTableHeader().setBackground(Color.WHITE);
            table.getTableHeader().setForeground(Color.BLACK);

            // Center align table data
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            // Scroll Pane with fixed height
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(200, 100));
            scrollPane.setBorder(null); // Remove unnecessary border
            scrollPane.getViewport().setBackground(Color.WHITE); // Ensure scroll pane matches background

            // Add components to the panel
            productPanel.add(id);
            productPanel.add(date);
            productPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
            productPanel.add(scrollPane);
            productPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
            productPanel.add(total);
            productPanel.add(cash);
            productPanel.add(change);
            
            // i add ang container sa productPanel
            container.add(productPanel);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        container = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(container);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Receipt History");

        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(historyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(historyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(historyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(historyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new historyFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
