package org.example.Exercicios.Primeira.test.dominio;

public class Box<T> {
    private T dentroDaCaixa;

    public T getDentroDaCaixa() {
        return dentroDaCaixa;
    }

    public void setDentroDaCaixa(T dentroDaCaixa) {
        this.dentroDaCaixa = dentroDaCaixa;
    }
}

