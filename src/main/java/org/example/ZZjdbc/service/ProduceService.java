package org.example.ZZjdbc.service;

import org.example.ZZjdbc.dominio.Producer;
import org.example.ZZjdbc.repository.ProducerRepository;

import java.util.List;

public class ProduceService {
    public static void save(Producer producer){
        ProducerRepository.save(producer);
    }
    public static List<Producer> findAll(){
        return ProducerRepository.findAll();
    }

    public static List<Producer> findByName(String name){
        return ProducerRepository.findByName(name);
    }

    public static void showProducerMetadata(){
        ProducerRepository.showProducerMetaData();
    }
    public static void showDriverMetadata(){
        ProducerRepository.showDriverMetaData();
    }

    public static void delete(Integer id){
        requireValidID(id);
        ProducerRepository.delete(id);
    }
    public static void update(Producer producer){
        requireValidID(producer.getId());
        ProducerRepository.update(producer);
    }

    private static void requireValidID(Integer id){
        if(id == null || id <= 0){
            throw new IllegalArgumentException("Invalid value for id");
        }



    }

}
