package com.pechnikovdg.parser;

import com.pechnikovdg.parser.model.Word;
import com.pechnikovdg.parser.util.WordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ParserApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/parse"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void save() throws Exception {
        MockHttpSession session = new MockHttpSession();
        List<Word> words = WordUtil.countWordsFrequency("mock_url", "one two three", " ");
        session.setAttribute("words", words);
        mockMvc.perform(MockMvcRequestBuilders.post("/save").session(session))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void saveWithEmptySession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(MockMvcRequestBuilders.post("/save").session(session))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
