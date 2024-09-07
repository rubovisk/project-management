package br.com.codegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	    public ResponseEntity<Members> createMember(@RequestBody Members member) {
	        Members savedMember = membersService.saveMember(member);
	        return ResponseEntity.ok(savedMember);
	    }

	    @GetMapping("/all")
	    public ResponseEntity<List<Members>> getAllMembers() {
	        List<Members> members = membersService.getAllMembers();
	        return ResponseEntity.ok(members);
	    }
}
