/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.datamanager;

import com.eysingenieria.pi.controller.ACKVagonJpaController;
import com.eysingenieria.pi.controller.CFG_AlarmaJpaController;
import com.eysingenieria.pi.controller.CFG_CamposAlarmaJpaController;
import com.eysingenieria.pi.controller.CFG_CamposCabeceraJpaController;
import com.eysingenieria.pi.controller.CFG_CamposEventoJpaController;
import com.eysingenieria.pi.controller.CFG_CamposValidosJpaController;
import com.eysingenieria.pi.controller.CFG_ConfiguracionJpaController;
import com.eysingenieria.pi.controller.CFG_EventoJpaController;
import com.eysingenieria.pi.controller.CFG_NivelAlarmaJpaController;
import com.eysingenieria.pi.controller.ComandoCDEGJpaController;
import com.eysingenieria.pi.controller.OP_ParametroJpaController;
import com.eysingenieria.pi.controller.OP_RegistroCrudoJpaController;
import com.eysingenieria.pi.controller.OP_RegistroJpaController;
import com.eysingenieria.pi.controller.OP_RegistroTemporalJpaController;
import com.eysingenieria.pi.controller.PuertaJpaController;
import com.eysingenieria.pi.controller.exceptions.NonexistentEntityException;
import com.eysingenieria.pi.entities.ACKVagon;
import com.eysingenieria.pi.entities.CFG_Alarma;
import com.eysingenieria.pi.entities.CFG_CamposAlarma;
import com.eysingenieria.pi.entities.CFG_CamposCabecera;
import com.eysingenieria.pi.entities.CFG_CamposEvento;
import com.eysingenieria.pi.entities.CFG_CamposValidos;
import com.eysingenieria.pi.entities.CFG_Configuracion;
import com.eysingenieria.pi.entities.CFG_Evento;
import com.eysingenieria.pi.entities.CFG_NivelAlarma;
import com.eysingenieria.pi.entities.ComandoCDEG;
import com.eysingenieria.pi.entities.OP_Parametro;
import com.eysingenieria.pi.entities.OP_Registro;
import com.eysingenieria.pi.entities.OP_RegistroCrudo;
import com.eysingenieria.pi.entities.OP_RegistroTemporal;
import com.eysingenieria.pi.entities.Puerta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DesarrolloJC
 */
public class DataAccess implements IDataAccess {

    private final OP_ParametroJpaController parametroJpaController;
    private final OP_RegistroJpaController registroJpaController;
    private final OP_RegistroTemporalJpaController registroTemporalJpaController;
    private final OP_RegistroCrudoJpaController registroCrudoJpaController;
    private final CFG_CamposValidosJpaController camposValidosJpaController;
    private final CFG_CamposCabeceraJpaController camposCabeceraJpaController;
    private final CFG_CamposEventoJpaController camposEventoJpaController;
    private final CFG_CamposAlarmaJpaController camposAlarmaJpaController;
    private final CFG_ConfiguracionJpaController configuracionJpaController;
    private final CFG_EventoJpaController eventoJpaController;
    private final CFG_AlarmaJpaController alarmaJpaController;
    private final CFG_NivelAlarmaJpaController nivelAlarmaJpaController;
    private final ComandoCDEGJpaController comandoController;
    private final ACKVagonJpaController vagonJpaController;

    private final PuertaJpaController puertaController;

    public DataAccess() {
        vagonJpaController = new ACKVagonJpaController();
        comandoController = new ComandoCDEGJpaController();
        parametroJpaController = new OP_ParametroJpaController();
        registroJpaController = new OP_RegistroJpaController();
        registroTemporalJpaController = new OP_RegistroTemporalJpaController();
        registroCrudoJpaController = new OP_RegistroCrudoJpaController();
        camposValidosJpaController = new CFG_CamposValidosJpaController();
        camposCabeceraJpaController = new CFG_CamposCabeceraJpaController();
        camposEventoJpaController = new CFG_CamposEventoJpaController();
        camposAlarmaJpaController = new CFG_CamposAlarmaJpaController();
        configuracionJpaController = new CFG_ConfiguracionJpaController();
        eventoJpaController = new CFG_EventoJpaController();
        alarmaJpaController = new CFG_AlarmaJpaController();
        nivelAlarmaJpaController = new CFG_NivelAlarmaJpaController();

        puertaController = new PuertaJpaController();
    }

//Parametros   
    @Override
    public List<OP_Parametro> GetParametros() {
        return parametroJpaController.findOP_ParametroEntities();
    }

    @Override
    public void UpdateParametros(OP_Parametro parametro) throws Exception {
        parametroJpaController.edit(parametro);
    }

//Registro
    @Override
    public void UpdateRegistro(OP_Registro registro) throws Exception {
        registroJpaController.edit(registro);
    }

    @Override
    public void AddRegistro(OP_Registro registro) {
        registroJpaController.create(registro);
    }

    @Override
    public List<OP_Registro> GetRegistro() {
        return registroJpaController.findOP_RegistroEventoEntities();
    }

//RegistroTemporal
    @Override
    public void AddRegistroTemporal(OP_RegistroTemporal registroTemporal) {
        registroTemporalJpaController.create(registroTemporal);
    }

    @Override
    public List<OP_RegistroTemporal> GetRegistroTemporal() {
        return registroTemporalJpaController.findOP_RegistroTemporalEntities();
    }

    @Override
    public void DeleteRegistroTemporal(long id) throws Exception {
        registroTemporalJpaController.destroy(id);
    }

//RegistroCrudos
    @Override
    public void UpdateRegistroCrudo(OP_RegistroCrudo registroCrudo) throws Exception {
        registroCrudoJpaController.edit(registroCrudo);
    }

    @Override
    public void AddRegistroCrudo(OP_RegistroCrudo registroCrudo) {
        registroCrudoJpaController.create(registroCrudo);
    }

    @Override
    public void DeleteRegistroCrudo(int id) throws Exception {
        registroCrudoJpaController.destroy(id);
    }

    @Override
    public List<OP_RegistroCrudo> GetRegistroCrudo() {
        return registroCrudoJpaController.findOP_RegistroCrudoEntities();
    }

//CamposValidos
    @Override
    public List<CFG_CamposValidos> GetCamposValidos() {
        List<CFG_CamposValidos> camposValidos = camposValidosJpaController.findCFG_CamposValidosEntities();
        if (camposValidos.isEmpty()) {
            return null;
        }
        return camposValidos;
    }

//CamposCabecera
    @Override
    public List<CFG_CamposCabecera> GetCamposCabecera() {
        List<CFG_CamposCabecera> camposCabeceraList = camposCabeceraJpaController.findCFG_CamposCabeceraEntities();
        if (camposCabeceraList.isEmpty()) {
            return null;
        }
        return camposCabeceraList;
    }

    @Override
    public void DeleteCamposCabecera(int id) throws Exception {
        camposCabeceraJpaController.destroy(id);
    }

    @Override
    public void AddCamposCabecera(CFG_CamposCabecera camposCabecera) throws Exception {
        camposCabeceraJpaController.create(camposCabecera);
    }

//CamposEvento
    @Override
    public List<CFG_CamposEvento> GetCamposEvento() {
        List<CFG_CamposEvento> camposEventoList = camposEventoJpaController.findCFG_CamposEventoEntities();
        if (camposEventoList.isEmpty()) {
            return null;
        }
        return camposEventoList;
    }

    @Override
    public void DeleteCamposEvento(int id) throws Exception {
        camposEventoJpaController.destroy(id);
    }

    @Override
    public void AddCamposEvento(CFG_CamposEvento camposEvento) throws Exception {
        camposEventoJpaController.create(camposEvento);
    }

//CamposAlarma
    @Override
    public List<CFG_CamposAlarma> GetCamposAlarma() {
        List<CFG_CamposAlarma> camposAlarma = camposAlarmaJpaController.findCFG_CamposAlarmaEntities();
    if (camposAlarma.isEmpty()) {
        return null;
    }
    return camposAlarma;
    }

    @Override
    public void DeleteCamposAlarma(int i) throws Exception {
        camposAlarmaJpaController.destroy(i);
    }

    @Override
    public void AddCamposAlarma(CFG_CamposAlarma campoAlarma) throws Exception {
        camposAlarmaJpaController.create(campoAlarma);
    }

//Configuracion
    @Override
    public void AddConfiguracion(CFG_Configuracion configuracion) {
        configuracionJpaController.create(configuracion);
    }

    @Override
    public CFG_Configuracion GetConfiguracion() {
        List<CFG_Configuracion> list = configuracionJpaController.findCFG_ConfiguracionEntities();
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

//Evento
    @Override
    public List<CFG_Evento> GetEventos() {
        return eventoJpaController.findCFG_EventoEntities();
    }

//Alarma
    @Override
    public List<CFG_Alarma> GetAlarmas() {
        return alarmaJpaController.findCFG_AlarmaEntities();
    }

//NivelAlarma
    @Override
    public List<CFG_NivelAlarma> GetNivelAlarma() {
        return nivelAlarmaJpaController.findCFG_NivelAlarmaEntities();
    }

    @Override
    public void DeleteNivelAlarma(int i) throws Exception {
        nivelAlarmaJpaController.destroy(i);
    }

    @Override
    public void AddNivelAlarma(CFG_NivelAlarma nivelAlarma) throws Exception {
        nivelAlarmaJpaController.create(nivelAlarma);
    }

    @Override
    public List<Puerta> GetPuertas() {
        return puertaController.findPuertaEntities();
    }

    @Override
    public Puerta GetPuerta(String canal, String vagon, String idPuerta) {
        List<Puerta> puertas = GetPuertas();
        for (Iterator<Puerta> iterator = puertas.iterator(); iterator.hasNext();) {
            Puerta temp = iterator.next();
            if (temp.getCanal().equalsIgnoreCase(canal)
                    && temp.getIdPuerta().equalsIgnoreCase(idPuerta)
                    && temp.getVagon().equalsIgnoreCase(vagon)) {

                return temp;
            }

        }
        return null;
    }

    @Override
    public Puerta GetPuerta(String descripcion) {
        List<Puerta> puertas = GetPuertas();
        for (Iterator<Puerta> iterator = puertas.iterator(); iterator.hasNext();) {
            Puerta temp = iterator.next();
            if (temp.getDescripcion().equalsIgnoreCase(descripcion)) {

                return temp;
            }

        }
        return null;
    }

    @Override
    public void AddPuerta(Puerta puerta) {
        puertaController.create(puerta);
    }

    @Override
    public void DeletePuerta(int id) throws Exception {
        puertaController.destroy(id);
    }

    @Override
    public void UpdatePuerta(Puerta puerta) {
        try {
            puertaController.edit(puerta);
        } catch (Exception ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void DeleteRegistro(int id) {
        try {
            registroJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void UpdateRegistroTemporal(OP_RegistroTemporal registroTemporal) {
        try {
            registroTemporalJpaController.edit(registroTemporal);
        } catch (Exception ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void AddParametros(OP_Parametro parametro) {
        parametroJpaController.create(parametro);
    }

    void DeletePuertas() {
        for (Puerta findPuertaEntity : puertaController.findPuertaEntities()) {
            try {
                puertaController.destroy(findPuertaEntity.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void deleteParametros(boolean deleteAll) {
        for (OP_Parametro object : parametroJpaController.findOP_ParametroEntities()) {
            try {
                if (!deleteAll) {
                    if ((!object.getNombre().equalsIgnoreCase("HoraInicioOperacion") && !object.getNombre().equalsIgnoreCase("HoraFinOperacion") && !object.getNombre().equalsIgnoreCase("TiempoFinOperacion") && !object.getNombre().equalsIgnoreCase("FechaInicioOperacion") && !object.getNombre().equalsIgnoreCase("FechaFinOperacion"))) {
                        parametroJpaController.destroy(object.getId());
                    }
                } else {
                    parametroJpaController.destroy(object.getId());
                }
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void deleteRegistros() {
        for (OP_Registro object : registroJpaController.findOP_RegistroEventoEntities()) {
            try {
                registroJpaController.destroy(object.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void deleteRegistrosCurdos() {
        for (OP_RegistroCrudo object : registroCrudoJpaController.findOP_RegistroCrudoEntities()) {
            try {
                registroCrudoJpaController.destroy(object.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void deleteRegistrosTemporales() {
        for (OP_RegistroTemporal object : registroTemporalJpaController.findOP_RegistroTemporalEntities()) {
            try {
                registroTemporalJpaController.destroy(object.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<OP_RegistroTemporal> GetRegistroTemporalByMTE() {
        return registroTemporalJpaController.findOP_RegistroTemporalEntitiesByManatee();
    }

    @Override
    public List<OP_RegistroTemporal> GetRegistroTemporalByCDEG() {
        return registroTemporalJpaController.findOP_RegistroTemporalEntitiesByCDEG();
    }

    @Override
    public void saveComando(ComandoCDEG comando) {
        comandoController.create(comando);
    }

    @Override
    public List<ComandoCDEG> getComandosCDEG() {
        return comandoController.findComandoCDEGEntities();
    }

    @Override
    public void deleteComandoCDEG(Long id) {
        try {
            comandoController.destroy(id);
        } catch (com.eysingenieria.pi.exceptions.NonexistentEntityException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void addCamposValidos(List<CFG_CamposValidos> camposValidosList) {
        try {
            for (CFG_CamposValidos cFG_CamposValidos : camposValidosList) {
                camposValidosJpaController.create(cFG_CamposValidos);
            }
        } catch (Exception e) {
        }
    }

    void addEventos(List<CFG_Evento> eventosList) {
        try {
            for (CFG_Evento evento : eventosList) {
                eventoJpaController.create(evento);
            }
        } catch (Exception e) {
        }
    }

    void addAlarma(CFG_Alarma alap) {
        alarmaJpaController.create(alap);
    }

    @Override
    public void saveVagon(ACKVagon vagon) {
        vagonJpaController.create(vagon);
    }

    @Override
    public void deleteAllVagonACK() {
       vagonJpaController.deleteAllVagonACK();
    }

    @Override
    public ArrayList<ACKVagon> GetAllVagonACK() {
        ArrayList<ACKVagon> vagonList = new ArrayList<>();

        // Obtiene una lista de todos los registros de ACKVagon
        List<ACKVagon> ackVagonEntities = vagonJpaController.findACKVagonEntities();

        // Convierte la lista de entidades en un ArrayList
        if (ackVagonEntities != null) {
            vagonList.addAll(ackVagonEntities);
        }

        return vagonList;
    }

    @Override
    public void UpdateVagonACK(String vagon, int canal, int registro, String idDispositivo) {
        vagonJpaController.updateACKVagonByCanalAndIdDispositivo(vagon, idDispositivo, canal, registro);
    }

}
