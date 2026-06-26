package university.entities;
import university.enums.Grade;
import university.interfaces.Payable;

public class Enrollment implements Payable {
    private Student student;
    private Course course;
    private int semester;
    private Grade grade;
    private boolean paid;

    public Enrollment(Student student, Course course, int semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = Grade.NA; 
        this.paid = false;     
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public int getSemester() { return semester; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }

    @Override
    public void markAsPaid() { this.paid = true; }

    @Override
    public boolean isPaid() { return paid; }

    @Override
    public String toString() {
        return "Зарахування: " + student.getName() + " -> " + course.getTitle() + " | Семестр: " + semester + " | Оцінка: " + grade + " | Оплачено: " + paid;
    }
}