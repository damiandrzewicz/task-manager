package com.moderndev.taskmanager.controllers.v1;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.moderndev.taskmanager.services.ProjectService;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;
import com.sun.istack.NotNull;
import com.moderndev.taskmanager.api.v1.model.ProjectDTO;
import com.moderndev.taskmanager.api.validators.Post;
import com.moderndev.taskmanager.api.validators.Put;

@RestController
@RequestMapping(ProjectController.BASE_URL)
public class ProjectController {
	
	public static final String BASE_URL = "/api/v1/project";
	
//	private final ProjectService projectService;
//
//	public ProjectController(ProjectService projectService) {
//		super();
//		this.projectService = projectService;
//	}
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public ProjectDTO saveProject(@Validated(Post.class) @RequestBody ProjectDTO dto) {
//		return projectService.save(dto);
//	}
//	
//	@GetMapping
//	public List<ProjectDTO> all(){
//		return projectService.findAll();
//	}
//	
//	@GetMapping({"/{id}"})
//	public ProjectDTO one(@PathVariable Long id) {
//		var optional = projectService.findById(id);
//		if(optional.isPresent()) {
//			return optional.get();
//		}else {
//			throw new ResourceNotFoundException(String.format("No element for id=[%s]", id));
//		}
//	}
//
//	@PutMapping
//	public ProjectDTO updateProject(@Validated(Put.class) @RequestBody ProjectDTO dto) {
//		return projectService.update(dto);
//	}
//	
//	@DeleteMapping({"/{id}"})
//	public void deletePRoject(@PathVariable Long id) {
//		projectService.delete(id);
//	}
//	

}
