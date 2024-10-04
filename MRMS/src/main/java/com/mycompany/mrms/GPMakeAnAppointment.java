/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mrms;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author patok
 */
public class GPMakeAnAppointment extends javax.swing.JFrame {

   
    private int loggedInGpID; // This is a class-level (instance) variable

    public GPMakeAnAppointment(int loggedInGpID) {
        this.loggedInGpID = loggedInGpID;
        initComponents();
        loadPatients();
        loadReasons();
        loadTimeSlots();
        
    }

    private void loadPatients() {
        jComboBoxPatient.removeAllItems();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mrms", "root", "admin")) {
            String query = "SELECT FirstName, LastName FROM patients";
            PreparedStatement stmt = con.prepareStatement(query);
            // Execute query and populate ComboBox with patient names
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String patientName = rs.getString("FirstName") + " " + rs.getString("LastName");
                jComboBoxPatient.addItem(patientName);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading patients: " + ex.getMessage());
        }
    }

    private void loadReasons() {
        jComboBoxReason.removeAllItems();
        String[] reasons = {
            "Primary Examination", "Secondary Examination", "Children's Consultation",
            "Dispensed Patient", "Other", "Issuance Medical Certificate",
            "Maternal Healthcare", "Medical Procedure", "Imaging Study", "Preventive Check-up"
        };
        for (String reason : reasons) {
            jComboBoxReason.addItem(reason);
        }
    }

    private void loadTimeSlots() {
        jComboBoxTime.removeAllItems(); // Clear existing items
        String[] timeSlots = {
            "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"
        };
        for (String time : timeSlots) {
            jComboBoxTime.addItem(time); // Add each time slot string to the combo box
        }
    }

    private void bookAppointment() {
        String patientName = (String) jComboBoxPatient.getSelectedItem();
        Date selectedDate = new Date(jCalendar1.getDate().getTime());
        String selectedTimeStr = (String) jComboBoxTime.getSelectedItem(); // Use a JComboBox for time selection
        String selectedReason = (String) jComboBoxReason.getSelectedItem(); // Get the selected reason from jComboBoxReason

        if (loggedInGpID == -1) {
            JOptionPane.showMessageDialog(this, "No GP logged in.");
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mrms", "root", "admin")) {
            String query = "INSERT INTO appointment (date, time, PatientID, firstName, lastName, status, reason) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(selectedDate.getTime()));
            ps.setString(2, selectedTimeStr); // Store selectedTimeSlot as VARCHAR in the database
            ps.setInt(3, loggedInGpID); // Set the loggedIngpID as the gpID
            
            // Assuming patientName is in the format "FirstName LastName"
            String[] names = patientName.split(" ");
            if (names.length < 2) {
                JOptionPane.showMessageDialog(this, "Invalid patient name format.");
                return;
            }
            ps.setString(4, names[0]); // First name of the patient
            ps.setString(5, names[1]); // Last name of the patient
            ps.setString(6, "Scheduled"); // Status of the appointment
            ps.setString(7, selectedReason); // Add the selected reason to the query

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Appointment booked successfully!");
            loadAppointments();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error booking appointment: " + ex.getMessage());
        } 
    }
    
    private void loadAppointments() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mrms", "root", "admin")) {
        String query = "SELECT appointmentNo, date, time, PatientID, status, reason FROM appointment WHERE PatientID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, loggedInGpID);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentNo = rs.getInt("appointmentNo");
            Date date = rs.getDate("date");
            String time = rs.getString("time");
            int patientID = rs.getInt("PatientID");
            String status = rs.getString("status");
            String reason = rs.getString("reason");
            model.addRow(new Object[]{appointmentNo, date, time, patientID, status, reason});
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading appointments: " + ex.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxPatient = new javax.swing.JComboBox<>();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBoxReason = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jComboBoxTime = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1060, 670));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(249, 180, 45));
        jPanel1.setPreferredSize(new java.awt.Dimension(1040, 130));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(4, 59, 92));
        jLabel2.setText("MAKE AN APPOINTMENT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(319, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(293, 293, 293))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, -1));

        jPanel2.setBackground(new java.awt.Color(4, 59, 92));

        jComboBoxPatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AppointmentNo", "Date", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("BOOK APPOINTMENT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBoxReason.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("GO BACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBoxTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxReason, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(170, 170, 170))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBoxTime, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1050, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        bookAppointment();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        int loggedInGpID = 1;
        GPAppointments gpApp = new GPAppointments(loggedInGpID);
        gpApp.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    

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
            java.util.logging.Logger.getLogger(GPMakeAnAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GPMakeAnAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GPMakeAnAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GPMakeAnAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        int loggedIngpID = 1; // Example value
java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
        new GPMakeAnAppointment(loggedIngpID).setVisible(true);
    }
});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jComboBoxPatient;
    private javax.swing.JComboBox<String> jComboBoxReason;
    private javax.swing.JComboBox<String> jComboBoxTime;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
