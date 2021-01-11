package com.moderndev.taskmanager.services;

import com.moderndev.taskmanager.api.v1.model.ProjectDTO;

import java.util.List;
import java.util.Optional;

public interface ProjectService{

	ProjectDTO save(ProjectDTO project);
	
	List<ProjectDTO> findAll();
	
	Optional<ProjectDTO> findById(Long id);

	ProjectDTO update(ProjectDTO project);
	
	void deleteById(Long id);

	List<ProjectDTO> findAllByParentId(Long id);
}
