package nlptools.engines;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.realiser.english.Realiser;

public class NlpEngine {

  public static NlpEngine instance = new NlpEngine();

  private static POSTaggerME posTaggerInstance = null;
  private static ChunkerME chunkerInstance = null;
  private static Realiser realiserInstance = null;
  private static NLGFactory nlgFactoryInstance = null;
  private static Lexicon lexiconInstance = null;

  public Lexicon lexicon() throws IOException {
    if (lexiconInstance == null) {
      lexiconInstance = Lexicon.getDefaultLexicon();
    }

    return lexiconInstance;
  }

  public NLGFactory nlgFactory() throws IOException {
    if (nlgFactoryInstance == null) {
      nlgFactoryInstance = new NLGFactory(lexicon());
    }

    return nlgFactoryInstance;
  }

  public Realiser realiser() throws IOException {
    if (realiserInstance == null) {
      realiserInstance = new Realiser(lexicon());
    }

    return realiserInstance;
  }

  public POSTaggerME posTagger () throws IOException {
    if (posTaggerInstance == null) {
      InputStream inputStreamPOSTagger = ModelLoader.POS_TAG_MODEL;
      POSModel posModel = new POSModel(inputStreamPOSTagger);
      posTaggerInstance = new POSTaggerME(posModel);
    }
    
    return posTaggerInstance;
  }

  public ChunkerME chunker () throws IOException {
    if (chunkerInstance == null) {
      InputStream inputStreamChunker = ModelLoader.CHUNKER_MODEL;
      ChunkerModel chunkerModel = new ChunkerModel(inputStreamChunker);
      chunkerInstance = new ChunkerME(chunkerModel);
    }

    return chunkerInstance;
  }
}
