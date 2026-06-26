package university.entities;

public class Course {
    private int courseId;
    private String title;
    private int credits;
    private Teacher teacher;

    public Course(int courseId, String title, int credits, Teacher teacher) {
        this.courseId = courseId;
        this.title = title;
        this.credits = credits;
        this.teacher = teacher;
    }

    public int getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Teacher getTeacher() { return teacher; }

    @Override
    public String toString() {
        return "Курс [" + courseId + "] " + title + " (" + credits + " кред.) | Викладач: " + (teacher != null ? teacher.getName() : "Немає");
    }
}