package in.ashwanthkumar.slack.webhook;

import in.ashwanthkumar.slack.webhook.service.SlackService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class SlackTest {

    SlackService slackService = mock(SlackService.class);

    @Test
    public void shouldSetChannelWithHashPrefix() throws IOException {
        Slack slack = new Slack("mockUrl", slackService);
        ArgumentCaptor<String> channelCaptor = ArgumentCaptor.forClass(String.class);
        SlackMessage message = new SlackMessage("hello");
        doNothing().when(slackService).push(anyString(), eq(message), anyString(), anyString(), channelCaptor.capture());
        slack.sendToChannel("dev-group").push(message);
        assertThat(channelCaptor.getValue(), is("#dev-group"));
    }

    @Test
    public void shouldSetUserWithAtTheRateOfPrefix() throws IOException {
        Slack slack = new Slack("mockUrl", slackService);
        ArgumentCaptor<String> channelCaptor = ArgumentCaptor.forClass(String.class);
        SlackMessage message = new SlackMessage("hello");
        doNothing().when(slackService).push(anyString(), eq(message), anyString(), anyString(), channelCaptor.capture());
        slack.sendToUser("slackbot").push(message);
        assertThat(channelCaptor.getValue(), is("@slackbot"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegaArgumentExceptionWhenWebHookUrlIsNotGiven() {
        new Slack(null);
    }

}