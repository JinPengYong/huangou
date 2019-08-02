package com.cheer.huangou.item.controller;

import com.cheer.huangou.item.service.CategoryService;
import com.cheer.huangou.model.Category;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点查询商品类目
     *
     * @param parentId 父节点id
     * @return
     */
    @GetMapping("list")
    //default="0"不写parentId默认parentId=0，此时0代表是最高一级目录，没有父id
    //value:请求参数名，如果接受的参数名与前台发过来的参数名是相同的，这个是可以不写的，否则，一定不能省略！！！
    public ResponseEntity<List<Category>> list(@RequestParam(value="pid",defaultValue = "0") Long parentId) {
        log.traceEntry();
        List<Category> categoryList = this.categoryService.queryListByParent(parentId);
        //如果categoryList是空，404
        return CollectionUtils.isEmpty(categoryList) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(categoryList);
    }
}
