package com.moderndev.taskmanager.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "project")
public class Project extends BaseEntity{

	private static final long serialVersionUID = 1L;

    @Column(length = 128, unique = true)
    @NotBlank(message = "Property 'name' cannot be null or empty")
    @Setter(AccessLevel.NONE)
    private String name;

    @Column(length = 1024)
    @Setter(AccessLevel.NONE)
    private String description;

    @Column
    @CreationTimestamp
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @Setter(AccessLevel.NONE)
    private Project parent;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private Set<Project> subProjects = new HashSet<>();


    @Builder
	public Project(String name, String description) {
    	setName(name);
    	setDescription(description);
	}
    
    public void setName(String name) {
    	this.name = name != null ? name.trim() : null;
    }
    
    public void setDescription(String description) {
    	this.description = description != null ? description.trim() : null;
    }

    public void setParent(Project parent){
        this.parent = parent;
        parent.getSubProjects().add(this);
    }
    
    public void moveProject(Project newParent) {
    	this.getParent().getSubProjects().remove(this);
    	this.setParent(newParent);
    	newParent.getSubProjects().add(this);
    }
   

	@Override
	public String toString() {
		return String.format("Project: [id= %d, name=%s, description=%s, created=%s, parent=[%s], subprojects=[%s]]", 
				getId(),
				getName(),
				getDescription(),
				getCreated(),
				parent != null ? String.format("id= %d, name=%s", parent.getId(), parent.getName()) : "null",
				getSubProjects().stream().map(p -> String.format("[id= %d, name=%s]", p.getId(), p.getName())).collect(Collectors.joining(",")));
	}
}