package br.com.Vendas.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Produto;
import br.com.Vendas.util.HibernateUtil;

public class ProdutosDAO {
	
	public void salvar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(produto);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
  		}

		finally {
			sessao.close();
		}

	}
	
	public List<Produto> listar() {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        String queryString = "SELECT produto FROM Produto produto";
	        TypedQuery<Produto> query = entityManager.createQuery(queryString, Produto.class);
	        return query.getResultList();
	    } catch (RuntimeException ex) {
	        throw ex;
	    } finally {
	        entityManager.close();
	    }
	}
	
	public Produto buscarPorCodigo(Long codigo) {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        TypedQuery<Produto> consulta = entityManager.createNamedQuery("Produto.buscarPorCodigo", Produto.class);
	        consulta.setParameter("codigo", codigo);
	        return consulta.getSingleResult();
	    } catch (NoResultException ex) {
	        return null; // ou lançar uma exceção adequada
	    } finally {
	        entityManager.close();
	    }
	}
	
	public void excluir(Produto produto) {
	    Session sessao = HibernateUtil.getSessionFactory().openSession();

	    Transaction transacao = null;

	    try {
	        transacao = sessao.beginTransaction();
	        sessao.delete(produto);
	        transacao.commit();

	    } catch (RuntimeException ex) {
	        if (transacao != null) {
	            transacao.rollback();
	        }
	    } finally {
	        sessao.close();
	    }
	}
	
	public void editar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(produto);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
  		}

		finally {
			sessao.close();
		}

	}
}
