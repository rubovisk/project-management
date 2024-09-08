package br.com.codegroup.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="project_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProjectManagement {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "nome", length = 500, nullable = false)
	    private String nome;

	    @Column(name = "dt_inicio", nullable = false)
	    private LocalDate dtInicio;

	    @Column(name = "gerente_responsavel", length = 200, nullable = false)
	    private String gerenteResponsavel;

	    @Column(name = "dt_prv_termino", nullable = false)
	    private LocalDate dtPrvTermino;

	    @Column(name = "dt_real_termino", nullable = true)
	    private LocalDate dtRealTermino;
	    
	    @Column(name="orcamento_total", nullable = true)
	    private Double orcamentoTotal;
	    
	    @Column(name = "descricao", length = 500, nullable = true)
	    private String descricao;
	    
		@Column(name = "status", length = 200, nullable = true)
	    private String status;
		
		@Column(name = "risco", length = 200, nullable = true)
	    private String risco;
		
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "member_id", nullable = true)
	    private Members member;
}
