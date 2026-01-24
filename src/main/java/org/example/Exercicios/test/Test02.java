package org.example.Exercicios.test;

import org.example.Exercicios.dominio.Funcionario;

import java.util.Collections;
import java.util.TreeSet;

public class Test02 {
    static void main(String[] args) {
        TreeSet<Funcionario> treeSet = new TreeSet<>();

        treeSet.add(new Funcionario("Carlos", "Dev", 3000));
        treeSet.add(new Funcionario("Ana", "Dev", 2000));
        treeSet.add(new Funcionario("Bruno", "QA", 2000));

        for (Funcionario f : treeSet) {
            System.out.println(f);
        }
    }
}
