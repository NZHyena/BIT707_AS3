package main;

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
 * The model in the the Model-View-Controller design pattern.<!-- --> Represents a task needing to be completed by the user
 * @author Grant Docherty - 5032768
 */
public class Task implements Comparable<Task>{

    // Initialization of variables
    private int id;
    private String taskName;
    private String details;
    private LocalDate date;
    
    /**
     * Intentionally blank class constructor for stepped Task object creation.
     */
    public Task(){
    }

    /**
     * Base Task constructor specifying the name of a task.
     * @param taskName - the name of the task
     */
    public Task(String taskName){
        id = TaskSingleton.NextId();
        setTaskName(taskName);
    }
    
    /**
     * Task constructor specifying the name of a task and the inherant details related to the task.
     * @param taskName - The name of the task
     * @param details - The task details
     */
    public Task(String taskName, String details){
        this(taskName);
        setDetails(details);
    }

    /**
     * Task constructor specifying the name and due date of a task.
     * @param taskName - The name of the task
     * @param date - The due date of the task
     */
    public Task(String taskName, LocalDate date){
        this(taskName);
        setDate(date);
    }
    
    /**
     * Task constructor specifying the name, details and due date of a task
     * @param taskName - The name of the task
     * @param details - The task details
     * @param date - The due date of the task
     */
    public Task(String taskName, String details, LocalDate date){
        this(taskName, details);
        setDate(date);
    }
    
    // Getters
    /**
     * Getter for the Task's primary identifier
     * @return - The integer representing the task
     */
    public int getId(){
        return id;
    }
    /**
     * Getter for the Task's name
     * @return - The Task's name
     */
    public String getTaskName(){
        return taskName;
    }
    /**
     * Getter for the Task's details
     * @return - The Task's details
     */
    public String getDetails(){
        return details;
    }
    /**
     * Getter for the Task's due date
     * @return - The Task's due date
     */
    public LocalDate getDate(){
        return date;
    }
    
    // Setters
    /**
     * Setter for the Task's primary identifier
     * @param newId - An Integer Value, usually set from the database
     */
    public void setId(int newId){
        this.id = newId;
    }
    /**
     * Setter for the Task's name
     * @param newTaskName - A String value of no more than 50 characters
     */
    public void setTaskName(String newTaskName){
        this.taskName = newTaskName;
    }
    /**
     * Setter for the Task's details
     * @param newDetails - A String value of no more than 500 characters
     */
    public void setDetails(String newDetails){
        this.details = newDetails;
    }
    /**
     * Setter for the Task's due date
     * @param newDate - A LocalDate value
     */
    public void setDate(LocalDate newDate){
        this.date = newDate;
    }

    /**
     * toString override method to represent all task information in a single string format.
     */
    @Override
    public String toString() {
        String str = String.format("Task %d: %s", this.getId(), this.getTaskName());

        // Start of if conditional
        if(getDetails() != null && this.getDate()!= null){
            str += String.format(" - Details: %s - Due: %td/%tm/%ty", this.getDetails(), this.getDate(), this.getDate(), this.getDate());
        } else if (this.getDetails() != null || this.getDate() != null){ // End of if conditional, start of else if
            // Start of nested if conditional
            if (this.getDetails() != null){
                str += String.format(" - Details: %s", this.getDetails());
            } else { // End of if conditional, start of else conditional
                str += String.format(" - Due: %tF", this.getDate());
            } // End of else conditional
        } // End of else if conditional

        return str;
    }
    
    /**
     * Method required to implement the comparable pattern to the class.
     * <p>
     * Used to sort the tasks so that the tasks with the soonest due dates are at the top of the list. Will sort the list top to bottom:
     * - Overdue
     * - Due Today
     * - Due after today
     * - No due date
     */
    public int compareTo(Task ta){
        // Start of if conditional
        if(ta.date == (null))
            return -1;
        else if(this.date == null) // End of if conditional start of else if conditional
            return 1;
        else if(this.date.equals(ta.date)) // End of if conditional start of else if conditional
            return 0;
        else if (this.date.isBefore(ta.date)) // End of if conditional start of else if conditional
            return -1;
        else // End of if conditional start of else conditional
            return 1;
        // End of else conditional
    }
}
