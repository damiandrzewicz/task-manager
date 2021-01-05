package com.moderndev.taskmanager.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import com.moderndev.taskmanager.domain.Project;

@DataJpaTest
class ProjectRepositoryIT {
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	ProjectRepository projectRepository;
	
	static Validator validator;
	
	@BeforeAll
	static void beforeAll() {
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    validator = factory.getValidator();
	}

	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	void givenProject_whenSave_thenSavedObjectReturned() {
		String name = "name";
		Project project = Project.builder().name(name).build();
		project = testEntityManager.persist(project);
		testEntityManager.flush();
		
		var found = projectRepository.findById(project.getId());
		if(found.isPresent()) {
			var saved = found.get();
			assertThat(saved.getId().equals(project.getId()));
			assertThat(found.get().getName().equals(project.getName()));
			assertNull(saved.getDescription());
			assertNull(saved.getParent());
		}else {
			fail("Project not found");
		}
	}
	
	@Test
	void saveMinimalEntity() {
		String name = "name";
		Project project = Project.builder().name(name).build();
		
		Project saved = projectRepository.save(project);
		assertEquals(project.getName(), saved.getName());
		assertNotNull(saved.getId());
	}
	
	@Test
	void givenMinimalProject_whenSave_thenSaved() {
		Project project = Project.builder()
				.name("myname")
				.description("mydescription")
				.build();
		project.setCreated(LocalDateTime.now());
		
		Project saved = projectRepository.save(project);
		assertNotNull(saved.getId());
		assertEquals(saved.getName(), project.getName());
		assertEquals(saved.getDescription(), project.getDescription());
		assertEquals(saved.getCreated(), project.getCreated());
	}
	
	@Test
	void givenProjecWithNullName_whenSave_thenThrowException() {
		Project project = Project.builder().build();
		
		assertThrows(ConstraintViolationException.class, () -> {
			projectRepository.save(project);
		});
	}
	
	@Test
	void givenProjecWithEmptyName_whenSave_thenThrowException() {
		Project project = Project.builder().name("").build();
		
		assertThrows(ConstraintViolationException.class, () -> {
			projectRepository.save(project);
		});
	}
	
	@Test
	void givenProject_whenSave_thenCreatedFilled() {
		Project project = Project.builder().name("project").build();
		var savedProject = projectRepository.save(project);
		assertNotNull(savedProject.getCreated());
	}
	
	@Test
	void givenProject_whenEdit_thenUpdate() {
		// Given
		Project project = projectRepository.save(Project.builder().name("project").build());
		project = testEntityManager.persistAndFlush(project);
		
		// When
		var foundProject = projectRepository.findById(project.getId()).orElseThrow(() ->  new RuntimeException("cannot find project"));
		foundProject.setName("updatedName");
		foundProject.setDescription("updatedDescription");
		
		// Then
		var updatedProject = projectRepository.save(foundProject);
		assertEquals(foundProject.getId(), updatedProject.getId());
		assertEquals(foundProject.getName(), updatedProject.getName());
		assertEquals(foundProject.getDescription(), updatedProject.getDescription());
	}
	
	@Test
	void givenParentAndChilds_whenDeleteParent_thenDeleteChilds() {
		// Given
		Project parent = Project.builder().name("parent").build();
		parent = projectRepository.save(parent);
		
		Project child1 = Project.builder().name("child1").build();
		child1.setParent(parent);
		child1 = projectRepository.save(child1);
		
		Project child2 = Project.builder().name("child2").build();
		child2.setParent(parent);
		child2 = projectRepository.save(child2);
		
		Project child3 = Project.builder().name("child3").build();
		child3.setParent(parent);
		child3 = projectRepository.save(child3);
		
		assertTrue(projectRepository.findById(parent.getId()).isPresent());
		assertTrue(projectRepository.findById(child1.getId()).isPresent());
		assertTrue(projectRepository.findById(child2.getId()).isPresent());
		assertTrue(projectRepository.findById(child3.getId()).isPresent());
		
		// When
		projectRepository.deleteById(parent.getId());
		
		// Then
		assertFalse(projectRepository.findById(parent.getId()).isPresent());
		assertFalse(projectRepository.findById(child1.getId()).isPresent());
		assertFalse(projectRepository.findById(child2.getId()).isPresent());
		assertFalse(projectRepository.findById(child3.getId()).isPresent());
	}

	@Test
	void givenProjectTree_whenDeleteParent_thenDeleteAllTree() {
		// Given
		Project project1 = Project.builder().name("project1").build();
		project1 = testEntityManager.persistAndFlush(project1);
		
		Project project2 = Project.builder().name("project2").build();
		project2.setParent(project1);
		project2 = testEntityManager.persistAndFlush(project2);
		
		Project project3 = Project.builder().name("project3").build();
		project3.setParent(project2);
		project3 = testEntityManager.persistAndFlush(project3);
		
		assertNotNull(project1.getId());
		assertNotNull(project2.getId());
		assertNotNull(project3.getId());
		
		// When
		projectRepository.deleteById(project1.getId());
		
		// Then
		assertFalse(projectRepository.findById(project1.getId()).isPresent());
		assertFalse(projectRepository.findById(project2.getId()).isPresent());
		assertFalse(projectRepository.findById(project3.getId()).isPresent());
	}
	
	@Test
	void givenParentAndChilds_whenFindAllSubProjects_thenGetResults() {
		// Given
		Project parent = Project.builder().name("parent").build();
		parent = testEntityManager.persistAndFlush(parent);
		
		Project child1 = Project.builder().name("child1").build();
		child1.setParent(parent);
		child1 = testEntityManager.persistAndFlush(child1);
		
		Project child2 = Project.builder().name("child2").build();
		child2.setParent(parent);
		child2 = testEntityManager.persistAndFlush(child2);
		
		Project child3 = Project.builder().name("child3").build();
		child3.setParent(parent);
		child3 = testEntityManager.persistAndFlush(child3);
		
		// When
		List<Project> subprojects = projectRepository.findAllByParentId(parent.getId());
		
		// Then
		assertThat(subprojects).hasSize(3).contains(child1, child2, child3);
	}
	
	@Test
	void givenParentWithChilds_whenGetSubProjects_thenCheckSize() {
		// Given
		Project parent = Project.builder().name("parent").build();
		parent = testEntityManager.persistAndFlush(parent);
		
		Project child1 = Project.builder().name("child1").build();
		child1.setParent(parent);
		child1 = testEntityManager.persistAndFlush(child1);
		
		Project child2 = Project.builder().name("child2").build();
		child2.setParent(parent);
		child2 = testEntityManager.persistAndFlush(child2);
		
		Project child3 = Project.builder().name("child3").build();
		child3.setParent(parent);
		child3 = testEntityManager.persistAndFlush(child3);
		
		// When
		Project foundProject = projectRepository.findById(parent.getId()).get();
		
		// Then
		assertThat(foundProject.getSubProjects()).hasSize(3).contains(child1, child2, child3);
	}
}
