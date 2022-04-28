package com.netocampana.mileniocapitaldesafio.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "data")
public class Data {

    @NonNull
    private String source;

    @NonNull
    private String target;
    //    @Digits(fraction = 0, integer = 3, message ="The distance must be an integer until 999 distance unit.")
    private Integer distance;
}
