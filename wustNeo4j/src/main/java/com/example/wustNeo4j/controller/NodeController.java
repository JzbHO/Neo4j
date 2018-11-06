package com.example.wustNeo4j.controller;

import com.example.wustNeo4j.common.ResponseCode;
import com.example.wustNeo4j.common.WebResponse;
import com.example.wustNeo4j.response.Node;
import com.example.wustNeo4j.service.NodeService;
import com.example.wustNeo4j.service.UploadExcelService;
import com.example.wustNeo4j.util.ResponseUtil;
import com.example.wustNeo4j.vo.Edge;
import com.example.wustNeo4j.vo.QueryNode;
import com.example.wustNeo4j.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2ng 018/10/16 0016.
 */
@Controller
public class NodeController {

    @Autowired
    UploadExcelService uploadExcelService;

    @Autowired
    NodeService nodeService;


    @GetMapping("/templates")
    String test(HttpServletRequest request) {
        //逻辑处理
        return "/index.html";
    }

    @PostMapping("/uploadDataSource")
    @ResponseBody
    public boolean addUser(@RequestParam("file") MultipartFile file) {
        boolean a = true;
        String fileName = file.getOriginalFilename();
        try {
            System.out.println("uploadDataSource ======");
            uploadExcelService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }



    @RequestMapping("/getRelatedNodes")
    @ResponseBody
    public WebResponse getRelatedNodes(@RequestParam("nodeId") String nodeId,
                                  @RequestParam("content") String content){

        ArrayList<QueryNode> list=nodeService.getRelatedNodes(content);
        return ResponseUtil.generateWebResponse(ResponseCode.OK,list);

    }


    /**
     * 点击查询功能
     * @param queryInfo
     * @param filedType
     * @return
     */
//    @RequestMapping("/queryNodes")
//    @ResponseBody
//    public WebResponse<ArrayList<QueryNode>> getConcreteNodes(@RequestParam("queryInfo") String queryInfo,
//                                                         @RequestParam("filedType") int filedType){
//
//        ArrayList<String> nodes=nodeService.queryNode(queryInfo,filedType);
//        ArrayList<QueryNode> vos=assembleService.toNodeVo(nodes);
//        return ResponseUtil.generateWebResponse(ResponseCode.OK,vos);
//
//    }

    /**
     * 一次性展开
     */
    @RequestMapping("/queryNodes")
    @ResponseBody
    public WebResponse<ArrayList<QueryNode>> getConcreteNodes(@RequestParam("queryInfo") String queryInfo,
                                                         @RequestParam("filedType") int filedType){

        //防止重复上传节点
        HashMap<String,String > log=new HashMap<>();
        ArrayList<QueryNode> nodes=new ArrayList<>();
        ArrayList<Edge>  edges=new ArrayList<>();

        //第一步根据查询条件找出云端节点
        ArrayList<Node> clouds=nodeService.queryNode(queryInfo,filedType);
        Iterator<Node> iterable=clouds.iterator();
        while (iterable.hasNext()) {
            LinkedHashMap map=iterable.next();
            QueryNode queryNode=new QueryNode();
            queryNode.setContent((String)map.get("cloud.content"));
            long id=(long)map.get("id(cloud)");
            queryNode.setId(id);
            long ll=(long)map.get("cloud.type");
            queryNode.setNodeType(new Long(ll).intValue());
            queryNode.setPid(-1);
            if(log.get(id+"")==null){
                nodes.add(queryNode);
                log.put(id+"",id+"");
            }
            //找出和它有一级关系的节点,这时就有边了
            nodeService.queryAllNodes((long)map.get("id(cloud)"),edges,nodes,log);
        }

        Response response=new Response();

        response.setEdges(edges);
        response.setNodes(nodes);

        return ResponseUtil.generateWebResponse(ResponseCode.OK,response);

    }

}
