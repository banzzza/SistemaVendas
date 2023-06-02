package br.com.Vendas.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.domain.Funcionario;

public class FuncionarioDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		Funcionario f1 = new Funcionario();
		f1.setNome("Laura Zin");
		f1.setCpf("13332312312");
		f1.setSenha("123456");
		f1.setFuncao("BlaBlaBla");
		
		FuncionariosDAO dao = new FuncionariosDAO();
		dao.salvar(f1);
	}
	
	@Test
	@Ignore
	public void listar() {
		FuncionariosDAO dao = new FuncionariosDAO();
		List<Funcionario> funcionarios = dao.listar();
		
		for(Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		FuncionariosDAO dao = new FuncionariosDAO();
		Funcionario f1 = dao.buscarPorCodigo(1L);
		
		System.out.println(f1);
	}
	
	@Test
	@Ignore
	public void excluir() {
		FuncionariosDAO dao = new FuncionariosDAO();
		
		Funcionario funcionario = dao.buscarPorCodigo(3L);
		
		if(funcionario != null) {
			dao.excluir(funcionario);
		}
		
	}
	
	@Test
	@Ignore
	public void editar() {
		FuncionariosDAO dao = new FuncionariosDAO();

		Funcionario funcionario = dao.buscarPorCodigo(2L);
		funcionario.setNome("Editando Teste");

		if (funcionario != null) {
			dao.editar(funcionario);
		}

	}
}
