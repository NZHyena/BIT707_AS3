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
 *
 * @author Grant Docherty - 5032768
 */
public class TaskController {
    
    // TODO: Comment everything

    private List<Task> allTasks;
    private final DatabaseConnection db = new DatabaseConnection();
    private List<TaskListener> listeners = new ArrayList<TaskListener>();

    public void addListener(TaskListener toAdd){
        listeners.add(toAdd);
    }

    public void notifyEdit(int id, String...updates){
        for (TaskListener tl :listeners){
            tl.TaskUpdated(id, updates);
        }
    }

    public TaskController(){
        allTasks = new ArrayList<Task>();
        addListener(db);
    }

    public List<Task> getAllTasks(){
        return allTasks;
    }
    
    public void CreateTask(String taskName){
        Task tmp = new Task(taskName);
        allTasks.add(tmp);
        for (TaskListener tl : listeners) {
            tl.TaskCreated(tmp.getId(), null, tmp.getTaskName());
        }
    }
    
    public void CreateTask(String taskName, String details){
        Task tmp = new Task(taskName, details);
        allTasks.add(tmp);
        for (TaskListener tl : listeners) {
            tl.TaskCreated(tmp.getId(), null, tmp.getTaskName(), tmp.getDetails());
        }
    }
    
    public void CreateTask(String taskName, String details, LocalDate date){
        Task tmp = new Task(taskName, details, date);
        allTasks.add(tmp);
        for (TaskListener tl : listeners) {
            tl.TaskCreated(tmp.getId(), tmp.getDate(), tmp.getTaskName(), tmp.getDetails());
        }
    }

    public void CreateTask(String taskName, LocalDate date){
        Task tmp = new Task(taskName, date);
        allTasks.add(tmp);
        for (TaskListener tl : listeners) {
            tl.TaskCreated(tmp.getId(), tmp.getDate(), tmp.getTaskName());
        }
    }
    
    public void DeleteTask(Task ta){
        allTasks.remove(ta);
        for (TaskListener tl : listeners) {
            tl.TaskDeleted(ta.getId());
        }
    }
    
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
    
    public String DisplayTask(Task ta){
        return ta.toString();
    }
    
    public Task FindTaskById(int id){
        Task temp = null;
        for(Task t: allTasks){
            if (t.getId() == id)
                temp = t;
        }
        return temp;
    }
    
    public List<Task> FindTasksByName(String name){
        List<Task> foundTasks = new ArrayList<Task>();
        for(Task t: allTasks){
            if (t.getTaskName().contains(name))
                foundTasks.add(t);
        }
        return foundTasks;
    }

    public List<Task> FindTasksByDate(LocalDate date){
        List<Task> foundTasks = new ArrayList<Task>();

        SortTask();

        for (Task t: allTasks){
            if (t.getDate() == null)
                continue;
            else if (t.getDate().isBefore(date))
                break;

            if (t.getDate().equals(date))
                foundTasks.add(t);
        }
        return foundTasks;
    }
    
    public List<Task> FindTasksBetweenDates(LocalDate date1, LocalDate date2){
        List<Task> foundTasks = new ArrayList<Task>();
        boolean filpDates = false;
        if (date1.isAfter(date2))
            filpDates = true;
        else if(date1.equals(date2)){
            return FindTasksByDate(date1);
        }

            SortTask();

        for (Task t: allTasks){
            if (t.getDate() == null)
                continue;
            
            if (filpDates){
                if(t.getDate().isBefore(date2)){
                    break;
                }

                if (t.getDate().equals(date2) || t.getDate().equals(date1)) {
                    foundTasks.add(t);
                }
                else if (t.getDate().isAfter(date2) && t.getDate().isBefore(date1)) {
                    foundTasks.add(t);
                }
            }
            else{
                if(t.getDate().isBefore(date1)){
                    break;
                }

                if (t.getDate().equals(date1) || t.getDate().equals(date2)) {
                    foundTasks.add(t);
                }
                else if (t.getDate().isAfter(date1) && t.getDate().isBefore(date2)) {
                    foundTasks.add(t);
                }
            }
        }

        return foundTasks;
    }

    public void SortTask(){
        Collections.sort(allTasks);
        for (TaskListener tl : listeners) {
            tl.RequestRefresh();
        }
    }
    
    /// <summary>
    /// The initial load required to sync the database with the application
    /// </summary>
    public void LoadAllTasks(){
        ResultSet results = db.SelectAllTasks();
        Task tmp;
        String strDate;
        
        // Start of while loop to display database results
        try{
            while(results.next()){
                tmp = new Task();
                tmp.setId(results.getInt("taskNumber"));
                TaskSingleton.getInstance().setId(results.getInt("taskNumber"));
                tmp.setTaskName(results.getString("taskName"));
                
                if(results.getString("details") != null){
                    tmp.setDetails(results.getString("details"));
                }
                
                if(results.getString("date") != null){
                    strDate = results.getString("date");
                    tmp.setDate(LocalDate.parse(strDate));
                }
            
                allTasks.add(tmp);
            }
        } catch (Exception e){ // End of try, Start of catch
            // Displaying relevant exception feedback
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            // Closing the application
            System.exit(0);

        } // End of Catch exception handling
        
        db.CloseConnection();
        
    }

    public void InitialLoad(){
        File directory = new File("./tmp/");
        if(!directory.exists()){
            directory.mkdirs();
        }

        File f = new File("./tmp/TaskSingleton.ser");
        if(f.exists()){
            ReadSerializable();
        }
        else {
            WriteSerializable();
            ReadSerializable();
        }
    }

    public void WriteSerializable(){
        try{
            FileOutputStream fileOut = new FileOutputStream("./tmp/TaskSingleton.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(TaskSingleton.getInstance());
            out.close();;
            fileOut.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ReadSerializable(){
        try{
            FileInputStream fileIn = new FileInputStream("./tmp/TaskSingleton.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            TaskSingleton.SetInstance((TaskSingleton)in.readObject());
            in.close();
            fileIn.close();
        } catch (IOException i){
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }
}

interface TaskListener {
    void TaskCreated(int id, LocalDate date, String...created);
    void TaskUpdated(int id, String...updates);
    void TaskDeleted(int taskId);
    void RequestRefresh();
}
