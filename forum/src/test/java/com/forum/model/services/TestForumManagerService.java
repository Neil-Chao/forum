package com.forum.model.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Service≤‚ ‘¿‡
 * @author 26026
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestForumManagerService {

	@Autowired
	private ForumManagerService service;
	
	@Test
	public void testCheckUserpwd() {
		System.out.println(service.checkUserpwd("Õı›Ë›Ë", "123456"));
		
	}
	@Test
	public void testModUserpwd() {
		System.out.println(service.modUserpwd(7, "1234567"));
	}
	
}
