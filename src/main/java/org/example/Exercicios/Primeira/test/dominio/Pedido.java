package org.example.Exercicios.Primeira.test.dominio;

import java.util.List;
import java.util.Objects;

public class Pedido  implements Comparable<Pedido>{
    private Double id;
    private int valor;
    private String cliente;


    public Pedido(Double id, int valor, String cliente) {
        this.id = id;
        this.valor = valor;
        this.cliente = cliente;
    }

    @Override
    public int compareTo(Pedido o) {
        return Double.compare(this.id, o.id);
    }




    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }


    public <T> void generico(List<?extends T> lista){
        for(T t : lista) {
            System.out.println(t);
        }
    }


}
