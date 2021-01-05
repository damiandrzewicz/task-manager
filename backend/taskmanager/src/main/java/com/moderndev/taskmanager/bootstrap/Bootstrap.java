package com.moderndev.taskmanager.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.moderndev.taskmanager.api.v1.model.ProjectDTO;
import com.moderndev.taskmanager.domain.Project;
import com.moderndev.taskmanager.repositories.ProjectRepository;
import com.moderndev.taskmanager.services.ProjectService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner{
	
//	private final ProjectService projectService;
//
//	public Bootstrap(ProjectService projectService) {
//		super();
//		this.projectService = projectService;
//	}
	


	ProjectRepository projectRepository;

	public Bootstrap(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public void run(String... args) throws Exception {
//        ProjectDTO project1 = projectService.save(
//            ProjectDTO.builder().name("Parent project").description("funny description1").build()
//        );
//        
//        ProjectDTO project2 = projectService.save(
//            ProjectDTO.builder().name("Child project").description("some description2").parentId(project1.getId()).build()
//        );
//
//        project1 = projectService.save(project1);
//        project2 = projectService.save(project2);
		
		Project parent = Project.builder().name("parent").build();
		projectRepository.save(parent);
		
		Project child = Project.builder().name("child1").build();
		child.setParent(parent);
		projectRepository.save(child);
		
		projectRepository.delete(parent);
//		
//		Project found = projectRepository.findById(parent.getId()).get();
//		
//		System.out.println("found: " + found.getSubprojects().size());
//		for(Project p : found.getSubprojects()) {
//			System.out.println("found: " + p.toString());
//		}
//		

        System.out.println("Bootstrap::run");
		
	}

}
