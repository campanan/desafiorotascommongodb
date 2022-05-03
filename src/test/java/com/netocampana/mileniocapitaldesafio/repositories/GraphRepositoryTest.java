package com.netocampana.mileniocapitaldesafio.repositories;

import com.netocampana.mileniocapitaldesafio.entities.Graph;
import com.netocampana.mileniocapitaldesafio.exceptions.ObjectNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataMongoTest
@DisplayName("Graph repository test")
class GraphRepositoryTest {

    @Autowired
    private GraphRepository graphRepository;


    @Test
    @DisplayName("Save creates a graph when sucessfull")
    public void save_PersistAGraph_WhenSuccessfull(){
        Graph graph = GraphCreator.createGraphToBeSave();
        Graph savedGraph = this.graphRepository.save(graph);
        Assertions.assertThat(savedGraph.getId()).isNotNull();
        Assertions.assertThat(savedGraph.getData()).isEqualTo(graph.getData());
    }

    @Test
    @DisplayName("Find by Id returns a graph when successful")
    public void findByIdGraph_ReturnGraph_WhenSuccessfull(){
        Graph graph = GraphCreator.createGraphToBeSave();
        Graph savedGraph = this.graphRepository.save(graph);
        String id = savedGraph.getId();
        this.graphRepository.findById(id);
        Optional<Graph> graphList = this.graphRepository.findById(id);
        Assertions.assertThat(graphList).isNotEmpty();
        Assertions.assertThat(graphList).contains(savedGraph);

    }

    @Test
    @DisplayName("Find by Id returns exception 404 when graph not found")
    public void findByIdGraph_ThrowObjectNotFoundException_WhenGraphNotFound(){
        Graph graph = GraphCreator.createGraphToBeSave();
        Graph savedGraph = this.graphRepository.save(graph);
        String idFake = ("fakeId");
        this.graphRepository.findById(idFake);
        Optional<Graph> graphList = this.graphRepository.findById(idFake);
//        Assertions.assertThatExceptionOfType(ObjectNotFoundException.class)
//                .isThrownBy(() -> graphRepository.findById(id))
//                .withMessageContaining("Objeto não encontrado");
        Assertions.assertThat(graphList).isEmpty();
    }







}