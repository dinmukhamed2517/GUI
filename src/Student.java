import java.util.Arrays;

public class Student {
    private String login;
    private String fullName;
    private String password;
    private String[] marks;

    public Student(String login, String fullName, String password) {
        this.login = login;
        this.fullName = fullName;
        this.password = password;
        this.marks = new String[0]; // Initialize an empty array for marks
    }

    public String getLogin() {
        return login;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String[] getMarks() {
        return marks;
    }

    public void addMark(String mark) {
        // Add a new mark to the array
        marks = Arrays.copyOf(marks, marks.length + 1);
        marks[marks.length - 1] = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "login='" + login + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", marks=" + Arrays.toString(marks) +
                '}';
    }
    public String getMarksAsString() {
        // Join the marks array into a single string, separated by commas
        return String.join(", ", marks);
    }
}