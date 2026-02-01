package org.example.Exercicios.Primeira.test;

import org.example.Exercicios.Primeira.test.dominio.Pedido;

import java.util.Comparator;

public class compararPorValor implements Comparator<Pedido> {
    @Override
    public int compare(Pedido p1, Pedido p2) {
        int c = Integer.compare(p1.getValor(), p2.getValor());


        return (c!= 0) ? c : p1.getCliente().compareTo(p2.getCliente());
    }

}
