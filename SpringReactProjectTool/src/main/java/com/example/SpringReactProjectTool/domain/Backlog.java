package com.example.SpringReactProjectTool.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String projectIdentifier;

    //OneToOne with project
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id",nullable =false)
    @JsonIgnore  // it should always use in child side(in our case Backlog is child and project is parent)
    				// to remove Json Recursion error else it will show error in console
    private Project project;  // "project"  should be exactly same which we use in oneToOne in Project class

    //OneToMany projectTasks


    public Backlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
    
    
    
    
    
}