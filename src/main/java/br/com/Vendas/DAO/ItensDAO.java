package br.com.Vendas.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Item;
import br.com.Vendas.util.HibernateUtil;

public class ItensDAO {

	public void salvar(Item item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(item);
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
	
	public List<Item> listar() {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        String queryString = "SELECT item FROM Item item";
	        TypedQuery<Item> query = entityManager.createQuery(queryString, Item.class);
	        return query.getResultList();
	    } catch (RuntimeException ex) {
	        throw ex;
	    } finally {
	        entityManager.close();
	    }
	}
	
	public Item buscarPorCodigo(Long codigo) {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        TypedQuery<Item> consulta = entityManager.createNamedQuery("Item.buscarPorCodigo", Item.class);
	        consulta.setParameter("codigo", codigo);
	        return consulta.getSingleResult();
	    } catch (NoResultException ex) {
	        return null; // ou lançar uma exceção adequada
	    } finally {
	        entityManager.close();
	    }
	}
	
	public void excluir(Item item) {
	    Session sessao = HibernateUtil.getSessionFactory().openSession();

	    Transaction transacao = null;

	    try {
	        transacao = sessao.beginTransaction();
	        sessao.delete(item);
	        transacao.commit();

	    } catch (RuntimeException ex) {
	        if (transacao != null) {
	            transacao.rollback();
	        }
	    } finally {
	        sessao.close();
	    }
	}
	
	public void editar(Item item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(item);
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
