package nlptools.realisation.containers;

import org.junit.Test;
import static org.junit.Assert.*;

public class PhraseTests {
  
  @Test
  public void toXmlWithNoSubPhrases() throws Exception {
    Phrase p = new Phrase();
    p.pos = "NP";
    p.content = "dog";
    p.element = "np";
    p.features = new Feature[] {
      new Feature("NUMBER", "PLURAL"),
      new Feature("GENDER", "MALE")
    };

    assertEquals(
      "<np xsi:type=\"NPPhraseSpec\" NUMBER=\"PLURAL\" GENDER=\"MALE\"><head cat=\"NOUN\"><base>dog</base></head></np>",
      p.toXml()
    );
  }
}
