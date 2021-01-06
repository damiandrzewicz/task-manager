package com.moderndev.taskmanager.services;

import com.moderndev.taskmanager.domain.Project;
import com.moderndev.taskmanager.repositories.ProjectRepository;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public Project save(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public Optional<Project> findById(Long id) {
		return projectRepository.findById(id);
	}

	@Override
	public Project update(Project project) {
		if(project == null || project.getId() == null){
			throw new IllegalArgumentException("Invalid project argument");
		}
		if(projectRepository.findById(project.getId()).isPresent()) {
			return projectRepository.save(project);
		}else{
			throw new ResourceNotFoundException(String.format("Project for id=[%d] not found", project.getId()));
		}
	}

	@Override
	public void deleteById(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public List<Project> findAllByParentId(Long id) {
		return projectRepository.findAllByParentId(id);
	}
}
