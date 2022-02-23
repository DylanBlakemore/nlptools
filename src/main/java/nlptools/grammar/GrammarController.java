package nlptools.grammar;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nlptools.engines.NlpEngine;
import simplenlg.framework.NLGFactory;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;

import org.springframework.http.HttpStatus;

import java.util.Map;

@RestController
public class GrammarController {
  
  @PostMapping(value="/grammar/article", produces="application/json")
  public String article(@RequestBody Map<String, Object> body) throws Exception {
    String noun = body.get("noun").toString();
    String article = body.get("article").toString();

    NLGFactory nlgFactory = NlpEngine.instance.nlgFactory();
    NPPhraseSpec nounPhrase = nlgFactory.createNounPhrase(noun);
    nounPhrase.setDeterminer(article);
    return NlpEngine.instance.realiser().realiseSentence(nounPhrase);
  }
}
