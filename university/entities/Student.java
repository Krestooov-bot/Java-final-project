package university.entities;
import university.enums.StudentStatus;

public class Student extends Person {
    private int yearOfStudy;
    private StudentStatus status;

    public Student(int id, String name, String email, int yearOfStudy, StudentStatus status) {
        super(id, name, email);
        if (yearOfStudy < 1) throw new IllegalArgumentException("Рік навчання має бути >= 1");
        this.yearOfStudy = yearOfStudy;
        this.status = status;
    }

    public int getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy; }
    public StudentStatus getStatus() { return status; }
    public void setStatus(StudentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Студент [" + getId() + "] " + getName() + " | Email: " + getEmail() + " | Курс: " + yearOfStudy + " | Статус: " + status;
    }
}