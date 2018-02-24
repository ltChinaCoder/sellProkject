package com.lt.sell.controller;

import com.lt.sell.dataobject.ProductCategory;
import com.lt.sell.dataobject.ProductInfo;
import com.lt.sell.service.ProductCategoryService;
import com.lt.sell.service.ProductInfoService;
import com.lt.sell.util.ResultVoUtil;
import com.lt.sell.vo.ProductInfoVo;
import com.lt.sell.vo.ProductVo;
import com.lt.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVo list() {
        List<ProductInfo> allProduct = productInfoService.findUpAll();
        ResultVo resultVo = new ResultVo();
        if (allProduct == null || allProduct.size() <= 0) {

            return ResultVoUtil.fail();
        }
        resultVo.setCode(0);
        resultVo.setMsg("ok");
        List<ProductVo> productInfos = new ArrayList<>();
        List<Integer> productCategories = allProduct.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> byCategoryTypeIn = productCategoryService.findByCategoryTypeIn(productCategories);
        for (ProductCategory productCategory : byCategoryTypeIn) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for (ProductInfo productInfo : allProduct) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            productVo.setProducts(productInfoVos);
            productInfos.add(productVo);
        }
        return ResultVoUtil.success(productInfos);

    }

}
