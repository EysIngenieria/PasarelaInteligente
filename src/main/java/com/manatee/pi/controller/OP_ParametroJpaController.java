/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.controller.exceptions.NonexistentEntityException;
import com.manatee.pi.entities.OP_Parametro;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author DesarrolloJC
 */
public class OP_ParametroJpaController implements Serializable {

    public OP_ParametroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public OP_ParametroJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OP_Parametro OP_Parametro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(OP_Parametro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OP_Parametro OP_Parametro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OP_Parametro = em.merge(OP_Parametro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = OP_Parametro.getId();
                if (findOP_Parametro(id) == null) {
                    throw new NonexistentEntityException("The oP_Parametro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OP_Parametro OP_Parametro;
            try {
                OP_Parametro = em.getReference(OP_Parametro.class, id);
                OP_Parametro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The OP_Parametro with id " + id + " no longer exists.", enfe);
            }
            em.remove(OP_Parametro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OP_Parametro> findOP_ParametroEntities() {
        return findOP_ParametroEntities(true, -1, -1);
    }

    public List<OP_Parametro> findOP_ParametroEntities(int maxResults, int firstResult) {
        return findOP_ParametroEntities(false, maxResults, firstResult);
    }

    private List<OP_Parametro> findOP_ParametroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OP_Parametro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OP_Parametro findOP_Parametro(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OP_Parametro.class, id);
        } finally {
            em.close();
        }
    }

    public int getOP_ParametroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OP_Parametro> rt = cq.from(OP_Parametro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
