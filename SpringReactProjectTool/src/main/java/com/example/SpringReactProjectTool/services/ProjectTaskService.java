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
	
	public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask)
	{
		
		try {
			
			// locate the backlog
			Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier);
			
			//set the backlog to the projectTask
			projectTask.setBacklog(backlog);
			
			// Our projectSequence should be like this....PROJ1-1,PROJ1-2.....PROJ1-n...PROJ1 is projectIdentifier
			Integer backlogSequence=backlog.getPTSequence();
			backlogSequence++;
			
			backlog.setPTSequence(backlogSequence);
			
			projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
			projectTask.setProjectIdentifer(projectIdentifier);
			
			//set the priority
			if(projectTask.getPriority()==null)  //projectTask.getPriority()==0 add this also with or(||) after UI complete
			{
				projectTask.setPriority(3);
			}
			
			if(projectTask.getStatus()==""||projectTask.getStatus()==null)
			{
				projectTask.setStatus("TO_DO");
			}
			
			return projectTaskRepository.save(projectTask);
			
		}
		catch(Exception e)
	    {
	    	   throw new ProjectNotFoundException("Project does not exists");
	    }
	}
	
	public Iterable<ProjectTask> findBacklogById(String backlog_id)
	{
		Project project=projectRepository.findByProjectIdentifier(backlog_id);
		if(project==null)
		{
			throw new ProjectNotFoundException("project with id "+backlog_id+" does not exist");
		}
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
	}
	

}
