/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mrms;

import static com.mycompany.mrms.GPDashboard.loggedInGpID;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author patok
 */
public class GPProfile extends javax.swing.JFrame {

     private static final String USRNM = "root";
    private static final String PASWD = "admin";
    private static final String DATA_BASE = "jdbc:mysql://localhost:3306/db_mrms";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    Connection db_mrms = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    private int loggedInGpID;
    
    Color DefaultColor = new Color(4, 59, 92);
    Color ClickedColor = new Color(249, 180, 45);
    
    public GPProfile(int loggedInGpID) {
        this.loggedInGpID = loggedInGpID;
        initComponents(); // Initialize your components if needed
        jPanel5.setBackground(DefaultColor);
        jPanel3.setBackground(ClickedColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel9.setBackground(DefaultColor);
    }
        private boolean allFieldsEmpty(String firstName, String middleName, String lastName, String phone, String nationality, String country, String city, String address, String postCode, String comments) {
    return firstName.isEmpty() &&
           middleName.isEmpty() &&
           lastName.isEmpty() &&
           phone.isEmpty() &&
           nationality.isEmpty() &&
           country.isEmpty() &&
           city.isEmpty() &&
           address.isEmpty() &&
           postCode.isEmpty() &&
           comments.isEmpty();
}
        private String buildUpdateQuery(String firstName, String middleName, String lastName, String phone, String nationality, String country, String city, String address, String postCode, String comments) {
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("UPDATE gp SET ");

    boolean firstField = true;

    if (!firstName.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpFirstName = ?");
        firstField = false;
    }

    if (!middleName.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpMiddleName = ?");
        firstField = false;
    }

    if (!lastName.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpLastName = ?");
        firstField = false;
    }

    if (!phone.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpPhoneNumber = ?");
        firstField = false;
    }

    if (!nationality.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpNationality = ?");
        firstField = false;
    }

    if (!country.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpCountry = ?");
        firstField = false;
    }

    if (!city.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpCity = ?");
        firstField = false;
    }

    if (!address.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpAddress = ?");
        firstField = false;
    }

    if (!postCode.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpPostCode = ?");
        firstField = false;
    }

    if (!comments.isEmpty()) {
        if (!firstField) queryBuilder.append(", ");
        queryBuilder.append("gpComments = ?");
        firstField = false;
    }

    queryBuilder.append(" WHERE gpID = ?");

    return queryBuilder.toString();
}
        
   private void connectDB() {
        try {
            Class.forName(DRIVER);
            db_mrms = DriverManager.getConnection(DATA_BASE, USRNM, PASWD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
    }
         private void loadGPData() {
        connectDB();
        try {
            String sql = "SELECT * FROM gp WHERE gpID = ?";
            ps = db_mrms.prepareStatement(sql);
            ps.setInt(1, loggedInGpID);
            rs = ps.executeQuery();
            if (rs.next()) {
                jFirstName.setText(rs.getString("FirstName"));
                jLastName.setText(rs.getString("LastName"));
                jPhone.setText(rs.getString("Phone"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading patient data: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (db_mrms != null) db_mrms.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
   private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (db_mrms != null) db_mrms.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            e.printStackTrace();
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

        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jFirstName = new javax.swing.JTextField();
        jMiddleName = new javax.swing.JTextField();
        jLastName = new javax.swing.JTextField();
        jPhone = new javax.swing.JTextField();
        jNationality = new javax.swing.JTextField();
        jCountry = new javax.swing.JTextField();
        jCity = new javax.swing.JTextField();
        jAddress = new javax.swing.JTextField();
        jPostCode = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jComments = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(249, 180, 45));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(4, 59, 92));
        jLabel2.setText("MRMS Menu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(411, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(411, 411, 411))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentShown(evt);
            }
        });

        jPostCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPostCodeActionPerformed(evt);
            }
        });

        jComments.setColumns(20);
        jComments.setRows(5);
        jScrollPane1.setViewportView(jComments);

        jLabel8.setText("First Name");

        jLabel14.setText("Middle Name");

        jLabel9.setText("Last Name");

        jLabel15.setText("Phone");

        jLabel33.setText("Nationality");

        jLabel10.setText("Country of Residence");

        jLabel11.setText("City");

        jLabel12.setText("Address");

        jLabel29.setText("Post Code");

        jLabel13.setText("Comments");

        jButton1.setText("UPDATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CLEAR");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("UPDATE PROFILE INFORMATION");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel16)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel29))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(73, 73, 73)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCountry)
                            .addComponent(jCity, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPostCode, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(jAddress))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPostCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(4, 59, 92));

        jPanel5.setBackground(new java.awt.Color(4, 59, 92));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel5MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HOME");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(4, 59, 92));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PROFILE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(4, 59, 92));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(4, 59, 92));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MEDICAL RECORD");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.setBackground(new java.awt.Color(4, 59, 92));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel9MousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("LOG OUT");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(4, 59, 92));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel7MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("APPOINTMENTS");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(34, 34, 34))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPostCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPostCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPostCodeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     String firstName = jFirstName.getText();
    String middleName = jMiddleName.getText();
    String lastName = jLastName.getText();
    String phone = jPhone.getText();
    String nationality = jNationality.getText();
    String country = jCountry.getText();
    String city = jCity.getText();
    String address = jAddress.getText();
    String postCode = jPostCode.getText();
    String comments = jComments.getText();

    if (allFieldsEmpty(firstName, middleName, lastName, phone, nationality, country, city, address, postCode, comments)) {
        JOptionPane.showMessageDialog(null, "To update information, please fill some information into the text fields.");
    } else {
        try {
            connectDB();
            String updateQuery = buildUpdateQuery(firstName, middleName, lastName, phone, nationality, country, city, address, postCode, comments);
            System.out.println("Generated SQL query: " + updateQuery); // Print generated SQL query for inspection
            
            PreparedStatement ps = db_mrms.prepareStatement(updateQuery);

            int parameterIndex = 1;
            if (!firstName.isEmpty()) { ps.setString(parameterIndex++, firstName); }
            if (!middleName.isEmpty()) { ps.setString(parameterIndex++, middleName); }
            if (!lastName.isEmpty()) { ps.setString(parameterIndex++, lastName); }
            if (!phone.isEmpty()) { ps.setString(parameterIndex++, phone); }
            if (!nationality.isEmpty()) { ps.setString(parameterIndex++, nationality); }
            if (!country.isEmpty()) { ps.setString(parameterIndex++, country); }
            if (!city.isEmpty()) { ps.setString(parameterIndex++, city); }
            if (!address.isEmpty()) { ps.setString(parameterIndex++, address); }
            if (!postCode.isEmpty()) { ps.setString(parameterIndex++, postCode); }
            if (!comments.isEmpty()) { ps.setString(parameterIndex++, comments); }

            ps.setInt(parameterIndex, loggedInGpID); // Use the logged-in gpID

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User information updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No changes were made.");
            }
            
            closeResources();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error occurred during update: " + e.getMessage());
            e.printStackTrace();
        }
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2ComponentShown

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed

    }//GEN-LAST:event_jLabel1MousePressed

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        // TODO add your handling code here:
        jPanel5.setBackground(DefaultColor);
        jPanel3.setBackground(ClickedColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel9.setBackground(DefaultColor);
    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed
        // TODO add your handling code here:
        jPanel5.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel6.setBackground(ClickedColor);
        jPanel7.setBackground(DefaultColor);
        jPanel9.setBackground(DefaultColor);
        
        this.dispose();
        Home.loggedInGpID = loggedInGpID;
        GPMedicalRecord gpMR;
         try {
             gpMR = new GPMedicalRecord(loggedInGpID);
             gpMR.setVisible(true);
         } catch (SQLException ex) {
             Logger.getLogger(GPProfile.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }//GEN-LAST:event_jPanel6MousePressed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.dispose();
        Home.loggedInPatientID = -1; // Нулираме идентификатора на логнатия потребител
        Home home = new Home();
        home.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jPanel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MousePressed
        // TODO add your handling code here:
        jPanel5.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel9.setBackground(ClickedColor);
        
        this.dispose();
        Home.loggedInGpID = loggedInGpID;
        Home home = new Home();
        home.setVisible(true);
    }//GEN-LAST:event_jPanel9MousePressed

    private void jPanel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MousePressed
        // TODO add your handling code here:
        jPanel5.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(ClickedColor);
        jPanel9.setBackground(DefaultColor);
        
        this.dispose();
        Home.loggedInGpID = loggedInGpID;
        GPAppointments gpAppointments = new GPAppointments(loggedInGpID);
        gpAppointments.setVisible(true);
    }//GEN-LAST:event_jPanel7MousePressed

    private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MousePressed
        jPanel5.setBackground(ClickedColor);
        jPanel3.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel9.setBackground(DefaultColor);
        
        this.dispose();
        Home.loggedInGpID = loggedInGpID;
        GPDashboard gpDashboard = new GPDashboard(loggedInGpID);
        gpDashboard.setVisible(true);
    }//GEN-LAST:event_jPanel5MousePressed

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
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(GPDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Replace 0 with the actual gpID you want to pass to GPDashboard
            int gpID = 0; // Replace with the actual gpID you want to pass
            new GPProfile(gpID).setVisible(true);
        }
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jAddress;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jCity;
    private javax.swing.JTextArea jComments;
    private javax.swing.JTextField jCountry;
    private javax.swing.JTextField jFirstName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLastName;
    private javax.swing.JTextField jMiddleName;
    private javax.swing.JTextField jNationality;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jPhone;
    private javax.swing.JTextField jPostCode;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
