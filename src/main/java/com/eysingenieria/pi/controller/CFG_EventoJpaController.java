/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.controller;

import com.eysingenieria.pi.controller.exceptions.NonexistentEntityException;
import com.eysingenieria.pi.entities.CFG_Evento;
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
public class CFG_EventoJpaController implements Serializable {

    public CFG_EventoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CFG_EventoJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CFG_Evento CFG_Evento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CFG_Evento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CFG_Evento CFG_Evento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CFG_Evento = em.merge(CFG_Evento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = CFG_Evento.getId();
                if (findCFG_Evento(id) == null) {
                    throw new NonexistentEntityException("The cFG_Evento with id " + id + " no longer exists.");
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
            CFG_Evento CFG_Evento;
            try {
                CFG_Evento = em.getReference(CFG_Evento.class, id);
                CFG_Evento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CFG_Evento with id " + id + " no longer exists.", enfe);
            }
            em.remove(CFG_Evento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CFG_Evento> findCFG_EventoEntities() {
        return findCFG_EventoEntities(true, -1, -1);
    }

    public List<CFG_Evento> findCFG_EventoEntities(int maxResults, int firstResult) {
        return findCFG_EventoEntities(false, maxResults, firstResult);
    }

    private List<CFG_Evento> findCFG_EventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CFG_Evento.class));
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

    public CFG_Evento findCFG_Evento(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CFG_Evento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCFG_EventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CFG_Evento> rt = cq.from(CFG_Evento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
