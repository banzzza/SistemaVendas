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
@Table(name = "tb_funcionarios")
@NamedQueries({ 
	@NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario FROM Funcionario funcionario"),
		@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.funCodigo = :codigo") })
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fun_codigo")
	private Long funCodigo;

	@Column(name = "fun_nome", nullable = false)
	private String funNome;

	@Column(name = "fun_cpf", nullable = false, unique = true)
	private String funCpf;

	@Column(name = "fun_senha", nullable = false)
	private String funSenha;

	@Column(name = "fun_funcao", nullable = false)
	private String funFuncao;

	public Long getCodigo() {
		return funCodigo;
	}

	public void setCodigo(Long codigo) {
		this.funCodigo = codigo;
	}

	public String getNome() {
		return funNome;
	}

	public void setNome(String nome) {
		this.funNome = nome;
	}

	public String getCpf() {
		return funCpf;
	}

	public void setCpf(String cpf) {
		this.funCpf = cpf;
	}

	public String getSenha() {
		return funSenha;
	}

	public void setSenha(String senha) {
		this.funSenha = senha;
	}

	public String getFuncao() {
		return funFuncao;
	}

	public void setFuncao(String funcao) {
		this.funFuncao = funcao;
	}

	@Override
	public String toString() {
		return "Funcionario [funCodigo=" + funCodigo + ", funNome=" + funNome + ", funCpf=" + funCpf + ", funSenha="
				+ funSenha + ", funFuncao=" + funFuncao + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(funCodigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return Objects.equals(funCodigo, other.funCodigo);
	}
	
}
