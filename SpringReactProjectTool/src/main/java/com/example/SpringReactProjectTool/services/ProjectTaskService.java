package com.example.SpringReactProjectTool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringReactProjectTool.domain.Backlog;
import com.example.SpringReactProjectTool.domain.ProjectTask;
import com.example.SpringReactProjectTool.repository.BacklogRepository;
import com.example.SpringReactProjectTool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask)
	{
		
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
	

}
