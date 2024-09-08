package br.com.codegroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codegroup.entities.ProjectManagement;

@Repository
public interface ProjectManagementRepository extends JpaRepository<ProjectManagement, Long> {
}
