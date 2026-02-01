package org.example.Exercicios.Segundo.test;

import org.example.Exercicios.Primeira.test.dominio.Pedido;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingDouble;

public class PedidoStreamTest01 {
    static void main(String[] args) {
        List<Pedido> lista = new ArrayList<>();
        lista.add(new Pedido(12345D, 20, "Arroz"));
        lista.add(new Pedido(132342345D, 503, "CCC"));
        lista.add(new Pedido(132342345D, 5023, "CCC"));
        lista.add(new Pedido(72345D, 10, "BrBBroz"));
        lista.add(new Pedido(72345D, 10, "ASdrBBroz"));
        lista.add(new Pedido(723245D, 101, "ASdrBBroz"));
        lista.stream().forEach(pedido -> {
            if (pedido.getValor() > 100) {
                System.out.println(pedido.getCliente());
            }
        });
        List<String> nomes = new ArrayList<>();
        lista.stream().filter(pedido -> pedido.getValor() > 100).forEach(pedido -> System.out.println(pedido.getCliente()));
        System.out.println("==================================================");
        lista.stream().map(Pedido::getCliente).distinct().collect(Collectors.toList()).forEach(System.out::println);

        lista.stream().sorted(new comparador()).forEach(System.out::println);
        System.out.println("==================================================");

        lista.stream().map(Pedido::getValor).reduce(Integer::sum).ifPresent(System.out::println);

        System.out.println("==================================================");

        System.out.println(lista.stream().anyMatch(pedido -> pedido.getValor() > 500));
        System.out.println(lista.stream().allMatch(pedido -> pedido.getValor() > 0));

        System.out.println("==================================================");

        Map<String, List<Pedido>> map = lista.stream().collect(Collectors.groupingBy(Pedido::getCliente));
        Map<String, Double> totalPorCliente =
                lista.stream()
                        .collect(Collectors.groupingBy(
                                Pedido::getCliente,
                                Collectors.summingDouble(Pedido::getValor)
                        ));
        System.out.println("==================================================");



    }

    public static class comparador implements Comparator<Pedido> {

        @Override
        public int compare(Pedido p1, Pedido p2) {
            int c = Integer.compare(p1.getValor(), p2.getValor());
            return (c != 0) ? c : p1.getCliente().compareTo(p2.getCliente());
        }

    }
}
