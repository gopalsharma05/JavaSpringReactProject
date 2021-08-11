package com.example.SpringReactProjectTool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringReactProjectTool.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
	
	ProjectTask findByProjectSequence(String projectSequence);
	
	List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifer);
}