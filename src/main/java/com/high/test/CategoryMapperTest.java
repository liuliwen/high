package com.high.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.high.entity.Category;
import com.high.mapper.CategoryMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CategoryMapperTest {
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Test
	public void testFindSecCateNameByTopCateName(){
		String name = "运动";
		System.out.println("类别: " + name);
		List<Category> names = categoryMapper.findSecCateByTopCateName(name);
		System.out.println(names);
	}

	@Test
	public void testDecode(){
		String name = "%E5%A8%B1%E4%B9%90=";
		try {
			String NEWnAME = URLDecoder.decode(name, "UTF-8");
			System.out.println(NEWnAME);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
