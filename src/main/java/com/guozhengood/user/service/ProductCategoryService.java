package com.guozhengood.user.service;

import com.guozhengood.user.domain.ProductCategory;

/**
 * @Author: swf
 * @Description:
 * @Date:Create：in 2020/1/20:16:38
 * @Modified By：
 */
public interface ProductCategoryService {

    int save(ProductCategory obj);

    ProductCategory getProductCategoryById(int id);

    ProductCategory getProductCategoryByName(String categoryName);
}
