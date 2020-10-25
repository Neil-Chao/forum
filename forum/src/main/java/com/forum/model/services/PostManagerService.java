package com.forum.model.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.model.dao.CommentMapper;
import com.forum.model.dao.PostMapper;
import com.forum.model.dao.PostkindMapper;
import com.forum.model.entity.Comment;
import com.forum.model.entity.CommentExample;
import com.forum.model.entity.CommentExample.Criteria;
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
	@Autowired
	private CommentMapper cm;
	
	
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
	 * 发帖
	 */
	public boolean insertPost(int uid,String title,String text) {
		Date date=new Date();
		Post post=new Post();
		post.setUid(uid);
		post.setTitle(title);
		post.setText(text);
		post.setBegintime(date);
		return pm.insertSelective(post)>0;
	}
	
	/*
	 * 发表评论
	 */
	public boolean insertComment(int pid,int uid,String text) {
		Date date=new Date();
		Comment comment=new Comment();
		comment.setCommenttime(date);
		comment.setPid(pid);
		comment.setUid(uid);
		comment.setText(text);
		return cm.insertSelective(comment)>0;
		
	}
	
	/*
	 * 根据帖子id查找所有评论
	 */
	public PageInfo<Comment> searchCommentsByPid(int pid,int pageNum,int pageSize){
		System.out.println(pid);
		CommentExample example=new CommentExample();
		Criteria cc=example.createCriteria();
		cc.andPidEqualTo(pid);
		PageHelper.startPage(pageNum,pageSize);
		List<Comment> res = cm.selectByExampleWithBLOBs(example);
		System.out.println(res.size());
		return new PageInfo<Comment>(res);
	}
}
