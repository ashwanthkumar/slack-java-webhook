package in.ashwanthkumar.slack.webhook;

import java.io.IOException;

import static in.ashwanthkumar.slack.webhook.util.StringUtils.isEmpty;

/**
 * Slack provides programmatic access to Slack web hooks
 */
public class Slack {
    private String webhookUrl;
    private String channel;
    private String user;
    private String icon;
    private SlackService slackService = new SlackService();

    public Slack(String webhookUrl) {
        if (isEmpty(webhookUrl)) {
            throw new IllegalArgumentException("Webhook url is not provided");
        } else if (!webhookUrl.startsWith("https://hooks.slack.com/services/")) {
            throw new IllegalArgumentException("Slack Webhook url starts with https://hooks.slack.com/services/");
        }
        this.webhookUrl = webhookUrl;
    }

    /**
     * Used for tests
     */
    Slack(String webhookUrl, SlackService mockService) {
        this.webhookUrl = webhookUrl;
        slackService = mockService;
    }

    /**
     * Send the message to a particular channel
     *
     * @param channel Channel to send
     */
    public Slack sendToChannel(String channel) {
        this.channel = "#" + channel;
        return this;
    }

    /**
     * Send the message to a particular user
     *
     * @param sendToUser User to send
     */
    public Slack sendToUser(String sendToUser) {
        this.channel = "@" + sendToUser;
        return this;
    }

    /**
     * Change the display name
     *
     * @param user Display name
     */
    public Slack displayName(String user) {
        this.user = user;
        return this;
    }

    /**
     * Change the bot icon
     *
     * @param imageOrIcon Icon Image URL or emoji code from http://www.emoji-cheat-sheet.com/
     */
    public Slack icon(String imageOrIcon) {
        this.icon = imageOrIcon;
        return this;
    }

    /**
     * Publishes messages to Slack Webhook
     *
     * @param message Message to send
     * @throws IOException
     */
    public void push(SlackMessage message) throws IOException {
        if (message != null) {
            slackService.push(webhookUrl, message, user, icon, channel);
        }
    }
}
