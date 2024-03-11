import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TaskListApplicationUI extends JFrame {
    private ArrayList<String> tasks = new ArrayList<>();
    private JTextArea taskListTextArea;

    public TaskListApplicationUI() {
        setTitle("Task List Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = JOptionPane.showInputDialog(null, "Enter task:");
                if (task != null && !task.isEmpty()) {
                    tasks.add(task);
                    updateTaskList();
                }
            }
        });
        JButton removeButton = new JButton("Remove Task");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tasks.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Task list is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String[] taskArray = tasks.toArray(new String[0]);
                String selectedTask = (String) JOptionPane.showInputDialog(null, "Select task to remove:", "Remove Task",
                        JOptionPane.QUESTION_MESSAGE, null, taskArray, taskArray[0]);
                if (selectedTask != null) {
                    tasks.remove(selectedTask);
                    updateTaskList();
                }
            }
        });
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        taskListTextArea = new JTextArea();
        taskListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taskListTextArea);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private void updateTaskList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        taskListTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TaskListApplicationUI();
            }
        });
    }
}
