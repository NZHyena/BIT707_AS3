package main;

import java.util.Date;

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
public class Task implements Comparable<Task>{
    private int id;
    private String taskName;
    private String details;
    private Date date;
    
    // Base Constructor
    public Task(int id, String taskName){
        setId(id);
        setTaskName(taskName);
    }
    
    public Task(int id, String taskName, String description){
        this(id, taskName);
        setDescription(description);
    }

    public Task(int id, String taskName, Date date){
        this(id, taskName);
        setDate(date);
    }
    
    public Task(int id, String taskName, String description, Date date){
        this(id, taskName, description);
        setDate(date);
    }
    
    // Getters
    public int getId(){
        return id;
    }
    public String getTaskName(){
        return taskName;
    }
    public String getDetails(){
        return details;
    }
    public Date getDate(){
        return date;
    }
    
    // Setters
    public void setId(int newId){
        this.id = newId;
    }
    public void setTaskName(String newTaskName){
        this.taskName = newTaskName;
    }
    public void setDescription(String newDescription){
        this.details = newDescription;
    }
    public void setDate(Date newDate){
        this.date = newDate;
    }

    @Override
    public String toString() {
        String str = String.format("Task %d: %s", this.getId(), this.getTaskName());

        if(getDetails() != null && this.getDate()!= null){
            str += String.format(" - Details: %s - Due: %td/%tm/%ty", this.getDetails(), this.getDate(), this.getDate(), this.getDate());
        }
        else if (this.getDetails() != null || this.getDate() != null){
            if (this.getDetails() != null){
                str += String.format(" - Details: %s", this.getDetails());
            }
            else {
                str += String.format(" - Due: %tF", this.getDate());
            }
        }

        return str;
    }
    
    public int compareTo(Task ta){
        if(this.date == ta.date)
            return 0;
        else if (this.date.after(ta.date)) // Flip this if date ordering is incorrect
            return 1;
        else
            return -1;
    }
}
