package com.moderndev.taskmanager.api.v1.mappers;

import com.moderndev.taskmanager.api.v1.model.ProjectDTO;
import com.moderndev.taskmanager.domain.BaseEntity;
import com.moderndev.taskmanager.domain.Project;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProjectDTOMapper {

    ProjectDTOMapper INSTANCE = Mappers.getMapper(ProjectDTOMapper.class);

    @Mappings({
        @Mapping(source = "parent", target = "parentId", qualifiedByName = "parentToDTO"),
        @Mapping(source = "subProjects", target = "subProjectsIds", qualifiedByName = "subProjectsToDTO")
    })
    ProjectDTO toDTO(Project project);

    @Mappings({
        @Mapping(source = "id", target = "id")
    })
    Project fromDTO(ProjectDTO projectDTO);

    @Named("parentToDTO")
    default Long parentToDTO(Project project){
        if(project == null){
            return null;
        }
        return project.getId();
    }

    @Named("subProjectsToDTO")
    default List<Long> subProjectsToDTO(List<Project> subProjects){
        return subProjects.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }
}
