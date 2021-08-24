package com.ftj.controller;

import com.ftj.domain.Ebook;
import com.ftj.req.EbookReq;
import com.ftj.resp.CommonResp;
import com.ftj.resp.EbookResp;
import com.ftj.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fengtj on 2021/8/24 23:37
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp<List<EbookResp>> list(EbookReq req) {
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
