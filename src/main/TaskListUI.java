/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

// TODO: Add button to Task Description to Cancel Edit
// TODO: Add Separator

/**
 *
 * @author user
 */
public class TaskListUI extends javax.swing.JFrame implements TaskListener{

    private int fullSize;
    private int partSize;
    private int height;
    private TaskController controller = new TaskController();
    private boolean panelTaskCreate = false;
    int taskId;

    /**
     * Creates new form TaskListUI
     */
    public TaskListUI() {
        initComponents();
        fullSize = this.getWidth();
        height = this.getHeight();
        partSize = fullSize - PanelTaskDetails.getWidth();
        FnHideDetailPanel();
        controller.InitialLoad();
        controller.LoadAllTasks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelToolbar = new javax.swing.JPanel();
        BtnDeleteTask = new javax.swing.JButton();
        BtnCalendarView = new javax.swing.JButton();
        BtnAddTask = new javax.swing.JButton();
        PanelTaskList = new javax.swing.JPanel();
        PanelTaskDetails = new javax.swing.JPanel();
        LblTaskName = new javax.swing.JLabel();
        LblTaskDescript = new javax.swing.JLabel();
        LblDueDate = new javax.swing.JLabel();
        BtnSaveTask = new javax.swing.JButton();
        BtnCompleteDelete = new javax.swing.JButton();
        InTaskName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        InTaskDescript = new javax.swing.JTextArea();
        InDueDate = new javax.swing.JTextField();
        BtnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setName("To Do List"); // NOI18N
        setPreferredSize(new java.awt.Dimension(768, 400));

        PanelToolbar.setBorder(null);
        PanelToolbar.setName("PanelTools"); // NOI18N
        PanelToolbar.setPreferredSize(new java.awt.Dimension(760, 35));
        PanelToolbar.setLayout(null);

        BtnDeleteTask.setBackground(new java.awt.Color(255, 255, 255));
        BtnDeleteTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/icons8-remove-24.png"))); // NOI18N
        BtnDeleteTask.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnDeleteTask.setBorderPainted(false);
        BtnDeleteTask.setName("BtnDeleteTask"); // NOI18N
        BtnDeleteTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeleteTaskActionPerformed(evt);
            }
        });
        PanelToolbar.add(BtnDeleteTask);
        BtnDeleteTask.setBounds(60, 5, 30, 30);

        BtnCalendarView.setBackground(new java.awt.Color(255, 255, 255));
        BtnCalendarView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/icons8-calendar-24.png"))); // NOI18N
        BtnCalendarView.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnCalendarView.setBorderPainted(false);
        BtnCalendarView.setName("BtnCalendarView"); // NOI18N
        BtnCalendarView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalendarViewActionPerformed(evt);
            }
        });
        PanelToolbar.add(BtnCalendarView);
        BtnCalendarView.setBounds(275, 5, 30, 30);

        BtnAddTask.setBackground(new java.awt.Color(255, 255, 255));
        BtnAddTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/icons8-plus-math-24.png"))); // NOI18N
        BtnAddTask.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAddTask.setBorderPainted(false);
        BtnAddTask.setName("BtnAddTask"); // NOI18N
        BtnAddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddTaskActionPerformed(evt);
            }
        });
        PanelToolbar.add(BtnAddTask);
        BtnAddTask.setBounds(10, 5, 30, 30);

        PanelTaskList.setName("PanelTaskList"); // NOI18N
        PanelTaskList.setPreferredSize(new java.awt.Dimension(300, 550));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(PanelTaskList);
        PanelTaskList.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );

        PanelTaskDetails.setBorder(null);
        PanelTaskDetails.setName("PanelTaskDetail"); // NOI18N
        PanelTaskDetails.setPreferredSize(new java.awt.Dimension(450, 550));
        PanelTaskDetails.setLayout(null);

        LblTaskName.setForeground(new java.awt.Color(0, 0, 0));
        LblTaskName.setLabelFor(InTaskName);
        LblTaskName.setText("Task Name:");
        LblTaskName.setName("LblTaskName"); // NOI18N
        PanelTaskDetails.add(LblTaskName);
        LblTaskName.setBounds(30, 30, 67, 16);

        LblTaskDescript.setForeground(new java.awt.Color(0, 0, 0));
        LblTaskDescript.setLabelFor(InTaskDescript);
        LblTaskDescript.setText("Task Description:");
        LblTaskDescript.setName("LblTaskDescription"); // NOI18N
        PanelTaskDetails.add(LblTaskDescript);
        LblTaskDescript.setBounds(30, 60, 96, 16);

        LblDueDate.setForeground(new java.awt.Color(0, 0, 0));
        LblDueDate.setText("Due Date:");
        LblDueDate.setName("LblDueDate"); // NOI18N
        PanelTaskDetails.add(LblDueDate);
        LblDueDate.setBounds(180, 200, 55, 16);

        BtnSaveTask.setText("Save & Collapse");
        BtnSaveTask.setName("BtnSaveTask"); // NOI18N
        BtnSaveTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSaveTaskActionPerformed(evt);
            }
        });
        PanelTaskDetails.add(BtnSaveTask);
        BtnSaveTask.setBounds(30, 270, 119, 28);

        BtnCompleteDelete.setText("Complete/Delete");
        BtnCompleteDelete.setName("BtnDeleteTask"); // NOI18N
        PanelTaskDetails.add(BtnCompleteDelete);
        BtnCompleteDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCompleteDeleteActionPerformed(evt);
            }
        });
        BtnCompleteDelete.setBounds(270, 270, 121, 28);

        InTaskName.setName("InTaskName"); // NOI18N
        PanelTaskDetails.add(InTaskName);
        InTaskName.setBounds(110, 20, 282, 28);

        InTaskDescript.setColumns(20);
        InTaskDescript.setRows(5);
        InTaskDescript.setName("InTaskDetails"); // NOI18N
        jScrollPane1.setViewportView(InTaskDescript);

        PanelTaskDetails.add(jScrollPane1);
        jScrollPane1.setBounds(30, 80, 361, 98);

        InDueDate.setText("dd/mm/yyyy");
        PanelTaskDetails.add(InDueDate);
        InDueDate.setBounds(160, 220, 105, 28);

        BtnCancel.setText("Cancel");
        PanelTaskDetails.add(BtnCancel);
        BtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelActionPerformed(evt);
            }
        });
        BtnCancel.setBounds(320, 270, 73, 28);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelToolbar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(PanelTaskList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTaskDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelTaskDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addComponent(PanelTaskList, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void BtnCancelActionPerformed(ActionEvent evt) {
        FnHideDetailPanel();
    }

    protected void BtnCompleteDeleteActionPerformed(ActionEvent evt) {
        // Messagebox to confirm

        // On confirm
            // Delete this current task
            // FnHideDetailPanel();
        // On Cancel
            // Leave panel up
    }

    protected void BtnSaveTaskActionPerformed(ActionEvent evt) {
        // Input Handle/ Check core fields have value

        // If input handling returns good
            // Process
                // If panelTaskCreate is true
                    //create task
                // Else
                    // Edit Task
            // Hide panel

        // Else input handling returns bad
            // Tell User Why it went poorly
    }

    protected void BtnDeleteTaskActionPerformed(ActionEvent evt) {
        // Loop through all listed pannels
            // For each that has a tick box
                // If ticked then confirm delete the task
                // Delete the task if confirm yes
                // Continue if pass on delete
        // Refresh the list
        FnUpdate();
    }

    protected void BtnCalendarViewActionPerformed(ActionEvent evt) {
        // Open calendar view form
        // CalendarView calendarView = new CalendarView();
        // calendarView.setVisible(true);
        this.setVisible(false);
    }

    private void BtnAddTaskActionPerformed(java.awt.event.ActionEvent evt) {
        FnShowDetailPanel();
        FnAddTaskSetup();
    }      

    private void FnShowDetailPanel(){
        this.setSize(fullSize, height);
        PanelToolbar.setSize(PanelTaskList.getWidth() + PanelTaskDetails.getWidth(), PanelToolbar.getHeight());
        PanelTaskDetails.setVisible(true);
    }

    private void FnHideDetailPanel(){
        this.setSize(partSize, height);
        PanelToolbar.setSize(PanelTaskList.getWidth(), PanelToolbar.getHeight());
        PanelTaskDetails.setVisible(false);
        BtnCompleteDelete.setVisible(false);
        BtnCancel.setVisible(false);
    }

    private void FnAddTaskSetup(){
        panelTaskCreate = true;
        BtnSaveTask.setText("Create Task");
        BtnCompleteDelete.setVisible(false);
        BtnCancel.setVisible(true);
    }

    private void FnEditTaskSetup(int id){
        panelTaskCreate = false;
        BtnSaveTask.setText("Save Task");
        BtnCompleteDelete.setText("Complete/Delete Task");
        BtnCompleteDelete.setVisible(true);
        BtnCancel.setVisible(false);

        InTaskName.setText(controller.FindTaskById(id).getTaskName());
        InTaskDescript.setText(controller.FindTaskById(id).getDetails());
    }

    private void FnUpdate(){

    }

    // TODO: Action for focus or text input length changes

    private LocalDate FnInputHandleTaskDate(String date){
        if (date == null || date.isEmpty()){
            return null;
        }

        SimpleDateFormat format = (date.charAt(2) == '/') ? new SimpleDateFormat("dd/MM/yy")
                                                          : new SimpleDateFormat("dd-MM-yy");

        try {
            format.parse(date);
            return LocalDate.parse(date);
        } catch (ParseException e) {
            // TODO: MessageBox here
        }
        return null;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAddTask;
    private javax.swing.JButton BtnDeleteTask;
    private javax.swing.JButton BtnCalendarView;
    private javax.swing.JButton BtnSaveTask;
    private javax.swing.JButton BtnCompleteDelete;
    private javax.swing.JLabel LblTaskName;
    private javax.swing.JLabel LblTaskDescript;
    private javax.swing.JLabel LblDueDate;
    private javax.swing.JPanel PanelToolbar;
    private javax.swing.JPanel PanelTaskList;
    private javax.swing.JPanel PanelTaskDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea InTaskDescript;
    private javax.swing.JTextField InTaskName;
    private javax.swing.JTextField InDueDate;
    private javax.swing.JButton BtnCancel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void TaskCreated(int id, LocalDate date, String... created) {
        FnUpdate();
    }

    @Override
    public void TaskUpdated(int id, String... updates) {
        FnUpdate();        
    }

    @Override
    public void TaskDeleted(int taskId) {
        FnUpdate();        
    }

    @Override
    public void RequestRefresh(){
        FnUpdate();
    }
}
