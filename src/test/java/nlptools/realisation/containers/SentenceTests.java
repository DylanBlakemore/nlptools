package nlptools.realisation.containers;

import org.junit.Test;
import static org.junit.Assert.*;

public class SentenceTests {

  @Test
  public void fromJSONCreatesPhrases() throws Exception {
    String jsonString = "{\"subject\":{\"pos\":\"NP\",\"content\":\"The dog\"},\"verb\":{\"pos\":\"ran\",\"content\":\"ran\"}}";
    Sentence s = Sentence.fromJSON(jsonString);

    assertNull(s.object);
    assertNotNull(s.subject);
    assertNotNull(s.verb);

    assertEquals("The dog", s.subject.content);
    assertEquals("ran", s.verb.content);
  }

  @Test
  public void toXmlReturnsCorrectString() throws Exception {
    String jsonString = "{\"subject\":{\"pos\":\"NP\",\"content\":\"The dog\"},\"verb\":{\"pos\":\"VP\",\"content\":\"ran\"}}";
    String expectedXml = "<child xsi:type=\"SPhraseSpec\"><subj xsi:type=\"NPPhraseSpec\"><head cat=\"NOUN\"><base>The dog</base></head></subj><vp xsi:type=\"VPPhraseSpec\"><head cat=\"VERB\"><base>ran</base></head></vp></child>";

    Sentence s = Sentence.fromJSON(jsonString);

    assertEquals(
      expectedXml,
      s.toXml()
    );
  }

  @Test
  public void toXmlWithObjectReturnsCorrectString() throws Exception {

  }
  
}
