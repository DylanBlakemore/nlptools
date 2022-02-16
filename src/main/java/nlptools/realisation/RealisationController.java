package nlptools.realisation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import simplenlg.xmlrealiser.XMLRealiserException;

import java.util.Map;

@RestController
public class RealisationController {

  @PostMapping(value="/realise", produces="application/json")
  public Realisation realise(@RequestBody Map<String, Object> body) {
    try {
      String xml = body.get("xml").toString();
      return Realisation.realiseXml(xml);
    } catch(XMLRealiserException e) {
      System.out.println(e.getMessage());
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to parse XML into text", e);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e);
    }
  }
}
