package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.time.LocalDate;

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
 * The Controller class in the 'Model-View-Controller'.<!-- --> Handles all Task object manipulations (create, update, delete and view) from the application and too the database.
 * @author Grant Docherty - 5032768
 */
public class TaskController {

    // Initalize all global variables
    private List<Task> allTasks;
    private final DatabaseConnection db = new DatabaseConnection();
    private List<TaskListener> listeners = new ArrayList<TaskListener>();

    /**
     * A method to add a new TaskListener to the list of event listeners
     * @param toAdd - The new TaskListener
     */
    public void addListener(TaskListener toAdd){
        listeners.add(toAdd);
    }

    /**
     * A method to notify all TaskListeners that a Task has been updated
     * @param id - The primary key/idetifier for the task being updated
     * @param updates - A String array containing all the updates that have been made to the task
     */
    public void notifyEdit(int id, String...updates){
        // Start of for each loop iterating over listeners list
        for (TaskListener tl :listeners){
            tl.TaskUpdated(id, updates);
        } // End of for each loop
    }

    /**
     * Creates a new TaskController
     */
    public TaskController(){
        allTasks = new ArrayList<Task>();
        addListener(db);
    }

    /**
     * Getter to retrieve the allTasks List
     * @return - The allTasks List
     */
    public List<Task> getAllTasks(){
        return allTasks;
    }
    
    /**
     * A method to create a new Task object containing the minimun required to create a task (task name).
     * Also notifies all TaskListeners that a task has been created
     * @param taskName - The task name for the created task object
     */
    public void CreateTask(String taskName){
        // Initialize new temp task
        Task tmp = new Task(taskName);

        // Add the task to the list of all tasks
        allTasks.add(tmp);

        // Start of for each loop iterating over listeners list
        for (TaskListener tl : listeners) {
            tl.TaskCreated(tmp.getId(), null, tmp.getTaskName());
        } // End of for each loop

        // Call WriteSerializable method
        WriteSerializable();
    }
    
    /**
     * A method to create a new Task object containing the Task name and task details.
     * Also notifies all TaskListeners that a task has been created
     * @param taskName - The task name for the created task object
     * @param details - The Task's details
     */
    public void CreateTask(String taskName, String details){
        // Initialize new temp task
        Task tmp = new Task(taskName, details);

        // Add the task to the list of all tasks
        allTasks.add(tmp);

        // Start of for each loop iterating over listeners list
        for (TaskListener tl : listeners) {
            tl.TaskCreated(tmp.getId(), null, tmp.getTaskName(), tmp.getDetails());
        } // End of for each loop

        // Call WriteSerializable method
        WriteSerializable();
    }
    
    /**
     * A method to create a new Task object containing all Task information (task name, task details, and due date for the task)
     * Also notifies all TaskListeners that a task has been created
     * @param taskName - The task name for the created task object
     * @param details - The Task's details
     * @param date - The date the task is due
     */
    public void CreateTask(String taskName, String details, LocalDate date){
        // Initialize a new temp task
        Task tmp = new Task(taskName, details, date);

        // Add the task to the list of all tasks
        allTasks.add(tmp);

        // Start of for each loop iterating over listeners list
        for (TaskListener tl : listeners) {
            tl.TaskCreated(tmp.getId(), tmp.getDate(), tmp.getTaskName(), tmp.getDetails());
        } // End of for each loop

        // Call WriteSerializable method
        WriteSerializable();
    }

    /**
     * A method to create a new Task object containing the Task name and due date for the task
     * Also notifies all TaskListeners that a task has been created
     * @param taskName - The task name for the created task object
     * @param date - The date the task is due
     */
    public void CreateTask(String taskName, LocalDate date){

        // Initialize a new temp task
        Task tmp = new Task(taskName, date);

        // Add the taks to the list of all tasks
        allTasks.add(tmp);

        // Start of for each loop iterating over listeners list
        for (TaskListener tl : listeners) {
            tl.TaskCreated(tmp.getId(), tmp.getDate(), tmp.getTaskName());
        } // End of for each loop

        // Call WriteSerializable method
        WriteSerializable();
    }
    
    /**
     * Method to delete a task object
     * Also notifies all TaskListeners that a task has been deleted
     * @param ta - The task to delete
     */
    public void DeleteTask(Task ta){
        // Remove the task from the list of all tasks
        allTasks.remove(ta);

        // Start of for each loop iterating over listeners list
        for (TaskListener tl : listeners) {
            tl.TaskDeleted(ta.getId());
        } // End of for each loop
    }
    

    // TODO: Fix me
    // TODO: JavaDoc commenting after fix
    // TODO: General Commenting within method once fixed
    public void EditTask(int id, String taskName, String details, String date){
        if ((details.isEmpty() && date.isEmpty()) || (details == null && date == null)){
            FindTaskById(id).setTaskName(taskName);
            notifyEdit(id, taskName);
        }
        else if (!details.isEmpty() && details != null && !date.isEmpty() && date != null){
            FindTaskById(id).setDetails(details);
            LocalDate tempDate = LocalDate.parse(date);
            FindTaskById(id).setDate(tempDate);
            notifyEdit(id, taskName, details, date);
        }
        else{
            if(!details.isEmpty()){
                FindTaskById(id).setDetails(details);
                notifyEdit(id, taskName, details);
            }
            else{
                LocalDate tempDate = LocalDate.parse(date);
                FindTaskById(id).setDate(tempDate);
                notifyEdit(id, taskName, "", date);
            }
        }
    }
    
    /**
     * A Method to retrieve all task information in a string
     * @param ta - The Task object to display
     * @return - A String containing the the relevant task information
     */
    public String DisplayTask(Task ta){
        return ta.toString();
    }
    
    /**
     * A Method to retrieve a task object that matches the id provided
     * @param id - The id of the task to find
     * @return - The Tasks with the matching id
     */
    public Task FindTaskById(int id){
        // Initializie a new temp task
        Task temp = null;

        // Start of for each loop iterating over allTasks list
        for(Task t: allTasks){
            // Start of if conditional
            if (t.getId() == id)
                temp = t;
            // End of if conditonal
        } // End of for each loop
        return temp;
    }
    
    /**
     * A Method to retrieve all tasks that contain matching name information
     * <p>
     * Note: This currently goes unused in the current application version, but should remain in order to add search functionality in future
     * @param name - The Task name information to search with
     * @return - A List of all matching tasks
     */
    public List<Task> FindTasksByName(String name){
        // Initialize a new List of task objects
        List<Task> foundTasks = new ArrayList<Task>();

        // Start of for each loop iterating over allTasks list
        for(Task t: allTasks){
            // Start of if conditional
            if (t.getTaskName().contains(name))
                foundTasks.add(t);
            // End of if conditional
        } // End of for each loop

        return foundTasks;
    }

    /**
     * A Method to retrieve all tasks that have a matching due date
     * <p>
     * Note: Not currently used in full capacity, but should remain in order to add search/filter functionality in futre
     * @param date = The date information to search with
     * @return - A List of all matching tasks
     */
    public List<Task> FindTasksByDate(LocalDate date){
        // Initialize a new list of task objects
        List<Task> foundTasks = new ArrayList<Task>();

        // Call SortTask in order to make searching easier/more efficient
        SortTask();

        // Start of for each loop iterating over allTasks list
        for (Task t: allTasks){
            // Start of if conditional to check continue should a date value equal null
            if (t.getDate() == null)
                continue;
            else if (t.getDate().isBefore(date)) // End of if conditional, start of else if conditional to break the loop if the tasks being looked at are later in the list than we are seraching
                break;
            // End of else if conditional

            // Start of if conditional to add tasks with a matching date to the foundTasks list
            if (t.getDate().equals(date))
                foundTasks.add(t);
            // End of if conditional
        } // End of for each loop

        return foundTasks;
    }
    
    /**
     * A Method to retrieve all tasks that fall between two dates
     * <p>
     * Note: Method is currently used for CalendarView class, however full implementation would rely on adding it to a search/filter function in future versions
     * @param date1 - The first date range value
     * @param date2 - The second date range value
     * @return - A list of all matching task objects
     */
    public List<Task> FindTasksBetweenDates(LocalDate date1, LocalDate date2){
        // Initialize a new Task List
        List<Task> foundTasks = new ArrayList<Task>();

        // Initialize a boolean value incase date1 is later that date2
        boolean filpDates = false;

        // Start of if conditional to check if we need to flip date1 and date2 in later conditionals
        if (date1.isAfter(date2))
            filpDates = true;
        else if(date1.equals(date2)){ // End of if conditional, start of else if conditional to check that we shouldn't be looking at one single date should date1 and date2 match
            return FindTasksByDate(date1);
        } // End of else if conditional

        // Calling SortTask method in order to make searching easier/more efficient
        SortTask();

        // Start of for each loop iterating over the allTask list
        for (Task t: allTasks){

            // Start of if conditional to continue the loop if a task date = null
            if (t.getDate() == null)
                continue;
            // End of if conditional

            // Start of if conditional to act if the flipDates boolean is true
            if (filpDates){

                // If conditional to stop checking this task should if fall earlier than the first date range bound
                // i.e. date2 = 20/02/2022 and the compared date is the 19/02/2022
                if(t.getDate().isBefore(date2)){
                    continue;
                } // End of if conditional

                // Start of if conditional to add the Date to the list of found tasks if it equals date1 or date2
                if (t.getDate().equals(date2) || t.getDate().equals(date1)) {
                    foundTasks.add(t);
                } else if (t.getDate().isAfter(date2) && t.getDate().isBefore(date1)) { // End of if conditional, start of else if conditional should the task fall between date2 and date1
                    foundTasks.add(t);
                } // End of else if conditional

            } else { // End of if conditional, start of else conditonal to act if flipDates boolean is false

                // If conditional to stop checking this task should if fall earlier than the first date range bound
                // i.e. date2 = 20/02/2022 and the compared date is the 19/02/2022
                if(t.getDate().isBefore(date1)){
                    continue;
                }

                // Start of if conditional to add the Date to the list of found tasks if it equals date1 or date2
                if (t.getDate().equals(date1) || t.getDate().equals(date2)) {
                    foundTasks.add(t);
                } else if (t.getDate().isAfter(date1) && t.getDate().isBefore(date2)) { // End of if conditional, start of else if conditional should the task fall between date2 and date1
                    foundTasks.add(t);
                } // End of else if conditional

            } // End of else conditional
        } // End of for each loop

        return foundTasks;
    }

    /**
     * Method to sort all tasks in the allTask list
     * @see compareTo in the Task Class
     */
    public void SortTask(){
        Collections.sort(allTasks);
    }
    

    /**
     * The initial load required to sync the database with the application
     */
    public void LoadAllTasks(){

        // Initialize ResultSet by calling database connect and requesting all tasks currently in the database
        ResultSet results = db.SelectAllTasks();

        // Initializing other local variables
        Task tmp;
        String strDate;
        
        // Start of try block for execption handling
        try{
            // Start of while loop to display database results
            while(results.next()){

                // Initialize a new task object
                tmp = new Task();

                // Set the task name from the results
                tmp.setId(results.getInt("taskNumber"));

                // Set the current max id for the task singleton
                TaskSingleton.getInstance().setId(results.getInt("taskNumber"));

                // Set the task name from the results
                tmp.setTaskName(results.getString("taskName"));
                
                // Start of if conditional to set task details if any exist
                if(results.getString("details") != null){
                    tmp.setDetails(results.getString("details"));
                } // End of if conditional
                
                // Start of if conditional to set due date for the task if one exists
                if(results.getString("date") != null){
                    strDate = results.getString("date");
                    // Parse the date into a LocalDate format
                    tmp.setDate(LocalDate.parse(strDate));
                } // End of if conditional
            
                // Add task to a list of all tasks
                allTasks.add(tmp);
            }
        } catch (Exception e){ // End of try, Start of catch
            // Displaying relevant exception feedback
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            // TODO: Better handling here
            // Closing the application
            System.exit(0);

        } // End of Catch exception handling
        
        // Close the connection with the database
        db.CloseConnection();
    }

    /**
     * The InitalLoad in order to read and write the serializable TaskSingleton class whenever the application opens
     */
    public void InitialLoad(){
        // Initialize a new File relating to the tmp folder in the project file
        File directory = new File("./tmp/");

        // Start of if conditional to check that the file exists
        if(!directory.exists()){
            // Make the directory if it returns false
            directory.mkdirs();
        } // End of if conditional

        // Initalize a new file relating to the TaskSingleton.ser file stored in the rmp folder
        File f = new File("./tmp/TaskSingleton.ser");

        // Start of if conditional
        if(f.exists()){
            // Call ReadSerializable if file exists
            ReadSerializable();
        } else { // End of if conditional, start of else conditional
            // Call WriteSerializable then ReadSerializable if file does not exist
            WriteSerializable();
            ReadSerializable();
        } // End of else conditional
    }

    /**
     * The Method to write information to a serializable binary file.
     * Primarily for the TaskSingleton class to save the current instance of it
     */
    public void WriteSerializable(){
        // Start of try block
        try{

            FileOutputStream fileOut = new FileOutputStream("./tmp/TaskSingleton.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(TaskSingleton.getInstance());
            out.close();;
            fileOut.close();

        } catch (IOException e){ // End of try block, start of catch exception handling
            e.printStackTrace();
        } // End of catch block exception handling
    }

    /**
     * The Method to retureve infromation from the serializable binary file in the tmp folder.
     * Primarily for the TaskSingleton class to read a previous instance of it.
     */
    public void ReadSerializable(){
        // Start of try blcok
        try{
            FileInputStream fileIn = new FileInputStream("./tmp/TaskSingleton.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            TaskSingleton.SetInstance((TaskSingleton)in.readObject());
            in.close();
            fileIn.close();
        } catch (IOException i){ // End of try block, start of catch block for IOExceptions
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) { // End of IOException catch block, start of catch block for ClassNotFoundExceptions
            c.printStackTrace();
            return;
        } // End of Catch block exception handling
    }
}

/**
 * Observer pattern TaskListener interface in order to update listeners without repeated calls to and from.<!-- --> Primarily used to update the database without calling the DatabaseConnection class
 * @see CreateTask
 * @see notifyEdit
 * @see DeleteTask
 */
interface TaskListener {
    void TaskCreated(int id, LocalDate date, String...created);
    void TaskUpdated(int id, String...updates);
    void TaskDeleted(int taskId);
}
