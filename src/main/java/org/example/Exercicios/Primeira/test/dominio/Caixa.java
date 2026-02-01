package org.example.Exercicios.Primeira.test.dominio;

import java.util.List;

class Caixa<T extends Comparable<T>> {
    private T valor;

    public Caixa(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public int compararCom(Caixa<T> outra) {
       return this.valor.compareTo(outra.valor);
    }

    public static <T extends Comparable<T>> T max(List<T> lista){
        T max = lista.get(0);
        for (T t : lista){
            if(t.compareTo(max) > 0){
                max = t;
            }

        } return max;
    }


}
