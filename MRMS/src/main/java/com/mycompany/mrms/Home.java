/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mrms;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author patok
 */
public class Home extends javax.swing.JFrame {

    private static final String USRNM = "root";
    private static final String PASWD = "admin";
    private static final String DATA_BASE = "jdbc:mysql://localhost:3306/db_mrms";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection mrms_db = null;
    private static HashMap<String, Integer> loggedInUsers = new HashMap<>();

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public static int loggedInPatientID;
    public static int loggedInGpID;

    public Home() {
        initComponents();
        connectDB();
    }

    private void connectDB() {
        try {
            Class.forName(DRIVER);
            mrms_db = DriverManager.getConnection(DATA_BASE, USRNM, PASWD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

/*    private boolean authenticateUser(String email, String password) {
        try {
            String sql = "SELECT PatientID FROM patients WHERE Email = ? AND Password = ?";
            ps = mrms_db.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                loggedInPatientID = rs.getInt("PatientID");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during authentication: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }
*/
   /* private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            e.printStackTrace();
        }
    }
*/
    private boolean authenticatePatient(String email, String password) {
        boolean isAuthenticated = false;

        try {
            String query = "SELECT patientID FROM patients WHERE Email = ? AND Password = ?";
            PreparedStatement ps = mrms_db.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isAuthenticated = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error authenticating patient: " + e.getMessage());
        }

        return isAuthenticated;
    }

    private boolean authenticateGP(String email, String password) {
        boolean isAuthenticated = false;
        try {
            String query = "SELECT gpID FROM gp WHERE gpEmail = ? AND gpPassword = ?";
            PreparedStatement ps = mrms_db.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                setLoggedInGpID(rs.getInt("gpID"));
                isAuthenticated = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error authenticating GP: " + e.getMessage());
            e.printStackTrace();
        }
        return isAuthenticated;
    }

    private int getLoggedInGpIDFromDatabase(String email) {
        int gpID = -1;

        try {
            String query = "SELECT gpID FROM gp WHERE gpEmail = ?";
            PreparedStatement ps = mrms_db.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                gpID = rs.getInt("gpID");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving GP ID from database: " + e.getMessage());
            e.printStackTrace();
        }

        return gpID;
    }

     

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLoginEmail = new javax.swing.JTextField();
        jLoginPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLoginButton = new javax.swing.JButton();
        jSignUpButton = new javax.swing.JButton();
        jExitButton = new javax.swing.JButton();
        choice1 = new java.awt.Choice();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(249, 180, 45));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Medical Records Management System");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, -1));

        jPanel1.setBackground(new java.awt.Color(4, 59, 92));

        jLoginEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLoginEmailActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Email:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");

        jLoginButton.setText("Login");
        jLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLoginButtonMouseClicked(evt);
            }
        });

        jSignUpButton.setText("Sign Up");
        jSignUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSignUpButtonMouseClicked(evt);
            }
        });

        jExitButton.setText("Exit");
        jExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExitButtonActionPerformed(evt);
            }
        });

        choice1.add("Patient");
        choice1.add("GP");

        choice1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = choice1.getSelectedItem();
                    if (selectedOption.equals("Patient")) {
                        // Show or enable components related to Patient
                        jSignUpButton.setText("Sign Up as Patient");
                    } else if (selectedOption.equals("GP")) {
                        // Show or enable components related to GP
                        jSignUpButton.setText("Sign Up as GP");
                    }
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jSignUpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLoginEmail)
                                .addComponent(jLoginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(268, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLoginEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLoginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLoginButton)
                    .addComponent(jSignUpButton))
                .addGap(18, 18, 18)
                .addComponent(jExitButton)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 770, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLoginEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLoginEmailActionPerformed

    }//GEN-LAST:event_jLoginEmailActionPerformed
private JFrame frame;
    private void jExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExitButtonActionPerformed
        frame = new JFrame("Exit");
        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to close the application?", "MRMS",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    
    }//GEN-LAST:event_jExitButtonActionPerformed

    private void jLoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLoginButtonMouseClicked
    final String email = jLoginEmail.getText().trim();
    final String password = new String(jLoginPassword.getPassword());

    if (email.isEmpty() || !email.contains("@")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
        return;
    }

    if (password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a password.");
        return;
    }

    if (choice1.getSelectedItem().equals("Patient")) {
        if (authenticatePatient(email, password)) {
            loggedInPatientID = getLoggedInPatientIDFromDatabase(email);
            System.out.println("Logged in patient ID: " + loggedInPatientID); // Добавен дебъг
            if (loggedInPatientID != -1) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new Dashboard(loggedInPatientID).setVisible(true); // Отваря Dashboard за пациенти
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.");
        }
    } else if (choice1.getSelectedItem().equals("GP")) {
        if (authenticateGP(email, password)) {
            loggedInGpID = getLoggedInGpIDFromDatabase(email);
            System.out.println("Logged in GP ID: " + loggedInGpID); // Добавен дебъг
            if (loggedInGpID != -1) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new GPDashboard(loggedInGpID).setVisible(true); // Отваря GPDashboard за GP
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.");
        }
    }

    this.dispose();


    }//GEN-LAST:event_jLoginButtonMouseClicked

    private void jSignUpButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSignUpButtonMouseClicked
        this.dispose();

        if (choice1.getSelectedItem().equals("Patient")) {
            SignUp signUp = new SignUp();
            signUp.setVisible(true);
        } else if (choice1.getSelectedItem().equals("GP")) {
            GPSignUp gpSignUp = new GPSignUp();
            gpSignUp.setVisible(true);
        }
    }//GEN-LAST:event_jSignUpButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private javax.swing.JButton jExitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jLoginButton;
    private javax.swing.JTextField jLoginEmail;
    private javax.swing.JPasswordField jLoginPassword;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jSignUpButton;
    // End of variables declaration//GEN-END:variables
public static int getLoggedInPatientID() {
        return loggedInPatientID;
    }

    public static void setLoggedInPatientID(int patientID) {
        loggedInPatientID = patientID;
    }

    public static void setLoggedInGpID(int gpID) {
        loggedInGpID = gpID;
    }

    public static int getLoggedInGpID() {
        return loggedInGpID;
    }

    private int getLoggedInPatientIDFromDatabase(String email) {
        int patientID = -1;
        connectDB();
        try {
            String sql = "SELECT PatientID FROM patients WHERE Email = ?";
            ps = mrms_db.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                patientID = rs.getInt("PatientID");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching PatientID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (mrms_db != null) mrms_db.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return patientID;
    }
}