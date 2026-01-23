package org.example.ZZJdbc.test;

import lombok.extern.log4j.Log4j2;
import org.example.ZZJdbc.dominio.Producer;
import org.example.ZZJdbc.repository.ProducerRepositoryRowSet;

import java.util.List;
@Log4j2
public class ConnecionFactoryTest02 {
    static void main(String[] args) {
        List<Producer> nhk = ProducerRepositoryRowSet.findByNameJdbcRowSet("NHK");
//        log.info((nhk));
        Producer producerToUpdate = Producer.builder().id(1).name("Studio DALACO").build();
//        ProducerRepositoryRowSet.updateJdbcRowSet(producerToUpdate);
        ProducerRepositoryRowSet.updateCachedRowSet(producerToUpdate);
    }
}
