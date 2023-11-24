/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.controller.exceptions.NonexistentEntityException;
import com.manatee.pi.entities.Puerta;
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
public class PuertaJpaController implements Serializable {

    public PuertaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    public PuertaJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puerta puerta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puerta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puerta puerta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puerta = em.merge(puerta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = puerta.getId();
                if (findPuerta(id) == null) {
                    throw new NonexistentEntityException("The puerta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void deleteAll() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            // Utiliza una consulta de borrado para eliminar todos los registros
            Query query = em.createQuery("DELETE FROM Puerta");
            query.executeUpdate();
            em.getTransaction().commit();
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
            Puerta puerta;
            try {
                puerta = em.getReference(Puerta.class, id);
                puerta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puerta with id " + id + " no longer exists.", enfe);
            }
            em.remove(puerta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puerta> findPuertaEntities() {
        return findPuertaEntities(true, -1, -1);
    }

    public List<Puerta> findPuertaEntities(int maxResults, int firstResult) {
        return findPuertaEntities(false, maxResults, firstResult);
    }

    private List<Puerta> findPuertaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puerta.class));
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

    public Puerta findPuerta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puerta.class, id);
        } finally {
            em.close();
        }
    }
    

    public int getPuertaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puerta> rt = cq.from(Puerta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
