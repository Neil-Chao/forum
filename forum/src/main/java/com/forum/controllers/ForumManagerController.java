package com.forum.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.forum.model.entity.Loginrecord;
import com.forum.model.entity.Userinfo;
import com.forum.model.services.ForumManagerService;
@RestController
public class ForumManagerController {
	public static final String CURRENT_USER="CURRENTUSER";
	public static final String FORGET_USER="FORGETUSER";
	
	
	@Autowired
	private ForumManagerService service;
	
	@ResponseBody //结果转换成json
	@RequestMapping("/checkname")
	public boolean doCheckname(Userinfo user) {    //检查用户名是否重复
		return service.checkUserName(user.getUsername());
	}
	
	@RequestMapping("/checkforget")
	public Userinfo doCheckUserpwd(Userinfo user) {
		return service.checkForget(user);
	}
	/*
	 * 注册
	 */
	@RequestMapping("/reg")
	public boolean doReg(Userinfo user) {
		return service.addNewUser(user);
	}
	/*
	 * 登录
	 */
	@RequestMapping("/login")
	public Userinfo doLogin(Userinfo user,HttpSession session) {
		Userinfo res=service.checkLogin(user);
		if(null!=res) {
			session.setAttribute(CURRENT_USER, res);
			return res;
		}
		else {
			return new Userinfo();
		}
	}
	
	
	
	@RequestMapping("/forget")
	public Userinfo doForget(Userinfo user,HttpSession session) {
		Userinfo res=service.checkForget(user);
		if(null!=res) {
			session.setAttribute(FORGET_USER, res);
			return res;
		}
		else {
			return new Userinfo();
		}
	}
	
	
	@RequestMapping("/logout")
	public void doLogout(HttpSession session) {
		session.invalidate();
	}
	
	@RequestMapping("/getcuruser")
	public Userinfo doGetCurUser(HttpSession session) {
		return (Userinfo)session.getAttribute(CURRENT_USER);
	}
	
	@RequestMapping("/getforgetuser")
	public Userinfo doGetForgetUser(HttpSession session) {
		return (Userinfo)session.getAttribute(FORGET_USER);
	}
	
	@RequestMapping("/modportrait")
	public boolean doModPortrait(int uid,int portrait) {
		return service.modPortrait(uid, portrait);
	}
	
	@RequestMapping("/moduserpwd")
	public boolean doModUserpwd(int uid,String userpwd) {
		return service.modUserpwd(uid, userpwd);
	}
	
	@RequestMapping("/recordlogin")
	public boolean doRecordLogin(int uid) {
		return service.insertRecord(uid);
	}
	
	@RequestMapping("/searchrecords")
	public List<Loginrecord> doSearchRecords(int uid){
		return service.searchRecords(uid);
	}
	
	@RequestMapping("/searchtargetuser")
	public Userinfo doSearchTargetUser(int uid) {
		return service.searchTargetUser(uid);
	}
	
	@RequestMapping("/searchuserbyid")
	public Userinfo doSearchUserById(int uid) {
		return service.searchUserById(uid);
	}
	
}
