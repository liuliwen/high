package com.high.controller;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.high.entity.Category;
import com.high.mapper.CategoryMapper;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 获取数据库中所有的顶级分类名字，并返回json数据给前台
	 * 
	 * @return
	 */
	@RequestMapping("/findAllTopCategoryName.do")
	public @ResponseBody List<String> findAllTopCategoryName() {
		List<String> names = categoryMapper.findAllTopCategoryName();
		System.out.println("names: " + names);
		return names;
	}

	/**
	 * 利用顶级分类的名字获取所有的次级分类，并返回json数据
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/findAllSecCate.do")
	public @ResponseBody List<Category> findAllSecCate(@RequestBody String name) {
		
		/**
		 * =====================================
		 * 
		 * 待解决。。。 编码方式 乱码 最后多一个等号字符
		 * 
		 * =====================================
		 * 
		 */

		// System.out.println("findAllSecCate");
		// System.out.println("name: "+name);
		try {
			name = URLDecoder.decode(name.substring(0, name.length() - 1), "UTF-8");
			System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Category> categorys = categoryMapper.findSecCateByTopCateName(name);
		System.out.println(categorys);
		return categorys;
	}

}
