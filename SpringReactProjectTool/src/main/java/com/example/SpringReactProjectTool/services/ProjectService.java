package com.example.SpringReactProjectTool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringReactProjectTool.domain.Project;
import com.example.SpringReactProjectTool.repository.ProjectRepository;

@Service
public class ProjectService {
	
	
	@Autowired
	private ProjectRepository projectRepository;
	
	 public Project saveOrUpdateProject(Project project) {

	        return projectRepository.save(project);
	    }

}
