package com.netocampana.desafiorotasspringbootwithmongodb.controllers;

import com.netocampana.desafiorotasspringbootwithmongodb.entities.Graph;
import com.netocampana.desafiorotasspringbootwithmongodb.repositories.GraphCreator;
import com.netocampana.desafiorotasspringbootwithmongodb.repositories.GraphRepository;
import com.netocampana.desafiorotasspringbootwithmongodb.services.GraphService;
import com.netocampana.desafiorotasspringbootwithmongodb.util.LowerDistanceDAO;
import com.netocampana.desafiorotasspringbootwithmongodb.util.RoutesDAO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class GraphControllerTest {

    @MockBean
    private GraphRepository graphRepositoryMock;

    @InjectMocks
    private GraphController graphController;

    @Mock
    private GraphService graphServiceMock;

    @BeforeEach
    public void setUp(){

        BDDMockito.when(graphServiceMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(GraphCreator.createGraphToBeSave());

        BDDMockito.when(graphRepositoryMock.save(GraphCreator.createGraphToBeSave()))
                .thenReturn(GraphCreator.createGraphToBeSave());

        List<RoutesDAO> listaRoutesDAO = new ArrayList<>();
        listaRoutesDAO.add(new RoutesDAO("AB",1));

        BDDMockito.when(graphServiceMock.findRoutesWithMaxStops(GraphCreator.createGraphToBeSave().getId(),
                        GraphCreator.createGraphToBeSave().getData().get(0).getSource(),GraphCreator.createGraphToBeSave().getData().get(0).getTarget(),3))
                .thenReturn(listaRoutesDAO);

        LowerDistanceDAO lowerDistanceDAO = new LowerDistanceDAO();
        List<String> path = new ArrayList<>();
        path.add("A");
        path.add("B");
        lowerDistanceDAO.setPath(path);
        lowerDistanceDAO.setDistance(3);

        BDDMockito.when(graphServiceMock.lowerDistanceOfARoute(GraphCreator.createGraphToBeSave().getId(),
                GraphCreator.createGraphToBeSave().getData().get(0).getSource(),GraphCreator.createGraphToBeSave().getData().get(0).getTarget()))
                .thenReturn(lowerDistanceDAO);
    }

    @Test
    @DisplayName("Find Graph with the especified Id When Succesfull")
    public void findById_ReturnGraphWithEspecifiedId_WhenSuccessful(){
        String expectedId = "a1b2c3";
        Graph graph = graphController.findById("a1b2c3").getBody();

        Assertions.assertThat(graph).isNotNull();

        Assertions.assertThat(graph.getId()).isNotNull();

        Assertions.assertThat(graph.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("Save creates an Graph when Succesful")
    public void save_CreatesAGraph_WhenSuccesful(){
        String expectedId = GraphCreator.createGraphToBeSave().getId();
        Graph graphToBeSaved = GraphCreator.createGraphToBeSave();
        Graph graph = graphRepositoryMock.save(graphToBeSaved);

        Assertions.assertThat(graph).isNotNull();
        Assertions.assertThat(graph.getId()).isNotNull();
        Assertions.assertThat(graph.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("Find Routes with Max Stops Defined When Succesful")
    public void findRoutesWithMaxStopsDefined_ReturnsARouteDAO_WhenSuccesful(){
        Graph graphToBeSaved = GraphCreator.createGraphToBeSave();
        Graph graph = graphRepositoryMock.save(graphToBeSaved);
        String id = graph.getId();
        String source = graph.getData().get(0).getSource();
        String target = graph.getData().get(0).getTarget();

        List<RoutesDAO> routes = graphServiceMock.findRoutesWithMaxStops(id,source,target,3);

        Assertions.assertThat(routes).isNotEmpty();
        Assertions.assertThat(routes).isNotNull();
        Assertions.assertThat(routes.get(0).getRoute()).isEqualTo("AB");
        Assertions.assertThat(routes.get(0).getStops()).isEqualTo(1);

    }

    @Test
    @DisplayName("Find lower distance between source and target When Succesful")
    public void findLowerDistanceBetweenASourceAndATarget(){
        Graph graphToBeSaved = GraphCreator.createGraphToBeSave();
        Graph graph = graphRepositoryMock.save(graphToBeSaved);
        String id = graph.getId();
        String source = graph.getData().get(0).getSource();
        String target = graph.getData().get(0).getTarget();

        LowerDistanceDAO lowerDistance = graphServiceMock.lowerDistanceOfARoute(id,source,target);

        Assertions.assertThat(lowerDistance).isNotNull();
        Assertions.assertThat(lowerDistance.getPath()).isEqualTo(List.of("A","B"));
        Assertions.assertThat(lowerDistance.getDistance()).isEqualTo(3);



    }






}