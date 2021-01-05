package com.moderndev.taskmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moderndev.taskmanager.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	List<Project> findAllByParentId(Long id);
}
