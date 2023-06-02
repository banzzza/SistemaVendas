package br.com.Vendas.domain;

import java.math.BigDecimal;
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
@Table(name = "tb_produtos")
@NamedQueries({ 
	@NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"),
		@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE produto.proCodigo = :codigo") })
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_codigo")
	private Long proCodigo;

	@Column(name = "pro_descricao", nullable = false)
	private String proDescricao;

	@Column(name = "pro_valor", nullable = false)
	private BigDecimal proValor;

	@Column(name = "pro_quantidade", nullable = false)
	private int proQuantidade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_fornecedores_for_codigo", referencedColumnName = "for_codigo", nullable = false)
	private Fornecedor fornecedor;

	public Long getProCodigo() {
		return proCodigo;
	}

	public void setProCodigo(Long proCodigo) {
		this.proCodigo = proCodigo;
	}

	public String getProDescricao() {
		return proDescricao;
	}

	public void setProDescricao(String proDescricao) {
		this.proDescricao = proDescricao;
	}

	public BigDecimal getProValor() {
		return proValor;
	}

	public void setProValor(BigDecimal proValor) {
		this.proValor = proValor;
	}

	public int getProQuantidade() {
		return proQuantidade;
	}

	public void setProQuantidade(int proQuantidade) {
		this.proQuantidade = proQuantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "Produto [proCodigo=" + proCodigo + ", proDescricao=" + proDescricao + ", proValor=" + proValor
				+ ", proQuantidade=" + proQuantidade + ", fornecedor=" + fornecedor + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(proCodigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(proCodigo, other.proCodigo);
	}
}
