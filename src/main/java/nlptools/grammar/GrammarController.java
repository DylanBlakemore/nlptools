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
  
  @PostMapping(value="/grammar/determiner", produces="application/json")
  public String determiner(@RequestBody Map<String, Object> body) throws Exception {
    String noun = body.get("noun").toString();
    String determiner = body.get("determiner").toString();

    NLGFactory nlgFactory = NlpEngine.instance.nlgFactory();
    NPPhraseSpec nounPhrase = nlgFactory.createNounPhrase(noun);
    nounPhrase.setDeterminer(determiner);
    return NlpEngine.instance.realiser().realiseSentence(nounPhrase);
  }
}
