package university.services;
import university.entities.Course;

public class CourseService {
    private Course[] courses = new Course[50];
    private int count = 0;

    public void addCourse(Course course) {
        if (count >= courses.length) throw new IllegalStateException("База курсів переповнена.");
        courses[count++] = course;
    }

    public void printAllCourses() {
        if (count == 0) {
            System.out.println("Немає доступних курсів.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(courses[i]);
        }
    }

    public Course findCourseById(int id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getCourseId() == id) return courses[i];
        }
        throw new IllegalArgumentException("Курс з ID " + id + " не знайдено.");
    }
}