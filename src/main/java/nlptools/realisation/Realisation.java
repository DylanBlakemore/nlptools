package nlptools.realisation;

// These need to be imported as is. The realiser converts XML
// into objects, so the classes need to all be loaded.
import simplenlg.aggregation.*;
import simplenlg.features.*;
import simplenlg.format.english.*;
import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.morphology.english.*;
import simplenlg.orthography.english.*;
import simplenlg.phrasespec.*;
import simplenlg.realiser.english.*;
import simplenlg.syntax.english.*;

import simplenlg.xmlrealiser.wrapper.RecordSet;
import simplenlg.xmlrealiser.XMLRealiser;
import simplenlg.xmlrealiser.XMLRealiserException;
import simplenlg.xmlrealiser.wrapper.DocumentRealisation;
import simplenlg.xmlrealiser.wrapper.XmlDocumentElement;

import java.io.StringReader;

import java.util.ArrayList;

public class Realisation {

  public String[] text;

  public Realisation (String[] text) {
    this.text = text;
  }

  public static Realisation realiseXml(String xml) throws XMLRealiserException {
    StringReader reader = new StringReader(xml);
    RecordSet records = XMLRealiser.getRecording(reader);
    ArrayList<String> text = new ArrayList<String>();

    for (DocumentRealisation document : records.getRecord()) {
      text.add(
        XMLRealiser
          .realise((XmlDocumentElement) document.getDocument())
          .trim()
      );
    }

    return new Realisation(text.toArray(new String[0]));
  }
}
