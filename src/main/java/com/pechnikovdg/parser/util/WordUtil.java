package com.pechnikovdg.parser.util;

import com.pechnikovdg.parser.model.Word;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordUtil {

    public static List<Word> countWordsFrequency(String url, String text, String delimiters) {
        Map<String, Integer> wordsWithFrequency = Stream.of(text.split(delimiters))
                .filter(word -> !word.matches("^\\p{Punct}+$|^\\d+$|^$"))
                .collect(Collectors.toMap(Function.identity(), value -> 1, Integer::sum, TreeMap::new));

        return wordsWithFrequency.entrySet()
                .stream()
                .map(entry -> new Word(url, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
