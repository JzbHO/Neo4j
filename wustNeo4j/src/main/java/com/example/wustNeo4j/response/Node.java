package com.example.wustNeo4j.response;

import lombok.Data;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2018/10/22 0022.
 */
@Data
@org.springframework.data.neo4j.annotation.QueryResult
public class Node extends LinkedHashMap{

    private long  id;
    private long pid;
    private int  nodeType;
    private String content;

}
