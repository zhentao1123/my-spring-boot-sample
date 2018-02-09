package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
	
	List<User> findAll();
	
	//JPA QL
	@Query(value="SELECT name FROM User WHERE id=?1")
	public String findNameById1(Long id);
	
	//JPA QL
	@Query(value="SELECT name FROM User WHERE id=:id")
	public String findNameById2(@Param("id") Long id);
	
	//JPA QL
	@Modifying
	@Query(value="UPDATE User u SET u.name=:newName WHERE u.id=:id")
	public int updateNameById1(@Param("id") Long id, @Param("newName") String name);
	
	//JPA QL
	@Query(value="SELECT u FROM User u WHERE u.name like :newName%")
	public List<User> findByName(String name);
	
	//Native sql
	@Query(value="SELECT age FROM user WHERE id=?1", nativeQuery=true)
	public Integer findAgeById1(Long id);
	
	//Native sql
	@Query(value="SELECT age FROM user WHERE id=:id", nativeQuery=true)
	public Integer findAgeById2(@Param("id") Long id);
}
