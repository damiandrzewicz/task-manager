package com.moderndev.taskmanager.services;

import com.moderndev.taskmanager.api.v1.mappers.ProjectDTOMapper;
import com.moderndev.taskmanager.api.v1.model.ProjectDTO;
import com.moderndev.taskmanager.domain.Project;
import com.moderndev.taskmanager.repositories.ProjectRepository;
import com.moderndev.taskmanager.services.exceptions.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    ProjectRepository projectRepository;

    @Spy
    ProjectDTOMapper mapper = ProjectDTOMapper.INSTANCE;

    @InjectMocks
    ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenProject_whenSave_thenReturnSavedProject() {
        // Given
        ProjectDTO projectDTO = ProjectDTO.builder().name("testname").build();

        // When
        Project returned = Project.builder().name("testname").build();
        Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(returned);

        // Then
        ProjectDTO saved = projectService.save(projectDTO);
        assertEquals(projectDTO.getName(), saved.getName());
    }

    @Test
    void givenFewProject_whenFindAll_thenReturnAll() {
        //Given
        Project project1 = Project.builder().name("project1").build();
        Project project2 = Project.builder().name("project2").build();
        Project project3 = Project.builder().name("project3").build();

        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);

        //When
        Mockito.when(projectRepository.findAll()).thenReturn(projects);

        // Then
        List<ProjectDTO> all = projectService.findAll();
        Assertions.assertThat(all).hasSize(3);
        assertTrue(all.stream().anyMatch(p -> p.getName().equals(project1.getName())));
        assertTrue(all.stream().anyMatch(p -> p.getName().equals(project2.getName())));
        assertTrue(all.stream().anyMatch(p -> p.getName().equals(project3.getName())));
    }

    @Test
    void givenProject_thenFindById_thenReturnProject() {
        // Given
        Project project = Project.builder().name("project1").build();
        project.setId(1L);

        // When
        Mockito.when(projectRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(project));

        // Then
        Optional<ProjectDTO> optionalProject = projectService.findById(project.getId());
        assertTrue(optionalProject.isPresent());
        assertEquals(project.getId(), optionalProject.get().getId());
        assertEquals(project.getName(), optionalProject.get().getName());
    }

    @Test
    void givenProject_thenFindById_thenReturnNotFound() {
        // When
        Mockito.when(projectRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // Then
        Optional<ProjectDTO> optionalProject = projectService.findById(1L);
        assertTrue(optionalProject.isEmpty());
    }

    @Test
    void givenProject_whenUpdate_thenReturnsUpdatedProject() {
        // Given
        Project project = Project.builder().name("project").build();
        project.setId(1L);

        Project updtedProject = Project.builder().name("project").build();
        updtedProject.setId(1L);
        updtedProject.setName("updatedName");

        ProjectDTO toUpdateProject = ProjectDTO.builder().name("project").build();
        toUpdateProject.setId(1L);
        toUpdateProject.setName("updatedName");

        // When
        Mockito.when(projectRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(project));
        Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(updtedProject);

        // Then
        ProjectDTO returnedProject = projectService.update(toUpdateProject);

        Mockito.verify(projectRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(projectRepository, Mockito.times(1)).save(Mockito.any(Project.class));

        assertEquals(updtedProject.getId(), returnedProject.getId());
        assertEquals(updtedProject.getName(), returnedProject.getName());
    }

    @Test
    void givenNothiing_whenUpdate_thenReturnsResourceNotFoundException(){
        // When
        Mockito.when(projectRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // Then
        assertThrows(ResourceNotFoundException.class, () -> {
            ProjectDTO project = ProjectDTO.builder().build();
            project.setId(1L);
            projectService.update(project);
        });
    }

    @Test
    void givenNullArgument_whenUpdate_thenThrowsException(){
        // Then
        assertThrows(IllegalArgumentException.class, () -> projectService.update(null));
    }

    @Test
    void givenNotValidProject_whenUpdate_thenThrowsException(){
        // Then
        assertThrows(IllegalArgumentException.class, () -> projectService.update(ProjectDTO.builder().build()));
    }

    @Test
    void givenProject_whenDeleteById_thenCallDelete() {
        // Given
        Project project = Project.builder().build();
        project.setId(1L);

        // When
        projectService.deleteById(project.getId());

        // Then
        Mockito.verify(projectRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void givenProjectsTree_whenFindAllByParentId_thenReturnList() {
        // Given
        Project parent = Project.builder().name("parent").build();
        parent.setId(1L);

        Project child1 = Project.builder().name("child1").build();
        child1.setParent(parent);

        Project child2 = Project.builder().name("child2").build();
        child2.setParent(parent);

        Project child3 = Project.builder().name("child3").build();
        child3.setParent(parent);

        // When
        Mockito.when(projectRepository.findAllByParentId(Mockito.anyLong())).thenReturn(parent.getSubProjects());

        // Then
        List<ProjectDTO> allByParentId = projectService.findAllByParentId(parent.getId());
        Assertions.assertThat(allByParentId).hasSize(3);
        assertTrue(allByParentId.stream().anyMatch(p -> p.getName().equals(child1.getName())));
        assertTrue(allByParentId.stream().anyMatch(p -> p.getName().equals(child2.getName())));
        assertTrue(allByParentId.stream().anyMatch(p -> p.getName().equals(child3.getName())));
    }
}