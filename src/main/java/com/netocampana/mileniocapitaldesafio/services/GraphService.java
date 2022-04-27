package com.netocampana.mileniocapitaldesafio.services;

import com.netocampana.mileniocapitaldesafio.entities.Graph;
import com.netocampana.mileniocapitaldesafio.repositories.GraphRepository;
import com.netocampana.mileniocapitaldesafio.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GraphService {

    private final Utils utils;

    @Autowired
    private final GraphRepository graphRepository;


    public Page<Graph>listAll(Pageable pageable) {
        return graphRepository.findAll(pageable);
    }

    @Transactional
    public Graph save(Graph graph) {
        return graphRepository.save(graph);
    }

}
