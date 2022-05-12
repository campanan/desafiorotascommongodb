package com.netocampana.desafiorotasspringbootwithmongodb.repositories;

import com.netocampana.desafiorotasspringbootwithmongodb.entities.Data;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DataRepository extends MongoRepository<Data, String> {

}