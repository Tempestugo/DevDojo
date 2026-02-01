package org.example.Exercicios.test;

import org.example.Exercicios.dominio.Pessoa;

import java.util.Comparator;

public class PessoaPorIdadeComparator implements Comparator<Pessoa> {

    @Override
    public int compare(Pessoa p1, Pessoa p2) {
        return Integer.compare(p1.idade, p2.idade);
    }
}
