package org.example.Exercicios.Primeira.test.dominio;

public class Produto implements Comparable<Produto>{
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public int compareTo(Produto o) {
        int porPreco = Double.compare(this.preco, o.preco);

        if (porPreco != 0) {
            return porPreco;
        }

        return this.nome.compareTo(o.nome);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
