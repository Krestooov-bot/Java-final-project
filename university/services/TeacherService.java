package university.services;
import university.entities.Teacher;

public class TeacherService {
    private Teacher[] teachers = new Teacher[50];
    private int count = 0;

    public void addTeacher(Teacher teacher) {
        if (count >= teachers.length) throw new IllegalStateException("База викладачів переповнена.");
        teachers[count++] = teacher;
    }

    public void printAllTeachers() {
        if (count == 0) {
            System.out.println("Немає зареєстрованих викладачів.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(teachers[i]);
        }
    }

    public Teacher findTeacherById(int id) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getId() == id) return teachers[i];
        }
        throw new IllegalArgumentException("Викладача з ID " + id + " не знайдено.");
    }
}