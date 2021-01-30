package com.moderndev.taskmanager.controllers.v1;

import com.moderndev.taskmanager.api.v1.models.ProjectDto;
import com.moderndev.taskmanager.api.validators.Post;
import com.moderndev.taskmanager.api.validators.Put;
import com.moderndev.taskmanager.services.ProjectService;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(ProjectController.BASE_URL)
public class ProjectController {
	
	public static final String BASE_URL = "/api/v1/projects";
	
	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}

	@GetMapping
	public List<ProjectDto> getAllProjects() {
		return projectService.findAll();
	}

//	@GetMapping("/root")
//	public List<ProjectDto> getRootProjects(){
//		return projectService.findAllByParentId(null).stream()
//				.map(p -> {
//					int randomProgress = new Random().nextInt(100);
//					p.setProgress(randomProgress);
//					return p;
//				})
//				.collect(Collectors.toList());
//	}

	@GetMapping({"/{id}/subprojects"})
	public List<ProjectDto> allProjectsByParentId(@PathVariable Long id){
		if(id == -1){
			return projectService.findAllByParentId(null);
		} else {
			return projectService.findAllByParentId(id);
		}
	}

	@GetMapping({"/{id}"})
	public ProjectDto getProject(@PathVariable Long id) {
		var optional = projectService.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new ResourceNotFoundException(String.format("No element for id=[%s]", id));
		}
	}
	
	@PostMapping	
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectDto saveProject(@Validated(Post.class) @RequestBody ProjectDto dto) {
		return projectService.save(dto);
	}

	@PutMapping
	public ProjectDto updateProject(@Validated(Put.class) @RequestBody ProjectDto dto) {
		return projectService.update(dto);
	}

	@DeleteMapping({"/{id}"})
	public void deleteProject(@PathVariable Long id) {
		projectService.deleteById(id);
	}


}
