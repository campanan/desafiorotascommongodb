package com.netocampana.mileniocapitaldesafio.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Graph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty(message = "The source can't be empty.")
    private String source;

    @NotNull
    @NotEmpty(message = "The target can't be empty.")
    private String target;
    //    @Digits(fraction = 0, integer = 3, message ="The distance must be an integer until 999 distance unit.")
    private Integer distance;

}
