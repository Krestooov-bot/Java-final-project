package university.services;
import university.entities.Enrollment;
import university.util.GPAUtils;

public class EnrollmentService {
    private Enrollment[] enrollments = new Enrollment[200];
    private int count = 0;

    public void enroll(Enrollment enrollment) {
        if (count >= enrollments.length) throw new IllegalStateException("Ліміт зарахувань вичерпано.");
        enrollments[count++] = enrollment;
    }

    public void printStudentTranscript(int studentId) {
        double totalPoints = 0;
        int totalCredits = 0;
        boolean found = false;

        System.out.println("Транскрипт студента ID: " + studentId);
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudent().getId() == studentId) {
                found = true;
                System.out.println(enrollments[i]);
                int credits = enrollments[i].getCourse().getCredits();
                totalPoints += GPAUtils.getPointsForGrade(enrollments[i].getGrade()) * credits;
                totalCredits += credits;
            }
        }
        
        if (!found) {
            System.out.println("Зарахувань не знайдено.");
        } else if (totalCredits > 0) {
            System.out.printf("Загальний GPA: %.2f\n", (totalPoints / totalCredits));
        }
    }

    public void printUnpaidEnrollments() {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (!enrollments[i].isPaid()) {
                System.out.println(enrollments[i]);
                found = true;
            }
        }
        if (!found) System.out.println("Усі курси оплачені!");
    }
}