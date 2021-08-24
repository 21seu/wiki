package com.ftj.service;

import com.ftj.domain.Ebook;
import com.ftj.req.EbookReq;
import com.ftj.resp.EbookResp;

import java.util.List;

/**
 * Created by fengtj on 2021/8/24 23:34
 */
public interface EbookService {

    List<EbookResp> list(EbookReq req);
}
