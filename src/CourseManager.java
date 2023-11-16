import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourseManager {

    private ArrayList<String> courses = new ArrayList<>();

    public void showCreateCourseDialog() {
        JFrame createCourseFrame = new JFrame("Create Course");
        createCourseFrame.setSize(300, 150);

        JPanel createCoursePanel = new JPanel();
        createCourseFrame.add(createCoursePanel);
        createCoursePanel.setLayout(null);

        JLabel courseLabel = new JLabel("Course Name:");
        courseLabel.setBounds(10, 20, 80, 25);
        createCoursePanel.add(courseLabel);

        JTextField courseText = new JTextField(20);
        courseText.setBounds(100, 20, 150, 25);
        createCoursePanel.add(courseText);

        JButton createButton = new JButton("Create Course");
        createButton.setBounds(10, 50, 150, 25);
        createCoursePanel.add(createButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(170, 50, 80, 25);
        createCoursePanel.add(backButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseText.getText();

                if (!courseName.isEmpty()) {
                    courses.add(courseName);
                    JOptionPane.showMessageDialog(null, "Course created successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a course name.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCourseFrame.dispose();
            }
        });

        createCourseFrame.setVisible(true);
    }

    public void showDeleteCourseDialog() {
        JFrame deleteCourseFrame = new JFrame("Delete Course");
        deleteCourseFrame.setSize(300, 150);

        JPanel deleteCoursePanel = new JPanel();
        deleteCourseFrame.add(deleteCoursePanel);
        deleteCoursePanel.setLayout(null);

        JLabel courseLabel = new JLabel("Course Name:");
        courseLabel.setBounds(10, 20, 80, 25);
        deleteCoursePanel.add(courseLabel);

        JTextField courseText = new JTextField(20);
        courseText.setBounds(100, 20, 150, 25);
        deleteCoursePanel.add(courseText);

        JButton deleteButton = new JButton("Delete Course");
        deleteButton.setBounds(10, 50, 150, 25);
        deleteCoursePanel.add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(170, 50, 80, 25);
        deleteCoursePanel.add(backButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseText.getText();

                if (!courseName.isEmpty()) {
                    boolean removed = courses.remove(courseName);

                    if (removed) {
                        JOptionPane.showMessageDialog(null, "Course deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Course not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a course name.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCourseFrame.dispose();
            }
        });

        deleteCourseFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CourseManager().showCreateCourseDialog();
        });
    }
}