package university.services;
import university.entities.Student;

public class StudentService {
    private Student[] students = new Student[100];
    private int count = 0;

    public void addStudent(Student student) {
        if (count >= students.length) throw new IllegalStateException("База студентів переповнена.");
        students[count++] = student;
    }

    public void printAllStudents() {
        if (count == 0) {
            System.out.println("Немає зареєстрованих студентів.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
    }

    public Student findStudentById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) return students[i];
        }
        throw new IllegalArgumentException("Студента з ID " + id + " не знайдено.");
    }

    public void deleteStudent(int id) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) throw new IllegalArgumentException("Студента не знайдено.");
        
        for (int i = index; i < count - 1; i++) {
            students[i] = students[i + 1];
        }
        students[--count] = null;
        System.out.println("Студента видалено.");
    }

    public void sortStudentsByName() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (students[j].getName().compareToIgnoreCase(students[j + 1].getName()) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        System.out.println("Студентів відсортовано за ПІБ.");
        printAllStudents();
    }

    public void searchByNameOrEmail(String query) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].getName().toLowerCase().contains(query.toLowerCase()) || 
                students[i].getEmail().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(students[i]);
                found = true;
            }
        }
        if (!found) System.out.println("За запитом '" + query + "' нічого не знайдено.");
    }
}