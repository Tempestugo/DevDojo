package org.example.Exercicios.TestesUnitarios;

public class Carro {
    private int velocidadeAtual;
    private int velocidadeMaxima;

    public Carro(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
        this.velocidadeAtual = 0;
    }

    public void acelerar(int velocidade) {
        if (velocidade < 0) {
            throw new IllegalArgumentException("Velocidade não pode ser negativa");
        }
        if (velocidadeAtual + velocidade > velocidadeMaxima) {
            velocidadeAtual = velocidadeMaxima;
        } else {
            velocidadeAtual += velocidade;
        }
    }

    public void frear(int velocidade) {
        if (velocidade < 0) {
            throw new IllegalArgumentException("Velocidade não pode ser negativa");
        }
        if (velocidadeAtual - velocidade < 0) {
            velocidadeAtual = 0;
        } else {
            velocidadeAtual -= velocidade;
        }
    }

    public int getVelocidadeAtual() {
        return velocidadeAtual;
    }
}
