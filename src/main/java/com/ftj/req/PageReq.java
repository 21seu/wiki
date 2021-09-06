package com.ftj.req;

import lombok.Data;
import lombok.ToString;

/**
 * Created by fengtj on 2021/9/6 22:48
 */
@Data
@ToString
public class PageReq {

    private int page;

    private int size;
}
