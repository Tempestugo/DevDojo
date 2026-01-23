package org.example.ZZJdbc.service;

import org.example.ZZJdbc.dominio.Producer;
import org.example.ZZJdbc.repository.ProducerRepository;

import java.util.List;

public class ProduceService {
    public static void saveTransaction(List<Producer> producers){
        ProducerRepository.saveTransaction(producers);
    }

    public static void save(Producer producer){
        ProducerRepository.save(producer);
    }
    public static List<Producer> findAll(){
        return ProducerRepository.findAll();
    }

    public static List<Producer> findByName(String name){
        return ProducerRepository.findByName(name);
    }
    public static List<Producer> findByNamePreparedStatement(String name){
        return ProducerRepository.findByNamePreparedStatement(name);
    }
    public static List<Producer> findByNameCallableStatement(String name){
        return ProducerRepository.findByNameCallableStatement(name);
    }

    public static void showProducerMetadata(){
        ProducerRepository.showProducerMetaData();
    }
    public static void showDriverMetadata(){
        ProducerRepository.showDriverMetaData();
    }
    public static void showTypeScrollWorking(){
        ProducerRepository.showTypeScrollWorking();
    }
    public static List<Producer> findByNameAndUpdateToUpperCase(String name){
       return ProducerRepository.findByNameAndUpdateToUpperCase(name);
    }

    public static List<Producer> findByNameAndInsertWhereNotFound(String name){
        return ProducerRepository.findByNameAndInsertWhereNotFound(name);
    }


    public static void delete(Integer id){
        requireValidID(id);
        ProducerRepository.delete(id);
    }
    public static void update(Producer producer){
        requireValidID(producer.getId());
        ProducerRepository.update(producer);
    }
    public static void updatePreparedStatement(Producer producer){
        requireValidID(producer.getId());
        ProducerRepository.updatePreparedStatement(producer);
    }

    private static void requireValidID(Integer id){
        if(id == null || id <= 0){
            throw new IllegalArgumentException("Invalid value for id");
        }



    }

}
