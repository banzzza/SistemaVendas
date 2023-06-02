package br.com.Vendas.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.util.HibernateUtil;

public class FuncionariosDAO {
	
	public void salvar(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
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
	
	public List<Funcionario> listar() {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        String queryString = "SELECT funcionario FROM Funcionario funcionario";
	        TypedQuery<Funcionario> query = entityManager.createQuery(queryString, Funcionario.class);
	        return query.getResultList();
	    } catch (RuntimeException ex) {
	        throw ex;
	    } finally {
	        entityManager.close();
	    }
	}
	
	public Funcionario buscarPorCodigo(Long codigo) {
	    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
	    try {
	        TypedQuery<Funcionario> consulta = entityManager.createNamedQuery("Funcionario.buscarPorCodigo", Funcionario.class);
	        consulta.setParameter("codigo", codigo);
	        return consulta.getSingleResult();
	    } catch (NoResultException ex) {
	        return null; // ou lançar uma exceção adequada
	    } finally {
	        entityManager.close();
	    }
	}
	
	public void excluir(Funcionario funcionario) {
	    Session sessao = HibernateUtil.getSessionFactory().openSession();

	    Transaction transacao = null;

	    try {
	        transacao = sessao.beginTransaction();
	        sessao.delete(funcionario);
	        transacao.commit();

	    } catch (RuntimeException ex) {
	        if (transacao != null) {
	            transacao.rollback();
	        }
	    } finally {
	        sessao.close();
	    }
	}
	
	public void editar(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(funcionario);
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
