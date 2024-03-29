/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.manatee.pi.datamanager;

import com.manatee.pi.entities.ACKVagon;
import com.manatee.pi.entities.CFG_Alarma;
import com.manatee.pi.entities.CFG_CamposAlarma;
import com.manatee.pi.entities.CFG_CamposCabecera;
import com.manatee.pi.entities.CFG_CamposEvento;
import com.manatee.pi.entities.CFG_CamposValidos;
import com.manatee.pi.entities.CFG_Configuracion;
import com.manatee.pi.entities.CFG_Evento;
import com.manatee.pi.entities.CFG_NivelAlarma;
import com.manatee.pi.entities.OP_Parametro;
import com.manatee.pi.entities.OP_Registro;
import com.manatee.pi.entities.OP_RegistroCrudo;
import com.manatee.pi.entities.OP_RegistroTemporal;
import com.manatee.pi.entities.Puerta;
import com.manatee.pi.entities.Comando;
import com.manatee.pi.entities.ComandoCDEG;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DesarrolloJC
 */
public interface IDataAccess {

//Parametros
    public List<OP_Parametro> GetParametros();
    
    public void AddParametros(OP_Parametro parametro);

    public void UpdateParametros(OP_Parametro parametro) throws Exception;

//Registro
    public void UpdateRegistro(OP_Registro registro) throws Exception;

    public void AddRegistro(OP_Registro registro);
    
    public void DeleteRegistro(int id);

    public List<OP_Registro> GetRegistro();

//RegistroTemporal
    public void AddRegistroTemporal(OP_RegistroTemporal registroTemporal);

    public List<OP_RegistroTemporal> GetRegistroTemporal();
    
    public List<OP_RegistroTemporal> GetRegistroTemporalByMTE();
    
    public List<OP_RegistroTemporal> GetRegistroTemporalByCDEG();

    public void DeleteRegistroTemporal(long id) throws Exception;
    public void UpdateRegistroTemporal(OP_RegistroTemporal registroTemporal);

//RegistroCrudo
    public void UpdateRegistroCrudo(OP_RegistroCrudo registroCrudo) throws Exception;

    public void AddRegistroCrudo(OP_RegistroCrudo registroCrudo);

    public void DeleteRegistroCrudo(int id) throws Exception;

    public List<OP_RegistroCrudo> GetRegistroCrudo();

//CamposValidos
    public List<CFG_CamposValidos> GetCamposValidos();

//CamposCabecera
    public List<CFG_CamposCabecera> GetCamposCabecera();

    public void DeleteCamposCabecera(int id) throws Exception;

    public void AddCamposCabecera(CFG_CamposCabecera camposCabecera) throws Exception;

//CamposEventos
    public List<CFG_CamposEvento> GetCamposEvento();

    public void DeleteCamposEvento(int id) throws Exception;

    public void AddCamposEvento(CFG_CamposEvento camposEvento) throws Exception;

//CamposAlarma
    public List<CFG_CamposAlarma> GetCamposAlarma();

    public void DeleteCamposAlarma(int i) throws Exception;

    public void AddCamposAlarma(CFG_CamposAlarma campoAlarma) throws Exception;

//Configuracion
    public void AddConfiguracion(CFG_Configuracion configuracion);

    public CFG_Configuracion GetConfiguracion();

//Evento
    public List<CFG_Evento> GetEventos();

//Alarma
    public List<CFG_Alarma> GetAlarmas();

//NivelAlarma
    public List<CFG_NivelAlarma> GetNivelAlarma();

    public void DeleteNivelAlarma(int i) throws Exception;

    public void AddNivelAlarma(CFG_NivelAlarma nivelAlarma) throws Exception;
    //PUERTA
    public List<Puerta> GetPuertas();
    public Puerta GetPuerta(String canal, String vagon, String idPuerta);
    public Puerta GetPuerta(String descripcion);
    public void AddPuerta(Puerta puerta);
    public void DeletePuerta(int id)throws Exception;
    public void UpdatePuerta(Puerta puerta);
    //Comando CDEG
    
    public void saveComando(ComandoCDEG comando);
    public List<ComandoCDEG> getComandosCDEG();
    public void deleteComandoCDEG(Long id);
    
    //VAGON ACK
    public void saveVagon(ACKVagon vagon);
    public void deleteAllVagonACK();
    public ArrayList<ACKVagon> GetAllVagonACK();
    public void UpdateVagonACK(String vagon, int canal, int registro, String idDispositivo);
    
}
