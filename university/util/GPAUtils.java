package university.util;
import university.enums.Grade;

public class GPAUtils {
    public static double getPointsForGrade(Grade grade) {
        switch (grade) {
            case A: return 4.0;
            case B: return 3.0;
            case C: return 2.0;
            case D: return 1.0;
            case F: return 0.0;
            case NA: default: return 0.0;
        }
    }
}