package com.moderndev.taskmanager.api.v1.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moderndev.taskmanager.api.validators.Patch;
import com.moderndev.taskmanager.api.validators.Post;
import com.moderndev.taskmanager.api.validators.Put;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDto {

	@NotNull(groups = {Patch.class, Put.class})
	private Long id;

	@NotBlank(groups = {Post.class})
	@Size(max = 128, groups = {Post.class, Patch.class, Put.class})
    private String name;

	@Size(max = 1024, groups = {Post.class, Patch.class, Put.class})
    private String description;
    
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	//@Builder.Default
    private LocalDateTime created;

    @Size(min = 0, max = 100)
    private int progress;

    private Long parentId;

    private List<Long> subProjectsIds;

    private String abbreveation;

	@Override
	public ProjectDto clone(){
		try {
			return (ProjectDto) super.clone();
		}catch(CloneNotSupportedException ex) {
			return new ProjectDto(this.id, this.name, this.description, this.created, this.progress, this.parentId, this.subProjectsIds, this.abbreveation);
		}
	}

	@Override
	public String toString() {
		return "ProjectDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", created=" + created +
				", progress=" + progress +
				", parentId=" + parentId +
				", subProjectsIds=" + subProjectsIds +
				'}';
	}
}
