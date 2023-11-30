/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.controller;

import com.eysingenieria.pi.entities.OP_RegistroTemporalManatee;
import com.eysingenieria.pi.exceptions.NonexistentEntityException;
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
 * @author pi
 */
public class OP_RegistroTemporalManateeJpaController implements Serializable {

    public OP_RegistroTemporalManateeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public OP_RegistroTemporalManateeJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    public void create(OP_RegistroTemporalManatee OP_RegistroTemporalManatee) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(OP_RegistroTemporalManatee);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OP_RegistroTemporalManatee OP_RegistroTemporalManatee) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OP_RegistroTemporalManatee = em.merge(OP_RegistroTemporalManatee);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = OP_RegistroTemporalManatee.getId();
                if (findOP_RegistroTemporalManatee(id) == null) {
                    throw new NonexistentEntityException("The oP_RegistroTemporalManatee with id " + id + " no longer exists.");
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
            OP_RegistroTemporalManatee OP_RegistroTemporalManatee;
            try {
                OP_RegistroTemporalManatee = em.getReference(OP_RegistroTemporalManatee.class, id);
                OP_RegistroTemporalManatee.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The OP_RegistroTemporalManatee with id " + id + " no longer exists.", enfe);
            }
            em.remove(OP_RegistroTemporalManatee);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OP_RegistroTemporalManatee> findOP_RegistroTemporalManateeEntities() {
        return findOP_RegistroTemporalManateeEntities(true, -1, -1);
    }

    public List<OP_RegistroTemporalManatee> findOP_RegistroTemporalManateeEntities(int maxResults, int firstResult) {
        return findOP_RegistroTemporalManateeEntities(false, maxResults, firstResult);
    }

    private List<OP_RegistroTemporalManatee> findOP_RegistroTemporalManateeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OP_RegistroTemporalManatee.class));
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

    public OP_RegistroTemporalManatee findOP_RegistroTemporalManatee(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OP_RegistroTemporalManatee.class, id);
        } finally {
            em.close();
        }
    }

    public int getOP_RegistroTemporalManateeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OP_RegistroTemporalManatee> rt = cq.from(OP_RegistroTemporalManatee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
