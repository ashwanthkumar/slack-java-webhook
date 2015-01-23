package in.ashwanthkumar.slack.webhook.util;

public class StringUtils {
    public static boolean isNotEmpty(String text) {
        return (text != null) && !text.isEmpty();
    }

    public static boolean isEmpty(String text) {
        return !isNotEmpty(text);
    }

    public static boolean startsWith(String str, String prefix) {
        return isNotEmpty(str) && str.startsWith(prefix);
    }
}
