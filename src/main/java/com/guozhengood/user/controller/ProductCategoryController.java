package com.guozhengood.user.controller;


import com.guozhengood.user.annotation.ISysLog;
import com.guozhengood.user.core.ResponseEntity;
import com.guozhengood.user.domain.ProductCategory;
import com.guozhengood.user.service.ProductCategoryService;
import com.guozhengood.user.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: swf
 * @Description: 产品类别管理
 * @Date:Create：in 2019/12/1:08:35
 * @Modified By：
 */
@RestController
@RequestMapping("/api/category")
public class ProductCategoryController extends BaseApiController{

    Logger log                              = LoggerFactory.getLogger(ProductCategoryController.class);

    private String                 page_prefix_m_product_category        = "mobile/product/category/";

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * SELECT 查询操作，返回一个JSON数组
     * 具有幂等性
     * */
    @GetMapping("/categorys/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductCategory getProductCategory(@PathVariable("id") Integer id){
        if(id == null){
            return null;
        }
        return productCategoryService.getProductCategoryById(id);
    }

    @ISysLog("新增产品分类信息")
    @PostMapping(value = "/categorys")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity product_category_save(@RequestBody ProductCategory obj, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        if(obj.check()){
            ProductCategory check = productCategoryService.getProductCategoryByName(obj.getCategoryName());
            if(check != null){
                return ResponseEntity.failure("201", String.format("类别名称[%s], 已存在.", obj.getCategoryName()));
            }
            productCategoryService.save(obj);
            log.info("[产品类别-添加], [成功], 类别名称={}", obj.toString());
            return ResponseEntity.success();
        }
        return ResponseEntity.failure();
    }

}
