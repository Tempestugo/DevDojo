package org.example.Exercicios.Primeira.test;

import org.example.Exercicios.Primeira.test.dominio.Pessoa;

import java.util.*;


public class Test02 {
    static void main(String[] args) {
//        TreeSet<Funcionario> treeSet = new TreeSet<>();
//
//        treeSet.add(new Funcionario("Carlos", "Dev", 3000));
//        treeSet.add(new Funcionario("Ana", "Dev", 2000));
//        treeSet.add(new Funcionario("Bruno", "QA", 2000));
//
//        for (Funcionario f : treeSet) {
//            System.out.println(f);
//        }

        ArrayList<Pessoa> lista = new ArrayList<>(List.of(new Pessoa("nome", 12), new Pessoa("aome2", 2), new Pessoa("joao", 1)));
        Collections.sort(lista, new PessoaPorIdadeComparator());
        for (Pessoa p : lista) {
            System.out.println(p.nome + " : " + p.idade);
        }
        Collections.sort(lista, (p1, p2) -> Integer.compare(p1.idade, p2.idade));
        for (Pessoa p : lista) {
            System.out.println(p.nome + " : " + p.idade);

        }

        lista.sort(Comparator.comparing(Pessoa::getIdade).reversed());
        for (Pessoa p : lista) {
            System.out.println(p.nome + " : " + p.idade);
        }
    }
}
