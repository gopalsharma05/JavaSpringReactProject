package com.example.SpringReactProjectTool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringReactProjectTool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

	@Override
	Iterable<Project> findAllById(Iterable<Long> iterable);
	
	

}