package com.netocampana.desafiorotasspringbootwithmongodb.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LowerDistanceDAO {

    public List<String> path;
    public int distance;


}
