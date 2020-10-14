package com.forum.model.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.forum.model.entity.Loginrecord;
import com.forum.model.entity.LoginrecordExample;
import com.forum.model.entity.Userinfo;
import com.forum.model.entity.UserinfoExample;
import com.forum.model.entity.UserinfoExample.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class testLoginrecordMapper {
	
	@Autowired
	private LoginrecordMapper lm;
	@Autowired
	private UserinfoMapper um;
	
	
	@Test
	public void testInsertRecord() {
		Date date=new Date();
		Loginrecord record=new Loginrecord(); 
		record.setUid(1);
		record.setLogtime(date);
		System.out.println(lm.insert(record));
	}
	
	@Test
	public void testSearchRecords() {
		LoginrecordExample example = new LoginrecordExample();
		com.forum.model.entity.LoginrecordExample.Criteria cc=example.createCriteria();
		cc.andUidEqualTo(3);
		System.out.println(lm.selectByExample(example));
	}
	
	@Test
	public void testSearch() {
		UserinfoExample example = new UserinfoExample();
		Criteria cc=example.createCriteria();
		cc.andUsernameEqualTo("Õı›Ë›Ë");
		cc.andUserpwdEqualTo("2602695722@qq.com");
		List<Userinfo> list=um.selectByExample(example);
		System.out.println(list);
	}
}
