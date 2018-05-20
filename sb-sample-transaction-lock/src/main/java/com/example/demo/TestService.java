package com.example.demo;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public <E> RowMapper<E> getRowMapper(Class<E> clazz) {
		return BeanPropertyRowMapper.newInstance(clazz);
	}
	
	/**
	 *  初始化
	 */
	@Transactional(readOnly=false)
	public void init() {
		String sql = "TRUNCATE TABLE `price`";
		jdbcTemplate.execute(sql);
		sql = "TRUNCATE TABLE `price_version`";
		jdbcTemplate.execute(sql);
		
		sql = "INSERT INTO price (total, front, end) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, 100, 90, 10);
		
		sql = "INSERT INTO price_version (total, front, end, `version`) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, 100, 90, 10, 1);
	}
	
	//------------------------------------------------------
	
	/**
	 * 不用锁
	 */
	@Transactional(readOnly=false)
	public void update1() {
		int ron = 10;
		String sql = "SELECT * FROM price WHERE id = 1";
		Price price = jdbcTemplate.queryForObject(sql, getRowMapper(Price.class));
		price.setFront(price.getFront().subtract(new BigDecimal(ron)));
		price.setEnd(price.getEnd().add(new BigDecimal(ron)));
		price.setTotal(price.getFront().add(price.getEnd()));
		
		sql = "UPDATE price SET total = ?, front = ?, end = ? WHERE id = 1";
		jdbcTemplate.update(sql, price.getTotal(), price.getFront(), price.getEnd());
		
		sql = "INSERT INTO price (total, front, end) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, price.getTotal(), price.getFront(), price.getEnd());
	}
	
	/**
	 * 悲观锁
	 */
	@Transactional(readOnly=false)
	public void update2() {
		int ron = 10;
		String sql = "SELECT * FROM price WHERE id = 1 FOR UPDATE";
		Price price = jdbcTemplate.queryForObject(sql, getRowMapper(Price.class));
		price.setFront(price.getFront().subtract(new BigDecimal(ron)));
		price.setEnd(price.getEnd().add(new BigDecimal(ron)));
		price.setTotal(price.getFront().add(price.getEnd()));
		
		sql = "UPDATE price SET total = ?, front = ?, end = ? WHERE id = 1";
		int count = jdbcTemplate.update(sql, price.getTotal(), price.getFront(), price.getEnd());
		System.out.println(count);
		if (count == 0){
            System.out.println("更新失败");
        }else {
        		System.out.println("更新成功");
        }
		
		sql = "INSERT INTO price (total, front, end) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, price.getTotal(), price.getFront(), price.getEnd());
	}
	
	/**
	 * 乐观锁
	 */
	@Transactional(readOnly=false)
	public void update3() {
		int ron = 10;
		String sql = "SELECT * FROM price WHERE id = 1 FOR UPDATE";
		PriceVersion price = jdbcTemplate.queryForObject(sql, getRowMapper(PriceVersion.class));
		price.setFront(price.getFront().subtract(new BigDecimal(ron)));
		price.setEnd(price.getEnd().add(new BigDecimal(ron)));
		price.setTotal(price.getFront().add(price.getEnd()));
		
		sql = "UPDATE price SET total = ?, front = ?, end = ? WHERE id = 1";
		int count = jdbcTemplate.update(sql, price.getTotal(), price.getFront(), price.getEnd());
		System.out.println(count);
		if (count == 0){
            System.out.println("更新失败");
        }else {
        		System.out.println("更新成功");
        }
		
		sql = "INSERT INTO price (total, front, end) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, price.getTotal(), price.getFront(), price.getEnd());
	}
}
