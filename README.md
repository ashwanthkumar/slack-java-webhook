[![Build Status](https://travis-ci.org/ashwanthkumar/slack-java-webhook.svg)](https://travis-ci.org/ashwanthkumar/slack-java-webhook)

# slack-java-webhook
Java Client to Slack's Webhook feature. 

## Dependencies

For Maven,
```xml
<dependency>
  <groupId>in.ashwanthkumar</groupId>
  <artifactId>slack-java-webhook</artifactId>
  <version>0.0.7</version>
</dependency>
```

For SBT,
```
libraryDependencies += "in.ashwanthkumar" % "slack-java-webhook" % "0.0.7"
```

## Usage
```java
// Using SlackMessage
new Slack(webhookUrl)
    .icon(":smiling_imp:") // Ref - http://www.emoji-cheat-sheet.com/
    .sendToUser("slackbot")
    .displayName("slack-java-client")
    .push(new SlackMessage("Text from my ").bold("Slack-Java-Client"));

// Using SlackAttachment
new Slack(webhookUrl)
    .sendToUser("slackbot")
    .displayName("slack-java-client")
    .push(new SlackAttachment("Text from my Slack-Java-Client").author("ashwanthkumar", "https://avatars0.githubusercontent.com/u/600279?v=3&s=40"));

```

## Notes
With `SlackMessage` you can create rich text as specified in https://api.slack.com/docs/formatting. Example usage
```java
new SlackMessage("Some text can be")
    .italic("Italic")
    .text(". :)")
```

Available methods on `SlackMessage`
- `text`
- `link`
- `bold`
- `italic`
- `code`
- `preformatted`
- `quote`

With `SlackAttachment` you can create much more sophisticated rich text as specified in https://api.slack.com/docs/attachments. Example usage
```java
new SlackAttachment()
    .author("ashwanthkumar")
    .author("ashwanthkumar", "https://avatars0.githubusercontent.com/u/600279?v=3&s=40")
```

Available methods on `SlackAttachment`
- `fallback`
- `color`
- `preText`
- `author`
- `title`
- `text`
- `imageUrl`
- `addField`

## License

http://www.apache.org/licenses/LICENSE-2.0
