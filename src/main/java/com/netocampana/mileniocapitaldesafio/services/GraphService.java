package com.netocampana.mileniocapitaldesafio.services;

import com.netocampana.mileniocapitaldesafio.entities.Data;
import com.netocampana.mileniocapitaldesafio.entities.Graph;
import com.netocampana.mileniocapitaldesafio.exceptions.ObjectNotFoundException;
import com.netocampana.mileniocapitaldesafio.repositories.GraphRepository;
import com.netocampana.mileniocapitaldesafio.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public List<RoutesService> findRoutesWithMaxStops(String id, String source, String target, int maxStops){

        Graph graphUsed = findById(id);
        List<Data> routes = graphUsed.getData();
        List<Data> newRoutes;

        String newSource = "";
        List<RoutesService> routesThatCanBeUsed = new ArrayList<>();
        int numberStops = 0;

        for(Data data : routes){
            if (data.getSource().equals(source)){
                if (data.getTarget().equals(target)) {
                    RoutesService route1 = new RoutesService(data.getSource()+data.getTarget(), 1);
                    routesThatCanBeUsed.add(route1);

                }else{
                    for(Data data2 : routes){
                        if(data2.getSource().equals(data.getTarget())&&(data2.getTarget().equals(target))) {
                            RoutesService route2 = new RoutesService(data.getSource() + data2.getSource() + data2.getTarget(), 2);
                            routesThatCanBeUsed.add(route2);

                        }else if (data2.getSource().equals(data.getTarget())){
                            for(Data data3 : routes){
                                if(data2.getTarget().equals(data3.getSource())&&data3.getTarget().equals(target)){
                                    RoutesService route3 = new RoutesService(data.getSource() + data2.getSource() + data2.getTarget() + data3.getTarget(), 3);
                                    routesThatCanBeUsed.add(route3);
                                }
                            }
                        }
                    }
                }
            }
        }

        return routesThatCanBeUsed;

    }

}

