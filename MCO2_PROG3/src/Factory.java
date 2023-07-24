/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author rogercanayon
 */
public class Factory extends javax.swing.JFrame {

    /**
     * Creates new form Factory
     */
    public Factory() {
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

        mainMenuPanel = new javax.swing.JPanel();
        VendingMachineLabel = new javax.swing.JLabel();
        FactoryLabel = new javax.swing.JLabel();
        CreateBtn = new javax.swing.JButton();
        TestBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BackgroundPicture = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(665, 590));
        setResizable(false);
        setSize(new java.awt.Dimension(665, 590));

        mainMenuPanel.setLayout(null);

        VendingMachineLabel.setBackground(null);
        VendingMachineLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        VendingMachineLabel.setForeground(new java.awt.Color(255, 255, 255));
        VendingMachineLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        VendingMachineLabel.setText("Vending Machine");
        mainMenuPanel.add(VendingMachineLabel);
        VendingMachineLabel.setBounds(400, 170, 182, 28);

        FactoryLabel.setBackground(null);
        FactoryLabel.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        FactoryLabel.setForeground(new java.awt.Color(255, 255, 255));
        FactoryLabel.setText("FACTORY");
        mainMenuPanel.add(FactoryLabel);
        FactoryLabel.setBounds(400, 200, 249, 56);

        CreateBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        CreateBtn.setText("Create");
        CreateBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mainMenuPanel.add(CreateBtn);
        CreateBtn.setBounds(400, 270, 230, 46);

        TestBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        TestBtn.setText("Test");
        TestBtn.setToolTipText("");
        TestBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mainMenuPanel.add(TestBtn);
        TestBtn.setBounds(400, 340, 230, 46);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("by Roger and Kristian");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        mainMenuPanel.add(jLabel1);
        jLabel1.setBounds(470, 420, 160, 16);

        BackgroundPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elements/backgroundMainMenu.png"))); // NOI18N
        mainMenuPanel.add(BackgroundPicture);
        BackgroundPicture.setBounds(0, 0, 670, 590);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundPicture;
    private javax.swing.JButton CreateBtn;
    private javax.swing.JLabel FactoryLabel;
    private javax.swing.JButton TestBtn;
    private javax.swing.JLabel VendingMachineLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainMenuPanel;
    // End of variables declaration//GEN-END:variables
}
