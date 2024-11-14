import java.util.*;

abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean isOutStanding();

}

class Professor extends Person {
    private int numberOfPublications;

    public Professor(String name, int numberOfPublications) {
        super(name);
        this.numberOfPublications = numberOfPublications;
    }

    public int getNumberOfPublications() {
        return numberOfPublications;
    }

    public void setNumberOfPublications(int numberOfPublications) {
        this.numberOfPublications = numberOfPublications;
    }

    @Override
    public boolean isOutStanding(){
        return numberOfPublications > 50;
    }
}

class Student extends Person{
    private double CGPA;

    public Student(String name, double CGPA) {
        super(name);
        this.CGPA = CGPA;
    }

    public double getCGPA() {
        return CGPA;
    }

    public void setCGPA(double CGPA) {
        this.CGPA = CGPA;
    }

    @Override
    public boolean isOutStanding(){
        return CGPA > 3.5;
    }
    
}

public class LabTask2 {
    public static void main(String[] args) {
        Student student = new Student("Ahmad", 3.3);
        Student student2 = new Student("Hasanat", 4);
        Student student3 = new Student("Mujataba", 3.9);

        Professor professor = new Professor("TRA", 2);
        Professor professor2 = new Professor("Mohsin Sir", 1000);

        
        Person[] persons = new Person[5];

        persons[0] = student;
        persons[1] = professor;
        persons[2] = student2;
        persons[3] = student3;
        persons[4] = professor2;

        for (Person person : persons) {
            System.out.println(person.getName() + " is outstanding: " + person.isOutStanding());
        }
    }
}
