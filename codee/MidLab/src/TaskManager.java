import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private TaskHandler taskHandler = new TaskHandler();
    private Scanner scanner = new Scanner(System.in);

    public void addTaskFromUser() {
        System.out.print("Enter Task Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Task Time: ");
        String time = scanner.nextLine();

        Task task = new Task(title, description, time);
        taskHandler.saveTask(task);
    }

    public void displayAllTasks() {
        List<Task> tasks = taskHandler.loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Saved Tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}
