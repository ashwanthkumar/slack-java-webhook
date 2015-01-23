package in.ashwanthkumar.slack.webhook;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SlackMessageTest {

    @Test
    public void shouldAddNormalText() {
        String normalText = new SlackMessage("Hey").toString();
        assertThat(normalText, is("Hey"));
    }

    @Test
    public void shouldAddBoldText() {
        String boldText = new SlackMessage().text("This text is in ").bold("BOLD").text(", but still lets see").toString();
        assertThat(boldText, is("This text is in *BOLD*, but still lets see"));
    }

    @Test
    public void shouldAddItalicText() {
        String italicText = new SlackMessage().text("Some text can be ").italic("Italic").text(". :)").toString();
        assertThat(italicText, is("Some text can be _Italic_. :)"));
    }

    @Test
    public void shouldAddCodeText() {
        String codeText = new SlackMessage().text("class ").code("SlackText").text(" {}").toString();
        assertThat(codeText, is("class `SlackText` {}"));
    }

    @Test
    public void shouldAddPreformattedText() {
        String preformatted = new SlackMessage().preformatted("random preformatted text").toString();
        assertThat(preformatted, is("```random preformatted text```"));
    }

    @Test
    public void shouldAddQuoteText() {
        String quoteText = new SlackMessage().quote("Krishna says ...").toString();
        assertThat(quoteText, is("> Krishna says ...\n"));
    }

    @Test
    public void shouldAddJustLink() {
        String linkText = new SlackMessage().link("http://ashwanthkumar.in").toString();
        assertThat(linkText, is("<http://ashwanthkumar.in>"));
    }

    @Test
    public void shouldAddLinkWithText() {
        String linkText = new SlackMessage().link("http://ashwanthkumar.in", "Ashwanth Kumar").toString();
        assertThat(linkText, is("<http://ashwanthkumar.in|Ashwanth Kumar>"));
    }


}