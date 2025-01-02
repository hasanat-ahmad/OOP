import java.io.*;
// import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Reportable {
    String generateReport();

    void exportToFile(String filename);
}

abstract class Person implements Serializable {
    private String name;
    private String email;
    private String dateOfBirth;

    public Person() {
        this.name = null;
        this.email = null;
        this.dateOfBirth = null;
    }

    public Person(String name, String email, String dob) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDateOfBirth(String dob) {
        this.dateOfBirth = dob;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public abstract void displayDetails();

    @Override
    public String toString() {
        return "Name: " + name + "\nEmail: " + email + "\nDate of Birth: " + dateOfBirth;
    }
}

class Student extends Person {
    private static int totalStudents = 0;
    private String studentID;
    private String address;
    private ArrayList<Course> enrolledCourses;
    private List<GradeEntry> grades = new ArrayList<>();

    public Student() {
        super();
        this.studentID = "";
        this.address = "";
        this.enrolledCourses = new ArrayList<>();
        totalStudents++;
    }

    public Student(String name, String email, String dob, String id, String address) {
        super(name, email, dob);
        this.studentID = id;
        this.address = address;
        this.enrolledCourses = new ArrayList<>();

        totalStudents++;
    }

    public void setStudentID(String id) {
        this.studentID = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getAddress() {
        return address;
    }

    public static int getTotalStudents() {
        return totalStudents;
    }

    private static class GradeEntry {
        private Course course;
        private double grade;

        public GradeEntry(Course course, double grade) {
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException("Grade must be between 0 and 100");
            }
            this.course = course;
            this.grade = grade;
        }

        public Course getCourse() {
            return course;
        }

        public double getGrade() {
            return grade;
        }
    }

    public void addGrade(Course course, double grade) {
        for (GradeEntry entry : grades) {
            if (entry.getCourse().equals(course)) {
                grades.remove(entry);
                break;
            }
        }
        grades.add(new GradeEntry(course, grade));
    }

    public List<GradeEntry> getGrades() {
        return new ArrayList<>(grades);
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (GradeEntry entry : grades) {
            sum += entry.getGrade();
        }
        return sum / grades.size();
    }

    public void enrollInCourse(ArrayList<Course> courses) {
        for (Course course : courses) {
            if (!enrolledCourses.contains(course)) {
                enrolledCourses.add(course);
                System.out.println("Student " + studentID + " successfully enrolled in " + course.getTitle());
            } else {
                System.out.println("Student " + studentID + " is already enrolled in " + course.getTitle());
            }
        }
    }

    // public void addACourse(Course course) {
    // if (!enrolledCourses.contains(course)) {
    // enrolledCourses.add(course);
    // }
    // }

    public void displayCourses() {
        System.out.println("Courses for Student " + studentID + ":");
        for (Course course : enrolledCourses) {
            System.out.println(course.getTitle() + " (" + course.getCredits() + "Credits)");
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("Student ID: " + studentID);
        System.out.println(this.toString());
        displayCourses();
    }
}

class Teacher extends Person implements Reportable {
    private static int totalTeachers = 0;
    private String teacherID;
    private String specialization;
    ArrayList<Course> coursesTaught;
    private List<Student> assignedStudents;

    public Teacher() {
        super();
        this.teacherID = " ";
        this.specialization = " ";
        coursesTaught = new ArrayList<>();
        totalTeachers++;
    }
    
    public void displaystudetsassignedtoTeachers(){
        for (Student student: assignedStudents){
            System.out.println(student.getName());
        }
    }

    public Teacher(String name, String email, String dob, String id, String specialization) {
        super(name, email, dob);
        this.teacherID = id;
        this.specialization = specialization;
        coursesTaught = new ArrayList<>();

        totalTeachers++;
    }

    public void setTeacherID(String id) {
        this.teacherID = id;
    }

    public void setSepcialization(String spec) {
        this.specialization = spec;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public static int getTotalTeachers() {
        return totalTeachers;
    }
    // public void displayCoursesforStudent(){
    //     for (Course coursesforStduent: enrolledCourses)
    // }

    @Override
    public String generateReport() {
        return ("Total Students: " + Student.getTotalStudents() + "\nTTotal Teachers: " + Teacher.getTotalTeachers()
                + "\nTotal Courses: " + Course.getTotalCourses());
    }

    @Override
    public void exportToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Teacher report successfully exported to " + fileName);
        } catch (IOException e) {
            System.out.println("Error exporting teacher report to file: " + e.getMessage());
        }
    }

    public void assignCourse(Course course) {
        coursesTaught.add(course);
        System.out.println("Course " + course.getTitle() + " assigned to the Teacher " + teacherID);
    }

    public void displayCourses() {
        System.out.println("Courses assigned to thr teacher " + teacherID + " are");
        for (Course course : coursesTaught) {
            System.out.println(course.getTitle() + " (" + course.getCredits() + "Credits)");
        }
    }

    public List<Course> teacherCourses(){

        return coursesTaught;

    }

    @Override
    public String toString() {
        return getTeacherID() + " - " + getName() + " (" + getSpecialization() + ")";
    }

    @Override
    public void displayDetails() {
        System.out.println("Teacher ID: " + teacherID);
        System.out.println(this.toString());
        displayCourses();
    }
}

class AdministrativeStaff extends Person implements Reportable {
    private String staffID;
    private String role;
    private String department;

    public AdministrativeStaff() {
        super();
        this.staffID = " ";
        this.role = " ";
        this.department = " ";
    }

    public AdministrativeStaff(String name, String email, String dob, String id, String role, String department) {
        super(name, email, dob);
        this.staffID = id;
        this.role = role;
        this.department = department;
    }

    public void setStaffID(String id) {
        this.staffID = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public void displayDetails() {
        System.out.println("Staff ID: " + staffID);
        System.out.println(this.toString());
    }

    @Override
    public String generateReport() {
        String report = "Total Students: " + Student.getTotalStudents() + "\n" +
                "Total Teachers: " + Teacher.getTotalTeachers() + "\n" +
                "Total Courses: " + Course.getTotalCourses();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
            writer.write(report);
            writer.newLine();
            System.out.println("Report generated and saved to 'report.txt'.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        return report;
    }

    @Override
    public void exportToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Administrative staff report successfully exported to " + fileName);
        } catch (IOException e) {
            System.out.println("Error exporting administrative staff report to file: " + e.getMessage());
        }
    }

}

class Course {
    private static int totalCourses = 0;
    private String courseID;
    private String title;
    private int credits;
    private Teacher assignedTeacher;
    private List<Double> grades;

    public Course() {
        this.courseID = " ";
        this.title = " ";
        this.credits = 0;
        this.assignedTeacher = new Teacher();
        totalCourses++;
    }

    public Course(String id, String title, int credits, Teacher assignedTeacher, ArrayList<Student> enrolledStudents) {
        this.courseID = id;
        this.title = title;
        this.credits = credits;
        this.assignedTeacher = assignedTeacher;
        this.grades = new ArrayList<>();

        totalCourses++;
    }

    public void setCourseID(String id) {
        this.courseID = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public static int getTotalCourses() {
        return totalCourses;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public void calculateAverageGrade() {
        if (grades.isEmpty()) {
            System.out.println("No grades available for this course.");
            return;
        }

        double total = 0.0;
        for (double grade : grades) {
            total += grade;
        }
        double average = total / grades.size();
        System.out.println("Average grade for " + title + ": " + average);
    }

    public Teacher getAssignedTeacher() {
        return assignedTeacher;
    }

    public void setAssignedTeacher(Teacher assignedTeacher) {
        this.assignedTeacher = assignedTeacher;
    }

    @Override
    public String toString() {
        return getCourseID() + " - " + getTitle() + " (" + getCredits() + " credits)";
    }
}

class Repository<T> {
    private List<T> items;

    public Repository() {
        items = new ArrayList<>();
    }

    public void add(T item) {
        if (items.contains(item)) {
            System.out.println("Item already exists: " + item.toString());
        } else {
            items.add(item);
            System.out.println("Item added: " + item.toString());
        }
    }

    public void remove(T item) {
        if (items.remove(item)) {
            System.out.println("Item removed: " + item.toString());
        } else {
            System.out.println("Item not found: " + item.toString());
        }
    }

    public List<T> getAll() {
        return items;
    }
}

class University {
    private Repository<Student> students;
    private Repository<Teacher> teachers;
    private Repository<Course> courses;

    public University() {
        students = new Repository<>();
        teachers = new Repository<>();
        courses = new Repository<>();
    }

    public void addStudent(Student student) {
        try {
            students.add(student);
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void addTeacher(Teacher teacher) {
        try {
            teachers.add(teacher);
        } catch (Exception e) {
            System.out.println("Error adding teacher: " + e.getMessage());
        }
    }

    public void addCourse(Course course) {
        try {
            courses.add(course);
        } catch (Exception e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }
    

    public void searchStudentByName(String name) {
        try {
            System.out.println("Searching for students with name: " + name);
            for (Student student : students.getAll()) {
                if (student.getName().equalsIgnoreCase(name)) {
                    student.displayDetails();
                    return;
                }

            }
            System.out.println("No student by that name");

        } catch (Exception e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
    }

    public void filterCoursesByCredits(int minCredits) {
        try {
            System.out.println("Courses with at least " + minCredits + " credits:");
            for (Course course : courses.getAll()) {
                if (course.getCredits() >= minCredits) {
                    System.out.println(course.getTitle() + " (" + course.getCredits() + " Credits)");
                }
            }
        } catch (Exception e) {
            System.out.println("Error filtering courses: " + e.getMessage());
        }
    }


    public void saveData(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            oos.writeObject(teachers);
            oos.writeObject(courses);
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void loadData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (Repository<Student>) ois.readObject();
            teachers = (Repository<Teacher>) ois.readObject();
            courses = (Repository<Course>) ois.readObject();
            System.out.println("Data loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public static void displaySystemStats() {
        try {
            System.out.println("Total Students: " + Student.getTotalStudents());
            System.out.println("Total Teachers: " + Teacher.getTotalTeachers());
            System.out.println("Total Courses: " + Course.getTotalCourses());
        } catch (Exception e) {
            System.out.println("Error displaying system stats: " + e.getMessage());
        }
    }



    public Repository<Student> getStudents() {
        return students;
    }

    public Repository<Teacher> getTeachers() {
        return teachers;
    }

    public Repository<Course> getCourses() {
        return courses;
    }

    public Teacher getTeacherById(String text) {
        return null;
    }
}

public class SchoolManagementSystem {
    public static void main(String[] args) {
        University university = new University();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n======= School Management System =======");
            System.out.println("1. Add a Student");
            System.out.println("2. Add a Teacher");
            System.out.println("3. Add a Course");
            System.out.println("4. Enroll Student in Courses");
            System.out.println("5. Assign Course to Teacher");
            System.out.println("6. Display All Students");
            System.out.println("7. Display All Teachers");
            System.out.println("8. Display All Courses");
            System.out.println("9. Search for a Student by Name");
            System.out.println("10. Filter Courses by Minimum Credits");
            System.out.println("11. Save Data to File");
            System.out.println("12. Load Data from File");
            System.out.println("13. Display System Statistics");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String studentEmail = scanner.nextLine();
                    System.out.print("Enter Date of Birth: ");
                    String studentDOB = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String studentAddress = scanner.nextLine();

                    Student newStudent = new Student(studentName, studentEmail, studentDOB, studentID, studentAddress);
                    university.addStudent(newStudent);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Teacher Name: ");
                    String teacherName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String teacherEmail = scanner.nextLine();
                    System.out.print("Enter Date of Birth: ");
                    String teacherDOB = scanner.nextLine();
                    System.out.print("Enter Teacher ID: ");
                    String teacherID = scanner.nextLine();
                    System.out.print("Enter Specialization: ");
                    String teacherSpecialization = scanner.nextLine();

                    Teacher newTeacher = new Teacher(teacherName, teacherEmail, teacherDOB, teacherID,
                            teacherSpecialization);
                    university.addTeacher(newTeacher);
                    System.out.println("Teacher added successfully!");
                    break;

                case 3:
                    System.out.print("Enter Course ID: ");
                    String courseID = scanner.nextLine();
                    System.out.print("Enter Course Title: ");
                    String courseTitle = scanner.nextLine();
                    System.out.print("Enter Course Credits: ");
                    int credits = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Teacher ID for this Course: ");
                    String assignedTeacherID = scanner.nextLine();

                    Teacher assignedTeacher = null;
                    for (Teacher teacher : university.getTeachers().getAll()) {
                        if (teacher.getTeacherID().equals(assignedTeacherID)) {
                            assignedTeacher = teacher;
                            break;
                        }
                    }

                    if (assignedTeacher == null) {
                        System.out.println("Teacher not found. Please add the teacher first.");
                    } else {
                        Course newCourse = new Course(courseID, courseTitle, credits, assignedTeacher,
                                new ArrayList<>());
                        university.addCourse(newCourse);
                        System.out.println("Course added successfully!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    String enrollStudentID = scanner.nextLine();

                    Student enrollStudent = null;
                    for (Student student : university.getStudents().getAll()) {
                        if (student.getStudentID().equals(enrollStudentID)) {
                            enrollStudent = student;
                            break;
                        }
                    }

                    if (enrollStudent == null) {
                        System.out.println("Student not found. Please add the student first.");
                    } else {
                        ArrayList<Course> availableCourses = new ArrayList<>(university.getCourses().getAll());
                        enrollStudent.enrollInCourse(availableCourses);
                    }
                    break;

                case 5:
                    System.out.print("Enter Teacher ID: ");
                    String assignTeacherID = scanner.nextLine();
                    Teacher assignTeacher = null;
                    for (Teacher teacher : university.getTeachers().getAll()) {
                        if (teacher.getTeacherID().equals(assignTeacherID)) {
                            assignTeacher = teacher;
                            break;
                        }
                    }

                    if (assignTeacher == null) {
                        System.out.println("Teacher not found.");
                    } else {
                        System.out.print("Enter Course ID: ");
                        String assignCourseID = scanner.nextLine();
                        Course assignCourse = null;
                        for (Course course : university.getCourses().getAll()) {
                            if (course.getCourseID().equals(assignCourseID)) {
                                assignCourse = course;
                                break;
                            }
                        }

                        if (assignCourse == null) {
                            System.out.println("Course not found.");
                        } else {
                            assignTeacher.assignCourse(assignCourse);
                        }
                    }
                    break;

                case 6:
                    for (Student student : university.getStudents().getAll()) {
                        student.displayDetails();
                    }
                    break;

                case 7:
                    for (Teacher teacher : university.getTeachers().getAll()) {
                        teacher.displayDetails();
                        teacher.displaystudetsassignedtoTeachers();
                    }
                    break;

                case 8:
                    for (Course course : university.getCourses().getAll()) {
                        System.out.println(course.getTitle() + " (" + course.getCredits() + " Credits)");
                    }
                    break;

                case 9:
                    System.out.print("Enter Student Name: ");
                    String searchName = scanner.nextLine();
                    university.searchStudentByName(searchName);
                    break;

                case 10:
                    System.out.print("Enter Minimum Credits: ");
                    int minCredits = scanner.nextInt();
                    scanner.nextLine();
                    university.filterCoursesByCredits(minCredits);
                    break;

                case 11:
                    System.out.print("Enter Filename to Save Data: ");
                    String saveFilename = scanner.nextLine();
                    university.saveData(saveFilename);
                    break;

                case 12:
                    System.out.print("Enter Filename to Load Data: ");
                    String loadFilename = scanner.nextLine();
                    university.loadData(loadFilename);
                    break;

                case 13:
                    University.displaySystemStats();
                    break;

                case 0:
                    System.out.println("Exiting the program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }
}
