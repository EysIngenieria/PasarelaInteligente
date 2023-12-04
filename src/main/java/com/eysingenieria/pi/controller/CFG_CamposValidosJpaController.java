/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.controller;

import com.eysingenieria.pi.controller.exceptions.NonexistentEntityException;
import com.eysingenieria.pi.entities.CFG_CamposValidos;
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
public class CFG_CamposValidosJpaController implements Serializable {

    public CFG_CamposValidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CFG_CamposValidosJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CFG_CamposValidos CFG_CamposValidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CFG_CamposValidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CFG_CamposValidos CFG_CamposValidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CFG_CamposValidos = em.merge(CFG_CamposValidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = CFG_CamposValidos.getId();
                if (findCFG_CamposValidos(id) == null) {
                    throw new NonexistentEntityException("The cFG_CamposValidos with id " + id + " no longer exists.");
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
            CFG_CamposValidos CFG_CamposValidos;
            try {
                CFG_CamposValidos = em.getReference(CFG_CamposValidos.class, id);
                CFG_CamposValidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CFG_CamposValidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(CFG_CamposValidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CFG_CamposValidos> findCFG_CamposValidosEntities() {
        return findCFG_CamposValidosEntities(true, -1, -1);
    }

    public List<CFG_CamposValidos> findCFG_CamposValidosEntities(int maxResults, int firstResult) {
        return findCFG_CamposValidosEntities(false, maxResults, firstResult);
    }

    private List<CFG_CamposValidos> findCFG_CamposValidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CFG_CamposValidos.class));
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

    public CFG_CamposValidos findCFG_CamposValidos(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CFG_CamposValidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCFG_CamposValidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CFG_CamposValidos> rt = cq.from(CFG_CamposValidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
