package in.ashwanthkumar.slack.webhook;

public class SlackLinkUtils {
    public static String email(String email, String user) {
        return String.format("<mailto:%s|%s>", email, user);
    }

    public static String email(String email) {
        return String.format("<mailto:%s>", email);
    }

    public static String link(String href, String name) {
        return String.format("<%s|%s>", href, name);
    }

    public static String link(String href) {
        return String.format("<%s>", href);
    }

    public static String user(String handle, String name) {
        return String.format("<@%s|%s>", handle, name);
    }

    public static String user(String handle) {
        return String.format("<@%s>", handle);
    }

    public static String channel(String channel) {
        return String.format("<#%s>", channel);
    }
}
