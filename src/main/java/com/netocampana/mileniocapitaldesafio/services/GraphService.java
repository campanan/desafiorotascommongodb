package com.netocampana.mileniocapitaldesafio.services;

import com.netocampana.mileniocapitaldesafio.entities.Graph;
import com.netocampana.mileniocapitaldesafio.exceptions.ObjectNotFoundException;
import com.netocampana.mileniocapitaldesafio.repositories.GraphRepository;
import com.netocampana.mileniocapitaldesafio.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GraphService {

    private final Utils utils;

    @Autowired
    private final GraphRepository graphRepository;


    public List<Graph>listAll() {
        return graphRepository.findAll();
    }

    public Graph findById(String id){
        Optional<Graph> obj = graphRepository.findById(id);
        return obj.orElseThrow(()
                -> new ObjectNotFoundException("Objecto não encontrado"));

    }

    public Graph insertGraph(Graph graph) {
        return graphRepository.insert(graph);
    }

    public void delete(String id){
//        findById(id);
        graphRepository.deleteById(id);
    }

    public void deleteAll(){
        graphRepository.deleteAll();
    }

}
