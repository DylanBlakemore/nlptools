package nlp_server.engines;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class NlpEngine {

  private static POSTaggerME posTaggerInstance = null;
  private static ChunkerME chunkerInstance = null;

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
