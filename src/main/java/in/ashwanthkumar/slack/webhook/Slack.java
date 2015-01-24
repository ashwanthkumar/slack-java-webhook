package in.ashwanthkumar.slack.webhook;

import in.ashwanthkumar.slack.webhook.service.SlackService;
import in.ashwanthkumar.slack.webhook.util.Lists;

import java.io.IOException;
import java.util.List;

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

    /**
     * Publish message as SlackAttachment
     *
     * @param attachment SlackAttachment to send
     * @throws IOException
     */
    public void push(SlackAttachment attachment) throws IOException {
        if (attachment != null) {
            slackService.push(webhookUrl, new SlackMessage(), user, icon, channel, Lists.of(attachment));
        }
    }

    /**
     * Publish message as SlackAttachment
     *
     * @param attachments SlackAttachment to send
     * @throws IOException
     */
    public void push(List<SlackAttachment> attachments) throws IOException {
        slackService.push(webhookUrl, new SlackMessage(), user, icon, channel, attachments);
    }
}
