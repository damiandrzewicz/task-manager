package com.moderndev.taskmanager.services;

import com.moderndev.taskmanager.api.v1.mappers.ProjectDTOMapper;
import com.moderndev.taskmanager.api.v1.model.ProjectDTO;
import com.moderndev.taskmanager.domain.Project;
import com.moderndev.taskmanager.repositories.ProjectRepository;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;

	private final ProjectDTOMapper mapper;

	public ProjectServiceImpl(ProjectRepository projectRepository, ProjectDTOMapper mapper) {
		super();
		this.projectRepository = projectRepository;
		this.mapper = mapper;
	}

	@Override
	public ProjectDTO save(ProjectDTO project) {
		Project dto = mapper.fromDTO(project);
		Project saved = projectRepository.save(dto);
		return mapper.toDTO(saved);
	}

	@Override
	public List<ProjectDTO> findAll() {
		return projectRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<ProjectDTO> findById(Long id) {
		return projectRepository.findById(id).map(mapper::toDTO);
	}

	@Override
	public ProjectDTO update(ProjectDTO project) {
		if(project == null || project.getId() == null){
			throw new IllegalArgumentException("Invalid project argument");
		}

		Project dto = mapper.fromDTO(project);
		if(projectRepository.findById(dto.getId()).isPresent()) {
			Project save = projectRepository.save(dto);
			return mapper.toDTO(save);
		}else{
			throw new ResourceNotFoundException(String.format("Project for id=[%d] not found", project.getId()));
		}
	}

	@Override
	public void deleteById(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public List<ProjectDTO> findAllByParentId(Long id) {
		return projectRepository.findAllByParentId(id).stream().map(mapper::toDTO).collect(Collectors.toList());
	}
}
