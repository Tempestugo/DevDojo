package org.example.ZZl.record.dominio;

import java.util.Objects;

// A declaração dos atributos é feita aqui entre parênteses
public record Manga(String name, int episodes) {

    // Compact Constructor: Não precisa repetir os parâmetros
    // Usado para validação. O Java executa isso antes de atribuir os valores.
    public Manga {
        Objects.requireNonNull(name); // Lança NullPointerException se o nome for nulo
    }
}