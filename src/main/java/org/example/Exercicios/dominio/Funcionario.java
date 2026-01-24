package org.example.Exercicios.dominio;

import java.util.TreeSet;

public class Funcionario implements Comparable<Funcionario> {
    private String nome;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    @Override
    public int compareTo(Funcionario o) {
        int c =  Double.compare(this.salario,o.salario);

        return (c != 0) ? c : this.nome.compareTo(o.nome);

    }
}
