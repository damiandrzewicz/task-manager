package com.moderndev.taskmanager.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.moderndev.taskmanager.domain.Project;

@SpringBootTest
class ProjectServiceIT {
	
	@Autowired
	ProjectService projectService;

	@BeforeEach
	void setUp() throws Exception {
	}

//	@Test
//	void whenCheckSubproject() {
//		Project parent = Project.builder().name("parent").build();
//		parent = projectService.save(parent);
//
//		Project child = Project.builder().name("child").parentId(parent.getId()).build();
//		child = projectService.save(child);
//		
//		var optionalParent = projectService.findById(parent.getId());
//		//var optionalChild = projectRepository.findById(child.getId());
//		
//		if(optionalParent.isPresent()) {
//			var foundParent = optionalParent.get();
//			//var foundChild = optionalChild.get();
//			
//			assertEquals(parent.getId(), foundParent.getId());
//			assertEquals(1, foundParent.getSubprojects().size());
//			
//			Optional<Project> matchigProject = foundParent.getSubprojects()
//				.stream()
//				.filter(p -> p.getId().equals(foundParent.getId()))
//				.findFirst();
//			
//			assertTrue(matchigProject.isPresent());
//	
//			
//			
//			assertEquals(child.getId(), matchigProject.get().getId());
//		}else {
//			fail("cannot find parent project");
//		}
//		
//	}
}
