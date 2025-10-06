import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Todo {
    List<String> tasks = new ArrayList<>();
    private final String FILE_NAME = "tasks.txt";

    public Todo() {
        loadTasksFromFile();
    }
    public void addTask(String task) {
        if(task.trim().isEmpty()) {
            System.out.println("Empyt Task.. Can't Be Added");
        } else {
            if (tasks.contains(task)) {
                System.out.println("Task Already Exist..!");
            } else {
                tasks.add(task);
                System.out.println("Task Added");
                saveTasksToFile();
            }
        }
    }

    public void removeTask(int index) {
        if(index < 0 || index >= tasks.size()) {
            System.out.println("Invalid Task Number");
            return;
        }
        System.out.println("Task Completed: " + tasks.remove(index));
        saveTasksToFile();
    }

    public void display() {
        if(tasks.isEmpty()) {
            System.out.println("No Tasks left..!");
        } else {
            System.out.println("Task To Be Done: ");
            for(int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + ") " + tasks.get(i));
            }
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}