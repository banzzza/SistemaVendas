package br.com.Vendas.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.util.HibernateUtil;

public class FornecedoresDAO {

	public void salvar(Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(fornecedor);
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
	
	public List<Fornecedor> listar() {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        String queryString = "SELECT fornecedor FROM Fornecedor fornecedor";
	        TypedQuery<Fornecedor> query = entityManager.createQuery(queryString, Fornecedor.class);
	        return query.getResultList();
	    } catch (RuntimeException ex) {
	        throw ex;
	    } finally {
	        entityManager.close();
	    }
	}
	
	public Fornecedor buscarPorCodigo(Long codigo) {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        TypedQuery<Fornecedor> consulta = entityManager.createNamedQuery("Fornecedor.buscarPorCodigo", Fornecedor.class);
	        consulta.setParameter("codigo", codigo);
	        return consulta.getSingleResult();
	    } catch (NoResultException ex) {
	        return null; // ou lançar uma exceção adequada
	    } finally {
	        entityManager.close();
	    }
	}
	
	public void excluir(Fornecedor fornecedor) {
	    Session sessao = HibernateUtil.getSessionFactory().openSession();

	    Transaction transacao = null;

	    try {
	        transacao = sessao.beginTransaction();
	        sessao.delete(fornecedor);
	        transacao.commit();

	    } catch (RuntimeException ex) {
	        if (transacao != null) {
	            transacao.rollback();
	        }
	    } finally {
	        sessao.close();
	    }
	}
	
	public void editar(Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(fornecedor);
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
