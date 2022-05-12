package com.netocampana.desafiorotasspringbootwithmongodb.repositories;

import com.netocampana.desafiorotasspringbootwithmongodb.entities.Data;
import com.netocampana.desafiorotasspringbootwithmongodb.entities.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphCreator {

    public static Graph createGraphToBeSave(){

        Data data = new Data("A","B",3);
        Data data2 = new Data("B","C",4);
        Data data3 = new Data("C","D",5);
        Data data4 = new Data("D","B",6);

        List<Data> listDatas = new ArrayList<>();
        listDatas.addAll(Arrays.asList(data,data2,data3,data4));


        return Graph.builder()
                .id("a1b2c3")
                .data(listDatas)
                .build();
    }
}
