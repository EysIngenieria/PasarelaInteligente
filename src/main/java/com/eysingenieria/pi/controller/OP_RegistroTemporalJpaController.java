/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.controller;

import com.eysingenieria.pi.controller.exceptions.NonexistentEntityException;
import com.eysingenieria.pi.entities.OP_RegistroTemporal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author DesarrolloJC
 */
public class OP_RegistroTemporalJpaController implements Serializable {

    public OP_RegistroTemporalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public OP_RegistroTemporalJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OP_RegistroTemporal OP_RegistroTemporal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(OP_RegistroTemporal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OP_RegistroTemporal OP_RegistroTemporal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OP_RegistroTemporal = em.merge(OP_RegistroTemporal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = OP_RegistroTemporal.getId();
                if (findOP_RegistroTemporal(id) == null) {
                    throw new NonexistentEntityException("The oP_RegistroTemporal with id " + id + " no longer exists.");
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
            OP_RegistroTemporal OP_RegistroTemporal;
            try {
                OP_RegistroTemporal = em.getReference(OP_RegistroTemporal.class, id);
                OP_RegistroTemporal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The OP_RegistroTemporal with id " + id + " no longer exists.", enfe);
            }
            em.remove(OP_RegistroTemporal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OP_RegistroTemporal> findOP_RegistroTemporalEntities() {
        return findOP_RegistroTemporalEntities(false, 100, 0);
    }

    public List<OP_RegistroTemporal> findOP_RegistroTemporalEntities(int maxResults, int firstResult) {
        return findOP_RegistroTemporalEntities(false, maxResults, firstResult);
    }

    private List<OP_RegistroTemporal> findOP_RegistroTemporalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OP_RegistroTemporal.class));
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

    public OP_RegistroTemporal findOP_RegistroTemporal(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OP_RegistroTemporal.class, id);
        } finally {
            em.close();
        }
    }
    public List<OP_RegistroTemporal> findOP_RegistroTemporalEntitiesByManatee() {
        return findOP_RegistroTemporalEntitiesByEnvioManatee(false, 100, 0);
    }
    private List<OP_RegistroTemporal> findOP_RegistroTemporalEntitiesByEnvioManatee(boolean envioManatee, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<OP_RegistroTemporal> cq = cb.createQuery(OP_RegistroTemporal.class);
            Root<OP_RegistroTemporal> root = cq.from(OP_RegistroTemporal.class);
            cq.where(cb.equal(root.get("estadoEnvioManatee"), envioManatee));
            Query q = em.createQuery(cq);
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public List<OP_RegistroTemporal> findOP_RegistroTemporalEntitiesByCDEG() {
        return findOP_RegistroTemporalEntitiesByCDEG(false, 100, 0);
    }
    private List<OP_RegistroTemporal> findOP_RegistroTemporalEntitiesByCDEG(boolean envio, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<OP_RegistroTemporal> cq = cb.createQuery(OP_RegistroTemporal.class);
            Root<OP_RegistroTemporal> root = cq.from(OP_RegistroTemporal.class);
            cq.where(cb.equal(root.get("estadoEnvio"), envio));
            Query q = em.createQuery(cq);
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
            return q.getResultList();
        } finally {
            em.close();
        }
    }


    public int getOP_RegistroTemporalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OP_RegistroTemporal> rt = cq.from(OP_RegistroTemporal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
