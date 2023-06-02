package br.com.Vendas.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Vendas;
import br.com.Vendas.util.HibernateUtil;

public class VendasDAO {
	
	public Long salvar(Vendas venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;
		
		Long codigo = null;

		try {
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(venda);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			
  		}
		finally {
			sessao.close();
		}
		return codigo;

	}
	
	public List<Vendas> listar() {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        String queryString = "SELECT venda FROM Vendas venda";
	        TypedQuery<Vendas> query = entityManager.createQuery(queryString, Vendas.class);
	        return query.getResultList();
	    } catch (RuntimeException ex) {
	        throw ex;
	    } finally {
	        entityManager.close();
	    }
	}
	
	public Vendas buscarPorCodigo(Long codigo) {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        TypedQuery<Vendas> consulta = entityManager.createNamedQuery("Vendas.buscarPorCodigo", Vendas.class);
	        consulta.setParameter("codigo", codigo);
	        return consulta.getSingleResult();
	    } catch (NoResultException ex) {
	        return null; // ou lançar uma exceção adequada
	    } finally {
	        entityManager.close();
	    }
	}
	
	public void excluir(Vendas venda) {
	    Session sessao = HibernateUtil.getSessionFactory().openSession();

	    Transaction transacao = null;

	    try {
	        transacao = sessao.beginTransaction();
	        sessao.delete(venda);
	        transacao.commit();

	    } catch (RuntimeException ex) {
	        if (transacao != null) {
	            transacao.rollback();
	        }
	    } finally {
	        sessao.close();
	    }
	}
	
	public void editar(Vendas venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(venda);
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
