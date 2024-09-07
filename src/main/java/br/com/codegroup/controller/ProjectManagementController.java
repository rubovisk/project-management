package br.com.codegroup.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.codegroup.entities.ProjectManagement;
import br.com.codegroup.enums.ProjectRisk;
import br.com.codegroup.enums.ProjectStatus;
import br.com.codegroup.service.ProjectManagementService;



@Controller
public class ProjectManagementController {

    private final ProjectManagementService projectManagementService;

    @Autowired
    public ProjectManagementController(ProjectManagementService projectManagementService) {
        this.projectManagementService = projectManagementService;
    }

    @GetMapping("/")
    public ModelAndView index() {
    	 List<ProjectManagement> projects = projectManagementService.getAllProjects();
    	    ModelAndView modelAndView = new ModelAndView("index");
    	    modelAndView.addObject("projects", projects);
    	    modelAndView.addObject("projectStatuses", ProjectStatus.values());
    	    modelAndView.addObject("projectRisks", ProjectRisk.values());
    	    return modelAndView;
    }
    
    @PostMapping("/api/projects/add")
    public ModelAndView addProject(
    		 @RequestParam("nome") String nome,
    	        @RequestParam("dtInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtInicio,
    	        @RequestParam("gerenteResponsavel") String gerenteResponsavel,
    	        @RequestParam(value = "dtPrvTermino", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtPrvTermino,
    	        @RequestParam(value = "dtRealTermino", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtRealTermino,
    	        @RequestParam("orcamentoTotal") Double orcamentoTotal,
    	        @RequestParam("descricao") String descricao,
    	        @RequestParam("status") Integer status,
    	        @RequestParam("risk") int risk) {
        
        ProjectManagement project = new ProjectManagement();
        project.setNome(nome);
        project.setDtInicio(dtInicio);
        project.setGerenteResponsavel(gerenteResponsavel);
        project.setDtPrvTermino(dtPrvTermino);
        project.setDtRealTermino(dtRealTermino);
        project.setOrcamentoTotal(orcamentoTotal);
        project.setDescricao(descricao);
        project.setStatus(ProjectStatus.fromCode(status).getDescription());
        project.setRisk(ProjectRisk.fromCode(risk).getDescription());
        
        projectManagementService.saveProject(project);
        
        return new ModelAndView("redirect:/"); 
    }
    
    @PostMapping("/api/projects/update")
    public ModelAndView updateProject(
            @RequestParam("id") Long id,
            @RequestParam("nome") String nome,
            @RequestParam("dtInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtInicio,
            @RequestParam("gerenteResponsavel") String gerenteResponsavel,
            @RequestParam(value = "dtPrvTermino", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtPrvTermino,
            @RequestParam(value = "dtRealTermino", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtRealTermino,
            @RequestParam("orcamentoTotal") Double orcamentoTotal,
	        @RequestParam("descricao") String descricao,
	        @RequestParam("status") Integer status) {
        
        ProjectManagement project = projectManagementService.getProjectById(id);
        project.setNome(nome);
        project.setDtInicio(dtInicio);
        project.setGerenteResponsavel(gerenteResponsavel);
        project.setDtPrvTermino(dtPrvTermino);
        project.setDtRealTermino(dtRealTermino);
        project.setOrcamentoTotal(orcamentoTotal);
        project.setDescricao(descricao);
        project.setStatus(ProjectStatus.fromCode(status).getDescription());

        projectManagementService.saveProject(project);
        
        return new ModelAndView("redirect:/");
    }
    
    @PostMapping("/api/projects/delete")
    public ModelAndView deleteProject(@RequestParam("id") Long id) {
        projectManagementService.deleteProject(id);
        return new ModelAndView("redirect:/");  // Redirect to the main page after deletion
    }

}
