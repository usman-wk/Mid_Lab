import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskHandler {
    private static final String FILE_NAME = "tasks.txt";

    public void saveTask(Task task) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME, true);
            writer.write(task.getTitle() + "|" + task.getDescription() + "|" + task.getTime() + "\n");
            writer.close();
            System.out.println("Task saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                int first = line.indexOf('|');
                int second = line.lastIndexOf('|');

                if (first != -1 && second != -1 && first != second) {
                    String title = line.substring(0, first);
                    String description = line.substring(first + 1, second);
                    String time = line.substring(second + 1);
                    Task task = new Task(title.trim(), description.trim(), time.trim());
                    tasks.add(task);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }

        return tasks;
    }
}
