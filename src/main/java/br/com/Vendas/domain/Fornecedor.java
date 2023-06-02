package br.com.Vendas.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;




@Entity
@Table(name = "tb_fornecedores")
@NamedQueries({ 
	@NamedQuery(name = "Fornecedor.listar", query = "SELECT fornecedor FROM Fornecedor fornecedor"),
		@NamedQuery(name = "Fornecedor.buscarPorCodigo", query = "SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.forCodigo = :codigo") })
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "for_codigo")
	private Long forCodigo;

	@Column(name = "for_descricao", nullable = false)
	private String forDescricao;
	
	@Column(name = "for_cnpj", nullable = false, unique = true)
	private String forCnpj;

	public Long getCodigo() {
		return forCodigo;
	}

	public void setCodigo(Long codigo) {
		this.forCodigo = codigo;
	}

	public String getDescricao() {
		return forDescricao;
	}

	public void setDescricao(String descricao) {
		this.forDescricao = descricao;
	}
	
	public String getCnpj() {
		return forCnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.forCnpj = cnpj;
	}

	@Override
	public String toString() {
		return forDescricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(forCodigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(forCodigo, other.forCodigo);
	}
}
