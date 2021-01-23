package com.moderndev.taskmanager.services;

import com.moderndev.taskmanager.api.v1.models.ProjectCreationDto;
import com.moderndev.taskmanager.api.v1.models.ProjectDto;

import java.util.List;
import java.util.Optional;

public interface ProjectService{

	boolean existsById(Long id);

	boolean existsByName(String name);

	ProjectDto save(ProjectDto project);
	
	List<ProjectDto> findAll();
	
	Optional<ProjectDto> findById(Long id);

	ProjectDto update(ProjectDto project);
	
	void deleteById(Long id);

	List<ProjectDto> findAllByParentId(Long id);
}
