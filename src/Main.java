import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


      class ToDoListGUI {
        private DefaultListModel<String> taskListModel; // За съхранение на задачите
        private JList<String> taskList; // Визуален компонент за списъка със задачи
        private JTextField taskInput; // Поле за въвеждане на задачи

        public ToDoListGUI() {
            // Създаване на основния прозорец (JFrame)
            JFrame frame = new JFrame("To-Do List");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 500);
            frame.setLayout(new BorderLayout());

            // Модел за задачите
            taskListModel = new DefaultListModel<>();
            taskList = new JList<>(taskListModel);
            taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(taskList);

            // Поле за въвеждане на задача
            taskInput = new JTextField();
            taskInput.setPreferredSize(new Dimension(250, 30));

            // Бутон за добавяне на задача
            JButton addButton = new JButton("Добави");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String task = taskInput.getText().trim();
                    if (!task.isEmpty()) {
                        taskListModel.addElement(task);
                        taskInput.setText(""); // Изчистване на полето след добавяне
                    } else {
                        JOptionPane.showMessageDialog(frame, "Моля, въведете задача!");
                    }
                }
            });

            // Бутон за изтриване на избрана задача
            JButton removeButton = new JButton("Изтрий");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        taskListModel.remove(selectedIndex);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Изберете задача за изтриване!");
                    }
                }
            });

            // Панел за въвеждане и бутон за добавяне
            JPanel inputPanel = new JPanel();
            inputPanel.add(taskInput);
            inputPanel.add(addButton);

            // Панел за бутона за изтриване
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(removeButton);

            // Добавяне на компонентите към прозореца
            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Показване на прозореца
            frame.setVisible(true);
        }

        public static void main(String[] args) {
            // Гарантиране, че GUI-то ще се стартира в отделен поток
            SwingUtilities.invokeLater(ToDoListGUI::new);
        }
    }
