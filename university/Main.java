package university;

import university.entities.*;
import university.enums.*;
import university.services.*;
import java.util.Scanner;

public class Main {
    private static StudentService studentService = new StudentService();
    private static TeacherService teacherService = new TeacherService();
    private static CourseService courseService = new CourseService();
    private static EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        seedData();

        while (true) {
            System.out.println("\n--- Університетська Система ---");
            System.out.println("1. Студенти");
            System.out.println("2. Викладачі");
            System.out.println("3. Курси");
            System.out.println("4. Зарахування та Оцінки");
            System.out.println("5. Звіти / Пошук");
            System.out.println("0. Вихід");
            System.out.print("Оберіть опцію: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1": manageStudents(scanner); break;
                    case "2": manageTeachers(scanner); break;
                    case "3": manageCourses(scanner); break;
                    case "4": manageEnrollments(scanner); break;
                    case "5": manageReports(scanner); break;
                    case "0": System.out.println("Вихід..."); return;
                    default: System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка вводу: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Неочікувана помилка: " + e.getMessage());
            }
        }
    }

    private static void manageStudents(Scanner scanner) {
        System.out.println("\n--- Студенти ---");
        System.out.println("1. Показати всіх\n2. Додати студента\n3. Видалити студента\n4. Сортувати за ПІБ");
        String opt = scanner.nextLine();
        
        if (opt.equals("1")) {
            studentService.printAllStudents();
        } else if (opt.equals("2")) {
            System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
            System.out.print("ПІБ: "); String name = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.print("Рік навчання: "); int year = Integer.parseInt(scanner.nextLine());
            
            if (!email.contains("@")) throw new IllegalArgumentException("Неправильний формат email");
            
            studentService.addStudent(new Student(id, name, email, year, StudentStatus.ACTIVE));
            System.out.println("Студента успішно додано.");
        } else if (opt.equals("3")) {
            System.out.print("Введіть ID студента для видалення: ");
            int id = Integer.parseInt(scanner.nextLine());
            studentService.deleteStudent(id);
        } else if (opt.equals("4")) {
            studentService.sortStudentsByName();
        }
    }

    private static void manageTeachers(Scanner scanner) {
        System.out.println("\n--- Викладачі ---");
        System.out.println("1. Показати всіх\n2. Додати викладача");
        String opt = scanner.nextLine();
        
        if (opt.equals("1")) {
            teacherService.printAllTeachers();
        } else if (opt.equals("2")) {
            System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
            System.out.print("ПІБ: "); String name = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            teacherService.addTeacher(new Teacher(id, name, email, TeacherPosition.LECTURER));
            System.out.println("Викладача успішно додано.");
        }
    }

    private static void manageCourses(Scanner scanner) {
        System.out.println("\n--- Курси ---");
        System.out.println("1. Показати всі\n2. Додати курс");
        String opt = scanner.nextLine();
        
        if (opt.equals("1")) {
            courseService.printAllCourses();
        } else if (opt.equals("2")) {
            System.out.print("ID курсу: "); int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Назва: "); String title = scanner.nextLine();
            System.out.print("Кредити: "); int credits = Integer.parseInt(scanner.nextLine());
            System.out.print("ID Викладача: "); int tId = Integer.parseInt(scanner.nextLine());
            
            Teacher teacher = teacherService.findTeacherById(tId);
            courseService.addCourse(new Course(id, title, credits, teacher));
            System.out.println("Курс додано.");
        }
    }

    private static void manageEnrollments(Scanner scanner) {
        System.out.println("\n--- Зарахування ---");
        System.out.println("1. Зарахувати студента на курс\n2. Транскрипт студента (GPA)");
        String opt = scanner.nextLine();
        
        if (opt.equals("1")) {
            System.out.print("ID Студента: "); int sId = Integer.parseInt(scanner.nextLine());
            System.out.print("ID Курсу: "); int cId = Integer.parseInt(scanner.nextLine());
            System.out.print("Семестр (число): "); int sem = Integer.parseInt(scanner.nextLine());
            
            Student student = studentService.findStudentById(sId);
            Course course = courseService.findCourseById(cId);
            enrollmentService.enroll(new Enrollment(student, course, sem));
            System.out.println("Зарахування створено.");
        } else if (opt.equals("2")) {
            System.out.print("ID Студента: ");
            int sId = Integer.parseInt(scanner.nextLine());
            enrollmentService.printStudentTranscript(sId);
        }
    }

    private static void manageReports(Scanner scanner) {
        System.out.println("\n--- Звіти та Пошук ---");
        System.out.println("1. Пошук студента за ім'ям / email\n2. Список неоплачених курсів");
        String opt = scanner.nextLine();
        
        if (opt.equals("1")) {
            System.out.print("Введіть текст для пошуку: ");
            String query = scanner.nextLine();
            studentService.searchByNameOrEmail(query);
        } else if (opt.equals("2")) {
            enrollmentService.printUnpaidEnrollments();
        }
    }

    private static void seedData() {
        Teacher t1 = new Teacher(1, "Олександр Іванов", "ivanov@krestov.com", TeacherPosition.PROFESSOR);
        teacherService.addTeacher(t1);
        
        Course c1 = new Course(101, "Основи Java", 4, t1);
        courseService.addCourse(c1);
        
        Student s1 = new Student(1, "Марія Петренко", "maria@krestov.com", 2, StudentStatus.ACTIVE);
        Student s2 = new Student(2, "Іван Коваленко", "ivan@krestov.com", 1, StudentStatus.ACTIVE);
        studentService.addStudent(s1);
        studentService.addStudent(s2);
        
        Enrollment e1 = new Enrollment(s1, c1, 1);
        e1.setGrade(Grade.A);
        enrollmentService.enroll(e1);
    }
}