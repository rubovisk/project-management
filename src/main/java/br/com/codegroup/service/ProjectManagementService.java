package br.com.codegroup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.codegroup.entities.ProjectManagement;
import br.com.codegroup.repository.ProjectManagementRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectManagementService {
    private final ProjectManagementRepository projectManagementRepository;

    public ProjectManagement saveProject(ProjectManagement projectManagement) {
        return projectManagementRepository.save(projectManagement);
    }
    
    public List<ProjectManagement> getAllProjects() {
        return projectManagementRepository.findAll();
    }
    
    public ProjectManagement getProjectById(Long id) {
        return projectManagementRepository
        		.findById(id)
        		.orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }
    
    public void deleteProject(Long id) {
        projectManagementRepository.deleteById(id);
    }
}
