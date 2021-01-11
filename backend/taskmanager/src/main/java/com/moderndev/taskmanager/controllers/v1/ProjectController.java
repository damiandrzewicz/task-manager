package com.moderndev.taskmanager.controllers.v1;

import com.moderndev.taskmanager.api.v1.model.ProjectDTO;
import com.moderndev.taskmanager.api.validators.Post;
import com.moderndev.taskmanager.api.validators.Put;
import com.moderndev.taskmanager.services.ProjectService;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProjectController.BASE_URL)
public class ProjectController {
	
	public static final String BASE_URL = "/api/v1/project";
	
	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectDTO saveProject(@Validated(Post.class) @RequestBody ProjectDTO dto) {
		return projectService.save(dto);
	}

	@GetMapping
	public List<ProjectDTO> all(){
		return projectService.findAll();
	}

	@GetMapping({"/{id}"})
	public ProjectDTO one(@PathVariable Long id) {
		var optional = projectService.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new ResourceNotFoundException(String.format("No element for id=[%s]", id));
		}
	}

	@PutMapping
	public ProjectDTO updateProject(@Validated(Put.class) @RequestBody ProjectDTO dto) {
		return projectService.update(dto);
	}

	@DeleteMapping({"/{id}"})
	public void deletePRoject(@PathVariable Long id) {
		projectService.deleteById(id);
	}


}
