import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentPanel {

    private ArrayList<Student> students;

    public StudentPanel(ArrayList<Student> students) {
        this.students = students;

        JFrame frame = new JFrame("Student Panel");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setBounds(10, 20, 150, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(170, 20, 150, 25);
        panel.add(nameText);

        JButton checkMarksButton = new JButton("Check Marks");
        checkMarksButton.setBounds(10, 50, 150, 25);
        panel.add(checkMarksButton);

        checkMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = nameText.getText();
                showCheckMarksDialog(panel, studentName);
            }
        });

        frame.setVisible(true);
    }

    private void showCheckMarksDialog(JPanel panel, String studentName) {
        JFrame checkMarksFrame = new JFrame("Check Marks");
        checkMarksFrame.setSize(300, 200);

        JPanel checkMarksPanel = new JPanel();
        checkMarksFrame.add(checkMarksPanel);
        checkMarksPanel.setLayout(null);

        JLabel markLabel = new JLabel("Your Marks:");
        markLabel.setBounds(10, 20, 120, 25);
        checkMarksPanel.add(markLabel);

        String studentMarks = getStudentMarks(studentName);
        JTextArea marksTextArea = new JTextArea(studentMarks);
        marksTextArea.setBounds(140, 20, 150, 100);
        marksTextArea.setEditable(false);
        checkMarksPanel.add(marksTextArea);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 130, 100, 25);
        checkMarksPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkMarksFrame.dispose();
            }
        });

        checkMarksFrame.setVisible(true);
    }

    private String getStudentMarks(String studentName) {
        Student findStudent = null;
        String res = "";
        if(!studentName.isEmpty()){
            for (Student student :students ) {
                if(student.getLogin().equals(studentName)){
                    findStudent = student;
                }
            }
            if(findStudent!= null){
                res = findStudent.getMarksAsString();
            }
            else{
                JOptionPane.showMessageDialog(null, "There is no such a student");
            }
        }
        else{
            res = "Student not found";
        }
        return res;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminPanel adminPanel = new AdminPanel();

            new StudentPanel(adminPanel.students);
        });
    }
}