
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

            } catch (Exception e){ // End of try, Start of catch
                // Displaying relevant exception feedback
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

                // Closing the application
                System.exit(0);

            } // End of Catch exception handling
        }
        
        public ResultSet SelectAllTasks(){
            Connection();
            try{
                // Querying the database
                stmt = c.createStatement();
                String query = "SELECT * FROM Task;";
                result = stmt.executeQuery(query);                
            } catch (Exception e){ // End of try, Start of catch
                // Displaying relevant exception feedback
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

                // Closing the application
                System.exit(0);

            } // End of Catch exception handling
            return result;
        }
        
        public void DeleteTask(){
            
        }
        
        public void CreateTask(){
            
        }
        
        public void UpdateTask(){
            
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
