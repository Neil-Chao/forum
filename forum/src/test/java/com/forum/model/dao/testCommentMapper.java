package com.forum.model.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.forum.model.entity.Comment;
import com.forum.model.entity.CommentExample;
import com.forum.model.entity.CommentExample.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class testCommentMapper {

	@Autowired
	private CommentMapper cm;
	
	@Test
	public void testSearchCommentsByPid() {
		CommentExample example=new CommentExample();
		Criteria cc=example.createCriteria();
		List<Comment> res = cm.selectByExampleWithBLOBs(example);
		
		System.out.println(res);
		for(Comment c:res) {
			System.out.println(c.getText());
		}
	}
}
