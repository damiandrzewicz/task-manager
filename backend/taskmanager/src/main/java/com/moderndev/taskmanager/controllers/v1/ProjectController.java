package com.moderndev.taskmanager.controllers.v1;

import com.moderndev.taskmanager.api.v1.models.ProjectDto;
import com.moderndev.taskmanager.api.validators.Post;
import com.moderndev.taskmanager.api.validators.Put;
import com.moderndev.taskmanager.services.ProjectService;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectDto saveProject(@Validated(Post.class) @RequestBody ProjectDto dto) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return projectService.save(dto);
	}

	@GetMapping
	public List<ProjectDto> all(){
		return projectService.findAll();
	}

	@GetMapping("/root")
	public List<ProjectDto> getRootProjects(){
		return projectService.findAllByParentId(null).stream()
				.map(p -> {
					int randomProgress = new Random().nextInt(100);
					p.setProgress(randomProgress);
					return p;
				})
				.collect(Collectors.toList());
	}

	@GetMapping({"/{id}"})
	public ProjectDto one(@PathVariable Long id) {
		var optional = projectService.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new ResourceNotFoundException(String.format("No element for id=[%s]", id));
		}
	}

	@GetMapping({"/{id}/subprojects"})
	public List<ProjectDto> allSubprojects(@PathVariable Long id){
		var roots = projectService.findAllByParentId(id);
		return roots;
	}

	@PutMapping
	public ProjectDto updateProject(@Validated(Put.class) @RequestBody ProjectDto dto) {
		return projectService.update(dto);
	}

	@DeleteMapping({"/{id}"})
	public void deletePRoject(@PathVariable Long id) {
		projectService.deleteById(id);
	}


}
