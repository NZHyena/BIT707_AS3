package main;

public class TaskSingleton implements java.io.Serializable{

    private int nextId;
    private static TaskSingleton instance;

    private TaskSingleton(){}

    public static TaskSingleton getInstance() {
        
        if (instance == null){
            instance = new TaskSingleton();
        }

        return instance;
    }

    public static int NextId() {
        instance.nextId++;
        return instance.nextId;
    }

    public static void SetInstance(TaskSingleton s){
        instance = s;
    }
}
