package com.example.SpringReactProjectTool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SpringReactProjectTool.domain.Backlog;
import com.example.SpringReactProjectTool.domain.Project;
import com.example.SpringReactProjectTool.domain.ProjectTask;
import com.example.SpringReactProjectTool.exception.ProjectIdException;
import com.example.SpringReactProjectTool.exception.ProjectNotFoundException;
import com.example.SpringReactProjectTool.repository.BacklogRepository;
import com.example.SpringReactProjectTool.repository.ProjectRepository;
import com.example.SpringReactProjectTool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	ProjectService projectService;
	
	public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask,String username)
	{
		
		 
			
			// locate the backlog
			Backlog backlog= projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();//backlogRepository.findByProjectIdentifier(projectIdentifier);
			
			//set the backlog to the projectTask
			projectTask.setBacklog(backlog);
			
			// Our projectSequence should be like this....PROJ1-1,PROJ1-2.....PROJ1-n...PROJ1 is projectIdentifier
			Integer backlogSequence=backlog.getPTSequence();
			backlogSequence++;
			
			backlog.setPTSequence(backlogSequence);
			
			projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
			projectTask.setProjectIdentifer(projectIdentifier);
			
			//set the priority
			if(projectTask.getPriority()==null||projectTask.getPriority()==0)  //projectTask.getPriority()==0 add this also with or(||) after UI complete
			{
				projectTask.setPriority(3);
			}
			
			if(projectTask.getStatus()==""||projectTask.getStatus()==null)
			{
				projectTask.setStatus("TO_DO");
			}
			
			return projectTaskRepository.save(projectTask);
			
		 
	}
	
	public Iterable<ProjectTask> findBacklogById(String backlog_id,String username)
	{
		projectService.findProjectByIdentifier(backlog_id, username);
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
	}
	
	public ProjectTask findProjectTaskByProjectSequence(String backlog_id,String project_seuqence,String username)
	{
		projectService.findProjectByIdentifier(backlog_id, username);
			
		ProjectTask projectTask= projectTaskRepository.findByProjectSequence(project_seuqence);
		if(projectTask==null)	
		{
			throw new ProjectNotFoundException("project task with id "+project_seuqence+" does not exist");
		}
		
		if(!(projectTask.getProjectIdentifer().equals(backlog_id)))
		{
			throw new ProjectNotFoundException("project task "+project_seuqence+" does not exist in project "+backlog_id);
		}
		
		
		return projectTask;
	}
	
	
	public ProjectTask updateProjectTask(ProjectTask updatedProjectTask,String backlog_id,String projectSequence,String username)
	{
//		String projectSequence=updatedProjectTask.getProjectSequence();
		ProjectTask projectTask=findProjectTaskByProjectSequence(backlog_id,projectSequence,username);
		 
		
		projectTask=updatedProjectTask;
		
		return projectTaskRepository.save(projectTask);
		
	}
	
	public void deleteProjectTask(String backlog_id,String projectSequence,String username)
	{
		ProjectTask projectTask=findProjectTaskByProjectSequence(backlog_id, projectSequence,username);
		
		projectTaskRepository.delete(projectTask);
	}
	

}
