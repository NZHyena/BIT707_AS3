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
 *
 * @author user
 */

// Note: Class does not work in testing as a part of the /test/
public final class DatabaseConnection implements TaskListener{
    // Initializing required SQLite variable
        Connection c = null;
        Statement stmt = null;
        ResultSet result = null;
        
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
        
        public ResultSet SelectAllTasks(){
            ResultSet out = ExecuteRequest("SELECT * FROM Task;");
            return out;
        }

        public ResultSet SelectTaskById(int id){
            String request = String.format("SELECT * FROM Task WHERE taskNumber = %d", id);
            ResultSet out =  ExecuteRequest(request);
            return out;
        }

        public ResultSet SelectTasksByName(String taskName){
            String request = String.format("SELECT * FROM Task WHERE taskName LIKE '%s'", taskName);
            ResultSet out =  ExecuteRequest(request);
            return out;
        }

        public ResultSet SelectTasksByDate(Date date){
            String request = String.format("SELECT * From Task WHERE date = '%tF'", date);
            ResultSet out =  ExecuteRequest(request);
            return out;
        }

        public ResultSet SelectTasksBetweenDates(Date date1, Date date2){
            String request;
            if(date1.before(date2)){
                request = String.format("SELECT * FROM Task WHERE date BETWEEN '%tF' and '%tF'", date1, date2);
            }
            else if(date1.equals(date2)){
                request = String.format("SELECT * From Task WHERE date = '%tF'", date1);
            }
            else {
                request = String.format("SELECT * FROM Task WHERE date BETWEEN '%tF' and '%tF'", date2, date1);
            }
            ResultSet out =  ExecuteRequest(request);
            return out;
        }
        
        public void DeleteTask(int id){
            String query = String.format("DELETE FROM Task WHERE taskNumber = %d", id);
            ExecuteUpdate(query);
        }
        
        public void CreateTask(int id, String taskName){
            String query = String.format("INSERT INTO Task (taskNumber, taskName) VALUES (%d, '%s');", id, taskName);
            ExecuteUpdate(query);
        }

        public void CreateTask(int id, String taskName, String details){
            String query = String.format("INSERT INTO Task (taskNumber, taskName, details) Values (%d, '%s', '%s');", id, taskName, details);
            ExecuteUpdate(query);
        }

        public void CreateTask(int id, String taskName, LocalDate date){
            String query = String.format("INSERT INTO Task (taskNumber, taskName, date) Values (%d, '%s', '%tF');", id, taskName, date);
            ExecuteUpdate(query);
        }

        public void CreateTask(int id, String taskName, String details, LocalDate date){
            String query = String.format("INSERT INTO Task (taskNumber, taskName, details, date) Values (%d, '%s', '%s', '%tF');", id, taskName, details, date);
            ExecuteUpdate(query);
        }
        
        public void UpdateTaskName(int id, String taskName){
            String query = String.format("UPDATE Task SET taskName = '%s' WHERE taskNumber = %d;", id, taskName);
            ExecuteUpdate(query);
        }

        public void UpdateTaskDetails(int id, String details){
            String query = String.format("UPDATE Task SET details = '%s' WHERE taskNumber = %d;", id, details);
            ExecuteUpdate(query);
        }

        public void UpdateTaskDate(int id, LocalDate date){
            String query = String.format("UPDATE Task SET date = %t WHERE taskNumber = %d;", id, date);
            ExecuteUpdate(query);
        }
        
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

        @Override
        public void TaskCreated(int id, LocalDate date, String... created) {
            int createdLength = created.length;
            if (date != null){
                switch (createdLength){
                    case 1:
                        CreateTask(id, created[0], date);
                        break;
                    case 2:
                        CreateTask(id, created[0], created[1], date);
                        break;
                }
            }
            else {
                switch (createdLength){
                    case 1:
                        CreateTask(id, created[0]);
                        break;
                    case 2:
                        CreateTask(id, created[0], created[1]);
                        break;
                }
            }
            
        }

        @Override
        public void TaskUpdated(int id, String... updates) {
            int updateLength = updates.length;

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
            }
            
        }

        @Override
        public void TaskDeleted(int taskId) {
            DeleteTask(taskId);
            
        }

        @Override
        public void RequestRefresh() {
            // TODO Auto-generated method stub
            
        }
}
