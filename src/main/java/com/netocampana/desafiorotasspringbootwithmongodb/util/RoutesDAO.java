package com.netocampana.desafiorotasspringbootwithmongodb.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RoutesDAO {

    public String route;
    public int stops;


}
