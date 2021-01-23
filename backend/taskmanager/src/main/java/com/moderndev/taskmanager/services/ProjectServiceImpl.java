package com.moderndev.taskmanager.services;

import com.moderndev.taskmanager.api.v1.mappers.ProjectMapper;
import com.moderndev.taskmanager.api.v1.models.ProjectDto;
import com.moderndev.taskmanager.domain.Project;
import com.moderndev.taskmanager.repositories.ProjectRepository;
import com.moderndev.taskmanager.services.exceptions.ResourceAlreadyExistsException;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;

	private final ProjectMapper projectMapper;

	public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
		this.projectRepository = projectRepository;
		this.projectMapper = projectMapper;
	}

	@Override
	@Transactional
	public boolean existsById(Long id){
		return projectRepository.existsById(id);
	}

	@Override
	public boolean existsByName(String name) {
		return projectRepository.existsByName(name);
	}

	@Override
	@Transactional
	public ProjectDto save(ProjectDto projectDTO) {
		Project project = projectMapper.fromDto(projectDTO);

		if(projectRepository.existsByName(projectDTO.getName())){
			throw new ResourceAlreadyExistsException(String.format("Project (name=[%s]) already exists", projectDTO.getName()));
		}

		// Check parent project
		if(projectDTO.getParentId() != null){
			projectRepository.findById(projectDTO.getParentId()).ifPresentOrElse(p -> project.setParent(p), () -> {
				throw new ResourceNotFoundException(String.format("Parent project (id=[%d]) for project (id=[%d]) not found", projectDTO.getParentId(), projectDTO.getId()));
			});
		}

		Project saved = projectRepository.save(project);
		return projectMapper.fromEntity(saved);
	}

	@Override
	@Transactional
	public List<ProjectDto> findAll() {
		return projectRepository.findAll().stream().map(projectMapper::fromEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Optional<ProjectDto> findById(Long id) {
		return projectRepository.findById(id).map(projectMapper::fromEntity);
	}

	@Override
	@Transactional
	public ProjectDto update(ProjectDto projectDTO) {
		if(projectDTO == null || projectDTO.getId() == null){
			throw new IllegalArgumentException("Invalid project argument");
		}

		// Check current project
		Project foundProject = projectRepository.findById(projectDTO.getId()).orElseThrow(() -> {
			throw new ResourceNotFoundException(String.format("Project (id=[%d]) not found", projectDTO.getId()));
		});

		projectMapper.updateFromDto(projectDTO, foundProject);

		// Check parent project
		if(projectDTO.getParentId() != null){
			projectRepository.findById(projectDTO.getParentId()).ifPresentOrElse(p -> foundProject.setParent(p), () -> {
				throw new ResourceNotFoundException(String.format("Parent project (id=[%d]) for project (id=[%d]) not found", projectDTO.getParentId(), projectDTO.getId()));
			});
		}

		return projectMapper.fromEntity(projectRepository.save(foundProject));
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	@Transactional
	public List<ProjectDto> findAllByParentId(Long id) {
		return projectRepository.findAllByParentId(id)
				.stream()
				.map(projectMapper::fromEntity)
				.collect(Collectors.toList());
	}
}
