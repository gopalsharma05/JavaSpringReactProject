package com.example.SpringReactProjectTool.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email(message="username should be email")
	@NotBlank(message="username is required")
	@Column(unique = true)
	private String username;
	
	@NotBlank(message="please enter your fullName") 
	private String fullName;
	
	@NotBlank(message="password is required")
	private String password;
	
	
	
	
	@Transient   // transient means that required for validation but not persist in the table
	private String confirmPassword;
	
	private Date created_At;
	private Date updated_At;
	
	//One to Many with the project
	@OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER,mappedBy = "user",orphanRemoval = true)
	private List<Project> projects=new ArrayList<>();
	
	
	
	
	
	
	
	
	public List<Project> getProjects() {
		return projects;
	}




	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}




	public User() {
		
	}
	
	
	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFullName() {
		return fullName;
	}




	public void setFullName(String fullName) {
		this.fullName = fullName;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getConfirmPassword() {
		return confirmPassword;
	}




	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}




	public Date getCreated_At() {
		return created_At;
	}




	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}




	public Date getUpdated_At() {
		return updated_At;
	}




	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}




	@PrePersist
	protected void onCreate() {
		this.created_At=new Date();
	
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_At=new Date();
	}


	// UserDetails interface methods

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		 
		return true;
	}




	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		 
		return true;
	}




	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}




	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	

}
