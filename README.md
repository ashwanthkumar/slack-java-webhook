# slack-java-webhook
Java Client to Slack's Webhook feature. 

## Usage
```java
Slack slack = new Slack("https://hooks.slack.com/services/...");
slack.icon(":smiling_imp:") // Anything from http://www.emoji-cheat-sheet.com/ should work here
    .sendToUser("slackbot")
    .displayName("slack-java-client")
    .push(new SlackMessage("Text from my Slack-Java-Client"));
```
