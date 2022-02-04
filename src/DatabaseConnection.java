import java.sql.*;

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
public final class DatabaseConnection {
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

        public void ExecuteQuery(String execQuery){
            Connection();
            // Start of try exception handling
            try {
                stmt = c.createStatement();
                String query = execQuery;
                stmt.executeQuery(query);
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

                return result;
            } catch (Exception e) { // End of try block, Start of catch block
                // Displaying relevant exception feedback
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

                // Closing the application
                System.exit(0);

            } // End of Catch exception handling

            return result;
        }
        
        public ResultSet SelectAllTasks(){
            return ExecuteRequest("SELECT * FROM Task;");
        }

        public ResultSet SelectTaskById(int id){
            String request = String.format("SELECT * FROM Task WHERE id = %d", id);
            return ExecuteRequest(request);
        }

        public ResultSet SelectTasksByName(String taskName){
            String request = String.format("SELECT * FROM Task WHERE taskName LIKE %s", taskName);
            return ExecuteRequest(request);
        }

        public ResultSet SelectTasksByDate(Date date){
            String request = String.format("SELECT * From Task WHERE date = %t", date);
            return ExecuteRequest(request);
        }

        public ResultSet SelectTasksBetweenDates(Date date1, Date date2){
            String request;
            if(date1.before(date2)){
                request = String.format("SELECT * FROM Task WHERE date BETWEEN %t and %t", date1, date2);
            }
            else if(date1.equals(date2)){
                request = String.format("SELECT * From Task WHERE date = %t", date1);
            }
            else {
                request = String.format("SELECT * FROM Task WHERE date BETWEEN %t and %t", date2, date1);
            }
            return ExecuteRequest(request);
        }
        
        public void DeleteTask(int id){
            String query = String.format("DELETE FROM Task WHERE taskNumber = %d", id);
            ExecuteQuery(query);
        }
        
        public void CreateTask(int id, String taskName){
            String query = String.format("INSERT INTO Task (taskNumber, taskName) VALUES (%d, %s);", id, taskName);
            ExecuteQuery(query);
        }

        public void CreateTask(int id, String taskName, String details){
            String query = String.format("INSERT INTO Task (taskNumer, taskName, details) Values (%d, %s, %s);", id, taskName, details);
            ExecuteQuery(query);
        }

        public void CreateTask(int id, String taskName, Date date){
            String query = String.format("INSERT INTO Task (taskNumer, taskName, date) Values (%d, %s, %t);", id, taskName, date);
            ExecuteQuery(query);
        }

        public void CreateTask(int id, String taskName, String details, Date date){
            String query = String.format("INSERT INTO Task (taskNumer, taskName, details, date) Values (%d, %s, %s, %t);", id, taskName, details, date);
            ExecuteQuery(query);
        }
        
        public void UpdateTask(){
            throw new UnsupportedOperationException();
            // TODO: Write Method Contents
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
}
