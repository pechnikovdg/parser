package com.pechnikovdg.parser.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Word {

    @Id
    @SequenceGenerator(name = "global_generator", sequenceName = "global_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_generator")
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "frequency", nullable = false)
    private Integer frequency;

    public Word(String url, String content, Integer frequency) {
        this.url = url;
        this.content = content;
        this.frequency = frequency;
    }
}
