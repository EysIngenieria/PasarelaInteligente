/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.controller;

import com.eysingenieria.pi.controller.exceptions.NonexistentEntityException;
import com.eysingenieria.pi.entities.CFG_Configuracion;
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
public class CFG_ConfiguracionJpaController implements Serializable {

    public CFG_ConfiguracionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CFG_ConfiguracionJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CFG_Configuracion CFG_Configuracion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CFG_Configuracion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CFG_Configuracion CFG_Configuracion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CFG_Configuracion = em.merge(CFG_Configuracion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = CFG_Configuracion.getId();
                if (findCFG_Configuracion(id) == null) {
                    throw new NonexistentEntityException("The cFG_Configuracion with id " + id + " no longer exists.");
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
            CFG_Configuracion CFG_Configuracion;
            try {
                CFG_Configuracion = em.getReference(CFG_Configuracion.class, id);
                CFG_Configuracion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CFG_Configuracion with id " + id + " no longer exists.", enfe);
            }
            em.remove(CFG_Configuracion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CFG_Configuracion> findCFG_ConfiguracionEntities() {
        return findCFG_ConfiguracionEntities(true, -1, -1);
    }

    public List<CFG_Configuracion> findCFG_ConfiguracionEntities(int maxResults, int firstResult) {
        return findCFG_ConfiguracionEntities(false, maxResults, firstResult);
    }

    private List<CFG_Configuracion> findCFG_ConfiguracionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CFG_Configuracion.class));
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

    public CFG_Configuracion findCFG_Configuracion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CFG_Configuracion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCFG_ConfiguracionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CFG_Configuracion> rt = cq.from(CFG_Configuracion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
