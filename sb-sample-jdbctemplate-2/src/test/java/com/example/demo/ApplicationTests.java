package com.example.demo;

import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;
	
	@Autowired
	private UserService userSerivce;

	@Before
	public void setUp() {
		jdbcTemplate1.update("delete from user");
		jdbcTemplate2.update("delete from user");
	}

	//@Test
	public void test1() throws Exception {

		// 往第一个数据源中插入两条数据
		jdbcTemplate1.update("insert into user(id, name, age, create_time) values(?, ?, ?, ?)", 1, "aaa", 20, new Date());
		jdbcTemplate1.update("insert into user(id, name, age, create_time) values(?, ?, ?, ?)", 2, "bbb", 30, new Date());

		// 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
		jdbcTemplate2.update("insert into user(id, name, age, create_time) values(?, ?, ?, ?)", 1, "aaa", 20, new Date());

		// 查一下第一个数据源中是否有两条数据，验证插入是否成功
		Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));

		// 查一下第一个数据源中是否有两条数据，验证插入是否成功
		Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));

	}

	//@Test
	public void test2() throws Exception {
		// 2数据源插入5个用户
		userSerivce.create("a", 1);
		userSerivce.create("b", 2);
		userSerivce.create("c", 3);
		userSerivce.create("d", 4);
		userSerivce.create("e", 5);

		// 查1数据源，应该有0个用户
		Assert.assertEquals(0, userSerivce.getAllUsers().intValue());

		// 2数据源删除两个用户
		userSerivce.deleteByName("a");
		userSerivce.deleteByName("e");

		// 查1数据源，应该有0个用户
		Assert.assertEquals(0, userSerivce.getAllUsers().intValue());
		
		// 查2数据源，应该有3个用户
		Assert.assertEquals("3", jdbcTemplate2.queryForObject("select count(1) from user", String.class));
		
		
		// 往1数据源中插入两条数据(如果该库作为读库，该操作应严格禁止)
		jdbcTemplate1.update("insert into user(id, name, age, create_time) values(?, ?, ?, ?)", 1, "aaa", 20, new Date());
		jdbcTemplate1.update("insert into user(id, name, age, create_time) values(?, ?, ?, ?)", 2, "bbb", 30, new Date());
		
		// 查1数据源，应该有2个用户
		Assert.assertEquals(2, userSerivce.getAllUsers().intValue());
	}
	
	@Test
	public void test3() throws Exception {
		List<User> userList = Lists.newArrayList();
		userList.add(new User(1l, "aaa", 20, new Date()));
		userList.add(new User(2l, "aaa", 20, new Date()));
		userSerivce.batchCreat(userList);
		// 查2数据源，应该有2个用户
		Assert.assertEquals("2", jdbcTemplate2.queryForObject("select count(1) from user", String.class));
		
		try {
			userSerivce.batchCreat(userList);//ID重复应该触发事务回滚
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 查2数据源，应该有2个用户
		Assert.assertEquals("2", jdbcTemplate2.queryForObject("select count(1) from user", String.class));
	}
}
