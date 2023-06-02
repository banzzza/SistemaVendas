package br.com.Vendas.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.DAO.VendasDAO;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.domain.Vendas;

public class VendasDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		FuncionariosDAO dao = new FuncionariosDAO();
		Funcionario funcionario = dao.buscarPorCodigo(4L);	
			
		Vendas v1 = new Vendas();
		v1.setVenHorario(new Date());
		v1.setVenValorTotal(new BigDecimal(25.00D));
		v1.setFuncionario(funcionario);
		
		VendasDAO vendasDao = new VendasDAO();
		vendasDao.salvar(v1);
	}
	
	@Test
	@Ignore
	public void listar() {
		VendasDAO dao = new VendasDAO();
		List<Vendas> vendas = dao.listar();
		
		for(Vendas venda: vendas) {
			System.out.println(venda);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		VendasDAO dao = new VendasDAO();
		Vendas f1 = dao.buscarPorCodigo(1L);
		
		System.out.println(f1);
	}
	
	@Test
	@Ignore
	public void excluir() {
		VendasDAO dao = new VendasDAO();
		
		Vendas venda = dao.buscarPorCodigo(2L);
		
		if(venda != null) {
			dao.excluir(venda);
		}
		
	}
	
	@Test
	@Ignore
   	public void editar() {
		FuncionariosDAO dao = new FuncionariosDAO();
		Funcionario funcionario = dao.buscarPorCodigo(4L);	
		
		VendasDAO vendasDao = new VendasDAO();
		
		Vendas venda = vendasDao.buscarPorCodigo(1L);
		venda.setVenHorario(new Date());
		venda.setVenValorTotal(new BigDecimal(20.00D));
		venda.setFuncionario(funcionario);

		if (venda != null) {
			vendasDao.editar(venda);
		}

	}
}
