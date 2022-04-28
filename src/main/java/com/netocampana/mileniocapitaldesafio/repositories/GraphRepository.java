package com.netocampana.mileniocapitaldesafio.repositories;

import com.netocampana.mileniocapitaldesafio.entities.Graph;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface GraphRepository extends MongoRepository<Graph, String> {

}