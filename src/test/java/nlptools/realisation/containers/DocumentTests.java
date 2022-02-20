package nlptools.realisation.containers;

import org.junit.Test;
import static org.junit.Assert.*;

public class DocumentTests {
  
  @Test
  public void toXmlReturnsCorrectStringWithNoSentences() throws Exception {
    StringBuilder expectedXml = new StringBuilder();
    expectedXml
      .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
      .append("<NLGSpec xmlns=\"https://github.com/simplenlg/simplenlg/tree/master/src/main/resources/xml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">")
      .append("<Recording name=\"\">")
      .append("<Record name=\"\">")
      .append("<Document cat=\"PARAGRAPH\">")
      .append("</Document>")
      .append("</Record></Recording></NLGSpec>");
    
    Document document = new Document();
    assertEquals(
      expectedXml.toString(),
      document.toXml()
    );
  }
  
}
