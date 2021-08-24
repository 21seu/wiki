package com.ftj.service.impl;

import com.ftj.domain.Ebook;
import com.ftj.domain.EbookExample;
import com.ftj.mapper.EbookMapper;
import com.ftj.req.EbookReq;
import com.ftj.resp.EbookResp;
import com.ftj.service.EbookService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengtj on 2021/8/24 23:35
 */
@Service
public class EbookServiceImpl implements EbookService {

    @Resource
    private EbookMapper ebookMapper;


    @Override
    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<EbookResp> ebookRespList = new ArrayList<>();
        List<Ebook> ebooks = ebookMapper.selectByExample(ebookExample);
        ebooks.forEach(e->{
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(e,ebookResp);
            ebookRespList.add(ebookResp);
        });
        return ebookRespList;
    }
}
