package br.com.codegroup.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="members")
public class Members {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "nome", length = 500, nullable = false)
	private String nome;
	@Column(name = "cargo", length = 500, nullable = false)
	private String cargo;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<ProjectManagement> projects;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public Set<ProjectManagement> getProjects() {
	    return projects;
	}

	public void setProjects(Set<ProjectManagement> projects) {
	    this.projects = projects;
	}
}
