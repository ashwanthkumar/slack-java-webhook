package in.ashwanthkumar.slack.webhook.service;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.gson.GsonFactory;
import in.ashwanthkumar.slack.webhook.SlackMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static in.ashwanthkumar.slack.webhook.util.StringUtils.isNotEmpty;
import static in.ashwanthkumar.slack.webhook.util.StringUtils.startsWith;

public class SlackService {
    private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();

    public void push(String webHookUrl, SlackMessage text, String username, String imageOrIcon, String destination) throws IOException {
        Map<String, String> payload = new HashMap<String, String>();
        if (isNotEmpty(username)) {
            payload.put("username", username);
        }
        if (startsWith(imageOrIcon, "http")) {
            payload.put("icon_url", imageOrIcon);
        } else if (isNotEmpty(imageOrIcon)) {
            payload.put("icon_emoji", imageOrIcon);
        }
        if (isNotEmpty(destination)) {
            payload.put("channel", destination);
        }
        payload.put("text", text.toString());
        execute(webHookUrl, payload);
    }

    public void execute(String webHookUrl, Map<String, String> payload) throws IOException {
        HttpRequest httpRequest = requestFactory.buildPostRequest(new GenericUrl(webHookUrl), new JsonHttpContent(new GsonFactory(), payload));
        httpRequest.execute();
    }
}
