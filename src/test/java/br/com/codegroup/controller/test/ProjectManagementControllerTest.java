package br.com.codegroup.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import br.com.codegroup.controller.ProjectManagementController;
import br.com.codegroup.entities.ProjectManagement;
import br.com.codegroup.service.MembersService;
import br.com.codegroup.service.ProjectManagementService;

class ProjectManagementControllerTest {

	@Mock
	private MembersService membersService;

	@Mock
	private ProjectManagementService projectManagementService;

	@InjectMocks
	private ProjectManagementController projectManagementController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testIndex() {
		List<ProjectManagement> projects = List.of(new ProjectManagement());
		when(projectManagementService.getAllProjects()).thenReturn(projects);
		when(membersService.getMembersByCargo(anyString())).thenReturn(List.of());

		ModelAndView modelAndView = projectManagementController.index();

		assertNotNull(modelAndView);
		assertEquals("index", modelAndView.getViewName());
		assertTrue(modelAndView.getModel().containsKey("projects"));
		assertTrue(modelAndView.getModel().containsKey("projectStatuses"));
		assertTrue(modelAndView.getModel().containsKey("projectRisks"));
		assertTrue(modelAndView.getModel().containsKey("members"));
		verify(projectManagementService, times(1)).getAllProjects();
		verify(membersService, times(1)).getMembersByCargo("funcionario");
	}

	@Test
	void testAddProject() {
		ProjectManagement project = new ProjectManagement();
		when(projectManagementService.saveProject(any(ProjectManagement.class))).thenReturn(project);

		ModelAndView modelAndView = projectManagementController.addProject("Teste", LocalDate.now(), "Gerente",
				LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), "R$ 1.000,00", "Teste Codegroup", 1, 2,
				1L);

		assertNotNull(modelAndView);
		assertEquals("redirect:/", modelAndView.getViewName());
		verify(projectManagementService, times(1)).saveProject(any(ProjectManagement.class));
	}

	@Test
	void testUpdateProject() {
		ProjectManagement existingProject = new ProjectManagement();
		existingProject.setId(1L);
		when(projectManagementService.getProjectById(anyLong())).thenReturn(existingProject);
		when(projectManagementService.saveProject(any(ProjectManagement.class))).thenReturn(existingProject);

		ModelAndView modelAndView = projectManagementController.updateProject(1L, "Teste",
				LocalDate.now(), "Gerente", LocalDate.now().plusDays(15), LocalDate.now().plusDays(25),
				"R$ 2.000,00", "Teste codegroup", 2, 3, 2L);

		assertNotNull(modelAndView);
		assertEquals("redirect:/", modelAndView.getViewName());
		verify(projectManagementService, times(1)).saveProject(any(ProjectManagement.class));
	}

	@Test
	void testDeleteProject() {
		ModelAndView modelAndView = projectManagementController.deleteProject(1L);

		assertNotNull(modelAndView);
		assertEquals("redirect:/", modelAndView.getViewName());
		verify(projectManagementService, times(1)).deleteProject(1L);
	}
}
