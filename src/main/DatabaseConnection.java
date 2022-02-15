package main;
import java.sql.*;
import java.time.LocalDate;

/*
 * Copyright (C) 2022 user
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
 * This is the work of a student and follows the principles of academic integrity set by the Open Polytech.
 */

/**
 * Class used to handle all connections and querries to and from the database.
 * @author Grant Docherty - 5032768
 */
// Note: Class does not work in testing as a part of the /test/
// Additional Note: Exception handling in this class needs work, however creating and handling relevant exceptions pushes my time management further than I have already pushed it
public final class DatabaseConnection implements TaskListener{
    // Initializing required SQLite variable
        Connection c = null;
        Statement stmt = null;
        ResultSet result = null;
        
        /**
         * Connection method to run at the start of every query and request of the database.
         * Handles the connection between the ToDo.db and this application
         */
        public void Connection(){
            // Start of try exception handling
            try{
                // Connecting to the database
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:ToDo.db");

                // Displaying relevant feedback
                System.out.println("Opened database successfully\n");

            } catch (Exception e){ // End of try block, Start of catch block
                // Displaying relevant exception feedback
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

                // Closing the application
                System.exit(0);
            } // End of Catch exception handling
        }

        /**
         * Database method to send an update query (no results or returns from the database) from the application to the ToDo database.
         * <p>
         * This primarily handles all 'INSERT', 'UPDATE' and 'DELETE' SQLite methods
         * @param execQuery - A formatted string containing the query to run on the database
         */
        public void ExecuteUpdate(String execQuery){
            Connection();
            // Start of try exception handling
            try {
                stmt = c.createStatement();
                String query = execQuery;
                stmt.executeUpdate(query);
            } catch (Exception e) { // End of try block, start of catch block
                // Displaying relevant exception feedback
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

                // Closing the application
                System.exit(0);
            } // End of catch exception handling
        }

        /**
         * Database method to send a request query (A result is returned from the database) from the application to the ToDo database.
         * <p>
         * This only handles all 'SELECT' statment SQLite methods and returns them in a java.sql.ResultSet
         * @param execRequest - A Formatted string containing the query to run on the database
         * @return - The results of the sent query
         */
        public ResultSet ExecuteRequest(String execRequest){
                Connection();
            // Start of try exception handling
            try {
                stmt = c.createStatement();
                String request = execRequest;
                result = stmt.executeQuery(request);
            } catch (Exception e) { // End of try block, Start of catch block
                // Displaying relevant exception feedback
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

                // Closing the application
                System.exit(0);

            } // End of Catch exception handling
            return result;
        }
        
        /**
         * Database method that creates the request to retrieve all tasks currently stored in the database.
         * @return - A ResultSet containing all records in the database
         * @see ExecuteRequest
         */
        public ResultSet SelectAllTasks(){
            ResultSet out = ExecuteRequest("SELECT * FROM Task;");
            return out;
        }
        
        /**
         * Database method that creates a query to delete a task currently stored in the database
         * @param id - The task identifier/primary key that you wish to delete
         * @see ExecuteUpdate
         */
        public void DeleteTask(int id){
            String query = String.format("DELETE FROM Task WHERE taskNumber = %d", id);
            ExecuteUpdate(query);
        }
        
        /**
         * Database method that creates a query to insert a task with the bare amount of information required (primary id and the task's name)
         * @param id - The primary key for the created task
         * @param taskName - The task's name
         * @see ExecuteUpdate
         */
        public void CreateTask(int id, String taskName){
            String query = String.format("INSERT INTO Task (taskNumber, taskName) VALUES (%d, '%s');", id, taskName);
            ExecuteUpdate(query);
        }

        /**
         * Database method that creates a query to insert a task containing primary id, the task's name, and any pertinant task details
         * @param id - The primary key for the created task
         * @param taskName - The task's name
         * @param details - The task's details
         * @see ExecuteUpdate
         */
        public void CreateTask(int id, String taskName, String details){
            String query = String.format("INSERT INTO Task (taskNumber, taskName, details) Values (%d, '%s', '%s');", id, taskName, details);
            ExecuteUpdate(query);
        }

        /**
         * Database method that creates a query to insert a task conatining primary id, task's name, and the date the task is due to be complete by
         * @param id - The primary key for the created task
         * @param taskName - The task's name
         * @param date - The due date for the task
         * @see ExecuteUpdate
         */
        public void CreateTask(int id, String taskName, LocalDate date){
            String query = String.format("INSERT INTO Task (taskNumber, taskName, date) Values (%d, '%s', '%tF');", id, taskName, date);
            ExecuteUpdate(query);
        }

        /**
         * Database method that creates a query to insert a task containing primary id, the task's name, task details, and the date the task is due to be complete by
         * @param id - The primary key for the created task
         * @param taskName - The task's name
         * @param details - The task's details
         * @param date - The due date for the task
         * @see ExecuteUpdate
         */
        public void CreateTask(int id, String taskName, String details, LocalDate date){
            String query = String.format("INSERT INTO Task (taskNumber, taskName, details, date) Values (%d, '%s', '%s', '%tF');", id, taskName, details, date);
            ExecuteUpdate(query);
        }
        
        /**
         * Database method to update the name of a task in the database
         * @param id - The primary key of the task to update
         * @param taskName - The task's updated name
         * @see ExecuteUpdate
         */
        public void UpdateTaskName(int id, String taskName){
            String query = String.format("UPDATE Task SET taskName = '%s' WHERE taskNumber = %d;", taskName, id);
            ExecuteUpdate(query);
        }

        /**
         * Database method to update the details of a task in the database
         * @param id - The primary key of the task to update
         * @param details - The task's updated details
         * @see ExecuteUpdate
         */
        public void UpdateTaskDetails(int id, String details){
            String query = String.format("UPDATE Task SET details = '%s' WHERE taskNumber = %d;", details, id);
            ExecuteUpdate(query);
        }

        /**
         * Database method to update the due date of a task in the database
         * @param id - The primary key of the task to update
         * @param date - The task's updated due date
         * @see ExecuteUpdate
         */
        public void UpdateTaskDate(int id, LocalDate date){
            String query = String.format("UPDATE Task SET date = '%tF' WHERE taskNumber = %d;", date, id);
            ExecuteUpdate(query);
        }
        
        /**
         * Database method to close the database connection
         */
        public void CloseConnection(){
            try{
                // Closing connection to the database
                result.close();
                stmt.close();
                c.close();
            } catch (Exception e){ // End of try, Start of catch
                // Displaying relevant exception feedback
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

                // Closing the application
                System.exit(0);

            } // End of Catch exception handling
        }

        /**
         * Override method to implement the TaskCreated TaskListener interface
         * <p>
         * On a task being created in the application a version/relica of the task needs to be created in the database.
         * This method takes all the information about the created task object and sends a relevant 'INSERT' query to the database
         * @param id - The identifier and new primary key of the task being replicated
         * @param date - The due date of the task (is null if has not been set/provided)
         * @param created - A String array that contains either the task name or the task name and task details
         */
        @Override
        public void TaskCreated(int id, LocalDate date, String... created) {
            // Initialize local variable to hold string array length
            int createdLength = created.length;
            
            // Start of if conditional
            if (date != null){
                // Start of switch conditional
                switch (createdLength){
                    case 1:
                        CreateTask(id, created[0], date);
                        break;
                    case 2:
                        CreateTask(id, created[0], created[1], date);
                        break;
                } // End of switch conditional
            } else { // End of if conditional, start of else conditional
                // Start of switch conditional
                switch (createdLength){
                    case 1:
                        CreateTask(id, created[0]);
                        break;
                    case 2:
                        CreateTask(id, created[0], created[1]);
                        break;
                } // End of switch conditional
            } // End of else conditional
        }

        /**
         * Override method to implement the TaskUpdated TaskListener interface
         * <p>
         * On a task being updated in the application, the version/replica of the task in the database also needs to be updated.
         * This method takes all the updated information about a task object and sends a relevant 'UPDATE' query to the database
         * @param id - The matching primary key for the replica in the database
         * @param updates - A String array that contains either the task name, task details, task due date, or all three/a combination of two of them
         */
        @Override
        public void TaskUpdated(int id, String... updates) {
            // Initialize a local variable to hold the task length
            int updateLength = updates.length;

            // Start of switch conditional
            switch (updateLength){
                case 1:
                    UpdateTaskName(id, updates[0]);
                    break;
                case 2:
                    UpdateTaskName(id, updates[0]);
                    UpdateTaskDetails(id, updates[1]);
                    break;
                case 3:
                    UpdateTaskName(id, updates[0]);
                    if (!updates[1].isEmpty())
                        UpdateTaskDetails(id, updates[1]);
                    UpdateTaskDate(id, LocalDate.parse(updates[2]));
            } // End of switch conditional
        }

        /**
         * Override method to implement the TaskDeleted TaskListener interface
         * <p>
         * On a task being deleted in the application, the verion/replica of the task in the database also need to be deleted.
         * This method sends a 'DELETE' update query to the database
         * @param taskId - The primary key of the task to be deleted
         */
        @Override
        public void TaskDeleted(int taskId) {
            DeleteTask(taskId);
        }
}
