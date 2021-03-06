package main;
import main.TaskItemPanel.ItemPanelListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

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
 * Main Application form
 * @author Grant Docherty - 5032768
 */
public class TaskListUI extends JFrame implements TaskListener, ItemPanelListener{

    // Initialize global form variables
    private int fullSize;
    private int partSize;
    private int height;
    private int editTaskId;
    private TaskController controller = new TaskController();
    private boolean panelTaskCreate = false;
    private List<JPanel> PanelList = new ArrayList<JPanel>();
    
    /**
     * Creates new form TaskListUI
     */
    public TaskListUI() {
        // Initalize components
        initComponents();
        
        // Initalize variables related to form size changes
        fullSize = this.getWidth();
        height = this.getHeight();
        partSize = fullSize - PanelTaskDetails.getWidth();

        // Hide the Detail Panel
        FnHideDetailPanel();

        // Load the Serializable file
        controller.InitialLoad();

        // Load all existing tasks from the database
        controller.LoadAllTasks();

        // Update the form
        FnUpdate();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelToolbar = new JPanel();
        BtnDeleteTask = new JButton();
        BtnCalendarView = new JButton();
        BtnAddTask = new JButton();
        PanelTaskList = new JPanel();
        PanelTaskDetails = new JPanel();
        LblTaskName = new JLabel();
        LblTaskDescript = new JLabel();
        LblDueDate = new JLabel();
        BtnSaveTask = new JButton();
        BtnCompleteDelete = new JButton();
        InTaskName = new JTextField();
        jScrollPane1 = new JScrollPane();
        InTaskDescript = new JTextArea();
        InDueDate = new JTextField();
        BtnCancel = new JButton();
        ToolbarSeparator = new JSeparator();
        LblPanelState = new JLabel();

        box = Box.createVerticalBox();        

        jScrollPane2 = new JScrollPane(box);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new Dimension(323, 550));
        jScrollPane2.setMinimumSize(jScrollPane2.getPreferredSize());
        jScrollPane2.setMaximumSize(jScrollPane2.getPreferredSize());
        

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setName("To Do List"); // NOI18N
        setPreferredSize(new Dimension(768, 650));
        setMinimumSize(new Dimension(partSize, 650));
        setResizable(false);

        PanelToolbar.setBorder(null);
        PanelToolbar.setName("PanelTools"); // NOI18N
        PanelToolbar.setPreferredSize(new Dimension(760, 35));
        PanelToolbar.setLayout(null);

        BtnDeleteTask.setBackground(new Color(255, 255, 255));
        BtnDeleteTask.setIcon(new ImageIcon(getClass().getResource("/main/resources/icons8-remove-24.png"))); // NOI18N
        BtnDeleteTask.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnDeleteTask.setBorderPainted(false);
        BtnDeleteTask.setName("BtnDeleteTask"); // NOI18N
        BtnDeleteTask.setToolTipText("Deletes all tasks that have been ticked as completed");
        BtnDeleteTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BtnDeleteTaskActionPerformed(evt);
            }
        });
        PanelToolbar.add(BtnDeleteTask);
        BtnDeleteTask.setBounds(60, 5, 30, 30);

        BtnCalendarView.setBackground(new Color(255, 255, 255));
        BtnCalendarView.setIcon(new ImageIcon(getClass().getResource("/main/resources/icons8-calendar-24.png"))); // NOI18N
        BtnCalendarView.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnCalendarView.setBorderPainted(false);
        BtnCalendarView.setName("BtnCalendarView"); // NOI18N
        BtnCalendarView.setToolTipText("Opens the Calendar Report");
        BtnCalendarView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BtnCalendarViewActionPerformed(evt);
            }
        });
        PanelToolbar.add(BtnCalendarView);
        BtnCalendarView.setBounds(275, 5, 30, 30);

        BtnAddTask.setBackground(new Color(255, 255, 255));
        BtnAddTask.setIcon(new ImageIcon(getClass().getResource("/main/resources/icons8-plus-math-24.png"))); // NOI18N
        BtnAddTask.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAddTask.setBorderPainted(false);
        BtnAddTask.setName("BtnAddTask"); // NOI18N
        BtnAddTask.setToolTipText("Opens the 'Create Task' Panel");
        BtnAddTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BtnAddTaskActionPerformed(evt);
            }
        });
        PanelToolbar.add(BtnAddTask);
        BtnAddTask.setBounds(10, 5, 30, 30);

        PanelTaskList.setName("PanelTaskList"); // NOI18N
        PanelTaskList.setPreferredSize(new Dimension(325, 550));
        PanelTaskList.setMaximumSize(PanelTaskList.getPreferredSize());
        PanelTaskList.setLayout(new BorderLayout());
        PanelTaskList.add(jScrollPane2, BorderLayout.NORTH);

        PanelTaskDetails.setBorder(null);
        PanelTaskDetails.setName("PanelTaskDetail"); // NOI18N
        PanelTaskDetails.setPreferredSize(new Dimension(450, 550));
        PanelTaskDetails.setLayout(null);

        LblPanelState.setHorizontalAlignment(SwingConstants.CENTER);
        LblPanelState.setForeground(new Color(0, 0, 0));
        LblPanelState.setText("Create Task");
        LblPanelState.setFont(new Font("sansserif", Font.BOLD, 14));
        LblPanelState.setName("LblTaskName"); // NOI18N
        PanelTaskDetails.add(LblPanelState);
        LblPanelState.setBounds(0, 15, 450, 16);

        LblTaskName.setForeground(new Color(0, 0, 0));
        LblTaskName.setLabelFor(InTaskName);
        LblTaskName.setText("Task Name:");
        LblTaskName.setName("LblTaskName"); // NOI18N
        PanelTaskDetails.add(LblTaskName);
        LblTaskName.setBounds(30, 60, 67, 16);

        LblTaskDescript.setForeground(new Color(0, 0, 0));
        LblTaskDescript.setLabelFor(InTaskDescript);
        LblTaskDescript.setText("Task Description:");
        LblTaskDescript.setName("LblTaskDescription"); // NOI18N
        PanelTaskDetails.add(LblTaskDescript);
        LblTaskDescript.setBounds(30, 90, 96, 16);

        LblDueDate.setForeground(new Color(0, 0, 0));
        LblDueDate.setText("Due Date:");
        LblDueDate.setName("LblDueDate"); // NOI18N
        PanelTaskDetails.add(LblDueDate);
        LblDueDate.setBounds(180, 230, 55, 16);

        BtnSaveTask.setText("Save & Collapse");
        BtnSaveTask.setName("BtnSaveTask"); // NOI18N
        BtnSaveTask.setEnabled(false);
        BtnSaveTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BtnSaveTaskActionPerformed(evt);
            }
        });
        PanelTaskDetails.add(BtnSaveTask);
        BtnSaveTask.setBounds(30, 300, 119, 28);

        BtnCompleteDelete.setText("Complete Task");
        BtnCompleteDelete.setName("BtnDeleteTask"); // NOI18N
        PanelTaskDetails.add(BtnCompleteDelete);
        BtnCompleteDelete.setToolTipText("Click to Delete this task");
        BtnCompleteDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BtnCompleteDeleteActionPerformed(evt);
            }
        });
        BtnCompleteDelete.setBounds(30, 330, 121, 28);

        InTaskName.setName("InTaskName"); // NOI18N
        PanelTaskDetails.add(InTaskName);
        InTaskName.setBounds(110, 50, 282, 28);
        InTaskName.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                InTaskNameFocusGained(evt);
            }
            public void focusLost(FocusEvent evt) {
                InTaskNameFocusLost(evt);
            }
        });

        InTaskDescript.setColumns(20);
        InTaskDescript.setRows(5);
        InTaskDescript.setName("InTaskDetails"); // NOI18N
        jScrollPane1.setViewportView(InTaskDescript);

        PanelTaskDetails.add(jScrollPane1);
        jScrollPane1.setBounds(30, 110, 361, 98);

        InDueDate.setText("dd/mm/yyyy");
        PanelTaskDetails.add(InDueDate);
        InDueDate.setBounds(160, 250, 105, 28);

        BtnCancel.setText("Cancel");
        PanelTaskDetails.add(BtnCancel);
        BtnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BtnCancelActionPerformed(evt);
            }
        });
        BtnCancel.setBounds(320, 300, 75, 28);
        getContentPane().add(ToolbarSeparator);
        ToolbarSeparator.setBounds(0, 40, 739, 10);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelToolbar, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(PanelTaskList, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTaskDetails, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelToolbar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(PanelTaskDetails, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addComponent(PanelTaskList, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    /** 
     * Event that fires once focus changes from the Task Name Input field
     * @param evt - The Focus event the fires when focus changes
     */
    protected void InTaskNameFocusLost(FocusEvent evt) {
        // Start of if conditional to check the length of the task name is greater than 0
        if (InTaskName.getText().length() > 0){
            // Set the Save button to enabled
            BtnSaveTask.setEnabled(true);
        } else{ // End of if conditional, start of else conditional
            BtnSaveTask.setEnabled(false);
        } // End of else conditional
    }
    
    /**
     * Event that fires once focus changes to the Task Name Input field 
     * @param evt - The Focus event that fires when focus changes
     */
    protected void InTaskNameFocusGained(FocusEvent evt) {
        // Start of if conditional to checl the length of the task name is greater than 0
        if (InTaskName.getText().length() > 0){
            // Set the Save button to enabled
            BtnSaveTask.setEnabled(true);
        } else{ // End of if conditional, start of else conditional
            BtnSaveTask.setEnabled(false);
        } // End of else condtional
    }


    
    /** 
     * Event that fires when the Cancel button is clicked
     * @param evt - The Action event that fires when a button is clicked
     */
    protected void BtnCancelActionPerformed(ActionEvent evt) {
        // Hide the detail panel
        FnHideDetailPanel();
    }


    
    /** 
     * // Event that fires when the Complete/Delete button is clicked
     * @param evt - The Action event that fires when a button is clicked
     */
    protected void BtnCompleteDeleteActionPerformed(ActionEvent evt) {

        // Initalize local variables
        String taskName = controller.FindTaskById(editTaskId).getTaskName();
        LocalDate date = controller.FindTaskById(editTaskId).getDate();

        // Initalize message for JOptionPane
        String msg = "Are your sure you wish to delete\nTask: " + taskName;

        // Start of if conditional to determine if date information can be included
        if (date != null){
            msg += "\nDue: " + date;
        } // End of if conditional

        // Additional message information
        msg += "?";

        // Initialize JOptionPane and get the result
        int result = JOptionPane.showConfirmDialog(
            this, 
            msg, "Confirm Task Delete", JOptionPane.YES_NO_OPTION);

        // Start of if conditional to determine if we delete the task
        if (result == JOptionPane.YES_OPTION){
            controller.DeleteTask(controller.FindTaskById(editTaskId));

        FnUpdate();
        FnHideDetailPanel();
        } // End of if conditional
    }


    
    /** 
     * Event that fires when the Save Task button is clicked
     * @param evt - The Action event that fires when a button is clicked
     */
    protected void BtnSaveTaskActionPerformed(ActionEvent evt) {
        // Start of if conditional for if the Detail panel is in create mode
        if (panelTaskCreate){
            // Call the Create Task Function
            FnCreateTask();
        } else { // End of if conditional, start of else conditional
            // Call the Edit Task Function
            FnEditTask();
        } // End of else conditional

        // Update the form
        FnUpdate();
    }

    /**
     * Method for creating a task in the form
     */
    private void FnCreateTask(){
        // Initalize all task information from the form
        String strTaskName = InTaskName.getText();
        String strDate = InDueDate.getText();
        LocalDate date;
        String strDetails = InTaskDescript.getText();

        // Start of if conditional to handle date formatting
        if (strDate.matches("(\\d{1,2}(\\/|-)\\d{2}(\\/|-)\\d{4})")){
            date = FnInputHandleTaskDate(strDate);
        } else { // End of if conditional, start of else conditional
            date = null;
        } // End of else conditional

        // Start of if conditional
        if(strDetails.isEmpty()){
            strDetails = null;
        } // End of if conditional
        
        // Start of if conditional to handle Task Name length
        if(strTaskName.length() > 50){
            // Display fail message box
            String msg = "Input Error: Task name cannot exceed 50 characters in length";
            JOptionPane.showConfirmDialog(
                    this, 
                    msg, "Input Error", JOptionPane.OK_OPTION);
            InTaskName.setText("");
        } else { // End of if conditional, start of else conditional
            
            // Start of nested if conditional
            if(date == null && strDetails == null){

                // Create a task with just a task name
                controller.CreateTask(strTaskName);

            } else if(strDetails == null){ // End of if conditional, start of if else conditional

                // Create a task with a task name and due date
                controller.CreateTask(strTaskName, date);

            } else if(date == null){ // End of else if conditional, start of else conditional

                // Create a task with a task name and task details
                controller.CreateTask(strTaskName, strDetails);

            } else{ // End of if else conditional, start of else conditonal

                // Create a task with all task information
                controller.CreateTask(strTaskName, strDetails, date);

            } // End of else conditonal

            // Show a Success messagebox
            JOptionPane.showConfirmDialog(
                this, 
                "Task Created Successfully", "Success", JOptionPane.OK_OPTION);
            FnHideDetailPanel();
        } // End of else conditional

        // Clear all fields
        InDueDate.setText("dd/mm/yyyy");
        InTaskDescript.setText("");
        InTaskName.setText("");
    }

    /**
     * Method for editing a task
     */
    private void FnEditTask(){
        // Initalize local method variables from form text input
        String strTaskName = InTaskName.getText();
        LocalDate date;
        String strDate = InDueDate.getText();
        String strDetails = InTaskDescript.getText();


        // Start of if conditional to handle date formatting
        if (strDate.matches("(\\d{1,2}(\\/|-)\\d{2}(\\/|-)\\d{4})")){
            date = FnInputHandleTaskDate(strDate);
        } else { // End of if conditional, start of else conditional
            date = null;
        } // End of else conditional

        
        // Start of if conditional to handle Task Name length
        if(strTaskName.length() > 50){
            // Display fail message box
            String msg = "Input Error: Task name cannot exceed 50 characters in length";
            JOptionPane.showConfirmDialog(
                    this, 
                    msg, "Input Error", JOptionPane.OK_OPTION);
            InTaskName.setText("");
        } else { // End of if conditional, start of else conditional

            // Start of if conditional
            if(date == null){
                // Send updated task info to be processed excluding date
                controller.EditTask(editTaskId, strTaskName, strDetails, null);
            } else{ // End of if conditional, start of else conditional
                // Send updated task infor to be processed
                controller.EditTask(editTaskId, strTaskName, strDetails, date.toString());
            } // End of else conditional

            // Show success message
            JOptionPane.showConfirmDialog(
                this, 
                "Task Edited Successfully", "Success", JOptionPane.OK_OPTION);

            // Hide the Detail panel
            FnHideDetailPanel();

             // Clear all fields
            InDueDate.setText("dd/mm/yyyy");
            InTaskDescript.setText("");
            InTaskName.setText("");

        } // End of if conditional
    }


    
    /** 
     * Event that fires when the Delete Task button is clicked
     * @param evt - The Action event that fires when a button is clicked
     */
    protected void BtnDeleteTaskActionPerformed(ActionEvent evt) {

        // Initalize a list of TaskItemPanels
        List<TaskItemPanel> tPanels = new ArrayList<TaskItemPanel>();

        // Start of for each loop to iterate over PanelList
        for (JPanel panel : PanelList) {

            // Start of if conditonal to pull all TaskItemPanels from the PanelList
            if(panel.getName() != "TaskHeadingPanel" && panel.getName() != "ListEmptyHeadingPanel")
                tPanels.add((TaskItemPanel) panel);
            // End of if conditional
        } // End of for each loop

        // Start of for each loop to iterate over tPanelList
        for (TaskItemPanel taskItem : tPanels) {

            // Start of if conditional to check if the Checkbox on the TaskItemPanel is currently ticked
            if(taskItem.FnGetCheckbox()){

                // Initalize local method variables
                int taskId = taskItem.FnGetTaskID();
                String taskName = controller.FindTaskById(taskId).getTaskName();

                // Initalize JOptionPane message
                String msg = "Are your sure you wish to delete Task:\n" + taskName;

                // Start of if conditional to add date information to the JOptionPane
                if (controller.FindTaskById(taskId).getDate() != null){
                    msg += " Due: " + controller.FindTaskById(taskId).getDate();
                } // End of if conditional

                // Addition JOptionPane message information
                msg += "?";

                // Get result of JOptionPane
                int result = JOptionPane.showConfirmDialog(
                    this, 
                    msg, "Confirm Task Delete", JOptionPane.YES_NO_OPTION);

                // Start of if conditional to delete the task if confirmed
                if (result == JOptionPane.YES_OPTION){
                    controller.DeleteTask(controller.FindTaskById(taskId));
                } // End of if conditional
            } // End of if conditonal
        } // End of for each loop

        // Refresh the list
        FnUpdate();
    }


    
    /** 
     * Event that fires when the Calendar View Button is clicked
     * @param evt - The Action event that fires when a button is clicked
     */
    protected void BtnCalendarViewActionPerformed(ActionEvent evt) {
        // Open a new Calendar view form
        CalendarView calendarView = new CalendarView(this, controller);
        calendarView.setVisible(true);

        // Hide this form
        this.setVisible(false);
    }


    
    /**
     * Event that fires when the Add Task Button is clicked
     * @param evt - The Action event that fires when a button is clicked
     */
    private void BtnAddTaskActionPerformed(ActionEvent evt) {
        // Show the Detail Panel
        FnShowDetailPanel();

        // Setup the Detail Panel to create tasks
        FnAddTaskSetup();
    }     

    /**
     * Method to show the Detail panel
     */
    private void FnShowDetailPanel(){
        // Set the size of the TaskListUI JFrame
        this.setSize(fullSize, height);

        // Increase the size of the Toolbar panel to match
        PanelToolbar.setSize(PanelTaskList.getWidth() + PanelTaskDetails.getWidth(), PanelToolbar.getHeight());

        // Set the Detail Panel to be visible
        PanelTaskDetails.setVisible(true);
    }

    /**
     * Method to hide the Detail Panel
     */
    private void FnHideDetailPanel(){

        // set the size of the TaskListUi JFrame
        this.setSize(partSize, height);

        // Decrease the size of the Toolbar panel to match
        PanelToolbar.setSize(PanelTaskList.getWidth(), PanelToolbar.getHeight());

        // Set the Detail Panel to be invisible
        PanelTaskDetails.setVisible(false);

        // Initalize list of TaskItemPanels
        List<TaskItemPanel> tPanels = new ArrayList<TaskItemPanel>();

        // Start of for each loop to iterate over the PanelList
        for (JPanel panel : PanelList) {

            // Start of if conditional to add TaskItemPanels to the list
            if(panel.getName() != "TaskHeadingPanel" && panel.getName() != "ListEmptyHeadingPanel")
                tPanels.add((TaskItemPanel) panel);
            // End of if conditional
        } // End of for each loop

        // Start of for each loop to iterate over the tPanels list
        for (TaskItemPanel taskItem : tPanels) {

            // Start of if conditional to set any open Tasks to the closed state
            if(taskItem.FnGetTaskOpen()){
                taskItem.FnSetTaskClosed();

                // As there should only ever be one task open, as soon as we find one that is we can break the loop
                break;
            } // End of if conditional
        } // End of for each loop
    }

    /**
     * Method to setup the Task Detail Panel for creating new tasks
     */
    private void FnAddTaskSetup(){
        // Set the Panel status to create
        panelTaskCreate = true;

        // Set text on panle to match the panel state
        LblPanelState.setText("Create Task");
        BtnSaveTask.setText("Create Task");
        InDueDate.setText("dd/mm/yyyy");
        InTaskDescript.setText("");
        InTaskName.setText("");

        // Initalize list of TaskItemPanels
        List<TaskItemPanel> tPanels = new ArrayList<TaskItemPanel>();
        
        // Start of for each loop to iterate over the PanelList
        for (JPanel panel : PanelList) {

            // Start of if conditional to add TaskItemPanels to the list
            if(panel.getName() != "TaskHeadingPanel" && panel.getName() != "ListEmptyHeadingPanel")
                tPanels.add((TaskItemPanel) panel);
            // End of if conditional
        } // End of for each loop

        // Start of for each loop to iterate over the tPanels list
        for (TaskItemPanel taskItem : tPanels) {

            // Start of if conditional to set any open Tasks to the closed state
            if(taskItem.FnGetTaskOpen()){
                taskItem.FnSetTaskClosed();

                // As there should only ever be one task open, as soon as we find one that is we can break the loop
                break;
            } // End of if conditional
        } // End of for each loop
    }


    
    /** 
     * Method to setup the Task Detail Panel for editing an existing task
     * @param id - The id of the task to edit
     */
    private void FnEditTaskSetup(int id){
        // Set the state of the panel from create to edit
        panelTaskCreate = false;

        // Set all components on the panel to match
        LblPanelState.setText("View and Edit Task");
        BtnSaveTask.setText("Save Task");
        BtnSaveTask.setEnabled(true);
        BtnCompleteDelete.setText("Complete Task");
        editTaskId = id;
        InTaskName.setText(controller.FindTaskById(id).getTaskName());
        InTaskDescript.setText(controller.FindTaskById(id).getDetails());

        // Start of if conditional to set date information if it exists
        if (controller.FindTaskById(id).getDate() != null){
            LocalDate date = controller.FindTaskById(id).getDate();
            InDueDate.setText(String.format("%td/%tm/%tY", date, date, date));
        } else { // End of if conditional, start of else conditional
            InDueDate.setText("");
        } // End of else conditional
    }

    /**
     * Method to update the form once changes have been made
     */
    private void FnUpdate(){

        // Remove all components from the box
        box.removeAll();

        // Remove all Panels from the PanelList
        PanelList.clear();

        // Revalidate and repaint the Panel
        PanelTaskList.revalidate();
        PanelTaskList.repaint();

        // Load all tasks
        FnLoadAllTasks();
        
    }

    /**
     * Method to load all tasks that exist into the task list panel
     */
    private void FnLoadAllTasks(){

        // Initializing local method variables
        LocalDate previousDate = null;
        TaskItemPanel tmp;

        // Initializing a JSeparator to add between task headings
        JSeparator taskSeparator = new JSeparator();
        taskSeparator.setPreferredSize(new Dimension(300, 2));
        taskSeparator.setMaximumSize(taskSeparator.getPreferredSize());

        // Start of if conditional to set a empty state UX pattern if no tasks exist
        if(controller.getAllTasks().size() == 0){
            PanelList.add(new ListEmptyHeadingPanel());
        } else { // End of if conditional, start of else conditional

            // Sort the tasks
            controller.SortTask();

            // Start of for each loop to iterate over the list of all tasks
            for (Task t : controller.getAllTasks()) {
                // Start of if conditional
                if (previousDate == t.getDate()){
                   
                    // If the tasks dates match then this task falls under the same date heading as the previous

                    // Create a new TaskItemPanel
                    tmp = new TaskItemPanel(t);

                    // Add this JFrame to the list of ItemPanelListener
                    tmp.addListener(this);

                    // Add the JPanel to the Panel List
                    PanelList.add(tmp);

                } else if(previousDate != t.getDate()){ // End of if conditional, start of else if conditional
                    
                    // If task dates do not match then a new task heading panel need to be created

                    // Add a new TaskHeadingPanel to the Panel List
                    PanelList.add(new TaskHeadingPanel(t.getDate()));
                   
                    // Create a new TaskItemPanel
                    tmp = new TaskItemPanel(t);

                    // Add this JFrame to the list of ItemPanelListener
                    tmp.addListener(this);

                    // Add the JPanel to the Panel List
                    PanelList.add(tmp);
                    
                } else { // End of else if conditional, start of else conditional
                    // Else, this is the first task in the list and needs a heading regardless

                    // Add a new TaskHeadingPanel to the Panel List
                    PanelList.add(new TaskHeadingPanel(t.getDate()));
                   
                    // Create a new TaskItemPanel
                    tmp = new TaskItemPanel(t);

                    // Add this JFrame to the list of ItemPanelListener
                    tmp.addListener(this);

                    // Add the JPanel to the Panel List
                    PanelList.add(tmp);

                } // End of if conditional

                // Set the previous date to the date of this task
                previousDate = t.getDate();
            } // End of for each loop
        } // End of else conditional

        // Start of for each loop to iterate over the list of Panels in the PanelList
        for (JPanel jPanel : PanelList) {

            // Add each panel to the list
            box.add(jPanel);

            // Add a separator between each panel
            box.add(taskSeparator);
        } // End of for each loop
    }


    
    /** 
     * Method to handle the conversion of a String date into a LocalDate variable handled by the rest of the code
     * @param date - The string date input
     * @return LocalDate - The LocalDate conversion (null if parse fails)
     */
    private LocalDate FnInputHandleTaskDate(String date){

        // Start of if conditional
        if (date == null || date.isEmpty()){
            return null;
        } // End of if conditional

        // Initalizeing a new DateTimeFormatter
        DateTimeFormatter format = (date.charAt(2) == '/') ? DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                                          : DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Start of try block
        try {
            // Return parsed date
            return LocalDate.parse(date, format);
        } catch (DateTimeParseException e) { // End of try block, start of catch exeption handling

            // Display a message box to the user
            String msg = "Input Error: Due Date does not match the required 'dd/mm/yyyy' pattern (i.e. '11/02/2022')\nTask will be created with an empty due date";
            JOptionPane.showConfirmDialog(
                    this, 
                    msg, "InputError", JOptionPane.OK_OPTION);

            // Set the date input to default
            InDueDate.setText("dd/mm/yyyy");
        } // End of catch excpetion handling
        return null;
    }

    
    /** 
     * Override method to implement the TaskCreated TaskListener interface
     * <p>
     * On a task being created in the application this JFrame needs to update to account for that
     * <p>
     * Params are provided by the notifier but not used in this instance
     * @param id - The Id of the task that was created (NOT USED BY THIS METHOD)
     * @param date - The date information of the created task (NOT USED BY THIS METHOD)
     * @param created - Extra information from task creation (NOT USED BY THIS METHOD)
     */
    @Override
    public void TaskCreated(int id, LocalDate date, String... created) {
        FnUpdate();
    }


    
    /** 
     * Override method to implement the TaskUpdated TaskListener interface
     * <p>
     * On a task being Updated in the application this JFrame needs to update to account for that
     * <p>
     * Params are provided by the notifier but not used in this instance
     * @param id - The Id of the task that was updated (NOT USED BY THIS METHOD)
     * @param updates - The updated task information (NOT USED BY THIS METHOD)
     */
    @Override
    public void TaskUpdated(int id, String... updates) {
        // Update the Frame
        FnUpdate();        
    }


    
    /** 
     * Override method to implement the TaskDeleted TaskListener interface
     * <p>
     * On a task being deleted in the application this JFrame needs to update to account for that
     * <p>
     * Params are provided by the notifier but not used in this instance
     * @param taskId - The task Id of the deleted task (NOT USED BY THIS METHOD)
     */
    @Override
    public void TaskDeleted(int taskId) {
        // Update the Frame
        FnUpdate();        
    }

    
    /** 
     * Override method to implement the TaskEditOpened ItemPanelListener interface
     * <p>
     * On a Task Item Panel being clicked to initiate editing a task, this JFrame needs to open the task detail panel 
     * and relevant task information for the user to edit
     * @param taskId - The id of the task to be edited or viewed
     */
    @Override
    public void TaskEditOpened(int taskId) {
        // Start of if conditional
        if (PanelTaskDetails.isVisible()){
            // If the panel is already visible we need to clear it before adding new information to it
            FnHideDetailPanel();
        } // End of if conditional

        // Setup the task panel to diplay relevant task information
        FnEditTaskSetup(taskId);

        // Show the detail panel
        FnShowDetailPanel();
    }

    /**
     * Override method to implement the TaskEditClosed ItemPanelListener interface
     * <p>
     * On a Task Item Panel being clicked to close the task edit and view panel, this JFrame needs to close the task detail panel 
     */
    @Override
    public void TaskEditClosed(){
        // Hid the task Detail panel
        FnHideDetailPanel();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton BtnAddTask;
    private JButton BtnDeleteTask;
    private JButton BtnCalendarView;
    private JButton BtnSaveTask;
    private JButton BtnCompleteDelete;
    private JLabel LblTaskName;
    private JLabel LblTaskDescript;
    private JLabel LblDueDate;
    private JPanel PanelToolbar;
    private JPanel PanelTaskList;
    private JPanel PanelTaskDetails;
    private JScrollPane jScrollPane1;
    private JTextArea InTaskDescript;
    private JTextField InTaskName;
    private JTextField InDueDate;
    private JButton BtnCancel;
    private JSeparator ToolbarSeparator;
    private Box box;
    private JScrollPane jScrollPane2;
    private JLabel LblPanelState;
    // End of variables declaration//GEN-END:variables
}
