package in.ashwanthkumar.slack.webhook;



/**
 * Wrapper to build rich text content in Slack
 * Ref - https://api.slack.com/docs/formatting
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
        if (text == null || "".equals(text.trim())) {
            textBuffer.append("<").append(url).append(">");
        } else {
            textBuffer.append("<").append(url).append("|").append(text).append(">");
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
        textBuffer.append("\n> ").append(text).append("\n");
        return this;
    }

    public String toString() {
        return textBuffer.toString();
    }

    public String rawText() {
        // We're not removing link because it's readable the way it is.
        return textBuffer.toString()
            .replaceAll("(.*)\\*(.*)\\*(.*)", "$1$2$3") // Remove bold formatting
            .replaceAll("(.*)_(.*)_(.*)", "$1$2$3")     // Remove italic formatting
            .replaceAll("(.*)```(.*)```(.*)", "$1$2$3") // Remove pretext formatting
            .replaceAll("(.*)`(.*)`(.*)", "$1$2$3")     // Remove code formatting
            .replaceAll("\n>\\s+(.*)\n", "$1");         // Remove Quote formatting
    }
}
