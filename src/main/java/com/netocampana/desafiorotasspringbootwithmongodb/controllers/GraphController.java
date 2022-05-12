package com.netocampana.desafiorotasspringbootwithmongodb.controllers;

import com.netocampana.desafiorotasspringbootwithmongodb.entities.Graph;
import com.netocampana.desafiorotasspringbootwithmongodb.util.LowerDistanceDAO;
import com.netocampana.desafiorotasspringbootwithmongodb.util.RoutesDAO;
import com.netocampana.desafiorotasspringbootwithmongodb.services.GraphService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("graph")
@Slf4j
@RequiredArgsConstructor
public class GraphController {

    @Autowired
    private final GraphService graphService;

    @GetMapping
    @Operation(summary = "List all Graphs already added in our database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation" )
    })
    public ResponseEntity<List<Graph>> listAll(){
//        log.info("Date formatted {}", utils.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(graphService.listAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Search for a graph using the Id.")
    public ResponseEntity<Graph> findById(@PathVariable String id){

        Graph graph = graphService.findById(id);
        return ResponseEntity.ok().body(graph);
    }



    @PostMapping
    @Operation(summary = "Add a new graph.")
    public ResponseEntity<Graph> save(@RequestBody @Valid Graph data){
        graphService.insertGraph(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId()).toUri();
        return ResponseEntity.created(uri).body(data);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a graph by ID.")
    public ResponseEntity<Void> delete(@PathVariable String id){
        graphService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value="/{id}/from/{source}/to/{target}")
    @Operation(summary = "Search all routes in a Graph ID from a Source to a Target." ,
            description = "This option can search all routhes available inside a Graph, searching by ID, and using a source and a target as PathVariable. " +
                    "Also you can use the maxStops parameter to set the maximum number of stops in the routes.")
    public ResponseEntity<List<RoutesDAO>> routesThatCanBeUsed(@PathVariable String id, @PathVariable String source, @PathVariable String target, @RequestParam(defaultValue  = "99") String maxStops){

        int maxStopsInt = Integer.parseInt(maxStops);

        List<RoutesDAO> routesNew = graphService.findRoutesWithMaxStops(id, source, target, maxStopsInt);

        return ResponseEntity.ok().body(routesNew);

    }

    @GetMapping(value="/distance/{id}/from/{source}/to/{target}")
    @Operation(summary = "Find the small distance possible route between a source and a target.")
    public ResponseEntity<LowerDistanceDAO> lowerDistanceForRoute(@PathVariable String id, @PathVariable String source, @PathVariable String target){

       LowerDistanceDAO routesNew = graphService.lowerDistanceOfARoute(id, source,target);

       return ResponseEntity.ok().body(routesNew);

    }
}
