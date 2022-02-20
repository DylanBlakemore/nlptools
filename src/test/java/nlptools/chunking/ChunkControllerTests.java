package nlptools.chunking;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class ChunkControllerTests {

  @Autowired
  private MockMvc mockMvc;
  
  @Test
  public void shouldReturnCategorisedChunks() throws Exception {
    String content = "{\"document\": \"He reckons the current account deficit will narrow to only 8 billion. He is wrong.\"}";
    String chunkedResult = "{\"chunks\":[{\"content\":\"He\",\"pos\":\"NP\"},{\"content\":\"reckons\",\"pos\":\"VP\"},{\"content\":\"the current account deficit\",\"pos\":\"NP\"},{\"content\":\"will narrow\",\"pos\":\"VP\"},{\"content\":\"to\",\"pos\":\"PP\"},{\"content\":\"only 8 billion\",\"pos\":\"NP\"},{\"content\":\"He\",\"pos\":\"NP\"},{\"content\":\"is\",\"pos\":\"VP\"},{\"content\":\"wrong\",\"pos\":\"ADJP\"}]}";

    this.mockMvc
      .perform(post("/chunk").contentType(MediaType.APPLICATION_JSON).content(content))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString(chunkedResult)));
  }

}
