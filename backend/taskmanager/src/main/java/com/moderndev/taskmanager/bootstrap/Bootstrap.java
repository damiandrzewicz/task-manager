package com.moderndev.taskmanager.bootstrap;

import com.moderndev.taskmanager.api.v1.models.ProjectDto;
import com.moderndev.taskmanager.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner{
	
	private final ProjectService projectService;

	public Bootstrap(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}

	@Override
	public void run(String... args) {
        ProjectDto project1 = projectService.save(
            ProjectDto.builder().name("Parent project").description("funny description1").build()
        );

        ProjectDto project2 = projectService.save(
            ProjectDto.builder().name("Child project1").description("some description2").build()
        );

		ProjectDto project3 = projectService.save(
				ProjectDto.builder().name("Child project2").description("some description2").build()
		);

		project3.setParentId(project2.getId());
		projectService.save(project3);

		ProjectDto project4 = projectService.save(
				ProjectDto.builder().name("Child project3").description("some description2").build()
		);

		ProjectDto project5 = projectService.save(
				ProjectDto.builder().name("Child project4").description("some description2").build()
		);

		ProjectDto project6 = projectService.save(
				ProjectDto.builder().name("Child project5").description("some description2").build()
		);

		project5.setParentId(project4.getId());
		project6.setParentId(project4.getId());
		projectService.save(project5);
		projectService.save(project6);

        System.out.println("Bootstrap::run");
		
	}

}
