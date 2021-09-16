package com.ftj.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by fengtj on 2021/9/6 22:48
 */
@Data
@ToString
public class PageReq {

    @NotNull(message = "【页码】不能为空")
    private int page;

    @NotNull(message = "【每页条数】不能为空")
    @Max(value = 1000,message = "【每页条数】不能超过1000")
    private int size;
}
