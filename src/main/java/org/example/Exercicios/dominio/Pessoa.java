package org.example.Exercicios.dominio;

public class Pessoa implements Comparable<Pessoa> {

    public String nome;
    public int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public int compareTo(Pessoa other) {
        return Integer.compare(this.idade, other.idade);
    }
}
