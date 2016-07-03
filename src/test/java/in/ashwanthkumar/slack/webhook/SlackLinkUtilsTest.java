package in.ashwanthkumar.slack.webhook;

import org.junit.Test;

import static in.ashwanthkumar.slack.webhook.SlackLinkUtils.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SlackLinkUtilsTest {
    @Test
    public void shouldGenerateEmailInLinkableFormat() {
        assertThat(email("foo-bar@ashwanthkumar.in", "Ashwanth"), is("<mailto:foo-bar@ashwanthkumar.in|Ashwanth>"));
        assertThat(email("foo-bar@ashwanthkumar.in"), is("<mailto:foo-bar@ashwanthkumar.in>"));
    }

    @Test
    public void shouldGenrateHrefsInLinkFormat() {
        assertThat(link("www.foo.com", "Foo"), is("<www.foo.com|Foo>"));
        assertThat(link("www.foo.com"), is("<www.foo.com>"));
    }

    @Test
    public void shouldGenerateUserHandlesInLinkableFormat() {
        assertThat(user("ashwanthkumar", "Ash"), is("<@ashwanthkumar|Ash>"));
        assertThat(user("ashwanthkumar"), is("<@ashwanthkumar>"));
    }

    @Test
    public void shouldGenerateChannelsInLinkableFormat() {
        assertThat(channel("general"), is("<#general>"));
    }


}