package com.forum.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.forum.model.entity.Comment;
import com.forum.model.entity.Post;
import com.forum.model.entity.Postkind;
import com.forum.model.entity.Userinfo;
import com.forum.model.services.ForumManagerService;
import com.forum.model.services.PostManagerService;
import com.github.pagehelper.PageInfo;

@RestController
public class PostManagerController {
	public static final String CURRENT_POST="CURRENTUPOST";
	public static final String VISIT_USER="VISITUSER";
	
	@Autowired
	private PostManagerService service;
	@Autowired
	private ForumManagerService fservice;
	
	
	@ResponseBody
	@RequestMapping("/searchallposts")
	public PageInfo<Post> doSearchAllPosts(int pageNum,int pageSize){
		return service.searchAllPosts(pageNum, pageSize);
	}
	
	@RequestMapping("/href")
	public Post doHref(int pid,HttpSession session) {
		Post res=service.searchPostById(pid);
		if(null!=res) {
			session.setAttribute(CURRENT_POST, res);
			return res;
		}
		else {
			return new Post();
		}
	}
	
	@RequestMapping("/visit")
	public Userinfo doVisit(int uid,HttpSession session) {
		Userinfo user=fservice.searchUserById(uid);
		if(null!=user) {
			session.setAttribute(VISIT_USER, user);
			return user;
		}
		else {
			return new Userinfo();
		}
	}
	
	@RequestMapping("/getcurvisit")
	public Userinfo doGetCurVisit(HttpSession session) {
		return (Userinfo)session.getAttribute(VISIT_USER);
	}
	
	@RequestMapping("/getcurhref")
	public Post doGetCurHref(HttpSession session) {
		return (Post)session.getAttribute(CURRENT_POST);
	}
	
	
	@RequestMapping("/insertpost")
	public boolean doInsertPost(Integer uid,String title,String text) {
		return service.insertPost(uid, title, text);
	}
	
	@RequestMapping("/insertcomment")
	public boolean doInsertComment(Integer uid,Integer pid,String text) {
		return service.insertComment(pid, uid, text);
	}
	
	@RequestMapping("/searchallcomments")
	public PageInfo<Comment> doSearchAllComments(int pid ,int pageNum,int pageSize){
		return service.searchCommentsByPid(pid ,pageNum, pageSize);
	}
}
