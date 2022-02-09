package main;

import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author user
 */
public class TaskItemPanel extends javax.swing.JPanel {

    int taskId;

    /**
     * Creates new form TaskItemPanel
     */
    public TaskItemPanel(Task t) {
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

        LblTaskDate = new javax.swing.JLabel();
        ChkBoxWithTaskName = new javax.swing.JCheckBox();
        BtnEditTask = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(400, 60));
        setLayout(null);

        LblTaskDate.setText("TaskDate");
        LblTaskDate.setName("TaskDate"); // NOI18N
        add(LblTaskDate);
        LblTaskDate.setBounds(200, 21, 53, 16);

        ChkBoxWithTaskName.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        ChkBoxWithTaskName.setText("TaskName");
        ChkBoxWithTaskName.setName("TaskNameAndCheck"); // NOI18N
        ChkBoxWithTaskName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        add(ChkBoxWithTaskName);
        ChkBoxWithTaskName.setBounds(10, 21, 83, 18);

        BtnEditTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/icons8-double-right-24.png"))); // NOI18N
        BtnEditTask.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BtnEditTask.setPreferredSize(new java.awt.Dimension(30, 30));
        add(BtnEditTask);
        BtnEditTask.setBounds(270, 15, 30, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnEditTask;
    private javax.swing.JCheckBox ChkBoxWithTaskName;
    private javax.swing.JLabel LblTaskDate;
    // End of variables declaration//GEN-END:variables
}
