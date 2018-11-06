package com.example.wustNeo4j.vo;

import lombok.Data;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/10/22 0022.
 */
@Data
public class Response {
    private ArrayList<Edge> edges;
    private ArrayList<QueryNode> nodes;



}
