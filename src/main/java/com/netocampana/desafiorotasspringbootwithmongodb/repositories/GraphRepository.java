package com.netocampana.desafiorotasspringbootwithmongodb.repositories;

import com.netocampana.desafiorotasspringbootwithmongodb.entities.Graph;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface GraphRepository extends MongoRepository<Graph, String> {

}