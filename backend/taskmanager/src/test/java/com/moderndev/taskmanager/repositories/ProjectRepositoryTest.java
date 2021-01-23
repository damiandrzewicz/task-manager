package com.moderndev.taskmanager.repositories;

import com.moderndev.taskmanager.domain.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProjectRepositoryTest {
	
//	@Mock
//	ProjectRepository projectRepository;
//
//	Project p;
//
//
//
//	@BeforeEach
//	void setUp() {
//		p = Project.builder().name("testname").description("testdescription").build();
//		p.setId(1L);
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
//		List<Project> returned = new ArrayList<>(returnedList);
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
//		var p1 = Project.builder().name("testname1").description("testdescription").build();
//		p1.setId(1L);
//
//		var p2 = Project.builder().name("testname2").description("testdescription").build();
//		p2.setId(2L);
//
//		List<Project> returnSet = new ArrayList<>();
//		returnSet.add(p1);
//		returnSet.add(p2);
//		returnSet.add(p);
//
//		Mockito.when(projectRepository.findAll()).thenReturn(returnSet);
//		Set<Project> projects = new HashSet<>(projectRepository.findAll());
//		assertEquals(3, projects.size());
//	}
//
//	@Test
//	void testFindAllById() {
//		var p1 = Project.builder().name("testname1").description("testdescription").build();
//		p1.setId(2L);
//
//		var p2 = Project.builder().name("testname2").description("testdescription").build();
//		p2.setId(3L);
//
//		List<Project> returnSet = new ArrayList<>();
//		returnSet.add(p1);
//		returnSet.add(p2);
//		returnSet.add(p);
//
//
//		Mockito.when(projectRepository.findAllById(any())).thenReturn(returnSet);
//
//		Set<Project> projects = new HashSet<>(projectRepository.findAllById(Arrays.asList(1L, 2L, 3L)));
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



}
