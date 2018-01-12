package in.ashwanthkumar.slack.webhook;


import org.junit.Test;

import java.io.IOException;

public class SlackIT {

    @Test
    public void testSendingForReal() throws IOException {
        new Slack(
            "your-url-here",
            "localhost",
            8888
            )
            .push(
                new SlackMessage("hello2")
            );


    }
}
