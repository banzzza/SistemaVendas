package br.com.Vendas.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.DAO.ItensDAO;
import br.com.Vendas.DAO.ProdutosDAO;
import br.com.Vendas.DAO.VendasDAO;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.domain.Item;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.domain.Vendas;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBVendas")
@ViewScoped
public class VendasBean {
	
	private Produto produto;
	private Vendas vendaCadastro;
	private ArrayList<Item> itens;
	private ArrayList<Item> itensFiltrados;
	
	private ArrayList<Produto> produtos;
	private ArrayList<Produto> produtosFiltrados;
	
	private ArrayList<Vendas> itensVendas;
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Vendas getVendaCadastro() {
		if(vendaCadastro == null) {
			vendaCadastro = new Vendas();
			vendaCadastro.setVenValorTotal(new BigDecimal("0.00"));
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Vendas vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public ArrayList<Item> getItens() {
		if (itens == null) {
			itens = new ArrayList<>();
		}
		return itens;
	}
	
	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Item> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Item> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(ArrayList<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	public ArrayList<Vendas> getItensVendas() {
		return itensVendas;
	}

	public void setItensVendas(ArrayList<Vendas> itensVendas) {
		this.itensVendas = itensVendas;
	}

	@PostConstruct
	public void carregarProdutos() {
		
		try {
			ProdutosDAO fdao = new ProdutosDAO();
			produtos = (ArrayList<Produto>) fdao.listar();
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
		
	}
	
	public void adicionar(Produto produto) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
			Item itemTemp = itens.get(pos);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}
		
		Item item = new Item();
		item.setProduto(produto);
		
		if (posicaoEncontrada < 0) {
			item.setIteQuantidade(1);
			item.setIteValorParcial(produto.getProValor());
			itens.add(item);
		} else {
			Item itemTemp = itens.get(posicaoEncontrada);
			item.setIteQuantidade(itemTemp.getIteQuantidade() + 1);
			item.setIteValorParcial(produto.getProValor().multiply(new BigDecimal(item.getIteQuantidade())));
			itens.set(posicaoEncontrada, item);
		}
		
		vendaCadastro.setVenValorTotal(vendaCadastro.getVenValorTotal().add(produto.getProValor()));

	}
	
	public void remover(Item item) {
		
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
			Item itemTemp = itens.get(pos);

			if (itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;
			}
		}
		
		if (posicaoEncontrada > -1) {
			itens.remove(posicaoEncontrada);
			vendaCadastro.setVenValorTotal(vendaCadastro.getVenValorTotal().subtract(item.getIteValorParcial()));
		}
		
	}
	
	public void carregarDadosVenda() {
		vendaCadastro.setVenHorario(new Date());
		
		FuncionariosDAO dao = new FuncionariosDAO();
		Funcionario funcionario = dao.buscarPorCodigo(1L);
		vendaCadastro.setFuncionario(funcionario);
	}
	
	public void salvar() {
		
		try {
			
			VendasDAO vdao = new VendasDAO();
			Long codigoVenda = vdao.salvar(vendaCadastro);
			
			ProdutosDAO pdao = new ProdutosDAO();
			Vendas vendaFK = vdao.buscarPorCodigo(codigoVenda);
			
			for(Item item :itens) {
				item.setVenda(vendaFK);
				ItensDAO itemdao = new ItensDAO();
				itemdao.salvar(item);
				
				Produto produto = item.getProduto();
			    int quantidadeVendida = item.getIteQuantidade();

			    produto.setProQuantidade(produto.getProQuantidade() - quantidadeVendida);
			    pdao.editar(produto);
			}
			
			vendaCadastro = new Vendas();
			vendaCadastro.setVenValorTotal(new BigDecimal("0.00"));
			itens = new ArrayList<Item>();
			
			JSFUtil.adicionarMensagemSucesso("Venda registrada com sucesso!");
		} catch(RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void prepararPesquisa() {
		
		try {
			VendasDAO fdao = new VendasDAO();
			itensVendas = (ArrayList<Vendas>) fdao.listar();
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
		
	}

}
