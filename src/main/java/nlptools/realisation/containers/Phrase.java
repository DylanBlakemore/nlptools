package nlptools.realisation.containers;

public class Phrase {

  public String content;
  public String pos;
  public String element;
  public Feature[] features = new Feature[0];

  public String toXml() {
    return toXml(this.element);
  }

  public String toXml(String elementOverride) {
    return XmlHelper.wrap(
      elementOverride,
      XmlHelper.elementProperties(xsiType(), features),
      XmlHelper.root(cat(), content)
    );
  }

  public String cat() {
    switch(this.pos) {
      case "VP": return "VERB";
      case "NP": return "NOUN";
      default: return "";
    }
  }

  public String xsiType() {
    switch(this.pos) {
      case "VP": return "VPPhraseSpec";
      case "NP": return "NPPhraseSpec";
      default: return "";
    }
  }
}
