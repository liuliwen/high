package com.high.mapper;

import java.util.List;

import com.high.entity.Category;


public interface CategoryMapper {

	/**
	 * 查找所有的顶级分类的名字
	 * @return
	 */
	List<String> findAllTopCategoryName();

	/**
	 * 通过顶级分类的名字查找次级分类
	 * @param topCategory
	 * @return
	 */
	List<Category> findSecCateByTopCateName(String topCategory);

	/**
	 * 通过分类的id获得分类的信息
	 * @param id
	 * @return
	 */
	Category findCategotyById(String id);
}
