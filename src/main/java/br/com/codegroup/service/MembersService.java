package br.com.codegroup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.codegroup.entities.Members;
import br.com.codegroup.repository.MembersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembersService {
	private final MembersRepository membersRepository;
	
    public Members saveMember(Members members) {
        return membersRepository.save(members);
    }
    
    public List<Members> getAllMembers() {
        return membersRepository.findAll();
    }
    
    public List<Members> getMembersByCargo(String cargo) {
        return membersRepository.findByCargoIgnoreCase(cargo);
    }
    
    public Members getMembersById(Long id) {
        return membersRepository.findById(id).orElse(null);
    }

}
