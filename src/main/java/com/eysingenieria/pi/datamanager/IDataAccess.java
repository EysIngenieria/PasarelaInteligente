/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.eysingenieria.pi.datamanager;

import com.eysingenieria.pi.data.entities.CFG_Alarma;
import com.eysingenieria.pi.data.entities.CFG_CamposAlarma;
import com.eysingenieria.pi.data.entities.CFG_CamposCabecera;
import com.eysingenieria.pi.data.entities.CFG_CamposEvento;
import com.eysingenieria.pi.data.entities.CFG_CamposValidos;
import com.eysingenieria.pi.data.entities.CFG_Configuracion;
import com.eysingenieria.pi.data.entities.CFG_Evento;
import com.eysingenieria.pi.data.entities.CFG_NivelAlarma;
import com.eysingenieria.pi.data.entities.OP_Parametro;
import com.eysingenieria.pi.data.entities.OP_Registro;
import com.eysingenieria.pi.data.entities.OP_RegistroCrudo;
import com.eysingenieria.pi.data.entities.OP_RegistroTemporal;
import com.eysingenieria.pi.data.entities.Puerta;
import com.eysingenieria.pi.data.model.Comando;
import com.eysingenieria.pi.data.entities.ComandoCDEG;
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

    public void DeleteRegistroTemporal(int id) throws Exception;
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
    
//Puerta
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
}
