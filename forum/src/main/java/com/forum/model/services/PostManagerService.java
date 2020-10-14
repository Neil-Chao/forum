package com.forum.model.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.model.dao.PostMapper;
import com.forum.model.dao.PostkindMapper;
import com.forum.model.entity.Post;
import com.forum.model.entity.PostExample;

import com.forum.model.entity.Postkind;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PostManagerService {

	@Autowired
	private PostMapper pm;
	@Autowired
	private PostkindMapper pkm;
	
	
	public PageInfo<Post> searchAllPosts(int pageNum,int pageSize){
		System.out.println("haha");
		PostExample example=new PostExample();
		PageHelper.startPage(pageNum,pageSize);
		List<Post> res = pm.selectByExample(example);
		System.out.println(res.size());
		return new PageInfo<Post>(res);
	}
	
	public Post searchPostById(int pid) {
		return pm.selectByPrimaryKey(pid);
	}
	
	/*
	 * ·¢Ìû
	 */
	public boolean insertPost(int uid,String title,String text) {
		Date date=new Date();
		Post post=new Post();
		post.setUid(uid);
		post.setTitle(title);
		post.setText(text);
		post.setBegintime(date);
		System.out.println(pm.insertSelective(post)>0);
		return true;
	}
}
