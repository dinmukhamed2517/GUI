import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminPanel {

    private ArrayList<String> teachers = new ArrayList<>();
    public ArrayList<Student> students = new ArrayList<>();
    private ArrayList<String> courses = new ArrayList<>();

    public AdminPanel() {
        JFrame frame = new JFrame("Admin Panel");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JButton addUserButton = new JButton("Add User");
        addUserButton.setBounds(10, 20, 150, 25);
        panel.add(addUserButton);

        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.setBounds(170, 20, 150, 25);
        panel.add(deleteUserButton);

        JButton setCoursesButton = new JButton("Set Courses");
        setCoursesButton.setBounds(10, 50, 150, 25);
        panel.add(setCoursesButton);

        JButton deleteCoursesButton = new JButton("Delete Courses");
        deleteCoursesButton.setBounds(170, 50, 150, 25);
        panel.add(deleteCoursesButton);

        JButton goToManagerPanelButton = new JButton("Go to Manager Panel");
        goToManagerPanelButton.setBounds(10, 80, 200, 25);
        panel.add(goToManagerPanelButton);

        JButton goToTeacherPanelButton = new JButton("Go to Teacher Panel");
        goToTeacherPanelButton.setBounds(220, 80, 170, 25);
        panel.add(goToTeacherPanelButton);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddUserDialog(panel);
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteUserDialog(panel);
            }
        });

        setCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSetCoursesDialog(panel);
            }
        });

        deleteCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteCoursesDialog(panel);
            }
        });

        goToManagerPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerPanel managerPanel = new ManagerPanel();
                managerPanel.showManagerPanel();
            }
        });
        goToTeacherPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeacherPanel teacherPanel = new TeacherPanel(students);
            }
        });

        frame.setVisible(true);
    }

    private void showAddUserDialog(JPanel panel) {
        JFrame addUserFrame = new JFrame("Add User");
        addUserFrame.setSize(400, 300);

        JPanel addUserPanel = new JPanel();
        addUserFrame.add(addUserPanel);
        addUserPanel.setLayout(null);

        JLabel userTypeLabel = new JLabel("User Type:");
        userTypeLabel.setBounds(10, 20, 80, 25);
        addUserPanel.add(userTypeLabel);

        String[] userTypes = {"Teacher", "Student"};
        JComboBox<String> userTypeComboBox = new JComboBox<>(userTypes);
        userTypeComboBox.setBounds(100, 20, 150, 25);
        addUserPanel.add(userTypeComboBox);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(10, 50, 80, 25);
        addUserPanel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100, 50, 250, 25);
        addUserPanel.add(nameText);

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setBounds(10, 80, 80, 25);
        addUserPanel.add(loginLabel);

        JTextField loginText = new JTextField(20);
        loginText.setBounds(100, 80, 150, 25);
        addUserPanel.add(loginText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 110, 80, 25);
        addUserPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 110, 150, 25);
        addUserPanel.add(passwordField);

        JButton addButton = new JButton("Add User");
        addButton.setBounds(10, 140, 150, 25);
        addUserPanel.add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(170, 140, 80, 25);
        addUserPanel.add(backButton);

        JTextArea userListArea = new JTextArea();
        userListArea.setBounds(10, 170, 350, 80);
        addUserPanel.add(userListArea);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userType = (String) userTypeComboBox.getSelectedItem();
                String fullName = nameText.getText();
                String login = loginText.getText();
                String password = new String(passwordField.getPassword());

                if (!fullName.isEmpty() && !login.isEmpty() && !password.isEmpty()) {
                    String user = userType + ": " + fullName + " (Login: " + login + ")\n";
                    userListArea.append(user);

                    if ("Teacher".equals(userType)) {
                        teachers.add(fullName);
                    } else if ("Student".equals(userType)) {
                        students.add(new Student(login, fullName, password));
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserFrame.dispose();
            }
        });

        addUserFrame.setVisible(true);
    }

    private void showDeleteUserDialog(JPanel panel) {
        JFrame deleteUserFrame = new JFrame("Delete User");
        deleteUserFrame.setSize(300, 200);

        JPanel deleteUserPanel = new JPanel();
        deleteUserFrame.add(deleteUserPanel);
        deleteUserPanel.setLayout(null);

        JTextArea userListArea = new JTextArea();
        userListArea.setBounds(10, 10, 250, 100);
        userListArea.setEditable(false);
        updateUserList(userListArea);
        deleteUserPanel.add(userListArea);

        JLabel nameLabel = new JLabel("User Name:");
        nameLabel.setBounds(10, 120, 80, 25);
        deleteUserPanel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100, 120, 150, 25);
        deleteUserPanel.add(nameText);

        JButton deleteButton = new JButton("Delete User");
        deleteButton.setBounds(10, 150, 150, 25);
        deleteUserPanel.add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(170, 150, 80, 25);
        deleteUserPanel.add(backButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = nameText.getText();

                if (!userName.isEmpty()) {
                    Student deletedStudent = null;
                    for(Student student: students){
                        if(student.getLogin().equals(userName)){
                            deletedStudent = student;
                        }
                    }
                    boolean removed = teachers.remove(userName) || students.remove(deletedStudent);

                    if (removed) {
                        JOptionPane.showMessageDialog(null, "User deleted successfully.");
                        updateUserList(userListArea);
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a user name.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUserFrame.dispose();
            }
        });

        deleteUserFrame.setVisible(true);
    }
    private void showSetCoursesDialog(JPanel panel) {
        JFrame setCoursesFrame = new JFrame("Set Courses");
        setCoursesFrame.setSize(300, 200);

        JPanel setCoursesPanel = new JPanel();
        setCoursesFrame.add(setCoursesPanel);
        setCoursesPanel.setLayout(null);

        JTextArea courseListArea = new JTextArea();
        courseListArea.setBounds(10, 10, 250, 100);
        courseListArea.setEditable(false);
        updateCourseList(courseListArea);
        setCoursesPanel.add(courseListArea);

        JLabel courseLabel = new JLabel("Course Name:");
        courseLabel.setBounds(10, 120, 80, 25);
        setCoursesPanel.add(courseLabel);

        JTextField courseText = new JTextField(20);
        courseText.setBounds(100, 120, 150, 25);
        setCoursesPanel.add(courseText);

        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.setBounds(10, 150, 150, 25);
        setCoursesPanel.add(addCourseButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(170, 150, 80, 25);
        setCoursesPanel.add(backButton);

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseText.getText();

                if (!courseName.isEmpty()) {
                    courses.add(courseName);
                    updateCourseList(courseListArea);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a course name.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCoursesFrame.dispose();
            }
        });

        setCoursesFrame.setVisible(true);
    }




    private void showDeleteCoursesDialog(JPanel panel) {
        // Implement the delete courses dialog
    }

    private void updateUserList(JTextArea userListArea) {
        userListArea.setText("Users:\n");
        for (String teacher : teachers) {
            userListArea.append(" - Teacher: " + teacher + "\n");
        }
        for (Student student : students) {
            userListArea.append(" - Student: " + student.getLogin()+ "\n");
        }
    }

    private void updateCourseList(JTextArea courseListArea) {
        courseListArea.setText("Courses:\n");
        for (String course : courses) {
            courseListArea.append(" - " + course + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminPanel();
        });
    }
}