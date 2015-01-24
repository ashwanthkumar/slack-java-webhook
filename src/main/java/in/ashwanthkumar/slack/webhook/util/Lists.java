package in.ashwanthkumar.slack.webhook.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lists {
    public static <T> List<T> of(T... elements) {
        if(elements == null) return new ArrayList<T>();
        else return Arrays.asList(elements);
    }
}
