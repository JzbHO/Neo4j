package com.example.wustNeo4j.vo;

import lombok.Data;

/**
 * Created by Administrator on 2018/10/22 0022.
 */
@Data
public class Edge {
    private long source;
    private long target;
    private String relation;

}
