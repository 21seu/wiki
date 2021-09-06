package com.ftj.resp;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by fengtj on 2021/9/6 22:53
 */
@Data
@ToString
public class PageResp<T> {

    private long total;

    private List<T> list;
}
