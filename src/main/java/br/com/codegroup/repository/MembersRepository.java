package br.com.codegroup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.codegroup.entities.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {
	@Query("SELECT m FROM Members m WHERE LOWER(m.cargo) = LOWER(:cargo)")
    List<Members> findByCargoIgnoreCase(@Param("cargo") String cargo);
}
