package com.ftj.service.impl;

import com.ftj.domain.Ebook;
import com.ftj.domain.EbookExample;
import com.ftj.mapper.EbookMapper;
import com.ftj.req.EbookReq;
import com.ftj.resp.EbookResp;
import com.ftj.service.EbookService;
import com.ftj.util.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        List<Ebook> ebooks = ebookMapper.selectByExample(ebookExample);
        /*List<EbookResp> ebookRespList = new ArrayList<>();
        ebooks.forEach(e -> {
            EbookResp ebookResp = CopyUtil.copy(e, EbookResp.class);
            ebookRespList.add(ebookResp);
        });*/
        return CopyUtil.copyList(ebooks, EbookResp.class);

    }
}
