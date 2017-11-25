package in.ashwanthkumar.slack.webhook;

import in.ashwanthkumar.slack.webhook.service.SlackService;
import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.lang.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import static in.ashwanthkumar.utils.lang.StringUtils.isEmpty;


/**
 * Slack provides programmatic access to Slack web hooks
 */
public class Slack {
    private String webhookUrl;
    private String channel;
    private String user;
    private String icon;
    private String parse;
    private SlackService slackService;

    public Slack(String webhookUrl, Proxy proxy) {
        if (isEmpty(webhookUrl)) {
            throw new IllegalArgumentException("Webhook url is not provided");
        }
        this.webhookUrl = webhookUrl;
        this.slackService = new SlackService(proxy);
    }

    public Slack(String webhookUrl) {
        this(webhookUrl, (Proxy) null);
    }

    public Slack(String webhookUrl, String hostname, int port) {
        this(webhookUrl, new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port)));
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
        if (StringUtils.isNotEmpty(imageOrIcon) && !StringUtils.startsWith(imageOrIcon, "http")) {
            if (!imageOrIcon.startsWith(":")) {
                imageOrIcon = ":" + imageOrIcon;
            }
            if (!imageOrIcon.endsWith(":")) {
                imageOrIcon = imageOrIcon + ":";
            }
        }

        this.icon = imageOrIcon;
        return this;
    }

    /**
     * Possible values being "full" or "none"
     * @param parse
     * @return
     */
    public Slack parse(String parse) {
        this.parse = parse;
        return this;
    }

    /**
     * Update the webhook url of the underlying Slack service
     * @param webhookUrl
     * @return
     */
    public Slack setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
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
            slackService.push(webhookUrl, message, user, icon, parse, channel);
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
            slackService.push(webhookUrl, new SlackMessage(), user, icon, channel, parse, Lists.of(attachment));
        }
    }

    /**
     * Publish message as SlackAttachment
     *
     * @param attachments SlackAttachment to send
     * @throws IOException
     */
    public void push(List<SlackAttachment> attachments) throws IOException {
        slackService.push(webhookUrl, new SlackMessage(), user, icon, channel, parse, attachments);
    }
}
