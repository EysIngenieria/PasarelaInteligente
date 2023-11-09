/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.controller.exceptions.NonexistentEntityException;
import com.manatee.pi.controller.exceptions.PreexistingEntityException;
import com.manatee.pi.entities.CFG_NivelAlarma;
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
public class CFG_NivelAlarmaJpaController implements Serializable {

    public CFG_NivelAlarmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CFG_NivelAlarmaJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CFG_NivelAlarma CFG_NivelAlarma) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CFG_NivelAlarma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCFG_NivelAlarma(CFG_NivelAlarma.getId()) != null) {
                throw new PreexistingEntityException("CFG_NivelAlarma " + CFG_NivelAlarma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CFG_NivelAlarma CFG_NivelAlarma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CFG_NivelAlarma = em.merge(CFG_NivelAlarma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = CFG_NivelAlarma.getId();
                if (findCFG_NivelAlarma(id) == null) {
                    throw new NonexistentEntityException("The cFG_NivelAlarma with id " + id + " no longer exists.");
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
            CFG_NivelAlarma CFG_NivelAlarma;
            try {
                CFG_NivelAlarma = em.getReference(CFG_NivelAlarma.class, id);
                CFG_NivelAlarma.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CFG_NivelAlarma with id " + id + " no longer exists.", enfe);
            }
            em.remove(CFG_NivelAlarma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CFG_NivelAlarma> findCFG_NivelAlarmaEntities() {
        return findCFG_NivelAlarmaEntities(true, -1, -1);
    }

    public List<CFG_NivelAlarma> findCFG_NivelAlarmaEntities(int maxResults, int firstResult) {
        return findCFG_NivelAlarmaEntities(false, maxResults, firstResult);
    }

    private List<CFG_NivelAlarma> findCFG_NivelAlarmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CFG_NivelAlarma.class));
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

    public CFG_NivelAlarma findCFG_NivelAlarma(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CFG_NivelAlarma.class, id);
        } finally {
            em.close();
        }
    }

    public int getCFG_NivelAlarmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CFG_NivelAlarma> rt = cq.from(CFG_NivelAlarma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
