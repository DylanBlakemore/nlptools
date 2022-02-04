package simple_nlg_server.controller;

import org.springframework.boot.SpringApplication;
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

import java.io.StringReader;

import java.util.Map;

@RestController
public class Realizer {

  public static void main(String[] args) {
    SpringApplication.run(Realizer.class, args);
  }

  @PostMapping("/realize")
  public String realize(@RequestBody Map<String, Object> xmlMap) {
    String xml = xmlMap.get("xml").toString();

    RecordSet records = null;

    try (StringReader reader = new StringReader(xml)) {
      records = XMLRealiser.getRecording(reader);
      String paragraph = "";
      for (DocumentRealisation document : records.getRecord()) {
        String realisation = XMLRealiser.realise((XmlDocumentElement) document.getDocument());
        paragraph = paragraph + "\n" + realisation.trim();
      }

      return "{\"text\": \"" + paragraph + "\"}";
    } catch(XMLRealiserException e) {
      System.out.println(e.getMessage());
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to parse XML into text", e);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e);
    }
  }
}
