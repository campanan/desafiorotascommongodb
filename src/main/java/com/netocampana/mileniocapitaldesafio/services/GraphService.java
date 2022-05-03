package com.netocampana.mileniocapitaldesafio.services;

import com.netocampana.mileniocapitaldesafio.entities.Data;
import com.netocampana.mileniocapitaldesafio.entities.Graph;
import com.netocampana.mileniocapitaldesafio.exceptions.ObjectNotFoundException;
import com.netocampana.mileniocapitaldesafio.repositories.GraphRepository;
import com.netocampana.mileniocapitaldesafio.util.LowerDistanceDAO;
import com.netocampana.mileniocapitaldesafio.util.RoutesDAO;
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


    public List<Graph> listAll() {
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


    public List<RoutesDAO> findRoutesWithMaxStops(String id, String source, String target, int maxStops){

        Graph graphUsed = findById(id);
        List<Data> routes = graphUsed.getData();
        List<Data> newRoutes;

        String newSource = "";
        List<RoutesDAO> routesThatCanBeUsed = new ArrayList<>();
        int numberStops = 0;

        for(Data data : routes){
            if (data.getSource().equals(source)){
                if (data.getTarget().equals(target)) {
                    RoutesDAO route1 = new RoutesDAO(data.getSource()+data.getTarget(), 1);
                    routesThatCanBeUsed.add(route1);

                }else{
                    for(Data data2 : routes){
                        if(data2.getSource().equals(data.getTarget())&&(data2.getTarget().equals(target))) {
                            RoutesDAO route2 = new RoutesDAO(data.getSource() + data2.getSource() + data2.getTarget(), 2);
                            routesThatCanBeUsed.add(route2);

                        }else if (data2.getSource().equals(data.getTarget())){
                            for(Data data3 : routes){
                                if(data2.getTarget().equals(data3.getSource())&&data3.getTarget().equals(target)){
                                    RoutesDAO route3 = new RoutesDAO(data.getSource() + data2.getSource() + data2.getTarget() + data3.getTarget(), 3);
                                    routesThatCanBeUsed.add(route3);
                                }
                            }
                        }
                    }
                }
            }
        }

        List<RoutesDAO> routesWithMaxStops = new ArrayList<>();
        for(RoutesDAO routesMaxStops: routesThatCanBeUsed){
            if(routesMaxStops.getStops()<= maxStops){
                routesWithMaxStops.add(routesMaxStops);
            }
        }
        return routesWithMaxStops;
    }

    public LowerDistanceDAO lowerDistanceOfARoute(String id, String source, String target){
        Graph graphUsed = findById(id);
        List<Data> routes = graphUsed.getData();
        List<LowerDistanceDAO> routesThatCanBeUsed = new ArrayList<>();

        for(Data data : routes){
            if (data.getSource().equals(source)){
                if (data.getTarget().equals(target)) {
                    List<String> path = new ArrayList<>();
                    path.add(data.getSource());
                    path.add(data.getTarget());
                    LowerDistanceDAO route1 = new LowerDistanceDAO(path, data.getDistance());
                    routesThatCanBeUsed.add(route1);

                }else{
                    for(Data data2 : routes){
                        List<String> path2 = new ArrayList<>();
                        path2.add(data.getSource());
                        path2.add(data.getTarget());
                        path2.add(data2.getTarget());
                        int distance2 = (data.getDistance()+data2.getDistance());
                        if(data2.getSource().equals(data.getTarget())&&(data2.getTarget().equals(target))) {
                            LowerDistanceDAO route2 = new LowerDistanceDAO(path2, distance2);
                            routesThatCanBeUsed.add(route2);

                        }else if (data2.getSource().equals(data.getTarget())){
                            for(Data data3 : routes){
                                List<String> path3 = new ArrayList<>();
                                path3.add(data.getSource());
                                path3.add(data.getTarget());
                                path3.add(data2.getTarget());
                                path3.add(data3.getTarget());
                                int distance3 = (data.getDistance()+data2.getDistance()+data3.getDistance());
                                if(data2.getTarget().equals(data3.getSource())&&data3.getTarget().equals(target)){
                                    LowerDistanceDAO route3 = new LowerDistanceDAO(path3, distance3);
                                    routesThatCanBeUsed.add(route3);
                                }
                            }
                        }
                    }
                }
            }
        }
        int lowerDistance = 0;
        int indexLowerDistance = 0;
        int indexArray = 0;
        for(LowerDistanceDAO routesa : routesThatCanBeUsed){
            indexArray++;
            if (routesa.getDistance()<lowerDistance){
                lowerDistance = routesa.getDistance();
                indexLowerDistance = indexArray;
            }
        }
        return routesThatCanBeUsed.get(indexLowerDistance);

    }
}

