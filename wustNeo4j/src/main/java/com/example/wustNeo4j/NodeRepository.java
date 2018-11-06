package com.example.wustNeo4j;

import com.example.wustNeo4j.response.Node;
import com.example.wustNeo4j.vo.QueryNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/10/15 0015.
 */
@Repository
public interface NodeRepository extends Neo4jRepository {

    @Query("MATCH (cloud:node {content:{0}}) RETURN id(cloud),cloud.content,cloud.type")
    ArrayList<Node> getCloudNode0Level(String content);


    @Query("MATCH (query:node {content:{0}})-[r:人员编号]->(cloud) RETURN  id(cloud) ,cloud.content,cloud.type")
    ArrayList<Node> getCloudNode1Level(String content);


    @Query("MATCH (query:node {content:{0}})-[r:身份证号]->(queryNode) RETURN queryNode.content")
    ArrayList<String> getPerson(String idCard);

    @Query("MATCH (query:node {content:{0}})-[r:姓名]->(queryNode) RETURN queryNode.content")
    ArrayList<String> getPersonCodeByName(String name);


    @Query("MATCH (query:node {content:{0}})-[]-(queryNode) RETURN id(queryNode) ,queryNode.content ,queryNode.type")
    ArrayList<QueryNode> getRelateNodes(String content);

    @Query("MATCH (query:node)<-[]-(queryNode) where id(query)={nodeId} RETURN id(queryNode) ,queryNode.content ,queryNode.type")
    ArrayList<Node> getAllNodes(@Param("nodeId") long nodeId);

    //查看指定id的Cloud
    @Query("MATCH (a:node {content:{0}})  return a")
    ArrayList<Node> getCloud(String cloudId);

    //取出指定Cloud的相关信息的人信息
    @Query("Match(n:node{content:{0}}) <-[r:人员编号]-(m) return m")
    ArrayList<Node> getPersonsOfCloud(String cloudId);


    //取出指定Cloud的相关信息的关联案件信息
    @Query("Match(n:node{content:{0}}) <-[r:关联案件编号]-(m) return m")
    ArrayList<Node> getCasesOfCloud(String cloudId);


}
