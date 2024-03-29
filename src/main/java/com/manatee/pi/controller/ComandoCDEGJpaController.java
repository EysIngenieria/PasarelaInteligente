/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.entities.ComandoCDEG;
import com.manatee.pi.exceptions.NonexistentEntityException;
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
public class ComandoCDEGJpaController implements Serializable {
    
    public ComandoCDEGJpaController(){
        emf = Persistence.createEntityManagerFactory("PIPU");
    }
    
    public ComandoCDEGJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComandoCDEG comandoCDEG) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(comandoCDEG);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComandoCDEG comandoCDEG) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            comandoCDEG = em.merge(comandoCDEG);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comandoCDEG.getId();
                if (findComandoCDEG(id) == null) {
                    throw new NonexistentEntityException("The comandoCDEG with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComandoCDEG comandoCDEG;
            try {
                comandoCDEG = em.getReference(ComandoCDEG.class, id);
                comandoCDEG.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comandoCDEG with id " + id + " no longer exists.", enfe);
            }
            em.remove(comandoCDEG);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComandoCDEG> findComandoCDEGEntities() {
        return findComandoCDEGEntities(true, -1, -1);
    }

    public List<ComandoCDEG> findComandoCDEGEntities(int maxResults, int firstResult) {
        return findComandoCDEGEntities(false, maxResults, firstResult);
    }

    private List<ComandoCDEG> findComandoCDEGEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComandoCDEG.class));
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

    public ComandoCDEG findComandoCDEG(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComandoCDEG.class, id);
        } finally {
            em.close();
        }
    }

    public int getComandoCDEGCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComandoCDEG> rt = cq.from(ComandoCDEG.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
