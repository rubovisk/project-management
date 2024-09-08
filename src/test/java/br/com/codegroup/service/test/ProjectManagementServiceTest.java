package br.com.codegroup.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.codegroup.entities.ProjectManagement;
import br.com.codegroup.repository.ProjectManagementRepository;
import br.com.codegroup.service.ProjectManagementService;

@ExtendWith(MockitoExtension.class)
class ProjectManagementServiceTest {

	@Mock
	private ProjectManagementRepository projectManagementRepository;

	@InjectMocks
	private ProjectManagementService projectManagementService;

	private ProjectManagement project;

	@BeforeEach
	void setUp() {
		project = new ProjectManagement();
		project.setId(1L);
		project.setNome("Teste");
	}

	@Test
	void testSaveProject() {
		when(projectManagementRepository.save(project)).thenReturn(project);

		ProjectManagement savedProject = projectManagementService.saveProject(project);

		assertEquals(project, savedProject);
	}

	@Test
	void testGetAllProjects() {
		when(projectManagementRepository.findAll()).thenReturn(Arrays.asList(project));

		var projects = projectManagementService.getAllProjects();

		assertEquals(1, projects.size());
		assertEquals(project, projects.get(0));
	}

	@Test
	void testGetProjectById() {
		when(projectManagementRepository.findById(1L)).thenReturn(Optional.of(project));

		ProjectManagement foundProject = projectManagementService.getProjectById(1L);

		assertEquals(project, foundProject);
	}

	@Test
	void testGetProjectById_NotFound() {
		when(projectManagementRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> projectManagementService.getProjectById(1L));
	}

	@Test
	void testDeleteProject() {
		projectManagementService.deleteProject(1L);

		verify(projectManagementRepository, times(1)).deleteById(1L);
	}
}
