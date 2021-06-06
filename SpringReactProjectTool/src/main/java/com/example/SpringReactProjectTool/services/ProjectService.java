package com.example.SpringReactProjectTool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringReactProjectTool.domain.Project;
import com.example.SpringReactProjectTool.exception.ProjectIdException;
import com.example.SpringReactProjectTool.repository.ProjectRepository;

@Service
public class ProjectService {
	
	
	@Autowired
	private ProjectRepository projectRepository;
	
	 public Project saveOrUpdateProject(Project project) {

	       try {
	    	   project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
	    	   return projectRepository.save(project);
	       }catch(Exception e)
	       {
	    	   throw new ProjectIdException("Project ID "+project.getProjectIdentifier().toUpperCase()+" already exists");
	       }
	    }
	 
	 
	    public Project findProjectByIdentifier(String ProjectId)
		{
	    	Project project=projectRepository.findByProjectIdentifier(ProjectId.toUpperCase());
	    	if(project==null)
	    		throw new ProjectIdException("Project ID "+ProjectId+" does not exist");
	    	
			return project;
		}
	    
	    public Iterable<Project> findAllProject()
	    {
	    	return projectRepository.findAll();
	    }
		
	    
	    public void deleteProjectByIdentifier(String projectid)
	    {
	    	Project project =projectRepository.findByProjectIdentifier(projectid.toUpperCase());
	    	if(project==null)
	    	{
	    		throw new ProjectIdException("The project id: "+projectid+" dows not exist");
	    	}
	    	
	    	projectRepository.delete(project);
	    }
	    
	    
	    public Project updateProject(Project projectAfterUpdate)
	    {
	    	
	    	String projectId=projectAfterUpdate.getProjectIdentifier().toUpperCase();
	    	
	    	Project existingProject=projectRepository.findByProjectIdentifier(projectId);
	    	if(existingProject ==null)
	    	{
	    		throw new ProjectIdException("the project with projectId :"+projectId+" does not exist. So you cant update" );
	    	}
	    	
	    	projectAfterUpdate.setProjectIdentifier(projectId);
	    	projectAfterUpdate.setId(existingProject.getId());
	    	return projectRepository.save(projectAfterUpdate);
	    	
	    	
	    }

}
