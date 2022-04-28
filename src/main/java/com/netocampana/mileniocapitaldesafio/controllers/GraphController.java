package com.netocampana.mileniocapitaldesafio.controllers;

import com.netocampana.mileniocapitaldesafio.entities.Data;
import com.netocampana.mileniocapitaldesafio.entities.Graph;
import com.netocampana.mileniocapitaldesafio.services.GraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("graph")
@Slf4j
@RequiredArgsConstructor
public class GraphController {

    @Autowired
    private final GraphService graphService;

    @GetMapping
    public ResponseEntity<List<Graph>> listAll(){
//        log.info("Date formatted {}", utils.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(graphService.listAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Graph> findById(@PathVariable String id){

        Graph graph = graphService.findById(id);
        return ResponseEntity.ok().body(graph);
    }



    @PostMapping
    public ResponseEntity<Graph> save(@RequestBody @Valid Graph data){
        graphService.insertGraph(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId()).toUri();
        return ResponseEntity.created(uri).body(data);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        graphService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(){
        graphService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/{id}/from/{source}/to/{target}")
    public ResponseEntity<List<String>> routesThatCanBeUsed(@PathVariable String id, @PathVariable String source, @PathVariable String target){
        int maxStops = 3;

        List<String> routesNew = graphService.findRoutesWithMaxStops(id, source, target, maxStops);

        return ResponseEntity.ok().body(routesNew);

    }


}
