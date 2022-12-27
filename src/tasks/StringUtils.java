package tasks;

public class StringUtils {
    public static boolean isValid (String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }
}
