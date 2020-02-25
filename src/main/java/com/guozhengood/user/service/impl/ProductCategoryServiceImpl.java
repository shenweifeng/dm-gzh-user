package com.guozhengood.user.service.impl;

import com.guozhengood.user.dao.ProductCategoryMapper;
import com.guozhengood.user.dao.SysLogCommonMapper;
import com.guozhengood.user.domain.ProductCategory;
import com.guozhengood.user.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: swf
 * @Description:
 * @Date:Create：in 2020/1/20:16:38
 * @Modified By：
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public int save(ProductCategory obj) {
        return productCategoryMapper.insertSelective(obj);
    }

    @Override
    public ProductCategory getProductCategoryById(int id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProductCategory getProductCategoryByName(String categoryName) {
        return productCategoryMapper.getProductCategoryByName(categoryName);
    }
}
