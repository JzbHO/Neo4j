package com.example.wustNeo4j.common;

import lombok.Data;

/**
 * Created by Administrator on 2018/10/22 0022.
 */
@Data
public class WebResponse<T> {
    private int result;

    private T dataSource;


}
