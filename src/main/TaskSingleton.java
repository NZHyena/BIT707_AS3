package main;
import java.io.*;

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
 * A Singleton class to store the current/max taskId to prevent duplicate id values in the application and database
 * @author Grant Docherty - 5032768
 */
public class TaskSingleton implements Serializable{

    // Initializie global variables
    private int nextId;
    private static TaskSingleton instance;

    /**
     * Creates a new Object TaskSingleton
     */
    private TaskSingleton(){}

    /**
     * Gets the current instance of the TaskSingleton class or creates one if one does not exist
     * @return - The current TaskSingleton instance
     */
    public static TaskSingleton getInstance() {
        // Start of if conditional
        if (instance == null){
            instance = new TaskSingleton();
        } // End of if conditional

        return instance;
    }

    /**
     * Gets the next Valid ID number
     * @return - The next valid ID number
     */
    public static int NextId() {
        instance.nextId++;
        return instance.nextId;
    }

    /**
     * Sets the current max id number
     * @param id - The current max id number
     */
    public void setId(int id){
        nextId = id;
    }

    /**
     * Sets the instance of the TaskSingleton class
     * @param s - An instance of the TaskSingleton class
     */
    public static void SetInstance(TaskSingleton s){
        instance = s;
    }
}
