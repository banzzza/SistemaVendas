package br.com.Vendas.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.DAO.FornecedoresDAO;
import br.com.Vendas.DAO.ProdutosDAO;
import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.domain.Produto;

public class ProdutoDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		FornecedoresDAO dao = new FornecedoresDAO();
		Fornecedor fornecedor = dao.buscarPorCodigo(1L);	
			
		Produto f1 = new Produto();
		f1.setProDescricao("Café");
		f1.setProValor(new BigDecimal(15.99D));
		f1.setProQuantidade(6);
		f1.setFornecedor(fornecedor);
		
		ProdutosDAO produtosDao = new ProdutosDAO();
		produtosDao.salvar(f1);
	}
	
	@Test
	@Ignore
	public void listar() {
		ProdutosDAO dao = new ProdutosDAO();
		List<Produto> produtos = dao.listar();
		
		for(Produto produto : produtos) {
			System.out.println(produto);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ProdutosDAO dao = new ProdutosDAO();
		Produto f1 = dao.buscarPorCodigo(1L);
		
		System.out.println(f1);
	}
	
	@Test
	@Ignore
	public void excluir() {
		ProdutosDAO dao = new ProdutosDAO();
		
		Produto produto = dao.buscarPorCodigo(2L);
		
		if(produto != null) {
			dao.excluir(produto);
		}
		
	}
	
	@Test
	@Ignore
	public void editar() {
		FornecedoresDAO dao = new FornecedoresDAO();
		Fornecedor fornecedor = dao.buscarPorCodigo(4L);	
		
		ProdutosDAO produtosDAO = new ProdutosDAO();

		Produto produto = produtosDAO.buscarPorCodigo(3L);
		produto.setProDescricao("Café Extra");
		produto.setProValor(new BigDecimal(15.99D));
		produto.setProQuantidade(2);
		produto.setFornecedor(fornecedor);

		if (produto != null) {
			produtosDAO.editar(produto);
		}

	}
}
