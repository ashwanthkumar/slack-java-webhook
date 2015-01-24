[![Build Status](https://snap-ci.com/ashwanthkumar/slack-java-webhook/branch/master/build_image)](https://snap-ci.com/ashwanthkumar/slack-java-webhook/branch/master)

# slack-java-webhook
Java Client to Slack's Webhook feature. 

## Dependencies

For Maven,
```xml
<dependency>
  <groupId>in.ashwanthkumar</groupId>
  <artifactId>slack-java-webhook</artifactId>
  <version>0.0.1</version>
</dependency>
```

For SBT,
```
libraryDependencies += "in.ashwanthkumar" % "slack-java-webhook" % "0.0.2"
```

## Usage
```java
Slack slack = new Slack("https://hooks.slack.com/services/...");
slack.icon(":smiling_imp:") // Anything from http://www.emoji-cheat-sheet.com/ should work here
    .sendToUser("slackbot")
    .displayName("slack-java-client")
    .push(new SlackMessage("Text from my Slack-Java-Client"));
```

## Notes
With `SlackMessage` you can create rich text like
```java
new SlackMessage()
    .text("Some text can be ")
    .italic("Italic")
    .text(". :)")
```

Available methods
- `text`
- `link`
- `bold`
- `italic`
- `code`
- `preformatted`
- `quote`

## License

http://www.apache.org/licenses/LICENSE-2.0
