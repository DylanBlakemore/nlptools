package nlptools.engines;

import java.io.InputStream;

public class ModelLoader {

  public static InputStream POS_TAG_MODEL = new ModelLoader().load("models/en-pos-maxent.bin");
  public static InputStream CHUNKER_MODEL = new ModelLoader().load("models/en-chunker.bin");

  private InputStream load(String path) {
    return getClass().getClassLoader().getResourceAsStream(path);
  }

}
