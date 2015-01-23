package in.ashwanthkumar.slack.webhook;


import in.ashwanthkumar.slack.webhook.util.StringUtils;

/**
 * Wrapper to build rich text content in Slack
 */
public class SlackMessage {
    private StringBuilder textBuffer = new StringBuilder();

    public SlackMessage() {
    }

    public SlackMessage(String text) {
        text(text);
    }

    public SlackMessage text(String text) {
        textBuffer.append(text);
        return this;
    }

    public SlackMessage link(String url) {
        link(url, "");
        return this;
    }

    public SlackMessage link(String url, String text) {
        if (StringUtils.isNotEmpty(text)) {
            textBuffer.append("<").append(url).append("|").append(text).append(">");
        } else {
            textBuffer.append("<").append(url).append(">");
        }

        return this;
    }

    public SlackMessage bold(String text) {
        textBuffer.append("*").append(text).append("*");
        return this;
    }

    public SlackMessage italic(String text) {
        textBuffer.append("_").append(text).append("_");
        return this;
    }

    public SlackMessage code(String code) {
        textBuffer.append("`").append(code).append("`");
        return this;
    }

    public SlackMessage preformatted(String text) {
        textBuffer.append("```").append(text).append("```");
        return this;
    }

    public SlackMessage quote(String text) {
        textBuffer.append("> ").append(text).append("\n");
        return this;
    }

    public String toString() {
        return textBuffer.toString();
    }
}
