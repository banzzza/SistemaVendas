package br.com.Vendas.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.DAO.ItensDAO;
import br.com.Vendas.DAO.ProdutosDAO;
import br.com.Vendas.DAO.VendasDAO;
import br.com.Vendas.domain.Item;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.domain.Vendas;

public class ItemDaoTest {
	
	@Test
	@Ignore
	public void salvar() {
		ProdutosDAO dao = new ProdutosDAO();
		Produto produto = dao.buscarPorCodigo(3L);	
		
		VendasDAO vendaDao = new VendasDAO();
		Vendas venda = vendaDao.buscarPorCodigo(1L);	
			
		Item i1 = new Item();
		i1.setIteQuantidade(3);
		i1.setIteValorParcial(new BigDecimal(10.00D));
		i1.setProduto(produto);
		i1.setVenda(venda);
		
		ItensDAO itensDao = new ItensDAO();
		itensDao.salvar(i1);
	}
	
	@Test
	@Ignore
	public void listar() {
		ItensDAO dao = new ItensDAO();
		List<Item> itens = dao.listar();
		
		for(Item item : itens) {
			System.out.println(item);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ItensDAO dao = new ItensDAO();
		Item f1 = dao.buscarPorCodigo(1L);
		
		System.out.println(f1);
	}
	
	@Test
	@Ignore
	public void excluir() {
		ItensDAO dao = new ItensDAO();
		
		Item item = dao.buscarPorCodigo(2L);
		
		if(item != null) {
			dao.excluir(item);
		}
		
	}
	
	@Test
	@Ignore
	public void editar() {
		ProdutosDAO dao = new ProdutosDAO();
		Produto produto = dao.buscarPorCodigo(3L);	
		
		VendasDAO vendaDao = new VendasDAO();
		Vendas venda = vendaDao.buscarPorCodigo(1L);
		
		ItensDAO itensDao = new ItensDAO();

		Item item = itensDao.buscarPorCodigo(1L);
		item.setIteQuantidade(10);
		item.setIteValorParcial(new BigDecimal(30.00D));
		item.setProduto(produto);
		item.setVenda(venda);

		if (item != null) {
			itensDao.editar(item);
		}

	}
}
