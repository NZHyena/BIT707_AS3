package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.Task;
import main.TaskController;

public class TaskControllerTest {

/*
 * Note: All current unit testing is run without code that interacts with the database. This is because of issue relating to getting unit tests to communicate with the database
 * and getting the database to communicate back. It is known that the Java to database code and database to java code is running in main(). Manual testing will be noted in the 
 * BIT707_AS3_Task1a_DeveloperTesting.xlsx spreadsheet
 */

    TaskController testControl;

    @BeforeClass
    public void setup(){
        testControl = new TaskController();
    }

    @Test
    public void testCreateTaskBase() {

        // Note: Tested and passes with call to database commented out

        // Arrange
        Task notMethod = new Task();
        notMethod.setTaskName("Task Testing");
        notMethod.setId(1);

        // Act
        testControl.CreateTask("Task Testing");
        

        // Assert
        assertEquals(testControl.getAllTasks().get(0).toString(), notMethod.toString());
    }

    @Test
    public void testCreateTaskWithDetails() {

        // Note: Tested and passes with call to database commented out

        // Arrange
        Task notMethod = new Task();
        notMethod.setTaskName("Task Testing");
        notMethod.setId(1);
        notMethod.setDetails("Test Details");

        // Act
        testControl.CreateTask("Task Testing", "Test Details");
        

        // Assert
        assertEquals(testControl.getAllTasks().get(0).toString(), notMethod.toString());
    }

    @Test
    public void testCreateTaskWithDate() throws ParseException {

        // Note: Tested and passes with call to database commented out

        // Arrange
        Task notMethod = new Task();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Date tempDate = formatter.parse("2022-02-07");
        notMethod.setDate(tempDate);
        notMethod.setTaskName("Task Testing");
        notMethod.setId(1);

        // Act
        testControl.CreateTask("Task Testing", tempDate);
        
        // Assert
        assertEquals(testControl.getAllTasks().get(0).toString(), notMethod.toString());
    }

    @Test
    public void testCreateTask4() throws ParseException {

        // Note: Tested and passes with call to database commented out

        // Arrange
        Task notMethod = new Task();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Date tempDate = formatter.parse("2022-02-07");
        notMethod.setDate(tempDate);
        notMethod.setTaskName("Task Testing");
        notMethod.setId(1);
        notMethod.setDetails("Test Details");

        // Act
        testControl.CreateTask("Task Testing", "Test Details", tempDate);
        
        // Assert
        assertEquals(testControl.getAllTasks().get(0).toString(), notMethod.toString());
    }

    @Test
    public void testDeleteTask() {

        // Note: Tested and passes with call to database commented out

        // Arrange
        Task test = new Task("Test");
        testControl.getAllTasks().add(test);

        // Act
        int initalVal = testControl.getAllTasks().size();
        testControl.DeleteTask(test);
        int newVal = testControl.getAllTasks().size();

        // Assert
        assertNotEquals(initalVal, newVal);
    }

    // Method DisplayTask has been used successfully in all CreateTest Unit Testing

    @Test
    public void testEditTask() {

        // Note: Tested and passes with call to database commented out

        // Arrange
        testControl.CreateTask("Incorrect Name");
        Task toEdit = testControl.getAllTasks().get(0);
        String initalVal = toEdit.toString();

        // Act
        testControl.EditTask(1, "Correct Name", "", "");
        Task edited = testControl.getAllTasks().get(0);
        String newVal = edited.toString();

        // Assert
        assertNotEquals(initalVal, newVal);
    }

    @Test
    public void testFindTaskById() {
        // Arrange
        testControl.CreateTask("FindMe");

        // Act
        Task found = testControl.FindTaskById(1);

        // Assert
        assertTrue(found != null);
        assertTrue(found.toString().length() > 0);
    }

    // Manual Testing done for method FindTasksByName

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

        // This previous passed

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
