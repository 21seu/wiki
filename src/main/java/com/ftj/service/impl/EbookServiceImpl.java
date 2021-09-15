package com.ftj.service.impl;

import com.ftj.domain.Ebook;
import com.ftj.domain.EbookExample;
import com.ftj.mapper.EbookMapper;
import com.ftj.req.EbookReq;
import com.ftj.resp.EbookResp;
import com.ftj.resp.PageResp;
import com.ftj.service.EbookService;
import com.ftj.util.CopyUtil;
import com.ftj.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Resource
    private SnowFlake snowFlake;


    @Override
    public PageResp<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebooks = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> ebookRespList = CopyUtil.copyList(ebooks, EbookResp.class);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooks);
        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ebookRespList);
        return pageResp;

    }

    @Override
    public void save(Ebook ebook) {
        //新增
        if (ObjectUtils.isEmpty(ebook.getId())) {
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }
        //更新
        else ebookMapper.updateByPrimaryKey(ebook);
    }

    @Override
    public void delete(Long id) {
        ebookMapper.deleteByPrimaryKey(id);
    }
}
