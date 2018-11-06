package com.example.wustNeo4j.service;

import com.example.wustNeo4j.NodeRepository;
import com.example.wustNeo4j.common.NodeType;
import com.example.wustNeo4j.response.Node;
import com.example.wustNeo4j.vo.Edge;
import com.example.wustNeo4j.vo.QueryNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2018/10/17 0017.
 */
@Service
public class NodeService<T> {


    @Autowired
    NodeRepository nodeRepository;



    public void saveNode(T t){
        nodeRepository.save(t);

    }

    public ArrayList<Node> queryNode(String relation, int nodeType){

        ArrayList<Node> list=null;
        //如果是零度关系
        if(nodeType==NodeType.CLOUD.getType()){
           list= nodeRepository.getCloudNode0Level(relation);
        }

        //如果是一度关系
        if(nodeType== NodeType.RELATE_CASE.getType()||
                nodeType==NodeType.PERSON_NUM.getType()){
            list= nodeRepository.getCloudNode1Level(relation);
        }

        //如果是二度关系
        if(nodeType==NodeType.ID_CARD.getType()){
            ArrayList<String>  personNum=nodeRepository.getPerson(relation);
            System.out.println(personNum.get(0)+"-+++++++++++++++");

            personNum =nodeRepository.getPersonCodeByName(personNum.get(0));
            System.out.println(personNum.get(0)+"-+++++++++++++++");
            list= nodeRepository.getCloudNode1Level(personNum.get(0));
        }

        return list;
    }


    public ArrayList<QueryNode> getRelatedNodes(String content){

        ArrayList<QueryNode> list=nodeRepository.getRelateNodes(content);

        return list;
    }


    //递归遍历每一层
    public void queryAllNodes(long nodeId, ArrayList<Edge> edges, ArrayList<QueryNode> nodes,
                              HashMap log){

        ArrayList<Node> list=nodeRepository.getAllNodes(nodeId);

        Iterator<Node> iterable=list.iterator();
        if (iterable==null){
            return;
        }
        while (iterable.hasNext()) {
            LinkedHashMap map=iterable.next();

            //查找出的每一个节点
            long tempId=(long)map.get("id(queryNode)");

            String content=(String)map.get("queryNode.content");

            long ll=(long)map.get("queryNode.type");

            int type=new Long(ll).intValue();

            //存储节点
            QueryNode queryNode=new QueryNode();
            if(log.get(tempId)==null) {
                queryNode.setContent(content);
                queryNode.setId(tempId);
                queryNode.setNodeType(type);
                queryNode.setPid(nodeId);
                nodes.add(queryNode);
                log.put(tempId, tempId);
            }

            //存储边
            Edge edge=new Edge();
            System.out.println("debug type ====="+type);
            edge.setRelation(NodeType.getRelation(type).relation());
            edge.setSource(tempId);
            edge.setTarget(nodeId);
            edges.add(edge);

            queryAllNodes(tempId,edges,nodes,log);


        }
    }


    //判断指定节点是否存在
    public boolean isExist(NodeType nodeType,String content){
        ArrayList list=nodeRepository.getCloud(content);
        if(list==null||list.size()==0){
            return false;
        }
        return  true;
    }



}
