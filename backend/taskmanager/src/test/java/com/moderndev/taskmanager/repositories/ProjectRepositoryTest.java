package com.moderndev.taskmanager.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.moderndev.taskmanager.domain.Project;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ExtendWith(MockitoExtension.class)
class ProjectRepositoryTest {
	
	@Mock
	ProjectRepository projectRepository;
	
	Project p;
	
	

//	@BeforeEach
//	void setUp() throws Exception {
//		p = Project.builder().id(1L).name("testname").description("testdescription").build();
//	}
//
//	@Test
//	void testSave() {
//
//		Mockito.when(projectRepository.save(any(Project.class))).thenReturn(p);
//		
//		p = projectRepository.save(p);
//		assertNotNull(p.getId());
//	}
//
//	@Test
//	void testSaveAll() {
//		List<Project> list = new ArrayList<>();
//		list.add(p);
//		Mockito.when(projectRepository.saveAll(any())).thenReturn(list);
//		var returnedList = projectRepository.saveAll(list);
//		List<Project> returned = StreamSupport.stream(returnedList.spliterator(), false).collect(Collectors.toList());
//		assertEquals(1, returned.size());
//	}
//
//	@Test
//	void testFindById() {
//		Mockito.when(projectRepository.findById(any())).thenReturn(Optional.of(p));
//		var project = projectRepository.findById(1L);
//		assertTrue(project.isPresent());
//		assertEquals(1L, project.get().getId());
//	}
//
//	@Test
//	void testExistsById() {
//		Mockito.when(projectRepository.existsById(any())).thenReturn(true);
//		boolean exists = projectRepository.existsById(1L);
//		assertTrue(exists);
//	}
//
//	@Test
//	void testFindAll() {
//		var p1 = Project.builder().id(1L).name("testname1").description("testdescription").build();
//		var p2 = Project.builder().id(1L).name("testname2").description("testdescription").build();
//		
//		List<Project> returnSet = new ArrayList<>();
//		returnSet.add(p1);
//		returnSet.add(p2);
//		returnSet.add(p);
//		
//		Mockito.when(projectRepository.findAll()).thenReturn(returnSet);
//		Set<Project> projects = StreamSupport
//				.stream(projectRepository.findAll().spliterator(), false)
//				.collect(Collectors.toSet());
//		assertEquals(3, projects.size());
//	}
//
//	@Test
//	void testFindAllById() {
//		var p1 = Project.builder().id(2L).name("testname1").description("testdescription").build();
//		var p2 = Project.builder().id(3L).name("testname2").description("testdescription").build();
//		
//		List<Project> returnSet = new ArrayList<>();
//		returnSet.add(p1);
//		returnSet.add(p2);
//		returnSet.add(p);
//		
//		
//		Mockito.when(projectRepository.findAllById(any())).thenReturn(returnSet);
//	
//		Set<Project> projects = StreamSupport
//				.stream(projectRepository.findAllById(Arrays.asList(1L, 2L, 3L)).spliterator(), false)
//				.collect(Collectors.toSet());
//		assertEquals(3, projects.size());
//	}
//
//	@Test
//	void testCount() {
//		Mockito.when(projectRepository.count()).thenReturn(1L);
//		assertEquals(1L, projectRepository.count());
//	}
//
//	@Test
//	void testDeleteById() {
//		projectRepository.deleteById(1L);
//		verify(projectRepository).deleteById(anyLong());
//	}
//
//	@Test
//	void testDelete() {
//		projectRepository.delete(p);
//		verify(projectRepository).delete(any());
//	}
//
//

}
