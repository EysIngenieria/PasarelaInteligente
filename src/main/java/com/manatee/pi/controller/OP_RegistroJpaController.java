/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.controller.exceptions.NonexistentEntityException;
import com.manatee.pi.entities.OP_Registro;
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
public class OP_RegistroJpaController implements Serializable {

    public OP_RegistroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public OP_RegistroJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OP_Registro OP_RegistroEvento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(OP_RegistroEvento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OP_Registro OP_RegistroEvento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OP_RegistroEvento = em.merge(OP_RegistroEvento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = OP_RegistroEvento.getId();
                if (findOP_RegistroEvento(id) == null) {
                    throw new NonexistentEntityException("The oP_RegistroEvento with id " + id + " no longer exists.");
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
            OP_Registro OP_RegistroEvento;
            try {
                OP_RegistroEvento = em.getReference(OP_Registro.class, id);
                OP_RegistroEvento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The OP_RegistroEvento with id " + id + " no longer exists.", enfe);
            }
            em.remove(OP_RegistroEvento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OP_Registro> findOP_RegistroEventoEntities() {
        return findOP_RegistroEventoEntities(false, 100, 0);
    }

    public List<OP_Registro> findOP_RegistroEventoEntities(int maxResults, int firstResult) {
        return findOP_RegistroEventoEntities(false, maxResults, firstResult);
    }

    private List<OP_Registro> findOP_RegistroEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OP_Registro.class));
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

    public OP_Registro findOP_RegistroEvento(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OP_Registro.class, id);
        } finally {
            em.close();
        }
    }

    public int getOP_RegistroEventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OP_Registro> rt = cq.from(OP_Registro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
