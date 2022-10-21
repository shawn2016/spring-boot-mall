package com.dai.mall.service.impl;

import com.dai.mall.exception.ImoocMallException;
import com.dai.mall.exception.ImoocMallExceptionEnum;
import com.dai.mall.model.dao.CategoryMapper;
import com.dai.mall.model.pojo.Category;
import com.dai.mall.model.request.AddCategoryReq;
import com.dai.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class categoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryReq addCategoryReq) {
        Category category = new Category();
//        BeanUtils.copyProperties()方法进行对象与对象之间属性的赋值，是将前端传来的AddCategoryReq类属性值赋值给Category类对应的属性。
        BeanUtils.copyProperties(addCategoryReq, category);
        Category categoryOld = categoryMapper.selectByName(addCategoryReq.getName());
        if (categoryOld != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        int count = categoryMapper.insertSelective(category);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.CREATE_FAILED);
        }

    }
}
