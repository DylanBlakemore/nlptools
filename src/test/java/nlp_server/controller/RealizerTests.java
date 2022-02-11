package nlp_server.controller;

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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
public class RealizerTests {

  @Autowired
  private MockMvc mockMvc;

  private String getXml() throws IOException {
    String xml = new String(Files.readAllBytes(Paths.get("./src/test/resources/test_xml.txt")));
    return xml;
  }
  
  @Test
  public void shouldReturnTheRealisation() throws Exception {
    String xml = getXml().trim();
    String content = String.format("{\"xml\": \"%s\"}", xml);

    this.mockMvc
      .perform(post("/realize").contentType(MediaType.APPLICATION_JSON).content(content))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("{\"text\": [\"An angioplasty balloon catheter, the D701000000992, was deployed.\"]}")));
  }

}
