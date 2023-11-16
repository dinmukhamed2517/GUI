import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TeacherPanel {

    private ArrayList<Student> students = new ArrayList<>();

    public TeacherPanel(ArrayList<Student> students) {
        this.students = students;
        JFrame frame = new JFrame("Teacher Panel");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JButton putMarksButton = new JButton("Put Marks");
        putMarksButton.setBounds(10, 20, 150, 25);
        panel.add(putMarksButton);

        putMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPutMarksDialog(panel);
            }
        });

        frame.setVisible(true);
    }

    private void showPutMarksDialog(JPanel panel) {
        JFrame putMarksFrame = new JFrame("Put Marks");
        putMarksFrame.setSize(300, 200);

        JPanel putMarksPanel = new JPanel();
        putMarksFrame.add(putMarksPanel);
        putMarksPanel.setLayout(null);

        JLabel studentLabel = new JLabel("Select Student:");
        studentLabel.setBounds(10, 20, 120, 25);
        putMarksPanel.add(studentLabel);

        JComboBox<Student> studentComboBox = new JComboBox<>(students.toArray(new Student[0]));
        studentComboBox.setBounds(140, 20, 150, 25);
        putMarksPanel.add(studentComboBox);

        JLabel markLabel = new JLabel("Enter Mark:");
        markLabel.setBounds(10, 50, 120, 25);
        putMarksPanel.add(markLabel);

        JTextField markText = new JTextField(20);
        markText.setBounds(140, 50, 150, 25);
        putMarksPanel.add(markText);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 80, 100, 25);
        putMarksPanel.add(submitButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(120, 80, 100, 25);
        putMarksPanel.add(backButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student selectedStudent = (Student) studentComboBox.getSelectedItem();
                String mark = markText.getText();

                if (!mark.isEmpty()) {
                    // Add the mark to the selected student
                    selectedStudent.addMark(mark);
                    JOptionPane.showMessageDialog(null, "Mark " + mark + " assigned to " + selectedStudent.getFullName());
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a mark.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                putMarksFrame.dispose();
            }
        });

        putMarksFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminPanel adminPanel = new AdminPanel();
            new TeacherPanel(adminPanel.students);
        });
    }
}
