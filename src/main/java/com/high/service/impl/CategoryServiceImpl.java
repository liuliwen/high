package com.high.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.high.entity.Category;
import com.high.mapper.CategoryMapper;
import com.high.service.CategoryService;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	
	
	@Override
	public List<String> findAllTopCategoryName() {
		return categoryMapper.findAllTopCategoryName();
	}

	@Override
	public List<Category> findSecCateByTopCateName(String name) {
		if(name == null || "".equals(name)){
			throw new IllegalArgumentException("输入分类为空！");
		}
		return categoryMapper.findSecCateByTopCateName(name);
		 
	}

	@Override
	public Category findCategotyById(String id) {
		return categoryMapper.findCategotyById(id);
	}

}
