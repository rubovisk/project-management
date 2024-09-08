package br.com.codegroup.controller.test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.codegroup.controller.MembersRestController;
import br.com.codegroup.dto.MemberDTO;
import br.com.codegroup.entities.Members;
import br.com.codegroup.service.MembersService;
import com.fasterxml.jackson.databind.ObjectMapper;

class MembersRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MembersService membersService;

    @InjectMocks
    private MembersRestController membersRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(membersRestController).build();
    }

    @Test
    void testCreateMember() throws Exception {
        MemberDTO memberDTO = new MemberDTO(null, "Rubens Daniel", "Desenvolvedor");
        Members savedMember = new Members();
        savedMember.setId(1L);
        savedMember.setNome("Rubens Daniel");
        savedMember.setCargo("Desenvolvedor");
        
        when(membersService.saveMember(any(Members.class))).thenReturn(savedMember);
        
        mockMvc.perform(post("/api/members/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(memberDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Rubens Daniel"))
                .andExpect(jsonPath("$.cargo").value("Desenvolvedor"));

        verify(membersService, times(1)).saveMember(any(Members.class));
    }

    @Test
    void testGetAllMembers() throws Exception {
        Members member1 = new Members();
        Members member2 = new Members();
        member1.setId(1L);
        member1.setNome("Rubens Daniel");
        member1.setCargo("Desenvolvedor");
        member2.setId(2L);
        member2.setNome("Fernanda Messias");
        member2.setCargo("Gerente");
        List<Members> members = List.of(member1, member2);

        when(membersService.getAllMembers()).thenReturn(members);

        mockMvc.perform(get("/api/members/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Rubens Daniel"))
                .andExpect(jsonPath("$[0].cargo").value("Desenvolvedor"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Fernanda Messias"))
                .andExpect(jsonPath("$[1].cargo").value("Gerente"));

        verify(membersService, times(1)).getAllMembers();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}