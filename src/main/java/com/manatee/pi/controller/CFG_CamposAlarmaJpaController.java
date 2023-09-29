/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.controller.exceptions.NonexistentEntityException;
import com.manatee.pi.controller.exceptions.PreexistingEntityException;
import com.manatee.pi.entities.CFG_CamposAlarma;
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
public class CFG_CamposAlarmaJpaController implements Serializable {

    public CFG_CamposAlarmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CFG_CamposAlarmaJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CFG_CamposAlarma CFG_CamposAlarma) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CFG_CamposAlarma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCFG_CamposAlarma(CFG_CamposAlarma.getId()) != null) {
                throw new PreexistingEntityException("CFG_CamposAlarma " + CFG_CamposAlarma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CFG_CamposAlarma CFG_CamposAlarma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CFG_CamposAlarma = em.merge(CFG_CamposAlarma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = CFG_CamposAlarma.getId();
                if (findCFG_CamposAlarma(id) == null) {
                    throw new NonexistentEntityException("The cFG_CamposAlarma with id " + id + " no longer exists.");
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
            CFG_CamposAlarma CFG_CamposAlarma;
            try {
                CFG_CamposAlarma = em.getReference(CFG_CamposAlarma.class, id);
                CFG_CamposAlarma.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CFG_CamposAlarma with id " + id + " no longer exists.", enfe);
            }
            em.remove(CFG_CamposAlarma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CFG_CamposAlarma> findCFG_CamposAlarmaEntities() {
        return findCFG_CamposAlarmaEntities(true, -1, -1);
    }

    public List<CFG_CamposAlarma> findCFG_CamposAlarmaEntities(int maxResults, int firstResult) {
        return findCFG_CamposAlarmaEntities(false, maxResults, firstResult);
    }

    private List<CFG_CamposAlarma> findCFG_CamposAlarmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CFG_CamposAlarma.class));
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

    public CFG_CamposAlarma findCFG_CamposAlarma(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CFG_CamposAlarma.class, id);
        } finally {
            em.close();
        }
    }

    public int getCFG_CamposAlarmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CFG_CamposAlarma> rt = cq.from(CFG_CamposAlarma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
