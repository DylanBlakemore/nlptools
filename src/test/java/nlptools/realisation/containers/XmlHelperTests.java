package nlptools.realisation.containers;

import org.junit.Test;
import static org.junit.Assert.*;

public class XmlHelperTests {

  @Test
  public void rootShouldReturnRootXml() throws Exception {
    assertEquals(
      "<head cat=\"NOUN\"><base>dog</base></head>",
      XmlHelper.root("NOUN", "dog")
    );
  }

  @Test
  public void elementShouldReturnCorrectString() throws Exception {
    Feature[] features = {
      new Feature("NUMBER", "PLURAL"),
      new Feature("TENSE", "PAST"),
    };

    assertEquals(
      "xsi:type=\"VPPhraseSpec\" NUMBER=\"PLURAL\" TENSE=\"PAST\"",
      XmlHelper.elementProperties("VPPhraseSpec", features)
    );
  }
  
}
