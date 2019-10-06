package com.talent518.demo;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.talent518.demo.entity.User;
import com.talent518.demo.service.UserService;

@RunWith(SpringRunner.class)
@MapperScan("com.talent518.demo.mapper")
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringBootMasterSlaveDatasourceApplicationTests {
	@Autowired
	UserService userService;
	Random random = new Random();
	
	@Test
	public void load() {
		
	}
	
	@Test
	public void test0Read() {
		userService.find(2);
	}

	@Test
	public void test1Write() {
		User user = new User();
		String pwd = String.format("%08x", random.nextInt());
		user.setUsername("test-" + pwd);
		user.setPassword(pwd);
		user.setEmail("test-" + pwd);
		user.setSalt(pwd);
		userService.register(user);
	}

	@Test
	public void test2Read() {
		for (int i = 0; i < 4; i++) {
			userService.list(0, 10);
		}
		userService.find(3);
	}

	@Test
	public void test3ReadFromMaster() {
		userService.count();
	}
	
	@Test
	public void test4Read() {
		userService.find(1);
	}
	
	@Test
	public void test5Read() {
		userService.find(2);
	}

}
