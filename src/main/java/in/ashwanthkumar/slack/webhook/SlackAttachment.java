package in.ashwanthkumar.slack.webhook;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Ref - https://api.slack.com/docs/attachments
 */
public class SlackAttachment {
    @SerializedName("fallback")
    private String fallback;
    @SerializedName("color")
    private String color;
    @SerializedName("pretext")
    private String pretext;
    @SerializedName("author_name")
    private String authorName;
    @SerializedName("author_link")
    private String authorLink;
    @SerializedName("author_icon")
    private String authorIcon;
    @SerializedName("title")
    private String title;
    @SerializedName("title_link")
    private String titleLink;
    @SerializedName("text")
    private String text;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("thumb_url")
    private String thumbUrl;
    @SerializedName("footer")
    private String footer;
    @SerializedName("footer_icon")
    private String footerIcon;
    @SerializedName("ts")
    private int timestamp;
    @SerializedName("fields")
    private List<Field> fields = new ArrayList<Field>();
    @SerializedName("mrkdwn_in")
    private List<String> markdown = new ArrayList<String>();
    
    public SlackAttachment(String text) {
        text(text);
    }

    public static class Field {
        @SerializedName("title")
        private String title;
        @SerializedName("value")
        private String value;
        @SerializedName("short")
        private boolean isShort;

        public Field(String title, String value, boolean isShort) {
            this.title = title;
            this.value = value;
            this.isShort = isShort;
        }

        public String getTitle() {
            return title;
        }

        public String getValue() {
            return value;
        }

        public boolean isShort() {
            return isShort;
        }
    }

    public SlackAttachment fallback(String fallbackText) {
        this.fallback = fallbackText;
        return this;
    }

    public SlackAttachment color(String colorInHex) {
        this.color = colorInHex;
        return this;
    }

    public SlackAttachment preText(String pretext) {
        this.pretext = pretext;
        return this;
    }

    public SlackAttachment author(String name) {
        this.authorName = name;
        return this;
    }

    public SlackAttachment author(String name, String link) {
        this.authorLink = link;
        return author(name);
    }

    public SlackAttachment author(String name, String link, String iconOrImageLink) {
        this.authorIcon = iconOrImageLink;
        return author(name, link);
    }

    public SlackAttachment title(String title) {
        this.title = title;
        return this;
    }

    public SlackAttachment title(String title, String link) {
        this.titleLink = link;
        return title(title);
    }

    public SlackAttachment imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public SlackAttachment text(String text) {
        this.text = text;
        return this;
    }

    public SlackAttachment text(SlackMessage message) {
        return text(message.toString());
    }

    public SlackAttachment thumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public SlackAttachment footer(String footer) {
        this.footer = footer;
        return this;
    }

    public SlackAttachment footerIcon(String footerIcon) {
        this.footerIcon = footerIcon;
        return this;
    }

    public SlackAttachment timestamp(int timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public SlackAttachment addField(Field field) {
        this.fields.add(field);
        return this;
    }

    public SlackAttachment addMarkdownIn(String markdownin) {
        this.markdown.add(markdownin);
        return this;
    }
    
    public String getText() {
        return text;
    }
}
