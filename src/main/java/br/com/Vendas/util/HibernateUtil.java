package br.com.Vendas.util;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.domain.Item;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.domain.Vendas;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            Metadata metadata = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(Fornecedor.class)
                    .addAnnotatedClass(Funcionario.class)
                    .addAnnotatedClass(Produto.class)
                    .addAnnotatedClass(Vendas.class)
                    .addAnnotatedClass(Item.class)
                    .getMetadataBuilder()
                    .build();

           // SchemaValidator validator = new SchemaValidator();
           // validator.validate(metadata);

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            // Mensagem de erro ao conectar
            System.err.println("Erro na conexão." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}