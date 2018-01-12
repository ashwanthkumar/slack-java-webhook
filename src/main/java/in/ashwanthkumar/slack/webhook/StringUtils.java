package in.ashwanthkumar.slack.webhook;

public class StringUtils {

    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }


    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }


    public static boolean startsWith(String haystack, String needle){
        return haystack != null && haystack.startsWith(needle);
    }
}
