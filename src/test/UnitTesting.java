package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.TaskSingleton;
import main.Task;
import main.TaskController;
import main.DatabaseConnection;

public class UnitTesting {

    static TaskController taskController;
    static DatabaseConnection dbc;
    Task tmp;

/*
 * Note: Attempts have been made to try and get some form of UnitTesting within the code running. Unfortunately this is failing to work.
 * All future testing will refer to the test plan later in the programming/development cycle.
 */

    // Database Connection Tests
    @Test
    public void testDBCCreateTaskBase() throws SQLException {

    }

    @Test
    public void testDBCDBCCreateTaskDetailsArg() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCCreateTaskDateArg() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCCreateTaskAllArgs() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCDeleteTask() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCSelectAllTasks() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCSelectTaskById() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCSelectTasksBetweenDates() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCSelectTasksByDate() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCSelectTasksByName() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCUpdateTaskDate() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCUpdateTaskDetails() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDBCUpdateTaskName() {
        // Arrange

        // Act

        // Assert
    }

}
