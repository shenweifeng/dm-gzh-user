package com.guozhengood.user.dao;

import com.guozhengood.user.domain.ProductCategory;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component("productCategoryMapper")
public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);

    // self code
    @Select(value="select * from tb_gzh_product_category where categoryName=#{categoryName} limit 1;")
    ProductCategory getProductCategoryByName(String categoryName);
}