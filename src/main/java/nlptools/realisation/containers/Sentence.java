package nlptools.realisation.containers;

import com.google.gson.Gson;

public class Sentence {

  public static Sentence fromJSON(String json) {
    Gson gson = new Gson();
    return gson.fromJson(json, Sentence.class);
  }

  public Phrase subject;
  public Phrase verb;
  public Phrase object;

  public String toXml() {
    StringBuilder content = new StringBuilder();
    content.append(subject.toXml("subj")).append(verb.toXml("vp"));
    return XmlHelper.wrap(
      "child",
      "xsi:type=\"SPhraseSpec\"",
      content.toString()
    );
  }
  
}
