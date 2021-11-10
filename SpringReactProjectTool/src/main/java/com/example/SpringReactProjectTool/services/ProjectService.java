package com.example.SpringReactProjectTool.services;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringReactProjectTool.domain.Backlog;
import com.example.SpringReactProjectTool.domain.Project;
import com.example.SpringReactProjectTool.domain.User;
import com.example.SpringReactProjectTool.exception.ProjectIdException;
import com.example.SpringReactProjectTool.exception.ProjectNotFoundException;
import com.example.SpringReactProjectTool.repository.BacklogRepository;
import com.example.SpringReactProjectTool.repository.ProjectRepository;
import com.example.SpringReactProjectTool.repository.UserRepository;

@Service
public class ProjectService {
	
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired 
	private UserRepository userRepository; 
	
	 public Project saveOrUpdateProject(Project project,String username) {
		 
		 if(project.getId()!=null)
		 {
			 Project existingProject=projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
			 if(existingProject!=null && (existingProject.getProjectLeader().equals(username)))
			 {
				 throw new ProjectNotFoundException("Project does not found in your account");
				 
			 }else if(existingProject==null)
			 {
				 throw new ProjectNotFoundException("Project with id "+project.getProjectIdentifier()+"can't updated because it does not exist");
			 }
		 }
			 
		 
		 

	       try {
	    	   
	    	   
	    	   User user=userRepository.getByUsername(username);
	    	   project.setUser(user);
	    	   project.setProjectLeader(user.getUsername());
	    	   
	    	   String pid=project.getProjectIdentifier().toUpperCase();
	    	   project.setProjectIdentifier(pid);
	    	   
	    	   if(project.getId()==null )  // backlog tb bnaan jb naya project create ho(usi ka id (not projectIdentifier)
	    		   							// null hota hai) update krne wale ka nahi....
	    	   {
	    		   Backlog backlog=new Backlog();
	    		   project.setBacklog(backlog);
	    		   backlog.setProject(project);
	    		   backlog.setProjectIdentifier(pid);
	    	   }
	    	   
	    	   if (project.getId()!=null)
	    	   {
	    		   project.setBacklog(backlogRepository.findByProjectIdentifier(pid));
	    	   }
	    	   
	    	   return projectRepository.save(project);
	       }catch(Exception e)
	       {
	    	   throw new ProjectIdException("Project ID "+project.getProjectIdentifier().toUpperCase()+" already exists");
	       }
	    }
	 
	 
	    public Project findProjectByIdentifier(String ProjectId,String username)
		{
	    	
	    	 
	    	Project project=projectRepository.findByProjectIdentifier(ProjectId.toUpperCase());
	    	if(project==null)
	    		throw new ProjectIdException("Project ID "+ProjectId+" does not exist");
	    	
	    	
	    	if(!project.getProjectLeader().equals(username)) {
	    		throw new ProjectNotFoundException("project not found in your account");
	    	}
	    	
			return project;
		}
	    
	    public Iterable<Project> findAllProject(String username)
	    {
	    	return projectRepository.findAllByProjectLeader(username);
	    }
		
	    
	    public void deleteProjectByIdentifier(String projectId,String username)
	    {
 	
	    	projectRepository.delete(findProjectByIdentifier(projectId, username));
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
