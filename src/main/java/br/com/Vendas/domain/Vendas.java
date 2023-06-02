package br.com.Vendas.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_vendas")
@NamedQueries({ 
	@NamedQuery(name = "Vendas.listar", query = "SELECT venda FROM Vendas venda"),
		@NamedQuery(name = "Vendas.buscarPorCodigo", query = "SELECT venda FROM Vendas venda WHERE venda.venCodigo = :codigo") })
public class Vendas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ven_codigo")
	private Long venCodigo;

	@Column(name = "ven_horario", nullable = false)
	private Date venHorario;

	@Column(name = "ven_valor_total", nullable = false)
	private BigDecimal venValorTotal;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_funcionarios_fun_codigo", referencedColumnName = "fun_codigo", nullable = false)
	private Funcionario funcionario;

	public Long getVenCodigo() {
		return venCodigo;
	}

	public void setVenCodigo(Long venCodigo) {
		this.venCodigo = venCodigo;
	}

	public Date getVenHorario() {
		return venHorario;
	}

	public void setVenHorario(Date venHorario) {
		this.venHorario = venHorario;
	}

	public BigDecimal getVenValorTotal() {
		return venValorTotal;
	}

	public void setVenValorTotal(BigDecimal venValorTotal) {
		this.venValorTotal = venValorTotal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Vendas [venCodigo=" + venCodigo + ", venHorario=" + venHorario + ", venValorTotal=" + venValorTotal
				+ ", funcionario=" + funcionario + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(venCodigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendas other = (Vendas) obj;
		return Objects.equals(venCodigo, other.venCodigo);
	}
}
