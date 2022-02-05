package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

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
    
    // TODO: Look into using java.time.date instead of java.utli.date
    // TODO: Comment everything
    // TODO: Test Everything
        // TODO: Test against Test Plan
    // TODO: Create method to Check all tasks against all database entries when app closes

    private List<Task> allTasks;
    private final DatabaseConnection db = new DatabaseConnection();
    
    public TaskController(){
        allTasks = new ArrayList<Task>(); 
    }

    public List<Task> getAllTasks(){
        return allTasks;
    }
    
    public void CreateTask(String taskName){
        Task tmp = new Task(taskName);
        allTasks.add(tmp);
        db.CreateTask(tmp.getId(), tmp.getTaskName());
    }
    
    public void CreateTask(String taskName, String details){
        Task tmp = new Task(taskName, details);
        allTasks.add(tmp);
        db.CreateTask(tmp.getId(), tmp.getTaskName(), tmp.getDetails());
    }
    
    public void CreateTask(int id, String taskName, String details, Date date){
        Task tmp = new Task(taskName, details, date);
        allTasks.add(tmp);
        db.CreateTask(tmp.getId(), tmp.getTaskName(), tmp.getDetails(), (java.sql.Date)tmp.getDate());
    }

    public void CreateTask(int id, String taskName, Date date){
        Task tmp = new Task(taskName, date);
        allTasks.add(tmp);
        db.CreateTask(tmp.getId(), tmp.getTaskName(), (java.sql.Date)tmp.getDate());
    }
    
    public void DeleteTask(Task ta){
        allTasks.remove(ta);
        db.DeleteTask(ta.getId());
    }
    
    public void EditTask(int id, String taskName, String details, String date){
        FindTaskById(id).setTaskName(taskName);
        db.UpdateTaskName(id, taskName);
        if(!details.isEmpty())
            FindTaskById(id).setDescription(details);
            db.UpdateTaskDetails(id, details);
        if(!date.isEmpty()){
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
                Date tempDate = formatter.parse(date);
                FindTaskById(id).setDate(tempDate);
                db.UpdateTaskDate(id, (java.sql.Date) tempDate);
            } catch (Exception e) {
                e.printStackTrace();
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
    
    public void SortTask(){
        Collections.sort(allTasks);
    }
    
    public void CheckTasks(){
        
    }
    
    /// <summary>
    /// The initial load required to sync the database with the application
    /// </summary>
    public void LoadDbAllTasks(){
        ResultSet results = db.SelectAllTasks();
        String tempDetails = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        String strDate;
        Date tempDate = new Date();
        int tempId;
        String tempName;
        
        // Start of while loop to display database results
        try{
            while(results.next()){
                Boolean hasDate = false;
                Boolean hasDetails = false;
                
                tempId = results.getInt(1);
                tempName = results.getString("taskName");
                
                if(results.getString("details") != null){
                    hasDetails = true;
                    tempDetails = results.getString("details");
                }
                
                if(results.getString("date") != null){
                    hasDate = true;
                    strDate = results.getString("date");
                    tempDate = formatter.parse(strDate);
                }
                
                if(hasDate && hasDetails)
                    allTasks.add(new Task(tempName, tempDetails, tempDate));
                else if(hasDate || hasDetails){
                    if(hasDate)
                        allTasks.add(new Task(tempName, tempDate));
                    else
                        allTasks.add(new Task(tempName, tempDetails));
                }
                else
                    allTasks.add(new Task(tempName));

            }
        } catch (Exception e){ // End of try, Start of catch
            // Displaying relevant exception feedback
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            // Closing the application
            System.exit(0);

        } // End of Catch exception handling
        
        db.CloseConnection();
        
    }

    public void WriteSerializable(){
        try{
            FileOutputStream fileOut = new FileOutputStream("/tmp/TaskSingleton.ser");
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
            FileInputStream fileIn = new FileInputStream("/tmp/TaskSingleton.ser");
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
