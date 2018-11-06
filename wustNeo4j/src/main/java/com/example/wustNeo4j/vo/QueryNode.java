package com.example.wustNeo4j.vo;

import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2018/10/22 0022.
 */
@Data
public class QueryNode{

    private long  id;
    private long pid;
    private int  nodeType;
    private String content;

}
