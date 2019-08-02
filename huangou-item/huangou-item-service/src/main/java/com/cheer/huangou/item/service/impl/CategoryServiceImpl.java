package com.cheer.huangou.item.service.impl;

import com.cheer.huangou.item.mapper.CategoryMapper;
import com.cheer.huangou.item.service.CategoryService;
import com.cheer.huangou.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional//事务
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    //@Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<Category> queryListByParent(Long parentId) {
        Category category=new Category();
        category.setParentId(parentId);
        List<Category> categoryList = this.categoryMapper.select(category);
        return categoryList;
}
}
