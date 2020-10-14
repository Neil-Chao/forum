package com.forum.model.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.model.dao.LoginrecordMapper;
import com.forum.model.dao.UserinfoMapper;
import com.forum.model.entity.Loginrecord;
import com.forum.model.entity.LoginrecordExample;
import com.forum.model.entity.Userinfo;
import com.forum.model.entity.UserinfoExample;
import com.forum.model.entity.UserinfoExample.Criteria;



@Service
public class ForumManagerService {
	
	@Autowired
	private UserinfoMapper um;
	@Autowired
	private LoginrecordMapper lm;
	
	/*
	 * 检查用户名是否重复
	 */
	public boolean checkUserName(String username) {
		UserinfoExample example=new UserinfoExample();
		Criteria cc=example.createCriteria();
		
		cc.andUsernameEqualTo(username);
		List<Userinfo> list=um.selectByExample(example);
		return list.size()==0;
	}
	
	/**
	 * 检查用户名和密码是否匹配
	 * @param userpwd
	 * @return
	 */
	public boolean checkUserpwd(String username,String userpwd) {
		UserinfoExample example = new UserinfoExample();
		Criteria cc = example.createCriteria();
		
		cc.andUsernameEqualTo(username);
		List<Userinfo> list = um.selectByExample(example);
		String password;
		password = list.get(0).getUserpwd();
		if(userpwd.equals(password)) {
			return true;
		}else {
			return false;			
		}
	}
	
	/*
	 * 添加新用户
	 */
	public boolean addNewUser(Userinfo user) {
		boolean isOK=checkUserName(user.getUsername());
		if(!isOK) {
			return false;
		}
		um.insert(user);
		return true;
	}
	/*
	 * 校验登录信息
	 */
	public Userinfo checkLogin(Userinfo user) {
		UserinfoExample example = new UserinfoExample();
		Criteria cc=example.createCriteria();
		cc.andUsernameEqualTo(user.getUsername());
		cc.andUserpwdEqualTo(user.getUserpwd());
		List<Userinfo> list=um.selectByExample(example);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public Userinfo checkForget(Userinfo user) {
		System.out.println(user.toString());
		System.out.println(user.getEmail());
		System.out.println(user.getUsername());
		UserinfoExample example = new UserinfoExample();
		Criteria cc=example.createCriteria();
		cc.andUsernameEqualTo(user.getUsername());
		cc.andEmailEqualTo(user.getEmail());
		List<Userinfo> list=um.selectByExample(example);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/*
	 * 更改头像
	 */
	public boolean modPortrait(int uid,int portrait) {
		Userinfo user=new Userinfo();
		user.setUid(uid);
		user.setPortrait(portrait);
		return um.updateByPrimaryKeySelective(user)>0;
	}
	
	/**
	 * 修改密码
	 * @param uid
	 * @param userpwd
	 * @return
	 */
	public boolean modUserpwd(int uid,String userpwd) {
		UserinfoExample example = new UserinfoExample();
		Criteria cc = example.createCriteria();
		cc.andUidEqualTo(uid);
		List<Userinfo> list = um.selectByExample(example);
		String conuserpwd;
		conuserpwd = list.get(0).getUserpwd();
		if(userpwd.equals(conuserpwd)) {
			return false;
		}
		Userinfo user = new Userinfo();
		user.setUid(uid);
		user.setUserpwd(userpwd);
		return um.updateByPrimaryKeySelective(user)>0;
	}
	
	/*
	 * 记录登陆时间
	 */
	public boolean insertRecord(int uid) {
		Date date=new Date();
		Loginrecord record=new Loginrecord(); 
		record.setUid(uid);
		record.setLogtime(date);
		return lm.insertSelective(record)>0;
	}
	
	/*
	 * 获取当前用户登录记录
	 */
	public List<Loginrecord> searchRecords(int uid) {
		LoginrecordExample example = new LoginrecordExample();
		com.forum.model.entity.LoginrecordExample.Criteria cc=example.createCriteria();
		cc.andUidEqualTo(uid);
		return lm.selectByExample(example);
	}
	
	public Userinfo searchTargetUser(int uid) {
		UserinfoExample example = new UserinfoExample();
		Criteria cc=example.createCriteria();
		cc.andUidEqualTo(uid);
		return um.selectByPrimaryKey(uid);
	}
	
	/*
	 * 根据id查找用户信息
	 */
	public Userinfo searchUserById(int uid) {
		return um.selectByPrimaryKey(uid);
	}
	
	
	
	
}
