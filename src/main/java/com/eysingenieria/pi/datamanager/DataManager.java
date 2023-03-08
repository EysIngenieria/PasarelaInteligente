/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eysingenieria.pi.datamanager;

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
import java.util.List;

/**
 *
 * @author DesarrolloJC
 */
public class DataManager implements IDataManager {
    
    DataAccess db;
    
    public DataManager() {
        db = new DataAccess();
    }
    
    @Override
    public List<OP_Parametro> GetParametros() {
        return db.GetParametros();
    }
    
    @Override
    public void UpdateParametros(OP_Parametro parametro) throws Exception{
        db.UpdateParametros(parametro);
    }
    
    @Override
    public void UpdateRegistro(OP_Registro registro) throws Exception {
        db.UpdateRegistro(registro);
    }
    
    @Override
    public void AddRegistro(OP_Registro registro) {
        db.AddRegistro(registro);
    }
    
    @Override
    public List<OP_Registro> GetRegistro() {
        return db.GetRegistro();
    }

//RegistroTemporal    
    @Override
    public void AddRegistroTemporal(OP_RegistroTemporal registroTemporal) {
        db.AddRegistroTemporal(registroTemporal);
    }
    
    @Override
    public List<OP_RegistroTemporal> GetRegistroTemporal() {
        return db.GetRegistroTemporal();
    }
    
    @Override
    public void DeleteRegistroTemporal(int id) throws Exception {
        db.DeleteRegistroTemporal(id);
    }
//RegistroCrudo

    @Override
    public void UpdateRegistroCrudo(OP_RegistroCrudo RegistroCrudo) throws Exception {
        db.UpdateRegistroCrudo(RegistroCrudo);
    }
    
    @Override
    public void AddRegistroCrudo(OP_RegistroCrudo registroCrudo) {
        db.AddRegistroCrudo(registroCrudo);
    }
    
    @Override
    public void DeleteRegistroCrudo(int id) throws Exception {
        db.DeleteRegistroCrudo(id);
    }
    
    @Override
    public List<OP_RegistroCrudo> GetRegistroCrudo() {
        return db.GetRegistroCrudo();
    }

//CamposValidos
    @Override
    public List<CFG_CamposValidos> GetCamposValidos() {
        return db.GetCamposValidos();
    }

//CamposCabecera
    @Override
    public List<CFG_CamposCabecera> GetCamposCabecera() {
        return db.GetCamposCabecera();
    }
    
    @Override
    public void DeleteCamposCabecera(int id) throws Exception {
        db.DeleteCamposCabecera(id);
    }
    
    @Override
    public void AddCamposCabecera(CFG_CamposCabecera camposCabecera) throws Exception {
        db.AddCamposCabecera(camposCabecera);
    }

//CamposEventos
    @Override
    public List<CFG_CamposEvento> GetCamposEvento() {
        return db.GetCamposEvento();
    }
    
    @Override
    public void DeleteCamposEvento(int id) throws Exception {
        db.DeleteCamposEvento(id);
    }
    
    @Override
    public void AddCamposEvento(CFG_CamposEvento camposEvento) throws Exception {
        db.AddCamposEvento(camposEvento);
    }

//CamposAlarma
    @Override
    public List<CFG_CamposAlarma> GetCamposAlarma() {
        return db.GetCamposAlarma();
    }

    @Override
    public void DeleteCamposAlarma(int i) throws Exception {
        db.DeleteCamposAlarma(i);
    }

    @Override
    public void AddCamposAlarma(CFG_CamposAlarma camposAlarma) throws Exception {
        db.AddCamposAlarma(camposAlarma);
    }

//Configuracion
    @Override
    public void AddConfiguracion(CFG_Configuracion configuracion) {
        db.AddConfiguracion(configuracion);
    }
    
    @Override
    public CFG_Configuracion GetConfiguracion() {
        return db.GetConfiguracion();
    }

//Evento
    @Override
    public List<CFG_Evento> GetEventos() {
        return db.GetEventos();
    }
    
//Alarma    
    @Override
    public List<CFG_Alarma> GetAlarmas(){
        return db.GetAlarmas();
    }

//NivelAlarma
    @Override
    public List<CFG_NivelAlarma> GetNivelAlarma() {
        return db.GetNivelAlarma();
    }

    @Override
    public void DeleteNivelAlarma(int i) throws Exception {
        db.DeleteNivelAlarma(i);
    }

    @Override
    public void AddNivelAlarma(CFG_NivelAlarma nivelAlarma) throws Exception {
        db.AddNivelAlarma(nivelAlarma);
    }

    @Override
    public List<Puerta> GetPuertas() {
        return db.GetPuertas();
    }

    @Override
    public Puerta GetPuerta(String canal, String vagon, String idPuerta) {
        return db.GetPuerta(canal, vagon, idPuerta);
    }

    @Override
    public Puerta GetPuerta(String descripcion) {
        return db.GetPuerta(descripcion);
    }

    @Override
    public void AddPuerta(Puerta puerta) {
        db.AddPuerta(puerta);
    }

    @Override
    public void DeletePuerta(int id)throws Exception{
        db.DeletePuerta(id);
    }

    @Override
    public void UpdatePuerta(Puerta puerta) {
        db.UpdatePuerta(puerta);
    }

    @Override
    public void DeleteRegistro(int id) {
        db.DeleteRegistro(id); 
    }

    @Override
    public void UpdateRegistroTemporal(OP_RegistroTemporal registroTemporal) {
        db.UpdateRegistroTemporal(registroTemporal); 
    }

    @Override
    public void AddParametros(OP_Parametro parametro) {
        db.AddParametros(parametro);
    }

    public void DeletePuertas() {
        db.DeletePuertas();
    }

    public void deletParametros() {
        db.deleteParametros();
    }

    public void DeleteRegistros() {
        db.deleteRegistros();
    }

    public void DeleteRegistrosCrudos() {
        db.deleteRegistrosCurdos();
    }

    public void deleteRegistrosTemporales() {
        db.deleteRegistrosTemporales();
    }

    @Override
    public List<OP_RegistroTemporal> GetRegistroTemporalByMTE() {
        return db.GetRegistroTemporalByMTE();
    }

    @Override
    public List<OP_RegistroTemporal> GetRegistroTemporalByCDEG() {
        return db.GetRegistroTemporalByCDEG();
    }

    @Override
    public void saveComando(ComandoCDEG comando) {
        db.saveComando(comando);
    }

    @Override
    public List<ComandoCDEG> getComandosCDEG() {
        return db.getComandosCDEG();
    }

    @Override
    public void deleteComandoCDEG(Long id) {
        db.deleteComandoCDEG(id);
    }

    
}
