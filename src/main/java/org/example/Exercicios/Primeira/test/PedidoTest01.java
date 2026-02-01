package org.example.Exercicios.Primeira.test;

import org.example.Exercicios.Primeira.test.dominio.Pedido;

import java.util.*;

public class PedidoTest01 {
    static void main(String[] args) {
        List<Pedido> lista = new ArrayList<>();
        lista.add(new Pedido(12345D,20,"Arroz"));
        lista.add(new Pedido(132342345D,50,"CCC"));
        lista.add(new Pedido(72345D,10,"BrBBroz"));
        lista.add(new Pedido(72345D,10,"ASdrBBroz"));
        for(Pedido p : lista){
            System.out.println(p.getCliente() + " " + p.getId());
        }

        Collections.sort(lista, new compararPorValor());
        Collections.sort(lista, (p1,p2) -> Integer.compare(p1.getValor(),p2.getValor()));
        Collections.sort(lista, Comparator.comparing(Pedido::getValor));
        lista.sort(Comparator.comparing(Pedido::getCliente));
        System.out.println("==================================");


        HashMap<String, List<Pedido>> map = new HashMap<>();
        map.put("Cliente A", lista);

        for(Map.Entry<String, List<Pedido>> entry : map.entrySet()){
            map.put(entry.getKey(), lista);

        }



        for (Map.Entry<String, List<Pedido>> entry : map.entrySet()) {
            System.out.println("Chave (Cliente): " + entry.getKey());

            for (Pedido p : entry.getValue()) {
                System.out.println(" - " + p.getCliente() + " ID: " + p.getId());
            }
        }


    }
}
