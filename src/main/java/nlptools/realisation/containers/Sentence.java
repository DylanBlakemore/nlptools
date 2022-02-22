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

    if (subject != null) content.append(subject.toXml("subj"));
    if (verb != null) content.append(verb.toXml("vp"));
    if (object != null) content.append(object.toXml("compl"));

    return XmlHelper.wrap(
      "child",
      "xsi:type=\"SPhraseSpec\"",
      content.toString()
    );
  }
  
}
