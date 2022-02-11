package nlp_server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

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

import com.google.gson.Gson;

import java.io.StringReader;

import java.util.Map;
import java.util.ArrayList;

@RestController
public class Realizer {

  @PostMapping(value="/realize", produces="application/json")
  public String realize(@RequestBody Map<String, Object> body) {
    String xml = body.get("xml").toString();

    try {
      ArrayList<String> text = realiseRecords(xml);
      return formatRealisation(text);
    } catch(XMLRealiserException e) {
      System.out.println(e.getMessage());
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to parse XML into text", e);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e);
    }
  }

  private ArrayList<String> realiseRecords(String xml) throws XMLRealiserException {
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
    return text;
  }

  private String formatRealisation(ArrayList<String> text) {
    String jsonText = new Gson().toJson(text);
    return "{\"text\": " + jsonText + "}";
  }
}
