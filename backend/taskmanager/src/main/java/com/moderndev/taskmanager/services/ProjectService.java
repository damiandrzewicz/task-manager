package com.moderndev.taskmanager.services;

import java.util.List;
import java.util.Optional;

import com.moderndev.taskmanager.api.v1.model.ProjectDTO;
import com.moderndev.taskmanager.domain.Project;

public interface ProjectService{

	Project save(Project project);
	
	List<Project> findAll();
	
	Optional<Project> findById(Long id);
	
	Project update(Project project);
	
	void delete(Long id);
	

}
