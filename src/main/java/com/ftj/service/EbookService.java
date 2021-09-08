package com.ftj.service;

import com.ftj.domain.Ebook;
import com.ftj.req.EbookReq;
import com.ftj.resp.EbookResp;
import com.ftj.resp.PageResp;

import java.util.List;

/**
 * Created by fengtj on 2021/8/24 23:34
 */
public interface EbookService {

    PageResp<EbookResp> list(EbookReq req);

    /**
     * 保存
     * @param ebook
     */
    void save(Ebook ebook);
}
