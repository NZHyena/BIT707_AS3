import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;

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
    
    private List<Task> allTasks;
    private final DatabaseConnection db = new DatabaseConnection();
    
    public TaskController(){
        allTasks = new ArrayList<Task>(); 
    }

    public List<Task> getAllTasks(){
        return allTasks;
    }
    
    public void CreateTask(int id, String taskName){
        allTasks.add(new Task(id, taskName));
        // Add to database
    }
    
    public void CreateTask(int id, String taskName, String description){
        allTasks.add(new Task(id, taskName, description));
        // Add to database
    }
    
    public void CreateTask(int id, String taskName, String description, Date date){
        allTasks.add(new Task(id, taskName, description, date));
        // Add to database
    }
    
    public void DeleteTask(Task ta){
        allTasks.remove(ta);
        // Remove from database
    }
    
    public void EditTask(int id, String taskname, String description, String date){
        FindTaskById(id).setTaskName(taskname);
        if(!description.isEmpty())
            FindTaskById(id).setDescription(description);
        if(!date.isEmpty()){
            FindTaskById(id).setDate(new Date(date));
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
    public void initialLoad(){
        ResultSet results = db.SelectAllTasks();
        
        Task temp;
        String tempDetails = null;
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
                
                // TODO: This needs a replacement since date is depreciated
                if(results.getDate("date") != null){
                    hasDate = true;
                    tempDate.setDate(results.getDate("date"));
                }
                
                if(hasDate && hasDetails)
                    allTasks.add(new Task(tempId, tempName, tempDetails, tempDate));
                else if(hasDate || hasDetails){
                    if(hasDate)
                        allTasks.add(new Task(tempId, tempName, "", tempDate));
                    else
                        allTasks.add(new Task(tempId, tempName, tempDetails));
                }
                else
                    allTasks.add(new Task(tempId, tempName));

            }
        } catch (Exception e){ // End of try, Start of catch
            // Displaying relevant exception feedback
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            // Closing the application
            System.exit(0);

        } // End of Catch exception handling
        
        db.CloseConnection();
        
    }
}
