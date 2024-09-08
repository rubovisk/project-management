package br.com.codegroup.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.codegroup.entities.Members;
import br.com.codegroup.repository.MembersRepository;
import br.com.codegroup.service.MembersService;

class MembersServiceTest {

	@Mock
	private MembersRepository membersRepository;

	@InjectMocks
	private MembersService membersService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveMember() {
		Members member = new Members();
		member.setId(1L);
		when(membersRepository.save(any(Members.class))).thenReturn(member);

		Members result = membersService.saveMember(member);
		assertNotNull(result);
		assertEquals(1L, result.getId());
		verify(membersRepository, times(1)).save(member);
	}

	@Test
	void testGetAllMembers() {
		List<Members> membersList = new ArrayList<>();
		Members member1 = new Members();
		member1.setId(1L);
		Members member2 = new Members();
		member2.setId(2L);
		membersList.add(member1);
		membersList.add(member2);

		when(membersRepository.findAll()).thenReturn(membersList);

		List<Members> result = membersService.getAllMembers();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.contains(member1));
		assertTrue(result.contains(member2));
		verify(membersRepository, times(1)).findAll();
	}

	@Test
	void testGetMembersByCargo() {
		List<Members> membersList = new ArrayList<>();
		Members member = new Members();
		member.setCargo("Developer");
		membersList.add(member);

		when(membersRepository.findByCargoIgnoreCase("Funcionario")).thenReturn(membersList);

		List<Members> result = membersService.getMembersByCargo("Funcionario");
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Developer", result.get(0).getCargo());
		verify(membersRepository, times(1)).findByCargoIgnoreCase("Funcionario");
	}

	@Test
	void testGetMembersById() {
		Members member = new Members();
		member.setId(1L);

		when(membersRepository.findById(1L)).thenReturn(Optional.of(member));

		Members result = membersService.getMembersById(1L);
		assertNotNull(result);
		assertEquals(1L, result.getId());
		verify(membersRepository, times(1)).findById(1L);
	}

	@Test
	void testGetMembersByIdNotFound() {
		when(membersRepository.findById(1L)).thenReturn(Optional.empty());

		Members result = membersService.getMembersById(1L);
		assertNull(result);
		verify(membersRepository, times(1)).findById(1L);
	}
}