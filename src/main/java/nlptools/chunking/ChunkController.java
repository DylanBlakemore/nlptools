package nlptools.chunking;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nlptools.engines.NlpEngine;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;

import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Map;

@RestController
public class ChunkController {
  
  @PostMapping(value="/chunk", produces="application/json")
  public ChunkResult chunk(@RequestBody Map<String, Object> body) {
    String sentence = body.get("sentence").toString();

    try {
      NlpEngine engine = new NlpEngine();
      String[] tokens = SimpleTokenizer.INSTANCE.tokenize(sentence);
      String[] tags = engine.posTagger().tag(tokens);
      Span[] spans = engine.chunker().chunkAsSpans(tokens, tags);
      return new ChunkResult(tokens, spans);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to parse XML into text", e);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e);
    }
  }
}
