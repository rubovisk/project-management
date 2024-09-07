package br.com.codegroup.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project_management")
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
		
		@Column(name = "risk", length = 200, nullable = true)
	    private String risk;
	    
	    public Double getOrcamentoTotal() {
			return orcamentoTotal;
		}

		public String getRisk() {
			return risk;
		}

		public void setRisk(String risk) {
			this.risk = risk;
		}

		public void setOrcamentoTotal(Double orcamentoTotal) {
			this.orcamentoTotal = orcamentoTotal;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

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

		public LocalDate getDtInicio() {
			return dtInicio;
		}

		public void setDtInicio(LocalDate dtInicio) {
			this.dtInicio = dtInicio;
		}

		public String getGerenteResponsavel() {
			return gerenteResponsavel;
		}

		public void setGerenteResponsavel(String gerenteResponsavel) {
			this.gerenteResponsavel = gerenteResponsavel;
		}

		public LocalDate getDtPrvTermino() {
			return dtPrvTermino;
		}

		public void setDtPrvTermino(LocalDate dtPrvTermino) {
			this.dtPrvTermino = dtPrvTermino;
		}

		public LocalDate getDtRealTermino() {
			return dtRealTermino;
		}

		public void setDtRealTermino(LocalDate dtRealTermino) {
			this.dtRealTermino = dtRealTermino;
		}
	    
	    
}
