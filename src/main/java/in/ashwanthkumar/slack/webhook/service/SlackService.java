package in.ashwanthkumar.slack.webhook.service;


import com.google.gson.Gson;
import in.ashwanthkumar.slack.webhook.SlackAttachment;
import in.ashwanthkumar.slack.webhook.SlackMessage;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static in.ashwanthkumar.slack.webhook.StringUtils.isNotEmpty;
import static in.ashwanthkumar.slack.webhook.StringUtils.startsWith;


public class SlackService {

    private final Proxy proxy;

    public SlackService(
        Proxy proxy
    ) {
        this.proxy = proxy;
    }

    public SlackService() {
        this(null);
    }

    public void push(
        String webHookUrl,
        SlackMessage text,
        String username,
        String imageOrIcon,
        String destination,
        String parse,
        List<SlackAttachment> attachments
    ) throws IOException {
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
        if (isNotEmpty(parse)) {
            payload.put("parse", parse);
        }
        payload.put("text", text.toString());
        execute(webHookUrl, payload);
    }

    public void push(String webHookUrl, SlackMessage text, String username, String imageOrIcon, String parse, String destination) throws IOException {
        push(webHookUrl, text, username, imageOrIcon, destination, parse, new ArrayList<SlackAttachment>());
    }

    public void execute(String webHookUrl, Map<String, Object> payload) throws IOException {
        String jsonEncodedMessage = new Gson().toJson(payload);

        URL obj = new URL(webHookUrl);
        HttpsURLConnection con = (HttpsURLConnection)(proxy != null ?
            obj.openConnection(proxy)
            :
            obj.openConnection()
        )
            ;

        //add reuqest header
        con.setRequestMethod("POST");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "payload=" + URLEncoder.encode(jsonEncodedMessage, "UTF-8");

        // Send post request
        con.setDoOutput(true);
        //con.setDoInput(false);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());

        try {
            wr.writeBytes(urlParameters);
            wr.flush();
        } finally {
            wr.close();
        }


        con.getInputStream().close();

        //TODO: do we throw for bad codes?
        int responseCode = con.getResponseCode();


    }


}
