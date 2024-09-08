package br.com.codegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codegroup.dto.MemberDTO;
import br.com.codegroup.entities.Members;
import br.com.codegroup.service.MembersService;

@RestController
@RequestMapping("/api/members")
public class MembersRestController {
	
	    private final MembersService membersService;
	    @Autowired
	    public MembersRestController(MembersService membersService) {
	        this.membersService = membersService;
	    }
	    
	    @PostMapping("/create")
	    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
	        Members member = new Members();
	        member.setNome(memberDTO.getNome());
	        member.setCargo(memberDTO.getCargo());
	        
	        Members savedMember = membersService.saveMember(member);
	        MemberDTO savedMemberDTO = new MemberDTO(savedMember.getId(), savedMember.getNome(), savedMember.getCargo());
	        
	        return ResponseEntity.ok(savedMemberDTO);
	    }

	    @GetMapping("/all")
	    public ResponseEntity<List<MemberDTO>> getAllMembers() {
	        List<Members> members = membersService.getAllMembers();
	        List<MemberDTO> memberDTOs = members.stream()
	            .map(m -> new MemberDTO(m.getId(), m.getNome(), m.getCargo()))
	            .toList();
	        
	        return ResponseEntity.ok(memberDTOs);
	    }
}
