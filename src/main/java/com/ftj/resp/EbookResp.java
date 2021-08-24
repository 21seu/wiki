package com.ftj.resp;

import lombok.Data;
import lombok.ToString;

/**
 * Created by fengtj on 2021/8/24 23:57
 */
@Data
@ToString
public class EbookResp {

    private Long id;

    private String name;

    private Long category1Id;

    private Long category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;
}
