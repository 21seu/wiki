package com.ftj.service.impl;

import com.ftj.domain.Ebook;
import com.ftj.mapper.EbookMapper;
import com.ftj.service.EbookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fengtj on 2021/8/24 23:35
 */
@Service
public class EbookServiceImpl implements EbookService {

    @Resource
    private EbookMapper ebookMapper;


    @Override
    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }
}
