package org.example.Exercicios.dominio;

import java.util.List;

public class Array {
    public static <T> void printArray(T[] array){
        for (T element : array) {
            System.out.println(element);
        }

    }

    public static void printList(List<?> list){
        for (Object element : list) {
            System.out.println(element);
        }

    }

    public static double sum(List<? extends Number> list) {
        double sum = 0.0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }





}
