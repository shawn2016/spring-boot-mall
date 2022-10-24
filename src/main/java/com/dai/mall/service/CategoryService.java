package com.dai.mall.service;

import com.dai.mall.model.pojo.Category;
import com.dai.mall.model.request.AddCategoryReq;
import com.dai.mall.model.vo.CategoryVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {
     void add(AddCategoryReq addCategoryReq);

     void update(Category updateCategory);

     void delete(Integer id);

     PageInfo listForAdmin(Integer pageNum, Integer pageSize);

     List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
