package com.example.SpringReactProjectTool.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringReactProjectTool.domain.Project;
import com.example.SpringReactProjectTool.services.MapValidationErrorService;
import com.example.SpringReactProjectTool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> creatNewProject(@Valid @RequestBody Project project,BindingResult result,Principal principal){
				
									//principal is used to quote and unquote the logged in user
							
		ResponseEntity<?> errorMap=mapValidationErrorService.MapValidationService(result);
		
		if(errorMap!=null) {return errorMap;}
		
		Project project1=projectService.saveOrUpdateProject(project,principal.getName());// user needs to be logged in before
																						//he can create project
		 return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId,Principal principal)
	{
		Project project=projectService.findProjectByIdentifier(projectId,principal.getName());
		return new ResponseEntity<Project> (project,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(Principal principal){return projectService.findAllProject(principal.getName());}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProjectById(@PathVariable String projectId,Principal principal)
	{
		projectService.deleteProjectByIdentifier(projectId,principal.getName());
		
		return new ResponseEntity<String>("project with id "+projectId+" was deleted",HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateProject(@Valid @RequestBody Project project , BindingResult result)
	{
		ResponseEntity<?> errMap=mapValidationErrorService.MapValidationService(result);
		if(errMap!=null)
		{
			return errMap;
		}
		
		
		Project updatedProject=projectService.updateProject(project);
		return new ResponseEntity<Project>(updatedProject,HttpStatus.OK);
				
	}
	

}
