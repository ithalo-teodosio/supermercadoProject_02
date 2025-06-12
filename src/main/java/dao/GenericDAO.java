package dao;

import javax.persistence.*;
import java.util.List;

public class GenericDAO<T> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SupermercadoJPA");
    protected EntityManager em = emf.createEntityManager();
    private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void salvar(T entidade) {
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
    }

    public T buscar(Long id) {
        return em.find(entityClass, id);
    }

    public List<T> listar() {
        return em.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    public void remover(T entidade) {
        em.getTransaction().begin();
        em.remove(em.contains(entidade) ? entidade : em.merge(entidade));
        em.getTransaction().commit();
    }

    public void fechar() {
        em.close();
    }
}