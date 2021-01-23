package com.moderndev.taskmanager.repositories;

import com.moderndev.taskmanager.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

	boolean existsById(Long id);

	boolean existsByName(String name);

	List<Project> findAllByParentId(Long id);
}
