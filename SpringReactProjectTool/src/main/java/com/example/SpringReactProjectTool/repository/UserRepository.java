package com.example.SpringReactProjectTool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringReactProjectTool.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	
	User getByUsername(String username);
	User getById(Long id);

}
