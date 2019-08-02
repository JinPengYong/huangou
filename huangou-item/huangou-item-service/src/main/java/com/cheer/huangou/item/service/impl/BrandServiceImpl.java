package com.cheer.huangou.item.service.impl;

import com.cheer.huangou.item.mapper.BrandMapper;
import com.cheer.huangou.item.service.BrandService;
import com.cheer.huangou.model.Brand;
import com.cheer.huangou.model.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional//事务
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //分页
        PageHelper.startPage(page,rows);

        //过滤
        Example example=new Example(Brand.class);

        if (StringUtils.isNotBlank(key)) {
            // 构造查询条件
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        if(StringUtils.isNotBlank(sortBy)){
            String orderByClause=sortBy+" "+(desc ? "DESC" : "ASC");
            example.setOrderByClause(orderByClause);
        }

        //查询
        List<Brand> brands = this.brandMapper.selectByExample(example);
        Page<Brand> pageInfo=(Page<Brand>)brands;

        return new PageResult<>(pageInfo.getTotal(),pageInfo);
    }
}
