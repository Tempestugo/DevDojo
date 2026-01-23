package org.example.ZZJdbc.test;

import org.example.ZZJdbc.dominio.Producer;
import org.example.ZZJdbc.service.ProduceService;

import java.util.List;

public class ConnectionFactoryTest03 {
    static void main(String[] args) {
        Producer producer = Producer.builder().name("NHK").build();
        Producer producer1 = Producer.builder().name("MadHouse").build();
        Producer producer2 = Producer.builder().name("whitefox").build();
        ProduceService.saveTransaction(List.of(producer, producer1, producer2));
    }
}
