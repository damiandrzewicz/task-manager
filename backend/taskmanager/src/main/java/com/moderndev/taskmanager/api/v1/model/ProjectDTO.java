package com.moderndev.taskmanager.api.v1.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.moderndev.taskmanager.api.validators.Post;
import com.moderndev.taskmanager.api.validators.Put;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDTO {

	@NotNull(groups = Put.class, message = "Id is mandatory")
	private Long id;
	
	//@NotEmpty(groups = Post.class, message = "Name is mandatory")
	@NotBlank(groups = {Post.class, Put.class}, message = "Name is mandatory")
    private String name;
	
    private String description;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime created;
    private Long parentId;
    
    
    
	@Override
	public ProjectDTO clone(){
		try {
			return (ProjectDTO) super.clone();
		}catch(CloneNotSupportedException ex) {
			return new ProjectDTO(this.id, this.name, this.description, this.created, this.parentId);
		}
	}
    
    
}
