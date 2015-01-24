package in.ashwanthkumar.slack.webhook;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SlackMessageTest {

    @Test
    public void shouldAddNormalText() {
        String normalText = new SlackMessage("Hey").toString();
        assertThat(normalText, is("Hey"));
    }

    @Test
    public void shouldAddBoldText() {
        SlackMessage message = new SlackMessage().text("This text is in ").bold("BOLD").text(", but still lets see");
        assertThat(message.toString(), is("This text is in *BOLD*, but still lets see"));
        assertThat(message.rawText(), is("This text is in BOLD, but still lets see"));
    }

    @Test
    public void shouldAddItalicText() {
        SlackMessage message = new SlackMessage().text("Some text can be ").italic("Italic").text(". :)");
        assertThat(message.toString(), is("Some text can be _Italic_. :)"));
        assertThat(message.rawText(), is("Some text can be Italic. :)"));
    }

    @Test
    public void shouldAddCodeText() {
        SlackMessage message = new SlackMessage().text("class ").code("SlackText").text(" {}");
        assertThat(message.toString(), is("class `SlackText` {}"));
        assertThat(message.rawText(), is("class SlackText {}"));
    }

    @Test
    public void shouldAddPreformattedText() {
        SlackMessage message = new SlackMessage().preformatted("random preformatted text");
        assertThat(message.toString(), is("```random preformatted text```"));
        assertThat(message.rawText(), is("random preformatted text"));
    }

    @Test
    public void shouldAddQuoteText() {
        SlackMessage message = new SlackMessage().quote("Krishna says ...");
        assertThat(message.toString(), is("\n> Krishna says ...\n"));
        assertThat(message.rawText(), is("Krishna says ..."));
    }

    @Test
    public void shouldAddJustLink() {
        SlackMessage message = new SlackMessage().link("http://ashwanthkumar.in");
        assertThat(message.toString(), is("<http://ashwanthkumar.in>"));
        assertThat(message.rawText(), is("<http://ashwanthkumar.in>"));
    }

    @Test
    public void shouldAddLinkWithText() {
        SlackMessage message = new SlackMessage().link("http://ashwanthkumar.in", "Ashwanth Kumar");
        assertThat(message.toString(), is("<http://ashwanthkumar.in|Ashwanth Kumar>"));
        assertThat(message.rawText(), is("<http://ashwanthkumar.in|Ashwanth Kumar>"));
    }


}