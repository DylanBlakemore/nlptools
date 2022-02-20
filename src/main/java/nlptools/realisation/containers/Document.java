package nlptools.realisation.containers;

public class Document {

  public Sentence[] sentences = new Sentence[0];

  public String toXml() {
    StringBuilder xml = new StringBuilder();
    xml
      .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
      .append("<NLGSpec xmlns=\"https://github.com/simplenlg/simplenlg/tree/master/src/main/resources/xml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">")
      .append("<Recording name=\"\">")
      .append("<Record name=\"\">")
      .append("<Document cat=\"PARAGRAPH\">");

    for (Sentence sentence : this.sentences) {
      xml.append(sentence.toXml());
    }

    xml.append("</Document></Record></Recording></NLGSpec>");

    return xml.toString();
  }
  
}
