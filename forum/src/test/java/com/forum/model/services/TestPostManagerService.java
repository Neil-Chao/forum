package com.forum.model.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.forum.model.entity.Post;
import com.github.pagehelper.PageInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPostManagerService {

	@Autowired
	private PostManagerService service;
	
	@Test
	public void testSearchAllPosts() {
		PageInfo<Post> pageinfo = service.searchAllPosts(1, 10);
		System.out.println(service.searchAllPosts(1, 10));
		for(Post post:pageinfo.getList()) {
			System.out.println(post);
		}
	}
}
