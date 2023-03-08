/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.controller;

import com.eysingenieria.pi.controller.exceptions.NonexistentEntityException;
import com.eysingenieria.pi.controller.exceptions.PreexistingEntityException;
import com.eysingenieria.pi.data.entities.CFG_CamposCabecera;
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
public class CFG_CamposCabeceraJpaController implements Serializable {

    public CFG_CamposCabeceraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CFG_CamposCabeceraJpaController() {
        emf = Persistence.createEntityManagerFactory("PIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CFG_CamposCabecera CFG_CamposCabecera) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(CFG_CamposCabecera);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCFG_CamposCabecera(CFG_CamposCabecera.getId()) != null) {
                throw new PreexistingEntityException("CFG_CamposCabecera " + CFG_CamposCabecera + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CFG_CamposCabecera CFG_CamposCabecera) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CFG_CamposCabecera = em.merge(CFG_CamposCabecera);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = CFG_CamposCabecera.getId();
                if (findCFG_CamposCabecera(id) == null) {
                    throw new NonexistentEntityException("The cFG_CamposCabecera with id " + id + " no longer exists.");
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
            CFG_CamposCabecera CFG_CamposCabecera;
            try {
                CFG_CamposCabecera = em.getReference(CFG_CamposCabecera.class, id);
                CFG_CamposCabecera.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CFG_CamposCabecera with id " + id + " no longer exists.", enfe);
            }
            em.remove(CFG_CamposCabecera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CFG_CamposCabecera> findCFG_CamposCabeceraEntities() {
        return findCFG_CamposCabeceraEntities(true, -1, -1);
    }

    public List<CFG_CamposCabecera> findCFG_CamposCabeceraEntities(int maxResults, int firstResult) {
        return findCFG_CamposCabeceraEntities(false, maxResults, firstResult);
    }

    private List<CFG_CamposCabecera> findCFG_CamposCabeceraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CFG_CamposCabecera.class));
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

    public CFG_CamposCabecera findCFG_CamposCabecera(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CFG_CamposCabecera.class, id);
        } finally {
            em.close();
        }
    }

    public int getCFG_CamposCabeceraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CFG_CamposCabecera> rt = cq.from(CFG_CamposCabecera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
