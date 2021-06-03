package com.example.SpringReactProjectTool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringReactProjectTool.domain.Project;
import com.example.SpringReactProjectTool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("")
	public ResponseEntity<Project> creatNewProject(@RequestBody Project project){
		
		Project project1=projectService.saveOrUpdateProject(project);
		 return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}

}
