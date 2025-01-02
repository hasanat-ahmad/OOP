import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;

public class SchoolManagementGUI extends JFrame {
    private University university;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private DefaultListModel<Course> courseListModel;

    public SchoolManagementGUI() {
        university = new University();
        setTitle("School Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        courseListModel = new DefaultListModel<>();

        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "MENU");

        mainPanel.add(createAddStudentPanel(), "ADD_STUDENT");
        mainPanel.add(createAddTeacherPanel(), "ADD_TEACHER");
        mainPanel.add(createAddCoursePanel(), "ADD_COURSE");
        mainPanel.add(createEnrollStudentPanel(), "ENROLL_STUDENT");
        mainPanel.add(createAssignTeacherPanel(), "ASSIGN_TEACHER");
        mainPanel.add(createDisplayPanel(), "DISPLAY");
        mainPanel.add(createSearchPanel(), "SEARCH");
        mainPanel.add(createFilterCoursesPanel(), "FILTER_COURSES");
        mainPanel.add(createGradeManagementPanel(), "GRADE_MANAGEMENT");

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
    
        String[] buttonLabels = {
                "Add Student", "Add Teacher", "Add Course",
                "Enroll Student in Courses", "Assign Course to Teacher",
                "Display All Records", "Search Student", "Filter Courses",
                "Grade Management", "Save Data", "Load Data", "Display Statistics"
        };
    
        String[] cardNames = {
                "ADD_STUDENT", "ADD_TEACHER", "ADD_COURSE",
                "ENROLL_STUDENT", "ASSIGN_TEACHER", "DISPLAY",
                "SEARCH", "FILTER_COURSES", "GRADE_MANAGEMENT" 
        };
    
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setPreferredSize(new Dimension(250, 40));
            final int index = i;
    
            button.addActionListener(e -> {
                if (index < cardNames.length) {
                    cardLayout.show(mainPanel, cardNames[index]);
                } else if (index == 9) { 
                    saveData();
                } else if (index == 10) { 
                    loadData();
                } else if (index == 11) { 
                    displayStats();
                }
            });
            panel.add(button, gbc);
        }
    
        return panel;
    }
    
    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField dobField = new JTextField(20);
        JTextField idField = new JTextField(20);
        JTextField addressField = new JTextField(20);

        panel.add(new JLabel("Name:"), gbc);
        panel.add(nameField, gbc);
        panel.add(new JLabel("Email:"), gbc);
        panel.add(emailField, gbc);
        panel.add(new JLabel("Date of Birth:"), gbc);
        panel.add(dobField, gbc);
        panel.add(new JLabel("Student ID:"), gbc);
        panel.add(idField, gbc);
        panel.add(new JLabel("Address:"), gbc);
        panel.add(addressField, gbc);

        JButton submitButton = new JButton("Add Student");
        submitButton.addActionListener(e -> {
            Student student = new Student(
                    nameField.getText(),
                    emailField.getText(),
                    dobField.getText(),
                    idField.getText(),
                    addressField.getText());
            university.addStudent(student);
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            clearFields(nameField, emailField, dobField, idField, addressField);
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));

        panel.add(submitButton, gbc);
        panel.add(backButton, gbc);

        return panel;
    }

    private JPanel createAddTeacherPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField dobField = new JTextField(20);
        JTextField idField = new JTextField(20);
        JTextField specializationField = new JTextField(20);

        panel.add(new JLabel("Name:"), gbc);
        panel.add(nameField, gbc);
        panel.add(new JLabel("Email:"), gbc);
        panel.add(emailField, gbc);
        panel.add(new JLabel("Date of Birth:"), gbc);
        panel.add(dobField, gbc);
        panel.add(new JLabel("Teacher ID:"), gbc);
        panel.add(idField, gbc);
        panel.add(new JLabel("Specialization:"), gbc);
        panel.add(specializationField, gbc);

        JButton submitButton = new JButton("Add Teacher");
        submitButton.addActionListener(e -> {
            Teacher teacher = new Teacher(
                    nameField.getText(),
                    emailField.getText(),
                    dobField.getText(),
                    idField.getText(),
                    specializationField.getText());
            university.addTeacher(teacher);
            JOptionPane.showMessageDialog(this, "Teacher added successfully!");
            clearFields(nameField, emailField, dobField, idField, specializationField);
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));

        panel.add(submitButton, gbc);
        panel.add(backButton, gbc);

        return panel;
    }

    private JPanel createAddCoursePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField idField = new JTextField(20);
        JTextField titleField = new JTextField(20);
        JTextField creditsField = new JTextField(20);
        JComboBox<Teacher> teacherComboBox = new JComboBox<>();

        panel.add(new JLabel("Course ID:"), gbc);
        panel.add(idField, gbc);
        panel.add(new JLabel("Course Title:"), gbc);
        panel.add(titleField, gbc);
        panel.add(new JLabel("Credits:"), gbc);
        panel.add(creditsField, gbc);
        panel.add(new JLabel("Select Teacher:"), gbc);
        panel.add(teacherComboBox, gbc);

        // Add refresh button to update teacher list
        JButton refreshTeachersButton = new JButton("Refresh Teachers List");
        refreshTeachersButton.addActionListener(e -> {
            teacherComboBox.removeAllItems();
            for (Teacher teacher : university.getTeachers().getAll()) {
                teacherComboBox.addItem(teacher);
            }
        });
        panel.add(refreshTeachersButton, gbc);

        JButton submitButton = new JButton("Add Course");
        submitButton.addActionListener(e -> {
            try {
                Teacher selectedTeacher = (Teacher) teacherComboBox.getSelectedItem();
                if (selectedTeacher != null) {
                    Course course = new Course(
                            idField.getText(),
                            titleField.getText(),
                            Integer.parseInt(creditsField.getText()),
                            selectedTeacher,
                            new ArrayList<>());
                    university.addCourse(course);
                    courseListModel.addElement(course); 
                    JOptionPane.showMessageDialog(this, "Course added successfully!");
                    clearFields(idField, titleField, creditsField);
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a teacher!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for credits!");
            }
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));

        panel.add(submitButton, gbc);
        panel.add(backButton, gbc);

        return panel;
    }

    private JPanel createEnrollStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JComboBox<Student> studentComboBox = new JComboBox<>();
        JList<Course> courseList = new JList<>(courseListModel);
        JScrollPane scrollPane = new JScrollPane(courseList);

        panel.add(new JLabel("Select Student:"), gbc);
        panel.add(studentComboBox, gbc);
        panel.add(new JLabel("Available Courses:"), gbc);
        panel.add(scrollPane, gbc);

        JButton refreshListsButton = new JButton("Refresh Lists");
        refreshListsButton.addActionListener(e -> {
            studentComboBox.removeAllItems();
            courseListModel.clear();

            for (Student student : university.getStudents().getAll()) {
                studentComboBox.addItem(student);
            }

            for (Course course : university.getCourses().getAll()) {
                courseListModel.addElement(course);
            }
        });
        panel.add(refreshListsButton, gbc);

        JButton enrollButton = new JButton("Enroll in Selected Courses");
        enrollButton.addActionListener(e -> {
            Student selectedStudent = (Student) studentComboBox.getSelectedItem();
            if (selectedStudent != null) {
                java.util.List<Course> selectedCourses = courseList.getSelectedValuesList();
                if (!selectedCourses.isEmpty()) {
                    selectedStudent.enrollInCourse(new ArrayList<>(selectedCourses));
                    JOptionPane.showMessageDialog(this, "Student enrolled in selected courses!");
                } else {
                    JOptionPane.showMessageDialog(this, "Please select at least one course!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student!");
            }
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));

        panel.add(enrollButton, gbc);
        panel.add(backButton, gbc);

        return panel;
    }

    private JPanel createAssignTeacherPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
    
        JComboBox<Teacher> teacherComboBox = new JComboBox<>();
        JComboBox<Course> courseComboBox = new JComboBox<>();
    
        // Add ComboBoxes instead of text fields
        panel.add(new JLabel("Select Teacher:"), gbc);
        panel.add(teacherComboBox, gbc);
        panel.add(new JLabel("Select Course:"), gbc);
        panel.add(courseComboBox, gbc);
    
        // Add refresh button
        JButton refreshButton = new JButton("Refresh Lists");
        refreshButton.addActionListener(e -> {
            teacherComboBox.removeAllItems();
            courseComboBox.removeAllItems();
            
            for (Teacher teacher : university.getTeachers().getAll()) {
                teacherComboBox.addItem(teacher);
            }
            
            for (Course course : university.getCourses().getAll()) {
                courseComboBox.addItem(course);
            }
        });
        panel.add(refreshButton, gbc);
    
        JButton assignButton = new JButton("Assign Course");
        assignButton.addActionListener(e -> {
            Teacher selectedTeacher = (Teacher) teacherComboBox.getSelectedItem();
            Course selectedCourse = (Course) courseComboBox.getSelectedItem();
            
            if (selectedTeacher != null && selectedCourse != null) {
                selectedTeacher.assignCourse(selectedCourse);
                JOptionPane.showMessageDialog(this, "Course assigned successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select both teacher and course!");
            }
        });
    
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));
    
        panel.add(assignButton, gbc);
        panel.add(backButton, gbc);
    
        return panel;
    }

    private JPanel createGradeManagementPanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);

    JComboBox<Student> studentComboBox = new JComboBox<>();
    JComboBox<Course> courseComboBox = new JComboBox<>();
    JTextField gradeField = new JTextField(20);
    JTextArea gradeDisplay = new JTextArea(10, 40);
    gradeDisplay.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(gradeDisplay);

    panel.add(new JLabel("Select Student:"), gbc);
    panel.add(studentComboBox, gbc);
    panel.add(new JLabel("Select Course:"), gbc);
    panel.add(courseComboBox, gbc);
    panel.add(new JLabel("Grade:"), gbc);
    panel.add(gradeField, gbc);
    panel.add(scrollPane, gbc);

    JButton refreshButton = new JButton("Refresh Lists");
    refreshButton.addActionListener(e -> {
        studentComboBox.removeAllItems();
        courseComboBox.removeAllItems();
        
        for (Student student : university.getStudents().getAll()) {
            studentComboBox.addItem(student);
        }
        
        for (Course course : university.getCourses().getAll()) {
            courseComboBox.addItem(course);
        }
    });
    panel.add(refreshButton, gbc);

    JButton addGradeButton = new JButton("Add Grade");
    addGradeButton.addActionListener(e -> {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();
        
        try {
            double grade = Double.parseDouble(gradeField.getText());
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException("Grade must be between 0 and 100");
            }
            
            if (selectedStudent != null && selectedCourse != null) {
                selectedStudent.addGrade(selectedCourse, grade);
                JOptionPane.showMessageDialog(this, "Grade added successfully!");
                gradeField.setText("");
                displayGrades(selectedStudent, gradeDisplay);
            } else {
                JOptionPane.showMessageDialog(this, "Please select both student and course!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for grade!");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    });

    JButton calculateAverageButton = new JButton("Calculate Average Grade");
    calculateAverageButton.addActionListener(e -> {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        if (selectedStudent != null) {
            double average = selectedStudent.calculateAverageGrade();
            gradeDisplay.setText("Average Grade: " + String.format("%.2f", average) + "\n\n");
            displayGrades(selectedStudent, gradeDisplay);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student!");
        }
    });

    JButton backButton = new JButton("Back to Menu");
    backButton.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));

    panel.add(addGradeButton, gbc);
    panel.add(calculateAverageButton, gbc);
    panel.add(backButton, gbc);

    return panel;
}

private void displayGrades(Student student, JTextArea gradeDisplay) {
    StringBuilder sb = new StringBuilder();
    sb.append("Grades for ").append(student.getName()).append(":\n\n");
    
    Map<Course, Double> grades = (Map<Course, Double>) student.getGrades();
    for (Map.Entry<Course, Double> entry : grades.entrySet()) {
        sb.append(entry.getKey().getTitle())
          .append(": ")
          .append(String.format("%.2f", entry.getValue()))
          .append("\n");
    }
    
    gradeDisplay.setText(sb.toString());
}

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JTextArea studentArea = new JTextArea(20, 40);
        studentArea.setEditable(false);
        JScrollPane studentScroll = new JScrollPane(studentArea);
        tabbedPane.addTab("Students", studentScroll);

        JTextArea teacherArea = new JTextArea(20, 40);
        teacherArea.setEditable(false);
        JScrollPane teacherScroll = new JScrollPane(teacherArea);
        tabbedPane.addTab("Teachers", teacherScroll);

        JTextArea courseArea = new JTextArea(20, 40);
        courseArea.setEditable(false);
        JScrollPane courseScroll = new JScrollPane(courseArea);
        tabbedPane.addTab("Courses", courseScroll);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            StringBuilder studentText = new StringBuilder();
            for (Student student : university.getStudents().getAll()) {
                studentText.append(student.toString()).append("\n\n");
            }
            studentArea.setText(studentText.toString());

            StringBuilder teacherText = new StringBuilder();
            for (Teacher teacher : university.getTeachers().getAll()) {
                teacherText.append(teacher.toString()).append("\n\n");
            }
            teacherArea.setText(teacherText.toString());

            StringBuilder courseText = new StringBuilder();
            for (Course course : university.getCourses().getAll()) {
                courseText.append(course.getTitle())
                        .append(" (").append(course.getCredits()).append(" Credits)")
                        .append("\n");
            }
            courseArea.setText(courseText.toString());
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(20);
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        panel.add(new JLabel("Student Name:"), gbc);
        panel.add(nameField, gbc);
        panel.add(scrollPane, gbc);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            resultArea.setText("");
            String searchName = nameField.getText();
            boolean found = false;

            for (Student student : university.getStudents().getAll()) {
                if (student.getName().toLowerCase().contains(searchName.toLowerCase())) {
                    resultArea.append(student.toString() + "\n\n");
                    found = true;
                }
            }

            if (!found) {
                resultArea.setText("No students found with the name: " + searchName);
            }
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));

        panel.add(searchButton, gbc);
        panel.add(backButton, gbc);

        return panel;
    }

    private JPanel createFilterCoursesPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField creditsField = new JTextField(20);
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        panel.add(new JLabel("Minimum Credits:"), gbc);
        panel.add(creditsField, gbc);
        panel.add(scrollPane, gbc);

        JButton filterButton = new JButton("Filter Courses");
        filterButton.addActionListener(e -> {
            try {
                int minCredits = Integer.parseInt(creditsField.getText());
                resultArea.setText("");
                boolean found = false;

                for (Course course : university.getCourses().getAll()) {
                    if (course.getCredits() >= minCredits) {
                        resultArea.append(course.getTitle() +
                                " (" + course.getCredits() + " Credits)\n");
                        found = true;
                    }
                }

                if (!found) {
                    resultArea.setText("No courses found with " + minCredits +
                            " or more credits.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number for credits!");
            }
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));

        panel.add(filterButton, gbc);
        panel.add(backButton, gbc);

        return panel;
    }

    private void saveData() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            university.saveData(fileChooser.getSelectedFile().getPath());
        }
    }

    private void loadData() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            university.loadData(fileChooser.getSelectedFile().getPath());
        }
    }

    private void displayStats() {
        JOptionPane.showMessageDialog(this,
                "Current System Statistics:\n\n" +
                        "Total Students: " + Student.getTotalStudents() + "\n" +
                        "Total Teachers: " + Teacher.getTotalTeachers() + "\n" +
                        "Total Courses: " + Course.getTotalCourses(),
                "System Statistics",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new SchoolManagementGUI().setVisible(true);
        });
    }
}
