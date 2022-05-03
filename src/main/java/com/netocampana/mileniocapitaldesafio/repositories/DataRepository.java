package com.netocampana.mileniocapitaldesafio.repositories;

import com.netocampana.mileniocapitaldesafio.entities.Data;
import com.netocampana.mileniocapitaldesafio.entities.Graph;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface DataRepository extends MongoRepository<Data, String> {

}