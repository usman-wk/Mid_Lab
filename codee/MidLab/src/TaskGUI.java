import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskGUI {

    private TaskHandler handler = new TaskHandler();

    public TaskGUI() {
        JFrame frame = new JFrame("Task Manager GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        JTextField titleField = new JTextField();
        JTextField descField = new JTextField();
        JTextField timeField = new JTextField();

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descField);
        inputPanel.add(new JLabel("Time:"));
        inputPanel.add(timeField);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton viewButton = new JButton("View Tasks");
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);

        JTextArea taskArea = new JTextArea(10, 30);
        taskArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taskArea);

        centerPanel.add(buttonPanel);
        centerPanel.add(scrollPane);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim();
                String desc = descField.getText().trim();
                String time = timeField.getText().trim();

                if (!title.isEmpty() && !desc.isEmpty() && !time.isEmpty()) {
                    Task task = new Task(title, desc, time);
                    handler.saveTask(task);
                    JOptionPane.showMessageDialog(frame, "Task Added!");
                    titleField.setText("");
                    descField.setText("");
                    timeField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Task> tasks = handler.loadTasks();
                taskArea.setText("");
                if (tasks.isEmpty()) {
                    taskArea.setText("No tasks found.");
                } else {
                    int i = 1;
                    for (Task t : tasks) {
                        taskArea.append(i++ + ". " + t.toString() + "\n");
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
