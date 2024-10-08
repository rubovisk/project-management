package br.com.codegroup.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.codegroup.entities.ProjectManagement;
import br.com.codegroup.enums.ProjectRisk;
import br.com.codegroup.enums.ProjectStatus;
import br.com.codegroup.service.MembersService;
import br.com.codegroup.service.ProjectManagementService;
import br.com.codegroup.utils.CurrencyUtils;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class ProjectManagementController {
	private static final Logger logger = LogManager.getLogger(ProjectManagementController.class);
	private final MembersService membersService;
    private final ProjectManagementService projectManagementService;
    private static final String CARGO_PERMITIDO = "funcionario";
    private static final String REDIRECT = "redirect:/";

    @GetMapping("/")
    public ModelAndView index() {
    	 List<ProjectManagement> projects = projectManagementService.getAllProjects();
    	    ModelAndView modelAndView = new ModelAndView("index");
    	    modelAndView.addObject("projects", projects);
    	    modelAndView.addObject("projectStatuses", ProjectStatus.values());
    	    modelAndView.addObject("projectRisks", ProjectRisk.values());
    	    modelAndView.addObject("members", membersService.getMembersByCargo(CARGO_PERMITIDO));
    	    return modelAndView;
    }
    
    @PostMapping("/projects/add")
    public ModelAndView addProject(
    		 @RequestParam("nome") String nome,
    	        @RequestParam("dtInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtInicio,
    	        @RequestParam("gerenteResponsavel") String gerenteResponsavel,
    	        @RequestParam(value = "dtPrvTermino", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtPrvTermino,
    	        @RequestParam(value = "dtRealTermino", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtRealTermino,
    	        @RequestParam("orcamentoTotal") String orcamentoTotal,
    	        @RequestParam("descricao") String descricao,
    	        @RequestParam("status") Integer status,
    	        @RequestParam("risco") int risco,
    	        @RequestParam(value = "member", required = false) Long memberId) {
        
        ProjectManagement project = new ProjectManagement();
        project.setNome(nome);
        project.setDtInicio(dtInicio);
        project.setGerenteResponsavel(gerenteResponsavel);
        project.setDtPrvTermino(dtPrvTermino);
        project.setDtRealTermino(dtRealTermino);
        project.setOrcamentoTotal(CurrencyUtils.getDoubleValue(orcamentoTotal));
        project.setDescricao(descricao);
        project.setStatus(ProjectStatus.fromCode(status).getDescription());
        project.setRisco(ProjectRisk.fromCode(risco).getDescription());
        if (memberId != null) {
            project.setMember(membersService.getMembersById(memberId));
        }
        
        projectManagementService.saveProject(project);
        logger.info("Projeto {} adicionado com sucesso", project.getNome());
        return new ModelAndView(REDIRECT); 
    }
    
    @PostMapping("/projects/update")
    public ModelAndView updateProject(
            @RequestParam("id") Long id,
            @RequestParam("nome") String nome,
            @RequestParam("dtInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtInicio,
            @RequestParam("gerenteResponsavel") String gerenteResponsavel,
            @RequestParam(value = "dtPrvTermino", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtPrvTermino,
            @RequestParam(value = "dtRealTermino", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtRealTermino,
            @RequestParam("orcamentoTotal") String orcamentoTotal,
	        @RequestParam("descricao") String descricao,
	        @RequestParam("status") Integer status,
	        @RequestParam("risco") int risco,
	        @RequestParam(value = "member", required = false) Long memberId) {
    	
    	Double valorOrcamentoTotal = Double.parseDouble(orcamentoTotal.trim().replace(".", "").replace(",", ".").replace("R$", ""));
        
        ProjectManagement project = projectManagementService.getProjectById(id);
        project.setNome(nome);
        project.setDtInicio(dtInicio);
        project.setGerenteResponsavel(gerenteResponsavel);
        project.setDtPrvTermino(dtPrvTermino);
        project.setDtRealTermino(dtRealTermino);
        project.setOrcamentoTotal(valorOrcamentoTotal);
        project.setDescricao(descricao);
        project.setStatus(ProjectStatus.fromCode(status).getDescription());
        project.setRisco(ProjectRisk.fromCode(risco).getDescription());
        
        if (memberId != null) {
            project.setMember(membersService.getMembersById(memberId));
        }
        

        projectManagementService.saveProject(project);
        logger.info("Projeto {} atualizado com sucesso", project.getNome());
        return new ModelAndView(REDIRECT);
    }
    
    @PostMapping("/projects/delete")
    public ModelAndView deleteProject(@RequestParam("id") Long id) {
        projectManagementService.deleteProject(id);
        logger.info("Projeto id: {} excluído com sucesso", id);
        return new ModelAndView(REDIRECT);
    }

}
