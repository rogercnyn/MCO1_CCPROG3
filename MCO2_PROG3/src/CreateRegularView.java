/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.event.ActionListener;

/**
 *
 * @author rogercanayon
 */
public class CreateRegularView extends javax.swing.JFrame {

    /**
     * Creates new form CreateRegularView
     */
    public CreateRegularView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CreateRegularPanel = new javax.swing.JPanel();
        RegularLabel = new javax.swing.JLabel();
        SaveBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vending Machine Factory");
        setBackground(new java.awt.Color(85, 88, 95));
        setPreferredSize(new java.awt.Dimension(665, 590));
        setSize(new java.awt.Dimension(665, 590));
        getContentPane().setLayout(null);

        CreateRegularPanel.setBackground(new java.awt.Color(85, 88, 95));
        CreateRegularPanel.setLayout(null);

        RegularLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        RegularLabel.setForeground(new java.awt.Color(255, 255, 255));
        RegularLabel.setText("Regular");
        CreateRegularPanel.add(RegularLabel);
        RegularLabel.setBounds(49, 36, 134, 42);

        SaveBtn.setBackground(new java.awt.Color(204, 255, 204));
        SaveBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SaveBtn.setText("Save");
        SaveBtn.setMaximumSize(new java.awt.Dimension(72, 73));
        SaveBtn.setMinimumSize(new java.awt.Dimension(72, 73));
        CreateRegularPanel.add(SaveBtn);
        SaveBtn.setBounds(550, 520, 102, 41);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        CreateRegularPanel.add(jPanel1);
        jPanel1.setBounds(390, 76, 260, 420);

        getContentPane().add(CreateRegularPanel);
        CreateRegularPanel.setBounds(0, 0, 670, 590);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setSaveBtnListener(ActionListener actn)
    {
        SaveBtn.addActionListener(actn);
    }

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
            java.util.logging.Logger.getLogger(CreateRegularView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateRegularView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateRegularView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateRegularView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateRegularView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CreateRegularPanel;
    private javax.swing.JLabel RegularLabel;
    private javax.swing.JButton SaveBtn;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
