package com.pechnikovdg.parser.controller;

import com.pechnikovdg.parser.model.Word;
import com.pechnikovdg.parser.repository.WordRepository;
import com.pechnikovdg.parser.util.WordUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class WordController {

    private final WordRepository wordRepository;

    public WordController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @GetMapping("/parse")
    public String index() {
        return "index";
    }

    @PostMapping("/parse")
    public String parse(@RequestParam String url, Model model, HttpSession session) throws Exception {
        String text = Jsoup.connect(url).get().text();
        String delimiters = "\\s|,|\\.|!|\\?|\"|;|:|]|\\[|\\(|\\)|\\n|\\r|\\t";

        List<Word> words = WordUtil.countWordsFrequency(url, text, delimiters);

        model.addAttribute("words", words);
        session.setAttribute("words", words);

        return "parse";
    }

    @PostMapping("/save")
    public String save(HttpSession session) {
        List<Word> words = (List<Word>) session.getAttribute("words");
        wordRepository.saveAll(words);
        return "save";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String error(Exception ex) {
        log.error("Exception has happened: " + ex);
        return "error";
    }
}
