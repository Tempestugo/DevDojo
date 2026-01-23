package org.example.ZZJdbc.test;

import lombok.extern.log4j.Log4j2;
import org.example.ZZJdbc.dominio.Producer;
import org.example.ZZJdbc.repository.ProducerRepository;

import java.util.List;

@Log4j2

public class ConnectionFactoryTest01 {
    public static void main(String[] args) {
//        Producer producer = Producer.ProducerBuilder.builder()
//                .name("NHK").build();

        Producer producer = Producer.builder().name("Studio Deen").build();
        Producer producerToUpdate = Producer.builder().id(1).name("Studio dalala").build();
//        ProduceService.save(producer);
//        ProduceService.delete(4);
//        ProduceService.update(producerToUpdate);
//        List<Producer> producers = ProduceService.findAll();
//        log.info("Producers: {}", producers);

//        ProduceService.showProducerMetadata();
//        ProduceService.showDriverMetadata();
//        ProducerRepository.showTypeScrollWorking();
//        List<Producer> studioDeen = ProducerRepository.findByNameAndUpdateToUpperCase("Studio Deen");
//        List<Producer> studioDeen = ProducerRepository.findByNameAndInsertWhereNotFound("Bababba");
//        log.info("Producer found: {}", studioDeen);


//          ProduceService.updatePreparedStatement(producerToUpdate);
        List<Producer> byNameCallableStatement = ProducerRepository.findByNameCallableStatement("NHK");
        log.info("Producers: {}", byNameCallableStatement);


//        log.info("INFO");
//        log.debug("DEBUG");
//        log.warn("WARN");
//        log.error("error");
//        log.trace("trace");
        //        ProducerRepository.save(producer);
    }
}
