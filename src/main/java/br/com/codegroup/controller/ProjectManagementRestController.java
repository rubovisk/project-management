package br.com.codegroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codegroup.entities.ProjectManagement;
import br.com.codegroup.service.ProjectManagementService;

@RestController
@RequestMapping("/api/projects")
public class ProjectManagementRestController {
	
	    private final ProjectManagementService projectManagementService;
	    @Autowired
	    public ProjectManagementRestController(ProjectManagementService projectManagementService) {
	        this.projectManagementService = projectManagementService;
	    }
}
