package org.example.ZZjdbc.test;

import lombok.extern.log4j.Log4j2;
import org.example.ZZjdbc.dominio.ConnectionFactory;
import org.example.ZZjdbc.dominio.Producer;
import org.example.ZZjdbc.repository.ProducerRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2

public class ConnectionFactoryTest01 {
    public static void main(String[] args) {
//        Producer producer = Producer.ProducerBuilder.builder()
//                .name("NHK").build();

        Producer producer = Producer.builder().name("Studio Deen").build();
        log.info("INFO");
        log.debug("DEBUG");
        log.warn("WARN");
        log.error("error");
        log.trace("trace");
        //        ProducerRepository.save(producer);
    }
}
