import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPanel {

    public void showManagerPanel() {
        JFrame managerFrame = new JFrame("Manager Panel");
        managerFrame.setSize(400, 300);

        JPanel managerPanel = new JPanel();
        managerFrame.add(managerPanel);
        managerPanel.setLayout(null);

        JButton createCourseButton = new JButton("Create Course");
        createCourseButton.setBounds(10, 20, 150, 25);
        managerPanel.add(createCourseButton);

        JButton deleteCourseButton = new JButton("Delete Course");
        deleteCourseButton.setBounds(170, 20, 150, 25);
        managerPanel.add(deleteCourseButton);

        JButton backToAdminButton = new JButton("Back to Admin Panel");
        backToAdminButton.setBounds(10, 50, 200, 25);
        managerPanel.add(backToAdminButton);

        createCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseManager courseManager = new CourseManager();
                courseManager.showCreateCourseDialog();
            }
        });

        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseManager courseManager = new CourseManager();
                courseManager.showDeleteCourseDialog();
            }
        });

        backToAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerFrame.dispose();
            }
        });

        managerFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManagerPanel().showManagerPanel();
        });
    }
}
