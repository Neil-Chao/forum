package com.forum.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.forum.model.entity.Post;
import com.forum.model.entity.Postkind;
import com.forum.model.services.PostManagerService;
import com.github.pagehelper.PageInfo;

@RestController
public class PostManagerController {
	public static final String CURRENT_POST="CURRENTUPOST";
	
	@Autowired
	private PostManagerService service;
	
	
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
	
	@RequestMapping("/getcurhref")
	public Post doGetCurHref(HttpSession session) {
		return (Post)session.getAttribute(CURRENT_POST);
	}
	
	
	@RequestMapping("/insertpost")
	public boolean doInsertPost(Integer uid,String title,String text) {
		return service.insertPost(uid, title, text);
	}
}
