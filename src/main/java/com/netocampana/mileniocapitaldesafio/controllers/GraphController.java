package com.netocampana.mileniocapitaldesafio.controllers;

import com.netocampana.mileniocapitaldesafio.entities.Graph;
import com.netocampana.mileniocapitaldesafio.services.GraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("graph")
@Slf4j
@RequiredArgsConstructor
public class GraphController {

    @Autowired
    private final GraphService graphService;

    @GetMapping
    public ResponseEntity<Page<Graph>> listAll(Pageable pageable){
//        log.info("Date formatted {}", utils.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(graphService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Graph> save(@RequestBody @Valid Graph graph){
        return ResponseEntity.ok(graphService.save(graph));
    }



}
