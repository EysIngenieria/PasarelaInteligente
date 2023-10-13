/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.controller.exceptions.NonexistentEntityException;
import com.manatee.pi.entities.OP_RegistroCrudo;
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
public class OP_RegistroCrudoJpaController implements Serializable {

    public OP_RegistroCrudoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public OP_RegistroCrudoJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OP_RegistroCrudo OP_RegistroCrudo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(OP_RegistroCrudo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OP_RegistroCrudo OP_RegistroCrudo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OP_RegistroCrudo = em.merge(OP_RegistroCrudo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = OP_RegistroCrudo.getId();
                if (findOP_RegistroCrudo(id) == null) {
                    throw new NonexistentEntityException("The oP_RegistroCrudo with id " + id + " no longer exists.");
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
            OP_RegistroCrudo OP_RegistroCrudo;
            try {
                OP_RegistroCrudo = em.getReference(OP_RegistroCrudo.class, id);
                OP_RegistroCrudo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The OP_RegistroCrudo with id " + id + " no longer exists.", enfe);
            }
            em.remove(OP_RegistroCrudo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OP_RegistroCrudo> findOP_RegistroCrudoEntities() {
        return findOP_RegistroCrudoEntities(false, 100, 0);
    }

    public List<OP_RegistroCrudo> findOP_RegistroCrudoEntities(int maxResults, int firstResult) {
        return findOP_RegistroCrudoEntities(false, maxResults, firstResult);
    }

    private List<OP_RegistroCrudo> findOP_RegistroCrudoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OP_RegistroCrudo.class));
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

    public OP_RegistroCrudo findOP_RegistroCrudo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OP_RegistroCrudo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOP_RegistroCrudoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OP_RegistroCrudo> rt = cq.from(OP_RegistroCrudo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
