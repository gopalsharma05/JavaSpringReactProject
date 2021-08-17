package com.example.SpringReactProjectTool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringReactProjectTool.domain.ProjectTask;
import com.example.SpringReactProjectTool.services.MapValidationErrorService;
import com.example.SpringReactProjectTool.services.ProjectService;
import com.example.SpringReactProjectTool.services.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired 
	MapValidationErrorService mapValidationErrorService;
	 
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask,
				BindingResult result,@PathVariable String backlog_id)
	{
		ResponseEntity<?> errorMap=mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null)
			return errorMap;
		
		ProjectTask projectTask1=projectTaskService.addProjectTask(backlog_id, projectTask);
		
		return new ResponseEntity<ProjectTask>(projectTask1,HttpStatus.CREATED);
	}
	
	@GetMapping("/{backlog_id}")
	public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){
		return projectTaskService.findBacklogById(backlog_id);
	}
	
	// to get the ProjectTask of a particular backlog using projectSequence
	@GetMapping("/{backlog_id}/{project_sequence}")
	public ResponseEntity<?> findProjectTaskByProjectSequence(@PathVariable String backlog_id,@PathVariable String project_sequence)
	{
		return new ResponseEntity<ProjectTask>( projectTaskService.findProjectTaskByProjectSequence(backlog_id,project_sequence),HttpStatus.OK);
	}
	
	@PatchMapping("/{backlog_id}/{project_sequence}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask updatedProjectTask,BindingResult 
			result ,@PathVariable String backlog_id,@PathVariable String project_sequence)
	{
		ResponseEntity<?> errorMap=mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null)
			return errorMap;
		
		ProjectTask newProjectTask=projectTaskService.updateProjectTask(updatedProjectTask, backlog_id, project_sequence);
		return new ResponseEntity<ProjectTask>(newProjectTask,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{backlog_id}/{project_sequence}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id,@PathVariable String project_sequence)
	{
		projectTaskService.deleteProjectTask(backlog_id, project_sequence);
		
		return new ResponseEntity<String> ("Project Task "+project_sequence +" deleted Successfully",HttpStatus.OK);
	}
	
 
		

}

