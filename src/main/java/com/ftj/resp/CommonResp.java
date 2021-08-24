package com.ftj.resp;

import lombok.Data;

/**
 * Created by fengtj on 2021/8/24 23:43
 */
@Data
public class CommonResp<T> {

    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据（类型自定义）
     */
    private T content;
}
