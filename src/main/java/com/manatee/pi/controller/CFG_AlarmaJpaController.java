/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.controller.exceptions.NonexistentEntityException;
import com.manatee.pi.entities.CFG_Alarma;
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
public class CFG_AlarmaJpaController implements Serializable {

    public CFG_AlarmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CFG_AlarmaJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CFG_Alarma CFG_Alarma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CFG_Alarma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CFG_Alarma CFG_Alarma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CFG_Alarma = em.merge(CFG_Alarma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = CFG_Alarma.getId();
                if (findCFG_Alarma(id) == null) {
                    throw new NonexistentEntityException("The cFG_Alarma with id " + id + " no longer exists.");
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
            CFG_Alarma CFG_Alarma;
            try {
                CFG_Alarma = em.getReference(CFG_Alarma.class, id);
                CFG_Alarma.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CFG_Alarma with id " + id + " no longer exists.", enfe);
            }
            em.remove(CFG_Alarma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CFG_Alarma> findCFG_AlarmaEntities() {
        return findCFG_AlarmaEntities(true, -1, -1);
    }

    public List<CFG_Alarma> findCFG_AlarmaEntities(int maxResults, int firstResult) {
        return findCFG_AlarmaEntities(false, maxResults, firstResult);
    }

    private List<CFG_Alarma> findCFG_AlarmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CFG_Alarma.class));
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

    public CFG_Alarma findCFG_Alarma(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CFG_Alarma.class, id);
        } finally {
            em.close();
        }
    }

    public int getCFG_AlarmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CFG_Alarma> rt = cq.from(CFG_Alarma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
