package com.cheer.huangou.item.service;


import com.cheer.huangou.model.Brand;
import com.cheer.huangou.model.PageResult;

public interface BrandService {
    PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key);
}
