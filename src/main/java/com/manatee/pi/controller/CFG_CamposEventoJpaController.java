/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.controller.exceptions.NonexistentEntityException;
import com.manatee.pi.controller.exceptions.PreexistingEntityException;
import com.manatee.pi.entities.CFG_CamposEvento;
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
public class CFG_CamposEventoJpaController implements Serializable {

    public CFG_CamposEventoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CFG_CamposEventoJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CFG_CamposEvento CFG_CamposEvento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CFG_CamposEvento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCFG_CamposEvento(CFG_CamposEvento.getId()) != null) {
                throw new PreexistingEntityException("CFG_CamposEvento " + CFG_CamposEvento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CFG_CamposEvento CFG_CamposEvento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CFG_CamposEvento = em.merge(CFG_CamposEvento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = CFG_CamposEvento.getId();
                if (findCFG_CamposEvento(id) == null) {
                    throw new NonexistentEntityException("The cFG_CamposEvento with id " + id + " no longer exists.");
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
            CFG_CamposEvento CFG_CamposEvento;
            try {
                CFG_CamposEvento = em.getReference(CFG_CamposEvento.class, id);
                CFG_CamposEvento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CFG_CamposEvento with id " + id + " no longer exists.", enfe);
            }
            em.remove(CFG_CamposEvento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CFG_CamposEvento> findCFG_CamposEventoEntities() {
        return findCFG_CamposEventoEntities(true, -1, -1);
    }

    public List<CFG_CamposEvento> findCFG_CamposEventoEntities(int maxResults, int firstResult) {
        return findCFG_CamposEventoEntities(false, maxResults, firstResult);
    }

    private List<CFG_CamposEvento> findCFG_CamposEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CFG_CamposEvento.class));
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

    public CFG_CamposEvento findCFG_CamposEvento(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CFG_CamposEvento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCFG_CamposEventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CFG_CamposEvento> rt = cq.from(CFG_CamposEvento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
