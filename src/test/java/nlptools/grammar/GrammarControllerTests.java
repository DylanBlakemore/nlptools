package nlptools.grammar;

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
public class GrammarControllerTests {

  @Autowired
  private MockMvc mockMvc;

  private void testDeterminer(String params, String expectedResult) throws Exception {
    this.mockMvc
      .perform(post("/grammar/article").contentType(MediaType.APPLICATION_JSON).content(params))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("a dog")));
  }
  
  @Test
  public void determinerShouldReturnTheCorrectForm() throws Exception {
    testDeterminer("{\"noun\":\"dog\",\"article\":\"a\"}", "a dog");
    testDeterminer("{\"noun\":\"dog\",\"article\":\"A\"}", "A dog");
    testDeterminer("{\"noun\":\"owl\",\"article\":\"a\"}", "an owl");
    testDeterminer("{\"noun\":\"unicorn\",\"article\":\"a\"}", "a unicorn");
  }
}
