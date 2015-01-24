package in.ashwanthkumar.slack.webhook.service;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Maps;
import com.google.gson.Gson;
import in.ashwanthkumar.slack.webhook.SlackAttachment;
import in.ashwanthkumar.slack.webhook.SlackMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static in.ashwanthkumar.utils.lang.StringUtils.isNotEmpty;
import static in.ashwanthkumar.utils.lang.StringUtils.startsWith;


public class SlackService {
    private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();

    public void push(String webHookUrl, SlackMessage text, String username, String imageOrIcon, String destination, List<SlackAttachment> attachments) throws IOException {
        Map<String, Object> payload = new HashMap<String, Object>();
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
        if (!attachments.isEmpty()) {
            payload.put("attachments", attachments);
        }
        payload.put("text", text.toString());
        execute(webHookUrl, payload);
    }

    public void push(String webHookUrl, SlackMessage text, String username, String imageOrIcon, String destination) throws IOException {
        push(webHookUrl, text, username, imageOrIcon, destination, new ArrayList<SlackAttachment>());
    }

    public void execute(String webHookUrl, Map<String, Object> payload) throws IOException {
        String jsonEncodedMessage = new Gson().toJson(payload);
        HashMap<Object, Object> payloadToSend = Maps.newHashMap();
        payloadToSend.put("payload", jsonEncodedMessage);

        requestFactory.buildPostRequest(new GenericUrl(webHookUrl), new UrlEncodedContent(payloadToSend))
                .execute();
    }
}
