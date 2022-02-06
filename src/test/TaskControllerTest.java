package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import main.Task;
import main.TaskController;

public class TaskControllerTest {

/*
 * Note: All current unit testing is run without code that interacts with the database. This is because of issue relating to getting unit tests to communicate with the database
 * and getting the database to communicate back. It is known that the Java to database and database to java code is running in main(). Manual testing will be noted in the 
 * BIT707_AS3_Task1a_DeveloperTesting.xlsx spreadsheet
 */

    TaskController testControl;

    @Test
    public void testCreateTask() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testCreateTask2() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testCreateTask3() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testCreateTask4() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testDeleteTask() {

        // TODO: test without db connection

        // Arrange
        testControl = new TaskController();
        Task test = new Task("Test");
        testControl.getAllTasks().add(test);

        // Act
        int initalVal = testControl.getAllTasks().size();
        testControl.DeleteTask(test);
        int newVal = testControl.getAllTasks().size();

        // Assert
        assertNotEquals(initalVal, newVal);
    }

    @Test
    public void testDisplayTask() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testEditTask() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testFindTaskById() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void testFindTasksByName() {
        // Arrange

        // Act

        // Assert
    }

    // Manual Testing done for method LoadAllTasks

    @Test
    public void testInitialLoadCreatesDirectory() {

        // Note: test passed with both no /tmp/ folder and one pre-existing

        // Arrange
        testControl = new TaskController();
        String dirPath = "./tmp";
        File f = new File(dirPath);

        // Act
        testControl.InitialLoad();

        // Assert
        assertTrue(f.exists());
    }

    @Test
    public void testInitalLoadCreatesFile() {

        // Note: tested only with file and directory not existing

        // Arrange
        testControl = new TaskController();
        String dirPath = "./tmp/TaskSingleton.ser";
        File f = new File(dirPath);

        // Act
        testControl.InitialLoad();

        // Assert
        assertTrue(f.exists());
    }

    // Manual Testing done for method ReadSerializable

    @Test
    public void testSortTask() {
        // Arrange

        // Act

        // Assert
    }

    @Before
    public void writeSerializableSetup(){
        testControl = new TaskController();
        testControl.InitialLoad();
    }
    
    @Test
    public void testWriteSerializable() throws InterruptedException {

        // Arrange
        String filePath = "./tmp/TaskSingleton.ser";
        File f = new File(filePath);

        // Act
        long initalVal = f.lastModified();
        TimeUnit.MINUTES.sleep(1);
        testControl.WriteSerializable();
        long newVal = f.lastModified();

        // Assert
        assertNotEquals(initalVal, newVal);
    }
}
