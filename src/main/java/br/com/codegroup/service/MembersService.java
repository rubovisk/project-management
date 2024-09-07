package br.com.codegroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codegroup.entities.Members;
import br.com.codegroup.repository.MembersRepository;

@Service
public class MembersService {
	private final MembersRepository membersRepository;
	
	@Autowired
	public MembersService(MembersRepository membersRepository) {
		this.membersRepository = membersRepository;
	}
	
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
