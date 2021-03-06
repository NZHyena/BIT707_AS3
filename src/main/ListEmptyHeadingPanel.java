package main;
import java.awt.*;
import javax.swing.*;
/*
 * Copyright (C) 2022 Grant Docherty
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * BIT707 – Software Engineering 2021-2022
 * Written for the Graduate Diploma in Information Technology at the Open Polytechnic.
 * This is the work of a student and follows the principles of academic integrity set by the Open Polytech
 */

/**
 * An extention of the javax.swing.JPanel to indicate no tasks being present in the task list.
 * @author Grant Docherty - 5032768
 */
public class ListEmptyHeadingPanel extends JPanel {

    /**
     * Creates new form TaskHeadingPanel
     */
    public ListEmptyHeadingPanel() {
        // Call the initComponents method
        initComponents();
        // Set the name of this panel
        this.setName("ListEmptyHeadingPanel");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MsgMainHeading = new JLabel();
        MsgSecondaryHeading = new JLabel();

        setMaximumSize(new Dimension(300, 50));
        setMinimumSize(new Dimension(300, 50));
        setPreferredSize(new Dimension(300, 50));
        setLayout(new BorderLayout());

        MsgMainHeading.setFont(new Font("sansserif", 1, 14)); // NOI18N
        MsgMainHeading.setHorizontalAlignment(SwingConstants.CENTER);
        MsgMainHeading.setText("No Tasks Created ");
        add(MsgMainHeading, BorderLayout.CENTER);

        MsgSecondaryHeading.setHorizontalAlignment(SwingConstants.CENTER);
        MsgSecondaryHeading.setText("Create a task to populate the list");
        add(MsgSecondaryHeading, BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel MsgMainHeading;
    private JLabel MsgSecondaryHeading;
    // End of variables declaration//GEN-END:variables
}
