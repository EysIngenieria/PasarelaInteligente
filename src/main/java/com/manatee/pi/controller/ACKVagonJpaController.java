/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi.controller;

import com.manatee.pi.controller.exceptions.NonexistentEntityException;
import com.manatee.pi.entities.ACKVagon;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author DesarrolloJC
 */
public class ACKVagonJpaController implements Serializable {

    public ACKVagonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ACKVagonJpaController(){
         emf = Persistence.createEntityManagerFactory("PIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ACKVagon ACKVagon) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ACKVagon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ACKVagon ACKVagon) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ACKVagon = em.merge(ACKVagon);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ACKVagon.getId();
                if (findACKVagon(id) == null) {
                    throw new NonexistentEntityException("The aCKVagon with id " + id + " no longer exists.");
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
            ACKVagon ACKVagon;
            try {
                ACKVagon = em.getReference(ACKVagon.class, id);
                ACKVagon.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ACKVagon with id " + id + " no longer exists.", enfe);
            }
            em.remove(ACKVagon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ACKVagon> findACKVagonEntities() {
        return findACKVagonEntities(true, -1, -1);
    }

    public List<ACKVagon> findACKVagonEntities(int maxResults, int firstResult) {
        return findACKVagonEntities(false, maxResults, firstResult);
    }

    private List<ACKVagon> findACKVagonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ACKVagon.class));
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

    public ACKVagon findACKVagon(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ACKVagon.class, id);
        } finally {
            em.close();
        }
    }

    public int getACKVagonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ACKVagon> rt = cq.from(ACKVagon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void updateACKVagonByCanalAndIdDispositivo(String vagon, String idDispositivo, int canal, int nuevoRegistro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Busca un registro existente con el mismo canal, idDispositivo y vagon
            TypedQuery<ACKVagon> query = em.createQuery("SELECT a FROM ACKVagon a WHERE a.idDispositivo = :idDispositivo AND a.canal = :canal AND a.vagon = :vagon", ACKVagon.class);
            query.setParameter("vagon", vagon);
            query.setParameter("idDispositivo", idDispositivo);
            query.setParameter("canal", canal);

            List<ACKVagon> resultList = query.getResultList();

            if (!resultList.isEmpty()) {
                // Actualiza el registro encontrado con el nuevo valor de registro
                ACKVagon ackVagon = resultList.get(0);
                ackVagon.setRegistro(nuevoRegistro);
                em.merge(ackVagon);
            } else {
                // Si no se encuentra un registro existente, crea uno nuevo
                ACKVagon nuevoACKVagon = new ACKVagon();
                nuevoACKVagon.setVagon(vagon);
                nuevoACKVagon.setIdDispositivo(idDispositivo);
                nuevoACKVagon.setCanal(canal);
                nuevoACKVagon.setRegistro(nuevoRegistro);
                em.persist(nuevoACKVagon);
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public void deleteAllVagonACK() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Consulta JPA para eliminar todos los registros de la entidad ACKVagon
            Query query = em.createQuery("DELETE FROM ACKVagon");
            query.executeUpdate();

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
