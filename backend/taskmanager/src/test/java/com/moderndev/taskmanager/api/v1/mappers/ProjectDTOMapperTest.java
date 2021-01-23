package com.moderndev.taskmanager.api.v1.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProjectDTOMapperTest {

//    private final ProjectDTOMapper mapper = ProjectDTOMapper.INSTANCE;
//
//    @Test
//    void testToDTO(){
//        // Given
//        Project parent = Project.builder().build();
//        parent.setId(1L);
//
//        Project child = Project.builder().build();
//        child.setId(2L);
//        child.setName("child");
//        child.setDescription("child-description");
//        child.setParent(parent);
//
//        Project subchild1 = Project.builder().build();
//        subchild1.setId(5L);
//        subchild1.setParent(child);
//
//        Project subchild2 = Project.builder().build();
//        subchild2.setId(6L);
//        subchild2.setParent(child);
//
//        // When
//        ProjectDTO projectDTO = mapper.toDTO(child);
//
//        // Then
//        assertEquals(child.getId(), projectDTO.getId());
//        assertEquals(child.getName(), projectDTO.getName());
//        assertEquals(child.getDescription(), projectDTO.getDescription());
//        assertEquals(child.getParent().getId(), projectDTO.getParentId());
//        assertEquals(child.getSubProjects().stream().map(BaseEntity::getId).collect(Collectors.toList()), projectDTO.getSubProjectsIds());
//
//    }
//
//    @Test
//    void testFromDTO(){
//        // Given
//        ProjectDTO projectDTO = new ProjectDTO();
//        projectDTO.setId(1L);
//        projectDTO.setName("testname");
//        projectDTO.setDescription("testdescription");
//        projectDTO.setCreated(LocalDateTime.now());
//        projectDTO.setParentId(3L);
//        projectDTO.setSubProjectsIds(Arrays.asList(5L, 6L, 7L));
//
//        // When
//        Project project = mapper.fromDTO(projectDTO);
//
//        // Then
//        assertEquals(projectDTO.getId(), project.getId());
//        assertEquals(projectDTO.getName(), project.getName());
//        assertEquals(projectDTO.getDescription(), project.getDescription());
//        assertNull(project.getCreated());
//        assertNull(project.getParent());
//        assertThat(project.getSubProjects()).hasSize(0);
//    }

}