package nlp_server.chunking;

import java.util.ArrayList;
import java.util.Arrays;

import opennlp.tools.util.Span;

public class ChunkResult {
  public ArrayList<Chunk> chunks = new ArrayList<Chunk>();

  public ChunkResult(String[] tokens, Span[] spans) {
    for (int i = 0; i < spans.length; i++) {
      Span span = spans[i];
      String phrase = String.join(" ",
        Arrays.copyOfRange(
          tokens,
          span.getStart(),
          span.getEnd()
        )
      );

      chunks.add(new Chunk(
        phrase,
        span.getType()
      ));
    }
  }
}
