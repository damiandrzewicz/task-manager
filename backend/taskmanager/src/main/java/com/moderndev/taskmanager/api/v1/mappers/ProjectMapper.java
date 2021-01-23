package com.moderndev.taskmanager.api.v1.mappers;

import com.moderndev.taskmanager.api.v1.models.ProjectDto;
import com.moderndev.taskmanager.domain.BaseEntity;
import com.moderndev.taskmanager.domain.Project;
import jdk.jfr.Name;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mappings({
        @Mapping(source = "parent", target = "parentId", qualifiedByName = "parentToDTO"),
        @Mapping(source = "subProjects", target = "subProjectsIds", qualifiedByName = "subprojectsToDTO"),
    })
    ProjectDto fromEntity(Project project);

    Project fromDto(ProjectDto projectDTO);

    void updateFromDto(ProjectDto projectDto, @MappingTarget Project project);

    @Named("parentToDTO")
    default Long parentToDTO(Project project){
        if(project == null){
            return null;
        }
        return project.getId();
    }

    @Named("subprojectsToDTO")
    default List<Long> subprojectsToDTO(List<Project> subprojects){
        if(subprojects == null){
            return null;
        }
        return subprojects.stream()
                .map(p -> p.getId())
                .collect(Collectors.toList());
    }

}
