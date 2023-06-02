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
@Table(name = "tb_itens")
@NamedQueries({ 
	@NamedQuery(name = "Item.listar", query = "SELECT item FROM Item item"),
		@NamedQuery(name = "Item.buscarPorCodigo", query = "SELECT item FROM Item item WHERE item.iteCodigo = :codigo") })
public class Item {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ite_codigo")
    private Long iteCodigo;

    @Column(name = "ite_quantidade", nullable = false)
    private Integer iteQuantidade;

    @Column(name = "ite_valor_parceial", nullable = false, precision = 7, scale = 2)
    private BigDecimal iteValorParcial;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tb_produtos_pro_codigo", referencedColumnName = "pro_codigo", nullable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tb_vendas_ven_codigo", referencedColumnName = "ven_codigo", nullable = false)
    private Vendas venda;

	public Long getIteCodigo() {
		return iteCodigo;
	}

	public void setIteCodigo(Long iteCodigo) {
		this.iteCodigo = iteCodigo;
	}

	public Integer getIteQuantidade() {
		return iteQuantidade;
	}

	public void setIteQuantidade(Integer iteQuantidade) {
		this.iteQuantidade = iteQuantidade;
	}

	public BigDecimal getIteValorParcial() {
		return iteValorParcial;
	}

	public void setIteValorParcial(BigDecimal iteValorParcial) {
		this.iteValorParcial = iteValorParcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Vendas getVenda() {
		return venda;
	}

	public void setVenda(Vendas venda) {
		this.venda = venda;
	}

	@Override
	public String toString() {
		return "Item [iteCodigo=" + iteCodigo + ", iteQuantidade=" + iteQuantidade + ", iteValorParcial="
				+ iteValorParcial + ", Produto=" + produto + ", venda=" + venda + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(iteCodigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(iteCodigo, other.iteCodigo);
	}
}
