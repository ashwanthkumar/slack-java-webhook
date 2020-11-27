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
        doNothing().when(slackService).push(anyString(), eq(message), anyString(), anyString(), anyString(), channelCaptor.capture(),anyString());
        slack.sendToChannel("test_slack").push(message);
        assertThat(channelCaptor.getValue(), is("#test_slack"));
    }

    @Test
    public void shouldSetUserWithAtTheRateOfPrefix() throws IOException {
        Slack slack = new Slack("mockUrl", slackService);
        ArgumentCaptor<String> channelCaptor = ArgumentCaptor.forClass(String.class);
        SlackMessage message = new SlackMessage("hello");
        doNothing().when(slackService).push(anyString(), eq(message), anyString(), anyString(), anyString(), channelCaptor.capture(),anyString());
        slack.sendToUser("slackbot").push(message);
        assertThat(channelCaptor.getValue(), is("@slackbot"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegaArgumentExceptionWhenWebHookUrlIsNotGiven() {
        new Slack(null);
    }

    @Test
    public void shouldAddColonToIconName() throws IOException {
        Slack slack = new Slack("mockUrl", slackService);
        slack.icon("ghost");
        ArgumentCaptor<String> iconCaptor = ArgumentCaptor.forClass(String.class);
        SlackMessage message = new SlackMessage("hello");
        doNothing().when(slackService).push(anyString(), eq(message), anyString(), iconCaptor.capture(), anyString(), anyString(),anyString());
        slack.sendToChannel("test-channel").push(message);
        assertThat(iconCaptor.getValue(), is(":ghost:"));
    }

    @Test
    public void shouldNotAddColonToIconNameIfAlreadyExists() throws IOException {
        Slack slack = new Slack("mockUrl", slackService);
        slack.icon(":ghost:");
        ArgumentCaptor<String> iconCaptor = ArgumentCaptor.forClass(String.class);
        SlackMessage message = new SlackMessage("hello");
        doNothing().when(slackService).push(anyString(), eq(message), anyString(), iconCaptor.capture(), anyString(), anyString(),anyString());
        slack.sendToChannel("test-channel").push(message);
        assertThat(iconCaptor.getValue(), is(":ghost:"));
    }

    @Test
    public void shouldNotAddColonToIconNameOrImageIfUrl() throws IOException {
        Slack slack = new Slack("mockUrl", slackService);
        slack.icon("http://github.com/i/am/sorry.png");
        ArgumentCaptor<String> iconCaptor = ArgumentCaptor.forClass(String.class);
        SlackMessage message = new SlackMessage("hello");
        doNothing().when(slackService).push(anyString(), eq(message), anyString(), iconCaptor.capture(), anyString(), anyString(),anyString());
        slack.sendToChannel("test-channel").push(message);
        assertThat(iconCaptor.getValue(), is("http://github.com/i/am/sorry.png"));
    }

}