package main;

import java.time.LocalDate;
import java.time.Period;
import java.awt.Color;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author user
 */
public class TaskHeadingPanel extends javax.swing.JPanel {

    /**
     * Creates new form TaskHeadingPanel
     */
    public TaskHeadingPanel(LocalDate date) {
        initComponents();
        this.setName("TaskHeadingPanel");
        if (date == null){
            DateHeading.setText("Unscheduled");
        } else if (date.isBefore(LocalDate.now())){
            Period overdue = Period.between(date, LocalDate.now());
            DateHeading.setText(String.format("Tasks overdue: %d Days", overdue.getDays()));
            DateHeading.setForeground(Color.RED);
        } else {
            DateHeading.setText(String.format("Tasks due: %ta %tb %td", date, date, date));
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

        DateHeading = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setPreferredSize(new java.awt.Dimension(300, 30));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setLayout(new java.awt.BorderLayout());

        DateHeading.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        DateHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DateHeading.setText("Unscheduled");
        DateHeading.setName("DateHeading"); // NOI18N
        add(DateHeading, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DateHeading;
    // End of variables declaration//GEN-END:variables
}