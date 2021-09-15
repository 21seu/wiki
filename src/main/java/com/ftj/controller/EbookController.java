package com.ftj.controller;

import com.ftj.domain.Ebook;
import com.ftj.req.EbookReq;
import com.ftj.resp.CommonResp;
import com.ftj.resp.EbookResp;
import com.ftj.resp.PageResp;
import com.ftj.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by fengtj on 2021/8/24 23:37
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody Ebook ebook) {
        CommonResp<Object> resp = new CommonResp<>();
        ebookService.save(ebook);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
