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
	 * ����û����Ƿ��ظ�
	 */
	public boolean checkUserName(String username) {
		UserinfoExample example=new UserinfoExample();
		Criteria cc=example.createCriteria();
		
		cc.andUsernameEqualTo(username);
		List<Userinfo> list=um.selectByExample(example);
		return list.size()==0;
	}
	
	/**
	 * ����û����������Ƿ�ƥ��
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
	 * ������û�
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
	 * У���¼��Ϣ
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
	 * ����ͷ��
	 */
	public boolean modPortrait(int uid,int portrait) {
		Userinfo user=new Userinfo();
		user.setUid(uid);
		user.setPortrait(portrait);
		return um.updateByPrimaryKeySelective(user)>0;
	}
	
	/**
	 * �޸�����
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
	 * ��¼��½ʱ��
	 */
	public boolean insertRecord(int uid) {
		Date date=new Date();
		Loginrecord record=new Loginrecord(); 
		record.setUid(uid);
		record.setLogtime(date);
		return lm.insertSelective(record)>0;
	}
	
	/*
	 * ��ȡ��ǰ�û���¼��¼
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
	 * ����id�����û���Ϣ
	 */
	public Userinfo searchUserById(int uid) {
		return um.selectByPrimaryKey(uid);
	}
	
	
	
	
}
