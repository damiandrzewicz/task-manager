package com.moderndev.taskmanager.services;

import com.moderndev.taskmanager.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService{

	Project save(Project project);
	
	List<Project> findAll();
	
	Optional<Project> findById(Long id);
	
	Project update(Project project);
	
	void deleteById(Long id);

	List<Project> findAllByParentId(Long id);
}
