package com.example.wustNeo4j.util;

import com.example.wustNeo4j.common.ResponseCode;
import com.example.wustNeo4j.common.WebResponse;

/**
 * Created by Administrator on 2018/10/22 0022.
 */
public class ResponseUtil {


    public static WebResponse generateWebResponse(ResponseCode code,Object data){
        WebResponse webResponse=new WebResponse();
        webResponse.setResult(code.getCode());
        webResponse.setDataSource(data);
        return webResponse;
    }
}
