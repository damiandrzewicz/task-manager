package com.moderndev.taskmanager.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.moderndev.taskmanager.api.v1.model.ProjectDTO;
import com.moderndev.taskmanager.domain.Project;
import com.moderndev.taskmanager.repositories.ProjectRepository;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepository projectRepository;;
	
	//private final ModelMapper modelMapper;
	
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
		return projectRepository.save(project);
	}

	@Override
	public void delete(Long id) {
		//projectRepository.delete(null);;
		
	}



//	@Override
//	@Transactional
//	public ProjectDTO save(Project project) {
//		project.setId(null);
//		project.setCreated(LocalDateTime.now());
//		var saved = projectRepo.save(fromDTO(project));
//		return toDTO(saved);
//	}
//	
//
//	@Override
//	@Transactional
//	public List<ProjectDTO> findAll() {
//		return projectRepo.findAll()
//			.stream()
//			.map(p -> toDTO(p))
//			.collect(Collectors.toList());
//	}
//	
//	@Override
//	@Transactional
//	public Optional<ProjectDTO>  findById(Long id) {
//		return projectRepo.findById(id)
//				.map(p -> toDTO(p));
//	}
//	
//	@Override
//	@Transactional
//	public ProjectDTO update(ProjectDTO project) {
//		var optional = findById(project.getId());
//		if(optional.isPresent()) {
//			//var foundProject = optional.get();
//			var saved = projectRepo.save(fromDTO(project));
//			return toDTO(saved);
//		}else {
//			throw new ResourceNotFoundException(String.format("Cannot find project for id=[%s]", project.getId()));
//		}
//
//	}
//	
//
//	@Override
//	@Transactional
//	public void delete(Long id) {
//		findById(id)
//			.ifPresentOrElse(p -> {
//				projectRepo.delete(fromDTO(p));
//			}, () -> {
//				throw new ResourceNotFoundException("Cannot find project by id=[" + id +"]");
//			});
	//}
	

//	public ProjectDTO toDTO(Project project) {
//		return modelMapper.map(project, ProjectDTO.class);
//	}
//	
//	public Project fromDTO(ProjectDTO projectDTO) {
//		return modelMapper.map(projectDTO, Project.class);
//	}







	
}
