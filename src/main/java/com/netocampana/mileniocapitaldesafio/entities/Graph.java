package com.netocampana.mileniocapitaldesafio.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "graph")
public class Graph {

    @Id
    private String id;

    @NonNull
    @NotEmpty
    private List<Data> data = new ArrayList<>();



}
