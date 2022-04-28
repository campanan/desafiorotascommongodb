package com.netocampana.mileniocapitaldesafio.repositories;

import com.netocampana.mileniocapitaldesafio.entities.Data;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DataRepository extends MongoRepository<Data, String> {

}