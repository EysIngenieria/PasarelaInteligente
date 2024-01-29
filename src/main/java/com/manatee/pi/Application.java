/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manatee.pi;

import com.manatee.pi.casting.Cast;
import com.manatee.pi.datamanager.DataManager;
import com.manatee.pi.entities.ACKVagon;
import com.manatee.pi.entities.Alarma;
import com.manatee.pi.entities.CFG_Alarma;
import com.manatee.pi.entities.CFG_CamposCabecera;
import com.manatee.pi.entities.CFG_CamposEvento;
import com.manatee.pi.entities.CFG_CamposAlarma;
import com.manatee.pi.entities.CFG_CamposValidos;
import com.manatee.pi.entities.CFG_Configuracion;
import com.manatee.pi.entities.CFG_Evento;
import com.manatee.pi.entities.CFG_NivelAlarma;
import com.manatee.pi.entities.Canal;
import com.manatee.pi.entities.Comando;
import com.manatee.pi.entities.ComandoCDEG;
import com.manatee.pi.entities.ComandoInterfazVisual;
import com.manatee.pi.entities.Configuracion;
import com.manatee.pi.entities.DatoCDEG;
import com.manatee.pi.entities.Evento;
import com.manatee.pi.entities.ModuloConcentradorVagon;
import com.manatee.pi.entities.NivelAlarma;
import com.manatee.pi.entities.OP_Parametro;
import com.manatee.pi.entities.OP_Registro;
import com.manatee.pi.entities.OP_RegistroCrudo;
import com.manatee.pi.entities.OP_RegistroTemporal;
import com.manatee.pi.entities.MLan;
import com.manatee.pi.entities.Puerta;
import com.manatee.pi.entities.Vagon;
import com.manatee.pi.service.TransmisorUDP;
import com.manatee.pi.service.Constantes;
import com.manatee.pi.service.GenerarClave;
import com.manatee.pi.service.AuxService;
import com.manatee.pi.service.ProcesarDatoCDEG;
import com.manatee.pi.service.ProcesarDatoEstacion;
import com.manatee.pi.service.PublicadorExternoMQTT;
import com.manatee.pi.service.PublicadorLocalMQTT;
import com.manatee.pi.service.PublicadorMANATEEMQTT1;
import com.manatee.pi.service.ReceptorUDP;
import com.sun.management.OperatingSystemMXBean;
import com.manatee.pi.service.SuscriptorLocalMQTT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Josues
 */
public class Application {

    private static final String VERSION = "1.1.7 BETA 1";
    private SimpleDateFormat formatoFechaMM_yyyy = new SimpleDateFormat("MM_yyyy");
    int activado = 0;
    int ModoACK = 0;
    private static final String LOG_FILE_PATH = "./Logs_pi/";    //jal, adicion_1
    String nameFile;    //jal, adicion_1
    DataManager dataManager;
    PublicadorLocalMQTT publisherMQTTServiceInterno;
    PublicadorExternoMQTT publicadorExternoMQTT;
    ProcesarDatoEstacion datoEstacion;
    List<DatoCDEG> vagonA;
    List<OP_RegistroCrudo> registrosCrudos;
    List<Vagon> vagones;
    private DatoCDEG puerta1;
    private DatoCDEG puerta2;
    private DatoCDEG vagon;
    private DatoCDEG estacion;
    private AuxService auxService;
    final static int ACK_ON = 1;

    Cast cast;
    private PublicadorMANATEEMQTT1 publicadorManatee;
    private String clienteManatee;
    private String brokerManatee;
    private String contraseñaManatee;
    private ArrayList<JSONArray> comandos;
    private ArrayList<MLan> MLans;
    private Date conexionCDEG;
    private Date conexionMTE;
    private boolean moduloPi;

    List<CFG_Evento> eventos;
    List<CFG_Alarma> alarmas;
    List<CFG_CamposValidos> camposValidos;
    List<CFG_CamposEvento> camposEventos;
    List<CFG_CamposCabecera> camposCabecera;
    List<CFG_CamposAlarma> camposAlarma;
    List<CFG_NivelAlarma> nivelesAlarma;
    List<Puerta> puertas;
    List<JSONObject> paradas;

    private String nombreEstacion;
    private String servidorLocalMQTT;

    //datos necesarios para el publicador y suscriptor externo
    private String servidorExternoMQTT;
    private String dispositivo;
    private String proyecto;
    private String region;
    private String registro;
    //--------------------------------------------------------

    //datos necesarios para el publicador de manatee
    private String macEthernet;
    private String macWifi;
    private String macBluetooth;

    private String idOperador;
    private String idEstacion;
    private String registroCrudoString;
    private String datoCDEGString;
    private volatile String clavePrivada;
    private String fechaInicioOperacion;
    private String fechaFinOperacion;
    private String direccionMLAN;
    private String puertoReceptorMLAN;
    private String puertoTransmisorMLAN;
    private String horaLimite;
    private String usuarioManatee;
    private String claveManatee;
    private String topicManatee;
    SimpleDateFormat formatoFecha;

    private String versionTrama;

    private String horaInicioOperacion;
    private String horaFinOperacion;
    private String tiempoFinOperacionDB;

    private int tiempoFinOperacion;
    private String PrimerVagonDerecha;
    private String CostadoPI;
    private String CanalCostadoPI;

    public Application() {
        formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        MLans = new ArrayList<>();
        registrosCrudos = new ArrayList<>();
        dataManager = new DataManager();
        datoEstacion = new ProcesarDatoEstacion();
        vagones = new ArrayList<>();
        vagonA = new ArrayList<>();
        puertas = new ArrayList<>();
        puerta1 = new DatoCDEG();
        puerta2 = new DatoCDEG();
        vagon = new DatoCDEG();
        estacion = new DatoCDEG();
        cast = new Cast();
        auxService = new AuxService();
        moduloPi = false;
    }

    public void Run() {

        try {
            Thread.sleep(3000);
            iniciarPrograma();
            cargarEventos();
            cargarCamposValidos();
            cargarConfiguracionCDEG();
            cargarConfiguracion();

        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage() + "");
        } catch (InterruptedException ex) {
            System.err.println(ex.getLocalizedMessage() + "");
        }
        GetParametros();

        GetCamposCabecera();
        GetCamposEventos();
        GetCamposAlarmas();
        GetCamposValidos();

        //System.out.println("ENTRADA1");
        GetNivelAlarma();
        GetEventos();
        GetAlarmas();
        InicializarVagones();
        //System.out.println("ENTRADA2");
        StartThreads();
        //System.out.println("ENTRADA3");
        InterrogarRed();
        //System.out.println("ENTRADA4");

        try {
            while (true) {
                //EliminarRegistroCrudo();

                //ProcesarRegistrosCrudos();
                //EnviarRegistro();
                try {
                    VerificarBotonUsuario();
                    VerificarReproducciónAudio();
                } catch (Exception e) {

                }
                //GenerarClave();
                //System.out.println("ENTRADA7");
                Thread.sleep(100);
            }
        } catch (InterruptedException ie) {

        }
    }

    private void GetParametros() {
        conexionMTE = new Date();
        conexionCDEG = new Date();
        List<OP_Parametro> parametros = dataManager.GetParametros();
        nombreEstacion = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("estacion")).findFirst().get().getValor();
        servidorLocalMQTT = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("servidorLocalMQTT")).findFirst().get().getValor();
        servidorExternoMQTT = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("servidorExternoMQTT")).findFirst().get().getValor();
        dispositivo = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("dispositivo")).findFirst().get().getValor();
        macEthernet = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("macEthernet")).findFirst().get().getValor();
        macWifi = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("macWifi")).findFirst().get().getValor();
        macBluetooth = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("macBluetooth")).findFirst().get().getValor();
        versionTrama = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("versionTrama")).findFirst().get().getValor();
        idOperador = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("idOperador")).findFirst().get().getValor();
        idEstacion = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("idEstacion")).findFirst().get().getValor();
        horaFinOperacion = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("horaFinOperacion")).findFirst().get().getValor();
        horaInicioOperacion = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("horaInicioOperacion")).findFirst().get().getValor();
        tiempoFinOperacionDB = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("tiempoFinOperacion")).findFirst().get().getValor();
        fechaInicioOperacion = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("fechaInicioOperacion")).findFirst().get().getValor();
        fechaFinOperacion = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("fechaFinOperacion")).findFirst().get().getValor();
        direccionMLAN = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("direccionMLAN")).findFirst().get().getValor();
        puertoReceptorMLAN = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("puertoReceptorMLAN")).findFirst().get().getValor();
        puertoTransmisorMLAN = parametros.stream().filter(info -> info.getNombre().equalsIgnoreCase("puertoTransmisorMLAN")).findFirst().get().getValor();
        publisherMQTTServiceInterno = new PublicadorLocalMQTT(servidorLocalMQTT);

        clavePrivada = new GenerarClave().Generar(nombreEstacion);
        publicadorExternoMQTT = new PublicadorExternoMQTT(clavePrivada, dispositivo, servidorExternoMQTT, proyecto, region, registro);
    }
    private static final Logger logger = Logger.getLogger(SuscriptorLocalMQTT.class.getName());

    // Resto del código de la clase
    public void iniciarPrograma() {
        // Especifica la ruta del directorio de logs

        // Registrar un mensaje de inicio en el archivo de log
        log("El programa ha sido iniciado. Version: " + VERSION);

        // Resto de la lógica del programa
    }

    public void GetPuertas() {
        puertas = dataManager.GetPuertas();
    }

    private void GetEventos() {
        eventos = dataManager.GetEventos();
    }

    private void GetCamposEventos() {
        camposEventos = dataManager.GetCamposEvento();
    }

    private void GetCamposCabecera() {
        camposCabecera = dataManager.GetCamposCabecera();
    }

    private void GetAlarmas() {
        alarmas = dataManager.GetAlarmas();
    }

    private void GetNivelAlarma() {
        nivelesAlarma = dataManager.GetNivelAlarma();
    }

    private void GetCamposAlarmas() {
        camposAlarma = dataManager.GetCamposAlarma();
    }

    private void GetCamposValidos() {
        camposValidos = dataManager.GetCamposValidos();
    }

    private void DeleteCamposCabecera() {
        GetCamposCabecera();
        if (camposCabecera != null) {
            for (CFG_CamposCabecera campoCabecera : camposCabecera) {
                try {
                    dataManager.DeleteCamposCabecera(campoCabecera.getId());
                } catch (Exception e) {
                    System.out.println("Error en eliminar campos de cabecera");
                }

            }
        }
    }

    private void DeleteCamposEvento() {
        GetCamposEventos();
        if (camposEventos != null) {
            for (CFG_CamposEvento campoEvento : camposEventos) {
                try {
                    dataManager.DeleteCamposEvento(campoEvento.getId());
                } catch (Exception e) {
                    System.out.println("Error en eliminar campos de eventos");
                }

            }
        }
    }

    private void DeleteCamposAlarma() {
        GetCamposAlarmas();
        if (camposAlarma != null) {
            for (CFG_CamposAlarma campoAlarma : camposAlarma) {
                try {
                    dataManager.DeleteCamposAlarma(campoAlarma.getId());
                } catch (Exception e) {
                    System.out.println("Error en eliminar campos alarma");
                }
            }
        }
    }

    private void DeleteNivelAlarma() {
        GetNivelAlarma();
        if (nivelesAlarma != null) {
            for (CFG_NivelAlarma nivelAlarma : nivelesAlarma) {
                try {
                    dataManager.DeleteNivelAlarma(nivelAlarma.getId());
                } catch (Exception e) {
                    System.out.println("Error en eliminar nivel de alarma");
                }
            }
        }
    }

    private Evento UpdateIdEvento(Evento evento) {

        for (CFG_Evento e : eventos) {
            if (e.getNombre().equalsIgnoreCase(evento.getCodigoEvento())) {
                evento.setId(e.getId());
            }
        }
        return evento;
    }

    private Alarma UpdateIdAlarma(Alarma alarma) {
        GetAlarmas();
        if (alarmas == null) {
            alarmas = new ArrayList<>(); // Inicializar la lista si es null
        }

        // Buscar una coincidencia en la lista
        boolean encontrado = false;
        for (CFG_Alarma a : alarmas) {
            if (a.getNombre().equalsIgnoreCase(alarma.getCodigoAlarma())) {
                alarma.setId(a.getId());
                encontrado = true;
                break; // Salir del bucle cuando se encuentra una coincidencia
            }
        }

        // Si no se encontró una coincidencia, agregar una nueva Alarma
        if (!encontrado) {
            // Crea un nuevo objeto Alarma y asigna un Id (asegúrate de generar un Id único)

            // Agregar la nueva Alarma a la lista
            CFG_Alarma alap = new CFG_Alarma();
            alap.setNombre(alarma.getCodigoAlarma());
            alap.setDescripcion(alarma.getNombreAlarma());
            dataManager.addAlarma(alap);
            alarmas.add(alap);
        }

        return alarma;
    }

    private void ArmarEventos(DatoCDEG dato) {
        DatoCDEG datoCDEGSalida = new DatoCDEG();
        dato.setVersionTrama(versionTrama);
        dato.setIdOperador(idOperador);
        dato.setIdEstacion(idEstacion);
        dato.setTipoTrama(2);
        String idRegistro = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + consecutivo();
        dato.setIdRegistro(Long.parseLong(idRegistro));
        //dato.setIdRegistro(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()));
        if (dato.getCodigoEvento() != null) {

            Map<String, Object> mapEvento = new ObjectMapper().convertValue(dato, Map.class);
            Map<String, Object> mapDato = new ObjectMapper().convertValue(datoCDEGSalida, Map.class);
            for (CFG_CamposCabecera campo : camposCabecera) {
                mapDato.put(campo.getCamposValidos().getNombre(), mapEvento.get(campo.getCamposValidos().getNombre()));
            }
            for (CFG_CamposEvento campo : camposEventos) {
                if (campo.getEvento().getNombre().equalsIgnoreCase(dato.getCodigoEvento())) {
                    mapDato.put(campo.getCamposValidos().getNombre(), mapEvento.get(campo.getCamposValidos().getNombre()));
                }
            }
            mapDato.put("fechaHoraLecturaDato", formatoFecha.format(dato.getFechaHoraLecturaDato()));
            mapDato.put("fechaHoraEnvioDato", formatoFecha.format(new Date()));
            String datoString = new Gson().toJson(mapDato);
            OP_RegistroTemporal registroTemporal = new OP_RegistroTemporal();
            //System.out.println("\n" + "Evento creado: " + datoString + "\n");
            registroTemporal.setTrama(datoString);
            registroTemporal.setIDManatee(Long.parseLong(idRegistro));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dato.getFechaHoraLecturaDato());
            calendar.add(Calendar.HOUR, -5);
            registroTemporal.setfechaHoraOcurrencia(calendar.getTime());

            dataManager.AddRegistroTemporal(registroTemporal);
        }

    }

    private void ArmarAlarmas(DatoCDEG dato) {
        DatoCDEG datoCDEGSalida = new DatoCDEG();

        dato.setTipoTrama(3);
        Map<String, Object> mapAlarma = new ObjectMapper().convertValue(dato, Map.class);
        System.out.println(dato.getCodigoAlarma());
        String idRegistro = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + consecutivo();
        dato.setIdRegistro(Long.parseLong(idRegistro));
        if (dato.getCodigoAlarma() != null) {

            //dato.setIdRegistro(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()));
            Map<String, Object> mapDato = new ObjectMapper().convertValue(datoCDEGSalida, Map.class);
            for (CFG_CamposCabecera campo : camposCabecera) {
                mapDato.put(campo.getCamposValidos().getNombre(), mapAlarma.get(campo.getCamposValidos().getNombre()));
            }
            for (CFG_CamposAlarma campo : camposAlarma) {
                if (campo.getAlarma().getNombre().equalsIgnoreCase(dato.getCodigoAlarma())) {
                    mapDato.put(campo.getCamposValidos().getNombre(), mapAlarma.get(campo.getCamposValidos().getNombre()));
                }
            }
            mapDato.put("fechaHoraLecturaDato", formatoFecha.format(dato.getFechaHoraLecturaDato()));
            mapDato.put("fechaHoraEnvioDato", formatoFecha.format(new Date()));
            String datoString = new Gson().toJson(mapDato);
            OP_RegistroTemporal registroTemporal = new OP_RegistroTemporal();
            registroTemporal.setTrama(datoString);
            registroTemporal.setIDManatee(dato.getIdRegistro());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dato.getFechaHoraLecturaDato());
            calendar.add(Calendar.HOUR, -5);
            registroTemporal.setfechaHoraOcurrencia(calendar.getTime());

            dataManager.AddRegistroTemporal(registroTemporal);

        }

    }

    public void cargarCamposValidos() {
        GetCamposValidos();
        if (camposValidos == null) {
            try {
                String jsonFile = new String(Files.readAllBytes(Paths.get("./ConfiguracionCDEG/camposValidos.json")));
                JSONArray jsonArray = new JSONArray(jsonFile);
                List<CFG_CamposValidos> camposValidosList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    CFG_CamposValidos campoValido = new CFG_CamposValidos();
                    campoValido.setId(jsonObject.getInt("id"));
                    campoValido.setNombre(jsonObject.getString("nombre"));
                    campoValido.setTipoCampo(jsonObject.getString("tipoDato"));
                    campoValido.setTipoCampoValido(jsonObject.getString("tipoCampoValido"));
                    campoValido.setManejaNivel(jsonObject.getBoolean("manejaNivel"));
                    campoValido.setDescripcion(jsonObject.isNull("descripcion") ? null : jsonObject.getString("descripcion"));

                    camposValidosList.add(campoValido);
                }

                dataManager.addCamposValidos(camposValidosList);
                camposValidos = dataManager.GetCamposValidos();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cargarEventos() {
        GetEventos();
        if (eventos == null) {
            try {
                String jsonFile = new String(Files.readAllBytes(Paths.get("./ConfiguracionCDEG/Eventos.json")));
                JSONArray jsonArray = new JSONArray(jsonFile);
                List<CFG_Evento> eventosList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    CFG_Evento evento = new CFG_Evento();
                    evento.setId(jsonObject.getInt("Id"));
                    evento.setNombre(jsonObject.getString("Nombre"));
                    evento.setDescripcion(jsonObject.optString("Descripcion"));

                    eventosList.add(evento);
                }

                dataManager.addEventos(eventosList);
                eventos = dataManager.GetEventos();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void ProcesarRegistrosCrudos() {
        //System.out.println("ENTRADA6");

        List<OP_RegistroCrudo> listRegistrosCrudos = new ArrayList<>();
        listRegistrosCrudos.addAll(dataManager.GetRegistroCrudo());
        for (OP_RegistroCrudo registroCrudo : listRegistrosCrudos) {
            try {
                DatoCDEG datoAux = new DatoCDEG();
                datoAux.setVersionTrama(versionTrama);
                datoAux.setIdOperador(idOperador);
                datoAux.setTipoTrama(2);
                Puerta temp = new Puerta();

                if (!registroCrudo.isEstado()) {

                    switch (registroCrudo.getOrigen()) {
                        case "Estacion":
//                            Vagon v = new Vagon();
//                            v.setNombre(registroCrudo.getIdVagon());
//                            conexionVagon(v);
                            //System.out.println("nombre vagon " +v.getNombre());
                            if (registroCrudo.getFuncion() != null) {
                                switch (registroCrudo.getFuncion()) {
                                    case "EVENTO":

                                        datoAux = datoEstacion.ProcessData(registroCrudo.getTrama(), datoAux);
                                        datoAux.setIdEstacion(idEstacion);
                                        JSONObject envioJson;
                                        if(datoAux.getInfo().equalsIgnoreCase("ERROR TRAMA")){
                                            temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                            log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " FUNCION: "+ registroCrudo.getFuncion());
                                            break;
                                        }
//                                        if (datoAux.getCanal() != null) {
//                                            conexionVagon(v, datoAux.getCanal());
//                                        }
                                        switch (datoAux.getInfo()) {

                                            case "ABRIENDO":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),13)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                        
                                                
                                                if (temp != null) {
                                                    temp.setEstadoAperturaCierre(datoAux.getEstadoAperturaCierrePuertas());
                                                    temp.setEntradasApertura(datoAux.getEntradasApertura());
                                                    temp.setTiempoApertura(datoAux.getTiempoApertura());
                                                    temp.setPorcentajeApertura(datoAux.getPorcentajeApertura());
                                                    temp.setUsoBotonManual(datoAux.getUsoBotonManual());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }

                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                    dataManager.UpdatePuerta(temp);
                                                }
                                                break;

                                            case "ABIERTA":
                                                //System.out.println("ABIERTA " + registroCrudo.getCanal() + " " + registroCrudo.getIdPuerta());
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),13)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setEstadoAperturaCierre(datoAux.getEstadoAperturaCierrePuertas());
                                                    temp.setEntradasApertura(datoAux.getEntradasApertura());
                                                    temp.setTiempoApertura(datoAux.getTiempoApertura());
                                                    temp.setPorcentajeApertura(datoAux.getPorcentajeApertura());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                    cast.datosPuerta(temp, datoAux);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                    if (temp.getEstadoErrorCritico() != null && temp.getModoOperacion() != null) {
                                                        if ((!temp.getEstadoErrorCritico() || temp.getEstado().equalsIgnoreCase("SIN CONEXION"))
                                                                && temp.getModoOperacion() != 1) {
                                                            temp.setEstado("ABIERTA");

                                                        } else if (temp.getEstadoErrorCritico() && temp.getModoOperacion() != 1) {
                                                            temp.setEstado("ERROR");
                                                        }
                                                    }
                                                    envioJson = new JSONObject(new Gson().toJson(temp));
                                                    envioJson.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                    dataManager.UpdatePuerta(temp);
                                                }
                                                break;

                                            case "CERRANDO":

                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),13)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setEstadoAperturaCierre(datoAux.getEstadoAperturaCierrePuertas());
                                                    temp.setEntradasApertura(datoAux.getEntradasApertura());
                                                    temp.setTiempoApertura(datoAux.getTiempoApertura());
                                                    temp.setPorcentajeApertura(datoAux.getPorcentajeApertura());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                    cast.datosPuerta(temp, datoAux);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                    dataManager.UpdatePuerta(temp);
                                                }

                                                break;

                                            case "CERRADA":
                                                //System.out.println("cerrada " + registroCrudo.getCanal() + " " + registroCrudo.getIdPuerta());
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),13)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setEstadoAperturaCierre(datoAux.getEstadoAperturaCierrePuertas());
                                                    temp.setEntradasApertura(datoAux.getEntradasApertura());
                                                    temp.setTiempoApertura(datoAux.getTiempoApertura());
                                                    temp.setPorcentajeApertura(datoAux.getPorcentajeApertura());

                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                    if (temp.getEstadoErrorCritico() != null && temp.getModoOperacion() != null) {
                                                        if ((!temp.getEstadoErrorCritico() || temp.getEstado().equalsIgnoreCase("SIN CONEXION"))
                                                                && temp.getModoOperacion() != 1) {
                                                            temp.setEstado("CERRADA");

                                                        } else if (temp.getEstadoErrorCritico() && temp.getModoOperacion() != 1) {
                                                            temp.setEstado("ERROR");
                                                        }
                                                    }
                                                    envioJson = new JSONObject(new Gson().toJson(temp));
                                                    envioJson.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                    cast.datosPuerta(temp, datoAux);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);

                                                    dataManager.UpdatePuerta(temp);
                                                }

                                                break;

                                            case "OBSTACULO_ABRIENDO":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),13)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setEstadoAperturaCierre(datoAux.getEstadoAperturaCierrePuertas());
                                                    temp.setEntradasApertura(datoAux.getEntradasApertura());
                                                    temp.setTiempoApertura(datoAux.getTiempoApertura());
                                                    temp.setPorcentajeApertura(datoAux.getPorcentajeApertura());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    dataManager.UpdatePuerta(temp);

                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {

                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                    cast.datosPuerta(temp, datoAux);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                }
                                                break;

                                            case "OBSTACULO_CERRANDO":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),13)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setEstadoAperturaCierre(datoAux.getEstadoAperturaCierrePuertas());
                                                    temp.setEntradasApertura(datoAux.getEntradasApertura());
                                                    temp.setTiempoApertura(datoAux.getTiempoApertura());
                                                    temp.setPorcentajeApertura(datoAux.getPorcentajeApertura());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    dataManager.UpdatePuerta(temp);
                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                    cast.datosPuerta(temp, datoAux);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                }
                                                break;

                                            case "APERTURA_FORZADA":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),9)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setEstadoAperturaCierre(datoAux.getEstadoAperturaCierrePuertas());
                                                    temp.setPorcentajeApertura(datoAux.getPorcentajeApertura());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    temp.setEstado("ABIERTA");
                                                    envioJson = new JSONObject(new Gson().toJson(temp));
                                                    envioJson.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                    dataManager.UpdatePuerta(temp);

                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                    cast.datosPuerta(temp, datoAux);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                }
                                                break;

                                            case "CIERRE_FORZADO":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),9)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setEstadoAperturaCierre(datoAux.getEstadoAperturaCierrePuertas());
                                                    temp.setPorcentajeApertura(datoAux.getPorcentajeApertura());
                                                    temp.setEstado("CERRADA");
                                                    dataManager.UpdatePuerta(temp);
                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    temp.setUltimaConexion(System.currentTimeMillis());

                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                    cast.datosPuerta(temp, datoAux);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                }
                                                break;

                                            case "CALIBRACION":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),9)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setEstadoCalibracion(datoAux.getEstadoCalibracion());
                                                    temp.setContadorEncoder(datoAux.getContadorEncoder());
                                                    dataManager.UpdatePuerta(temp);
                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                }
                                                break;

                                            case "CAMBIO_FUENTE_ALIMENTACION":

                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),12)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setEstadoPoder(datoAux.getEstadoPoder());
                                                    temp.setVoltajeBateria(datoAux.getVoltajeBateria());
                                                    temp.setPorcentajeCargaBaterias(datoAux.getPorcentajeCargaBaterias());
                                                    temp.setVoltajeVI(datoAux.getVoltajeVI());

                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }

                                                    cast.datosPuerta(temp, datoAux);
                                                    if (temp.getEstadoErrorCritico()) {
                                                        temp.setEstado("ERROR");
                                                        envioJson = new JSONObject(new Gson().toJson(temp));
                                                        envioJson.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                    }
                                                    if (!temp.getEstadoErrorCritico()) {
                                                        if (temp.isEstadoAperturaCierre()) {
                                                            temp.setEstado("ABIERTA");
                                                            envioJson = new JSONObject(new Gson().toJson(temp));
                                                            envioJson.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                        } else {
                                                            temp.setEstado("CERRADA");
                                                            envioJson = new JSONObject(new Gson().toJson(temp));
                                                            envioJson.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                        }
                                                    }
                                                    dataManager.UpdatePuerta(temp);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                }
                                                break;

                                            case "BATERIA_BAJA":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if (temp != null) {
                                                    temp.setEstadoPoder(datoAux.getEstadoPoder());
                                                    temp.setTipoEnergizacion(datoAux.getTipoEnergizacion());
                                                    temp.setVoltajeBateria(datoAux.getVoltajeBateria());
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    temp.setPorcentajeCargaBaterias(datoAux.getPorcentajeCargaBaterias());
                                                    temp.setVoltajeVI(datoAux.getVoltajeVI());
                                                    dataManager.UpdatePuerta(temp);
                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                    cast.datosPuerta(temp, datoAux);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                }
                                                break;

                                            case "BATERIA_NO_CARGA":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if (temp != null) {
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    temp.setEstadoPoder(datoAux.getEstadoPoder());
                                                    temp.setTipoEnergizacion(datoAux.getTipoEnergizacion());
                                                    temp.setVoltajeBateria(datoAux.getVoltajeBateria());
                                                    temp.setPorcentajeCargaBaterias(datoAux.getPorcentajeCargaBaterias());
                                                    temp.setVoltajeVI(datoAux.getVoltajeVI());
                                                    dataManager.UpdatePuerta(temp);
                                                    //new Gson().toJson(puerta1));
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }
                                                    cast.datosPuerta(temp, datoAux);
                                                    datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                    ArmarEventos(datoAux);
                                                }
                                                break;

                                            case "PERIODICO_1":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),19)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                //new Gson().toJson(puerta1));
                                                if (temp != null) {
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    temp.setEstadoAperturaCierre(datoAux.getEstadoAperturaCierrePuertas());
                                                    temp.setEntradasApertura(datoAux.getEntradasApertura());
                                                    temp.setTiempoApertura(datoAux.getTiempoApertura());
                                                    temp.setPorcentajeApertura(datoAux.getPorcentajeApertura());
                                                    temp.setEstadoPoder(datoAux.getEstadoPoder());
                                                    temp.setTipoEnergizacion(datoAux.getTipoEnergizacion());
                                                    temp.setVoltajeBateria(datoAux.getVoltajeBateria());

                                                    temp.setPorcentajeCargaBaterias(datoAux.getPorcentajeCargaBaterias());

                                                    temp.setVoltajeVI(datoAux.getVoltajeVI());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }

                                                    ComprobarAlarmas1(datoAux);

                                                    if (datoAux.getNivelAlarma1() != null) {
                                                        if (temp.getNivelAlarma1() == null) {
                                                            temp.setNivelAlarma1(datoAux.getNivelAlarma1());
                                                            datoAux.setIdVagon(nombreVagon(datoAux.getIdVagon()));
                                                            ArmarAlarmas(datoAux);

                                                        } else if (!temp.getNivelAlarma1().equalsIgnoreCase(datoAux.getNivelAlarma1())) {
                                                            temp.setNivelAlarma1(datoAux.getNivelAlarma1());
                                                            datoAux.setIdVagon(nombreVagon(datoAux.getIdVagon()));
                                                            ArmarAlarmas(datoAux);
                                                            envioJson = new JSONObject(new Gson().toJson(temp));
                                                            envioJson.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                            publisherMQTTServiceInterno.Publisher(envioJson.toString().getBytes(), "ActualizacionEstado");
                                                        }
                                                    } else {
                                                        temp.setNivelAlarma1(null);

                                                    }
                                                    ComprobarAlarmas2(datoAux);
                                                    if (datoAux.getNivelAlarma2() != null) {
                                                        if (temp.getNivelAlarma2() != null) {
                                                            if (!temp.getNivelAlarma2().equalsIgnoreCase(datoAux.getNivelAlarma2())) {
                                                                temp.setNivelAlarma2(datoAux.getNivelAlarma2());
                                                                envioJson = new JSONObject(new Gson().toJson(temp));
                                                                envioJson.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                                datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                                ArmarAlarmas(datoAux);
                                                            }
                                                        } else {
                                                            temp.setNivelAlarma2(datoAux.getNivelAlarma2());
                                                            datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                            ArmarAlarmas(datoAux);
                                                            envioJson = new JSONObject(new Gson().toJson(temp));
                                                            envioJson.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                        }
                                                    } else {
                                                        temp.setNivelAlarma2(null);
                                                    }

                                                    dataManager.UpdatePuerta(temp);
                                                    cast.datosPuerta(temp, datoAux);
                                                }
                                                break;

                                            case "PERIODICO_2":
                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),20)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                //new Gson().toJson(puerta1));
                                                if (temp != null) {
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    datoAux.setCanal(temp.getCanal());
                                                    datoAux.setIdVagon(temp.getVagon());
                                                    datoAux.setCodigoPuerta(temp.getDescripcion());
                                                    datoAux.setIdPuerta(temp.getDescripcion());
                                                    temp.setCiclosApertura(datoAux.getCiclosApertura());
                                                    temp.setTiempoOperacionMotor(datoAux.getTiempoOperacionMotor());
                                                    temp.setHorasServicio(datoAux.getHorasServicio());
                                                    temp.setModoOperacion(datoAux.getModoOperacion());
                                                    temp.setVelocidadApertura(datoAux.getVelocidadApertura());
                                                    temp.setVelocidadCierre(datoAux.getVelocidadCierre());
                                                    temp.setVelocidadMotor(datoAux.getVelocidaMotor());
                                                    temp.setTiempoPausa(datoAux.getTiempoPausa());
                                                    temp.setEstadoBluetooth(datoAux.getEstadoBluetooth());
                                                    temp.setFuerzaMotor(datoAux.getFuerzaMotor());
                                                    temp.setEstadoBotonManual(datoAux.getEstadoBotonManual());
                                                    {
                                                        try {
                                                            datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                                        } catch (ParseException ex) {
                                                            System.err.println(ex.getLocalizedMessage() + "");
                                                        }
                                                    }

                                                    if (temp.getEstadoErrorCritico() == null) {

                                                        temp.setEstadoErrorCritico(datoAux.getEstadoErrorCritico());
                                                        datoAux.setCodigoEvento("EVP5");
                                                        datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                        ArmarEventos(datoAux);
                                                    } else {
                                                        if (!Objects.equals(temp.getEstadoErrorCritico(), datoAux.getEstadoErrorCritico())) {
                                                            temp.setEstadoErrorCritico(datoAux.getEstadoErrorCritico());
                                                            datoAux.setCodigoEvento("EVP5");
                                                            datoAux.setIdVagon(nombreVagon(temp.getVagon()));
                                                            ArmarEventos(datoAux);

                                                        }
                                                    }
                                                    if (temp.getEstadoErrorCritico() != null) {
                                                        if (temp.getEstadoErrorCritico()) {
                                                            temp.setEstado("ERROR");

                                                        }
                                                        if (!temp.getEstadoErrorCritico() && temp.isEstadoAperturaCierre() != null) {
                                                            if (temp.isEstadoAperturaCierre()) {
                                                                temp.setEstado("ABIERTA");

                                                            } else {
                                                                temp.setEstado("CERRADA");

                                                            }
                                                        }
                                                        if (temp.getModoOperacion() == 1 && temp.getModoOperacion() != null) {
                                                            temp.setEstado("MANUAL");

                                                        }
                                                    }
                                                    dataManager.UpdatePuerta(temp);
                                                    cast.datosPuerta(temp, datoAux);
                                                }
                                                break;

                                            case "DESCONOCIDO":

                                                break;
                                            case "CAMBIO_MODO":

                                                temp = dataManager.GetPuerta(registroCrudo.getCanal(), registroCrudo.getIdVagon(), registroCrudo.getIdPuerta());
                                                if(!datoEstacion.verifyTrama(registroCrudo.getTrama(),7)){
                                                    log("TRAMA: " + registroCrudo.getTrama() + " ID_PUERTA: " + temp.getDescripcion() + " EVENTO: "+ datoAux.getInfo());
                                                    break;
                                                }
                                                if (temp != null) {
                                                    temp.setUltimaConexion(System.currentTimeMillis());
                                                    temp.setModoOperacion(datoAux.getModoOperacion());
                                                    if (temp.getModoOperacion() == 1) {
                                                        temp.setEstado("MANUAL");
                                                        dataManager.UpdatePuerta(temp);
                                                    } else {
                                                        if (temp.getEstadoErrorCritico() != null && temp.getModoOperacion() != null) {
                                                            if ((!temp.getEstadoErrorCritico() || temp.getEstado().equalsIgnoreCase("SIN CONEXION"))
                                                                    && temp.getModoOperacion() != 1) {
                                                                temp.setEstado("ABIERTA");

                                                            } else if (temp.getEstadoErrorCritico() && temp.getModoOperacion() != 1) {
                                                                temp.setEstado("ERROR");
                                                            }
                                                        }
                                                        dataManager.UpdatePuerta(temp);
                                                    }
                                                }
                                                break;

                                            default:

                                        }
                                        break;

                                    case "BOTON_EMERGENCIA":
                                        datoAux.setIdPuerta("EMERGENCIA");
                                        datoAux.setCodigoPuerta("EMERGENCIA");
                                        datoAux.setIdVagon(registroCrudo.getIdVagon());
                                        datoAux.setCodigoEvento("EVP7");
                                        System.out.println("VAGON EMERGENCIA  " + new Gson().toJson(datoAux));
                                         {
                                            try {
                                                //vagon.setIdPuerta("9115-WA-OR-1");
                                                //vagon.setCodigoPuerta("9115-WA-OR-1");
                                                datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(registroCrudo.getFechaOcurrencia().getTime()))));
                                            } catch (ParseException ex) {
                                                System.err.println(ex.getLocalizedMessage() + "");
                                            }
                                        }
                                        datoAux.setIdVagon(nombreVagon(registroCrudo.getIdVagon()));
                                        ArmarEventos(datoAux);
                                        break;
                                    case "CONEXION_PUERTAS":

                                        for (Vagon vagone : vagones) {
                                            if (vagone.getNombre().equalsIgnoreCase(registroCrudo.getIdVagon())) {

                                                vagone.setPuertas(registroCrudo.getTrama());
                                            }
                                        }
                                        break;

                                    case "GET_DATE":
                                        AddComando(registroCrudo.getIdVagon(), auxService.wr_date());
                                        //publisherMQTTServiceInterno.Publisher(auxService.wr_date().toString().getBytes(), registroCrudo.getIdVagon());
                                        break;

                                    default:
                                }
                            }

                            dataManager.DeleteRegistroCrudo(registroCrudo.getId());
                            break;
                        case "CDEG":

                        case "/devices/E09115_0_Estacion_V1/commands":
                        case "/devices/pasarela_inteligente2_test/config":
                            JSONObject obj = new JSONObject(registroCrudo.getTrama());
                            switch (obj.getInt("tipoTrama")) {
                                case 1:

                                    break;
                                case 2:

                                    break;
                                case 3:

                                    break;
                                case 4:
                                    CFG_Configuracion cfg_Configuracion = dataManager.GetConfiguracion();
                                    if (!(cfg_Configuracion.getTrama().equalsIgnoreCase(registroCrudo.getTrama()))) {
                                        System.out.println("Updated Configuration");
                                        Configuracion configuracion = cast.JSONtoConfiguracion(registroCrudo.getTrama());
                                        cfg_Configuracion.setTrama(registroCrudo.getTrama());
                                        dataManager.AddConfiguracion(cfg_Configuracion);
                                        ///Update Cabecera
                                        DeleteCamposCabecera();
                                        for (String cabecera : configuracion.getCabecera()) {
                                            for (CFG_CamposValidos campoValido : camposValidos) {
                                                if (campoValido.getTipoCampoValido().equalsIgnoreCase("Cabecera")) {
                                                    if (campoValido.getNombre().equals(cabecera)) {
                                                        CFG_CamposCabecera campo = new CFG_CamposCabecera();
                                                        campo.setConfiguracion(cfg_Configuracion);
                                                        campo.setCamposValidos(campoValido);
                                                        try {
                                                            dataManager.AddCamposCabecera(campo);
                                                            GetCamposCabecera();
                                                        } catch (Exception e) {
                                                            System.out.println("Error en agregar campos cabecera del CDEG");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        ///Update Evento
                                        DeleteCamposEvento();
                                        CFG_CamposEvento campo = new CFG_CamposEvento();
                                        List<Evento> listEventos = new ArrayList<>();
                                        for (Evento evento : configuracion.getEvento()) {
                                            evento = UpdateIdEvento(evento);
                                            listEventos.add(evento);
                                        }
                                        configuracion.setEvento(listEventos);
                                        for (Evento e : configuracion.getEvento()) {
                                            for (CFG_CamposValidos campoValido : camposValidos) {
                                                if (campoValido.getTipoCampoValido().equalsIgnoreCase("Evento")) {
                                                    for (String variable : e.getVariable()) {
                                                        if (campoValido.getNombre().equalsIgnoreCase(variable)) {
                                                            campo.setEvento(cast.EventotoCFG_Evento(e));
                                                            campo.setConfiguracion(cfg_Configuracion);
                                                            campo.setCamposValidos(campoValido);
                                                            try {
                                                                dataManager.AddCamposEvento(campo);
                                                                GetCamposEventos();
                                                            } catch (Exception ex) {
                                                                System.out.println("Error en añadir campos Eventos CDEG");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        DeleteCamposAlarma();
                                        DeleteNivelAlarma();
                                        CFG_CamposAlarma campoAlarma = new CFG_CamposAlarma();
                                        List<Alarma> listAlarmas = new ArrayList<>();
                                        for (Alarma alarma : configuracion.getAlarma()) {
                                            alarma = UpdateIdAlarma(alarma);
                                            listAlarmas.add(alarma);
                                        }
                                        configuracion.setAlarma(listAlarmas);
                                        for (Alarma a : configuracion.getAlarma()) {
                                            for (CFG_CamposValidos campoValido : camposValidos) {
                                                if (campoValido.getTipoCampoValido().equalsIgnoreCase("Alarma")) {
                                                    if (campoValido.getNombre().equalsIgnoreCase(a.getVariableAlarma())) {
                                                        campoAlarma.setAlarma(cast.AlarmatoCFG_Alarma(a));
                                                        campoAlarma.setConfiguracion(cfg_Configuracion);
                                                        campoAlarma.setCamposValidos(campoValido);

                                                        try {
                                                            dataManager.AddCamposAlarma(campoAlarma);
                                                            GetCamposAlarmas();
                                                        } catch (Exception e) {
                                                            System.out.println("Error en añadir Campos Alarma CDEG");
                                                        }
                                                    }
                                                }
                                                if (campoValido.getNombre().equalsIgnoreCase("codigoAlarma") || campoValido.getNombre().equalsIgnoreCase("codigoNivelAlarma")) {
                                                    campoAlarma.setAlarma(cast.AlarmatoCFG_Alarma(a));
                                                    campoAlarma.setConfiguracion(cfg_Configuracion);
                                                    campoAlarma.setCamposValidos(campoValido);
                                                    try {
                                                        dataManager.AddCamposAlarma(campoAlarma);
                                                        GetCamposAlarmas();
                                                    } catch (Exception e) {
                                                        System.out.println("Error en añadir Campos Alarma CDEG 2");
                                                    }

                                                }
                                            }
                                            //NivelAlarma nivelAlarma = new NivelAlarma();
                                            for (NivelAlarma nivel : a.getNivelAlarma()) {
                                                CFG_NivelAlarma nivelAlarma = cast.NivelAlarmatoCFG_NivelAlarma(nivel);
                                                nivelAlarma.setAlarma(cast.AlarmatoCFG_Alarma(a));
                                                try {
                                                    dataManager.AddNivelAlarma(nivelAlarma);
                                                } catch (Exception ex) {
                                                    System.err.println(ex.getLocalizedMessage() + "");
                                                }
                                            }
                                            //nivelAlarma.setCodigoNivelAlarma(a.getNivelAlarma());
                                        }

                                    }
                                    //dataManager.DeleteRegistroCrudo(registroCrudo.getId());
                                    break;
                                case 5:
                                    Comando comando = cast.JSONtoComando(registroCrudo.getTrama());
                                    ProcesarComandoCDEG(comando);
                                    break;
                            }
                            dataManager.DeleteRegistroCrudo(registroCrudo.getId());
                            break;
                        case "MLAN":
                            if (!registroCrudo.getTrama().isEmpty() && registroCrudo.getTrama().contains("<") && registroCrudo.getTrama().contains(">")) {
                                ProcesarMLAN(registroCrudo.getTrama());
                            }
                            dataManager.DeleteRegistroCrudo(registroCrudo.getId());
                            break;
                        default:
                            dataManager.DeleteRegistroCrudo(registroCrudo.getId());
                            break;
                    }
                }
                Thread.sleep(40);
            } catch (Exception ex) {
                try {
                    dataManager.DeleteRegistroCrudo(registroCrudo.getId());
                    System.out.println("Error en registro Crudo " + ex.getLocalizedMessage());
                    System.err.println(ex.getLocalizedMessage() + "");
                } catch (Exception ex1) {
                    System.err.println(ex.getLocalizedMessage() + "");
                }
            }
        }

    }

    private void ProcesarComandoCDEG(Comando comando) {
        Puerta temp = dataManager.GetPuerta(comando.getCodigoPuerta());
        String comandoString = new ProcesarDatoCDEG().ProcesarComando(comando, dataManager);

        DatoCDEG datoAux = new DatoCDEG();
        datoAux.setVersionTrama(versionTrama);
        datoAux.setIdOperador(idOperador);
        datoAux.setTipoTrama(2);
        datoAux.setCanal(temp.getCanal());
        datoAux.setIdVagon(nombreVagon(temp.getVagon()));
        datoAux.setCodigoPuerta(temp.getDescripcion());
        datoAux.setIdPuerta(temp.getDescripcion());

        OP_RegistroCrudo comandoCrudo = new OP_RegistroCrudo();
        comandoCrudo.setOrigen("CDEG");
        comandoCrudo.setIdPuerta(temp.getIdPuerta());
        comandoCrudo.setCanal(temp.getCanal());
        comandoCrudo.setCodigoPuerta(temp.getDescripcion());
        comandoCrudo.setIdVagon(comando.getIdVagon());
        comandoCrudo.setTrama(comandoString);

        datoAux = datoEstacion.ProcessData(comandoCrudo.getTrama(), datoAux);
        datoAux.setIdEstacion(idEstacion);
        cast.datosPuerta(temp, datoAux);

        switch (comando.getCodigoMensaje()) {
            case Constantes.Comandos.AUTODIAGNOSTICO: //new Gson().toJson(puerta1));
            {
                try {
                    datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                } catch (ParseException ex) {
                    System.err.println(ex.getLocalizedMessage() + "");
                }
            }
            datoAux.setCodigoEvento("EVP10");

            ArmarEventos(datoAux);
            break;

            case Constantes.Comandos.RESET: //new Gson().toJson(puerta1));
            {
                try {
                    //puerta.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                    datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                } catch (ParseException ex) {
                    System.err.println(ex.getLocalizedMessage() + "");
                }
            }
            datoAux.setCodigoEvento("EVP11");
            ArmarEventos(datoAux);

            break;

            case Constantes.Comandos.BOTON_MANUAL:
                try {
                // System.out.println(new Gson().toJson(puerta1));
                {
                    try {
                        datoAux.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
                                .parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                    } catch (ParseException ex) {
                        System.err.println(ex.getLocalizedMessage() + "");
                    }
                }
                datoAux.setFechaHoraInicioActivaciondesactivacion(comando.getFechaHoraInicioActivaciondesactivacion());
                datoAux.setFechaHoraFinalActivaciondesactivacion(comando.getFechaHoraFinalActivaciondesactivacion());
                datoAux.setActivadoDesactivado(comando.getActivadoDesactivado());
                datoAux.setEstadoBotonManual(comando.getActivadoDesactivado());
                datoAux.setCodigoEvento("EVP14");
                ArmarEventos(datoAux);
                // Check if activadoDesactivado is 1 or 2 before proceeding
                if (comando.getActivadoDesactivado() == 1 || comando.getActivadoDesactivado() == 2) {
                    // DECIDIMOS RESPONDER CON LO QUE ELLA MANDA PERO NO ES EL ESTADO REAL

                    temp.setFechaHoraFinalActivacionDesactivacion(comando.getFechaHoraFinalActivaciondesactivacion());
                    temp.setFechaHoraInicioActivacionDesactivacion(comando.getFechaHoraInicioActivaciondesactivacion());
                    temp.setActivadoDesactivado(comando.getActivadoDesactivado());
                    dataManager.UpdatePuerta(temp);

                    System.out.println(temp.getCanal() + " " + temp.getIdPuerta() + " " + temp.getActivadoDesactivado()
                            + " fecha" + temp.getFechaHoraInicioActivacionDesactivacion() + " "
                            + temp.getFechaHoraFinalActivacionDesactivacion());
                } else {
                    // Handle the case where activadoDesactivado is not 1 or 2
                    System.out.println("Invalid value for activadoDesactivado: " + comando.getActivadoDesactivado());
                    // You may want to throw an exception, log an error, or take appropriate action here.
                }

            } catch (Exception e) {
                System.out.println("Error en boton manual CDEG");
            }
            break;
            case Constantes.Comandos.REPRODUCCION:
                //System.out.println(new Gson().toJson(puerta1));

                datoAux.setProcesarReproduccion(false);
                temp.setProcesarReproduccion(false);
                datoAux.setFechaHoraInicioReproducción(comando.getFechaHoraInicioReproduccion());
                temp.setFechaHoraInicioReproduccion(comando.getFechaHoraInicioReproduccion());
                datoAux.setFechaHoraFinReproducción(comando.getFechaHoraFinalReproduccion());
                temp.setFechaHoraFinalReproduccion(comando.getFechaHoraFinalReproduccion());
                datoAux.setCantidadReproduccion(comando.getCantidadReproduccion());
                temp.setCantidadReproduccion(comando.getCantidadReproduccion());
                datoAux.setCodigoMensajeReproducir(comando.getCodigoMensajeReproducir());
                temp.setCodigoMensajeReproduccion(comando.getCodigoMensajeReproducir());
                ArmarEventos(datoAux);
                dataManager.UpdatePuerta(temp);
                break;
            case Constantes.Comandos.APERTURA:
                JSONObject envio = auxService.ComandoAperturaPuertaCDEG(comandoCrudo, temp, numeroVagon(comandoCrudo.getIdVagon()));
                AddComando(comandoCrudo.getIdVagon(), envio);
                //publisherMQTTServiceInterno.Publisher(envio.toString().getBytes(), comandoCrudo.getIdVagon());
                break;
            case Constantes.Comandos.CIERRE:
                comandoCrudo.setFuncion("CMD");
                String tramaC = "00";
                tramaC += String.format("%02x", (Integer.parseInt(temp.getIdPuerta()) & 0xFF));
                String consecutivoC = "01";
                tramaC += consecutivoC + "7101";
                tramaC += "0404";
                tramaC += "00";
                comandoCrudo.setIdVagon(numeroVagon(comandoCrudo.getIdVagon()));
                comandoCrudo.setTrama(tramaC);
                JSONObject envioC = new JSONObject();
                envioC.put("origen", comandoCrudo.getOrigen());
                envioC.put("funcion", comandoCrudo.getFuncion());
                envioC.put("idVagon", comandoCrudo.getIdVagon());
                envioC.put("canal", comandoCrudo.getCanal());
                envioC.put("idPuerta", temp.getIdPuerta());
                envioC.put("trama", comandoCrudo.getTrama());
                System.out.println(envioC + "CIERRE CDG");
                AddComando(comandoCrudo.getIdVagon(), envioC);
                //publisherMQTTServiceInterno.Publisher(envioC.toString().getBytes(), comandoCrudo.getIdVagon());
                break;
        }
        //ArmarEventos();
    }

    private void ProcesarMLAN(String dato) {
        //System.out.println("DATO MLAN " + dato);
        String[] datos = ((dato.split(">")[1]).split("<")[0]).split(",");
        if (datos.length == 10) {
            try {
                vagon.setCodigoEvento("EVP12");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date date = formatter.parse(datos[1] + " " + datos[2]);
                vagon.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(date)));
                vagon.setTipoTramaBusEstacion(datos[0]);
                vagon.setNombreEstacion(datos[3]);
                vagon.setNombreVagon("W" + datos[4]);
                vagon.setIdVagon(vagon.getNombreVagon());
                //cambio
                String nombreParada = idEstacion + "-" + vagon.getNombreVagon() + "-" + nombreParada(vagon.getNombreVagon(), datos[5]);

                vagon.setNumeroParada(nombreParada);

                vagon.setNumeroEventoBusEstacion(Integer.parseInt(datos[6]));
                vagon.setIdVehiculo(datos[7]);
                vagon.setPlacaVehiculo(datos[8]);
                vagon.setTipologiaVehiculo(datos[9]);
                vagon.setIdPuerta("BUS-ESTACION");
                vagon.setCodigoPuerta("BUS-ESTACION");
                vagonA.set(2, vagon);
                ArmarEventos(vagon);
            } catch (ParseException ex) {
                System.err.println(ex.getLocalizedMessage() + "");
            }
        }
    }

    //TOCA REVISARLO !
    private void VerificarBotonUsuario() {
        List<Puerta> aux = dataManager.GetPuertas();
        for (Puerta puerta : aux) {

            OP_RegistroCrudo comando = new OP_RegistroCrudo();
            comando.setIdVagon(puerta.getVagon());
            comando.setIdPuerta(puerta.getIdPuerta());
            comando.setCanal(puerta.getCanal());
            comando.setFuncion("CMD");
            String trama = "00";
            trama += String.format("%02x", (Integer.parseInt(puerta.getIdPuerta()) & 0xFF));
            trama += "00710107";
            if (puerta.getActivadoDesactivado() != null) {
                //System.out.println("entro " + puerta.getCanal() + " " + puerta.getVagon() + " " + puerta.getIdPuerta() + " " + puerta.getActivadoDesactivado());

                try {
                    if (puerta.getFechaHoraInicioActivacionDesactivacion() == null && puerta.getFechaHoraFinalActivacionDesactivacion() == null) {
                        //zona = "ninguna";
                        //ACTIVAR/DESACTIVAR INMEDIATAMENTE
                        switch (puerta.getActivadoDesactivado()) {
                            case 1:
//                                System.out.println("boton activado");
                                trama += "0100";
                                puerta.setEstadoBotonManual(1);
                                comando.setTrama(trama);
                                System.out.println(new Gson().toJson(comando));
                                AddComando(comando.getIdVagon(), new JSONObject(new Gson().toJson(comando)));
                                //publisherMQTTServiceInterno.Publisher(new Gson().toJson(comando).getBytes(), comando.getIdVagon());
                                puerta.setActivadoDesactivado(null);
                                dataManager.UpdatePuerta(puerta);
                                log("Activar boton usuario - V" + puerta.getVagon() + "-CH" + puerta.getCanal() + "-P" + puerta.getIdPuerta());  //jal log test
                                break;

                            case 2:
//                                System.out.println("boton desactivado");
                                trama += "0000";
                                puerta.setEstadoBotonManual(1);
                                comando.setTrama(trama);
                                System.out.println(new Gson().toJson(comando));
                                AddComando(comando.getIdVagon(), new JSONObject(new Gson().toJson(comando)));
                                //publisherMQTTServiceInterno.Publisher(new Gson().toJson(comando).getBytes(), comando.getIdVagon());
                                puerta.setActivadoDesactivado(null);
                                dataManager.UpdatePuerta(puerta);
                                log("Desactivar boton usuario - V" + puerta.getVagon() + "-CH" + puerta.getCanal() + "-P" + puerta.getIdPuerta());  //jal log test
                                break;

                            default:
                                break;
                        }
                    }

                    if (formatoFecha.parse(puerta.getFechaHoraInicioActivacionDesactivacion()).before(new Date()) && formatoFecha.parse(puerta.getFechaHoraFinalActivacionDesactivacion()).after(new Date())) {
                        //zona = "rango";
                        switch (puerta.getActivadoDesactivado()) {
                            case 1:
//                                System.out.println("entro en rango 1 - boton activado");
                                trama += "0100";
                                puerta.setEstadoBotonManual(1);
                                comando.setTrama(trama);
                                System.out.println(new Gson().toJson(comando));
                                AddComando(comando.getIdVagon(), new JSONObject(new Gson().toJson(comando)));
                                //publisherMQTTServiceInterno.Publisher(new Gson().toJson(comando).getBytes(), comando.getIdVagon());
                                puerta.setActivadoDesactivado(201);
                                dataManager.UpdatePuerta(puerta);
                                log("Activar boton usuario - V" + puerta.getVagon() + "-CH" + puerta.getCanal() + "-P" + puerta.getIdPuerta());  //jal log test
                                break;

                            case 2:
//                                System.out.println("entro en rango 2 - boton desactivado");
                                trama += "0000";
                                puerta.setEstadoBotonManual(1);
                                comando.setTrama(trama);
                                System.out.println(new Gson().toJson(comando));
                                AddComando(comando.getIdVagon(), new JSONObject(new Gson().toJson(comando)));
                                //publisherMQTTServiceInterno.Publisher(new Gson().toJson(comando).getBytes(), comando.getIdVagon());
                                puerta.setActivadoDesactivado(202);
                                dataManager.UpdatePuerta(puerta);
                                log("Desactivar boton usuario - V" + puerta.getVagon() + "-CH" + puerta.getCanal() + "-P" + puerta.getIdPuerta());  //jal log test
                                break;

                            default:
                                break;
                        }
                    }

                    if (formatoFecha.parse(puerta.getFechaHoraFinalActivacionDesactivacion()).before(new Date())) {
                        //zona = "despues";
                        switch (puerta.getActivadoDesactivado()) {
                            case 1:
                            case 201:
//                                System.out.println("salio de rango 1 - boton desactivado");
                                trama += "0000";
                                puerta.setEstadoBotonManual(1);
                                comando.setTrama(trama);
                                System.out.println(new Gson().toJson(comando));
                                AddComando(comando.getIdVagon(), new JSONObject(new Gson().toJson(comando)));
                                //publisherMQTTServiceInterno.Publisher(new Gson().toJson(comando).getBytes(), comando.getIdVagon());
                                puerta.setActivadoDesactivado(null);
                                dataManager.UpdatePuerta(puerta);
                                log("Desactivar boton usuario - V" + puerta.getVagon() + "-CH" + puerta.getCanal() + "-P" + puerta.getIdPuerta());  //jal log test
                                break;

                            case 2:
                            case 202:
//                                System.out.println("salio de rango 2 - boton activado");
                                trama += "0100";
                                puerta.setEstadoBotonManual(1);
                                comando.setTrama(trama);
                                System.out.println(new Gson().toJson(comando));
                                AddComando(comando.getIdVagon(), new JSONObject(new Gson().toJson(comando)));
                                //publisherMQTTServiceInterno.Publisher(new Gson().toJson(comando).getBytes(), comando.getIdVagon());
                                puerta.setActivadoDesactivado(null);
                                dataManager.UpdatePuerta(puerta);
                                log("Activar boton usuario - V" + puerta.getVagon() + "-CH" + puerta.getCanal() + "-P" + puerta.getIdPuerta());  //jal log test
                                break;

                            default:
                                break;
                        }
                    }
                } catch (ParseException ex) {
                    System.err.println(ex.getLocalizedMessage() + "");
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                System.err.println(ex.getLocalizedMessage() + "");
            }
        }
    }

    private void VerificarReproducciónAudio() {
        List<Puerta> aux = dataManager.GetPuertas();
        for (Puerta puerta : aux) {
            DatoCDEG datoAux = new DatoCDEG();
            datoAux.setVersionTrama(versionTrama);
            datoAux.setIdOperador(idOperador);
            datoAux.setTipoTrama(2);
            datoAux.setCanal(puerta.getCanal());
            datoAux.setIdVagon(nombreVagon(puerta.getVagon()));
            datoAux.setCodigoPuerta(puerta.getDescripcion());
            datoAux.setIdPuerta(puerta.getDescripcion());
            try {
                if (puerta.getFechaHoraInicioReproduccion() != null && puerta.getFechaHoraFinalReproduccion() != null && formatoFecha.parse(puerta.getFechaHoraFinalReproduccion()).after(new Date()) && formatoFecha.parse(puerta.getFechaHoraInicioReproduccion()).before(new Date())) {
                    if (!puerta.getProcesarReproduccion()) {
                        puerta.setProcesarReproduccion(true);
                        //datoAux.setProcesarReproduccion(Boolean.TRUE);
                        puerta.setCantidadReproduccionT(0);
                        long diff = formatoFecha.parse(puerta.getFechaHoraFinalReproduccion()).getTime() - formatoFecha.parse(puerta.getFechaHoraInicioReproduccion()).getTime();
                        TimeUnit time = TimeUnit.MINUTES;
                        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
                        puerta.setTiempoReproduccion((int) (diffrence / (puerta.getCantidadReproduccion())));
                        datoAux.setTiempoReproduccion(puerta.getTiempoReproduccion());
                        puerta.setTiempoReproduccionT(puerta.getTiempoReproduccion() * 600);
                        datoAux.setTiempoReproduccionT(puerta.getTiempoReproduccionT());
                    }
                }
                if (puerta.getCantidadReproduccionT() != null && puerta.getCantidadReproduccionT() < puerta.getCantidadReproduccion()) {
                    puerta.setTiempoReproduccionT(puerta.getTiempoReproduccionT() + 1);
                    if (puerta.getTiempoReproduccionT() >= (puerta.getTiempoReproduccion() * 600)) {
                        puerta.setTiempoReproduccionT(0);
                        //datoAux.setTiempoReproduccionT(0);
                        puerta.setCantidadReproduccionT(puerta.getCantidadReproduccionT() + 1);
                        //datoAux.setCantidadReproduccion(puerta.getCantidadReproduccion());
                        OP_RegistroCrudo comando = new OP_RegistroCrudo();
                        comando.setIdVagon(puerta.getVagon());
                        comando.setCanal(puerta.getCanal());
                        comando.setIdPuerta(puerta.getIdPuerta());
                        String trama = "00";
                        trama += String.format("%02x", (Integer.parseInt(puerta.getIdPuerta()) & 0xFF));
                        trama += "00710106";
                        trama += String.format("%02d", (puerta.getCodigoMensajeReproduccion() & 0xFF));
                        trama += "00";
                        comando.setTrama(trama);
                        comando.setFuncion("CMD");
                        System.out.println("comando reproduccion " + (JSONObject) JSONObject.wrap(comando));

                        AddComando(comando.getIdVagon(), new JSONObject(new Gson().toJson(comando)));
                        //publisherMQTTServiceInterno.Publisher(new Gson().toJson(comando).getBytes(), comando.getIdVagon());

                    }
                }
                dataManager.UpdatePuerta(puerta);
            } catch (Exception ex) {

            }
            ArmarEventos(datoAux);
        }
    }

    private void InicioOperacion() {
        try {
            // Get the current time as hours and minutes
            int currentHour = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));

            // Check if the current time is between 4:00 AM and 5:00 AM
            if (new SimpleDateFormat("HHmm").parse(new SimpleDateFormat("HHmm").format(new Date())).after(new SimpleDateFormat("HHmm").parse(horaInicioOperacion))) {
                for (Puerta temp : dataManager.GetPuertas()) {
                    DatoCDEG puerta = new DatoCDEG();
                    puerta.setVersionTrama(versionTrama);
                    puerta.setIdOperador(idOperador);
                    puerta.setTipoTrama(2);
                    puerta.setCanal(temp.getCanal());
                    puerta.setIdVagon(temp.getVagon());
                    puerta.setCodigoPuerta(temp.getDescripcion());
                    puerta.setIdPuerta(temp.getDescripcion());
                    cast.datosPuerta(temp, puerta);
                    Boolean ce = temp.isEstadoAperturaCierre();
                    if (ce != null && ce) {
                        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                        if (!fechaInicioOperacion.equalsIgnoreCase(fecha)) {
                            OP_Parametro parametro = new OP_Parametro();
                            parametro.setId(14);
                            parametro.setNombre("FechaInicioOperacion");
                            parametro.setValor(fecha);
                            fechaInicioOperacion = fecha;
                            dataManager.UpdateParametros(parametro);
                            puerta.setIdVagon("INICIO-OP");
                            puerta.setIdPuerta("INICIO-OP");
                            puerta.setCodigoPuerta("INICIO-OP");
                            puerta.setCodigoEvento("EVP8");
                            puerta.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                            ArmarEventos(puerta);
                            break;
                        }
                    }

                }
            } else {
                // Handle the case when the current time is not within the specified range
                // You can log a message or take any appropriate action here.
            }
        } catch (Exception e) {
            System.out.println("Error en INICIO OPERACION");
        }
    }

    private void FinOperacion() {
        try {

            DatoCDEG estacion = new DatoCDEG();
            if (new SimpleDateFormat("HHmm").parse(new SimpleDateFormat("HHmm").format(new Date())).after(new SimpleDateFormat("HHmm").parse(horaFinOperacion)) && new SimpleDateFormat("HHmm").parse(new SimpleDateFormat("HHmm").format(new Date())).before(new SimpleDateFormat("HHmm").parse(horaLimite))) {
                tiempoFinOperacion += 1;
                for (Puerta puerta : dataManager.GetPuertas()) {
                    if (puerta.isEstadoAperturaCierre() != null && puerta.isEstadoAperturaCierre()) {
                        tiempoFinOperacion = 0;
                    }
                }
                if (tiempoFinOperacion > Integer.parseInt(tiempoFinOperacionDB)) {
                    tiempoFinOperacion = 0;
                    String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                    if (!fechaFinOperacion.equalsIgnoreCase(fecha)) {
                        fechaFinOperacion = fecha;
                        OP_Parametro parametro = new OP_Parametro();
                        parametro.setId(15);
                        parametro.setNombre("FechaFinOperacion");
                        parametro.setValor(fecha);
                        dataManager.UpdateParametros(parametro);
                        estacion.setIdVagon("FIN-OP");
                        estacion.setIdPuerta("FIN-OP");
                        estacion.setCodigoPuerta("FIN-OP");
                        estacion.setCodigoEvento("EVP9");
                        estacion.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                        ArmarEventos(estacion);
                    }
                }
            } else if (new SimpleDateFormat("HHmm").parse(new SimpleDateFormat("HHmm").format(new Date())).after(new SimpleDateFormat("HHmm").parse(horaLimite))) {
                String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                if (!fechaFinOperacion.equalsIgnoreCase(fecha)) {
                    fechaFinOperacion = fecha;
                    OP_Parametro parametro = new OP_Parametro();
                    parametro.setId(15);
                    parametro.setNombre("FechaFinOperacion");
                    parametro.setValor(fecha);
                    dataManager.UpdateParametros(parametro);
                    estacion.setIdVagon("FIN-OP");
                    estacion.setIdPuerta("FIN-OP");
                    estacion.setCodigoPuerta("FIN-OP");
                    estacion.setCodigoEvento("EVP9");
                    estacion.setFechaHoraLecturaDato(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                    ArmarEventos(estacion);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en FIN OPERACION " + e);
        }
    }

    private void EnviarRegistro() {
        List<OP_RegistroTemporal> registros = dataManager.GetRegistroTemporalByCDEG();

        for (OP_RegistroTemporal registroTemporal : registros) {

            if (!registroTemporal.isestadoEnvio()) {
                envio(registroTemporal);
            }

        }

    }

    private void ComprobarAlarmas1(DatoCDEG dato) {
        String idRegistro = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + consecutivo();
        dato.setIdRegistro(Long.parseLong(idRegistro));
        for (CFG_NivelAlarma nivelAlarma : nivelesAlarma) {
            if (nivelAlarma.getAlarma().getNombre().equalsIgnoreCase("ALAP1") && dato.getPorcentajeCargaBaterias() != null) {
                if ((dato.getPorcentajeCargaBaterias() >= nivelAlarma.getNivelInferior()) && (dato.getPorcentajeCargaBaterias() < nivelAlarma.getNivelSuperior())) {
                    if (dato.getNivelAlarma1() == null || !dato.getNivelAlarma1().equals(nivelAlarma.getValor())) {
                        if ((dato.getNivelAlarmaSuperior1() == null) || dato.getNivelAlarmaSuperior1() < nivelAlarma.getNivelSuperior()) {
                            dato.setNivelAlarmaSuperior1(nivelAlarma.getNivelSuperior());
                        }
                        dato.setNivelAlarma1(nivelAlarma.getValor());
                        dato.setNivelAlarma(nivelAlarma.getValor());
                        dato.setCodigoAlarma(nivelAlarma.getAlarma().getNombre());
                        dato.setCodigoNivelAlarma(nivelAlarma.getValor());

                    }
                } else if (dato.getNivelAlarmaSuperior1() != null && (dato.getNivelAlarmaSuperior1() <= dato.getPorcentajeCargaBaterias())) {
                    dato.setNivelAlarma1(null);
                    dato.setNivelAlarma(null);
                }
            }

        }

    }

    private void ComprobarAlarmas2(DatoCDEG dato) {
        dato.setIdRegistro(Long.parseLong(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + consecutivo()));
        for (CFG_NivelAlarma nivelAlarma : nivelesAlarma) {

            if (nivelAlarma.getAlarma().getNombre().equalsIgnoreCase("ALAP2") && dato.getTiempoApertura() != null) {
                if ((dato.getTiempoApertura() >= nivelAlarma.getNivelInferior()) && (dato.getTiempoApertura() < nivelAlarma.getNivelSuperior())) {
                    if (dato.getNivelAlarma2() == null || !dato.getNivelAlarma2().equals(nivelAlarma.getValor())) {
                        if ((dato.getNivelAlarmaInferior2() == null) || dato.getNivelAlarmaInferior2() > nivelAlarma.getNivelInferior()) {
                            dato.setNivelAlarmaInferior2(nivelAlarma.getNivelInferior());
                        }
                        dato.setNivelAlarma2(nivelAlarma.getValor());
                        dato.setNivelAlarma(nivelAlarma.getValor());
                        dato.setCodigoAlarma(nivelAlarma.getAlarma().getNombre());
                        dato.setCodigoNivelAlarma(nivelAlarma.getValor());

                    }
                } else if (dato.getNivelAlarmaInferior2() != null && (dato.getNivelAlarmaInferior2() > dato.getTiempoApertura())) {
                    dato.setNivelAlarma2(null);
                    dato.setNivelAlarma(null);
                }
            }
        }

    }

    private void InicializarVagones() {
        puerta1.setVersionTrama(versionTrama);
        puerta1.setIdOperador(idOperador);
        puerta1.setIdEstacion(idEstacion);
        puerta1.setTipoTrama(2);
        puerta1.setIdPuerta("9115-WA-OR-1");
        puerta1.setIdVagon("1");
        puerta1.setCodigoPuerta("9115-WA-OR-1");
        puerta1.setEstadoBotonManual(2);
        puerta1.setCanal("1");
        vagonA.add(puerta1);

        puerta2.setVersionTrama(versionTrama);
        puerta2.setIdOperador(idOperador);
        puerta2.setIdEstacion(idEstacion);
        puerta2.setTipoTrama(2);
        puerta2.setIdPuerta("9115-WA-OR-2");
        puerta2.setIdVagon("1");
        puerta2.setCodigoPuerta("9115-WA-OR-2");
        puerta2.setEstadoBotonManual(2);
        puerta2.setCanal("1");
        vagonA.add(puerta2);

        vagon.setVersionTrama(versionTrama);
        vagon.setIdOperador(idOperador);
        vagon.setIdEstacion(idEstacion);
        vagon.setTipoTrama(2);
        vagon.setIdVagon("1");
        vagonA.add(vagon);

        estacion.setVersionTrama(versionTrama);
        estacion.setIdOperador(idOperador);
        estacion.setIdEstacion(idEstacion);
        estacion.setTipoTrama(2);
        vagonA.add(estacion);
    }

    public void actualizarCampos(String trama) {
        GetCamposValidos();
        CFG_Configuracion cfg_Configuracion = dataManager.GetConfiguracion();
        if (cfg_Configuracion == null) {
            cfg_Configuracion = new CFG_Configuracion();
            System.out.println("Updated Configuration");

            Configuracion configuracion = cast.JSONtoConfiguracion(trama);
            cfg_Configuracion.setTrama(trama);
            dataManager.AddConfiguracion(cfg_Configuracion);
            ///Update Cabecera
            DeleteCamposCabecera();
            for (String cabecera : configuracion.getCabecera()) {
                for (CFG_CamposValidos campoValido : camposValidos) {
                    if (campoValido.getTipoCampoValido().equalsIgnoreCase("Cabecera")) {
                        if (campoValido.getNombre().equals(cabecera)) {
                            CFG_CamposCabecera campo = new CFG_CamposCabecera();
                            campo.setConfiguracion(cfg_Configuracion);
                            campo.setCamposValidos(campoValido);
                            try {
                                dataManager.AddCamposCabecera(campo);
                                GetCamposCabecera();
                            } catch (Exception e) {
                                System.out.println("Error en agregar campos cabecera del CDEG");
                            }
                        }
                    }
                }
            }
            ///Update Evento
            DeleteCamposEvento();
            CFG_CamposEvento campo = new CFG_CamposEvento();
            List<Evento> listEventos = new ArrayList<>();
            for (Evento evento : configuracion.getEvento()) {
                evento = UpdateIdEvento(evento);
                listEventos.add(evento);
            }
            configuracion.setEvento(listEventos);
            for (Evento e : configuracion.getEvento()) {
                for (CFG_CamposValidos campoValido : camposValidos) {
                    if (campoValido.getTipoCampoValido().equalsIgnoreCase("Evento")) {
                        for (String variable : e.getVariable()) {
                            if (campoValido.getNombre().equalsIgnoreCase(variable)) {
                                campo.setEvento(cast.EventotoCFG_Evento(e));
                                campo.setConfiguracion(cfg_Configuracion);
                                campo.setCamposValidos(campoValido);
                                try {
                                    dataManager.AddCamposEvento(campo);
                                    GetCamposEventos();
                                } catch (Exception ex) {
                                    System.out.println("Error en añadir campos Eventos CDEG");
                                }
                            }
                        }
                    }
                }
            }
            DeleteCamposAlarma();
            DeleteNivelAlarma();
            CFG_CamposAlarma campoAlarma = new CFG_CamposAlarma();
            List<Alarma> listAlarmas = new ArrayList<>();
            for (Alarma alarma : configuracion.getAlarma()) {
                alarma = UpdateIdAlarma(alarma);
                listAlarmas.add(alarma);
            }
            configuracion.setAlarma(listAlarmas);

            for (Alarma a : configuracion.getAlarma()) {
                for (CFG_CamposValidos campoValido : camposValidos) {
                    if (campoValido.getTipoCampoValido().equalsIgnoreCase("Alarma")) {
                        if (campoValido.getNombre().equalsIgnoreCase(a.getVariableAlarma())) {
                            campoAlarma.setAlarma(getAlarma(a));
                            campoAlarma.setConfiguracion(cfg_Configuracion);
                            campoAlarma.setCamposValidos(campoValido);

                            try {
                                dataManager.AddCamposAlarma(campoAlarma);
                                GetCamposAlarmas();
                            } catch (Exception e) {
                                System.out.println("Error en añadir Campos Alarma CDEG" + e.getLocalizedMessage());
                            }
                        }
                    }
                    if (campoValido.getNombre().equalsIgnoreCase("codigoAlarma") || campoValido.getNombre().equalsIgnoreCase("codigoNivelAlarma")) {
                        campoAlarma.setAlarma(getAlarma(a));
                        campoAlarma.setConfiguracion(cfg_Configuracion);
                        campoAlarma.setCamposValidos(campoValido);
                        try {
                            dataManager.AddCamposAlarma(campoAlarma);
                            GetCamposAlarmas();
                        } catch (Exception e) {
                            System.out.println("Error en añadir Campos Alarma CDEG 2" + e.getLocalizedMessage());
                        }

                    }
                }
                //NivelAlarma nivelAlarma = new NivelAlarma();
                for (NivelAlarma nivel : a.getNivelAlarma()) {
                    CFG_NivelAlarma nivelAlarma = cast.NivelAlarmatoCFG_NivelAlarma(nivel);
                    nivelAlarma.setAlarma(getAlarma(a));
                    try {
                        dataManager.AddNivelAlarma(nivelAlarma);
                    } catch (Exception ex) {
                        System.err.println(ex.getLocalizedMessage() + "");
                    }
                }

                //nivelAlarma.setCodigoNivelAlarma(a.getNivelAlarma());
            }
        }
    }

    public CFG_Alarma getAlarma(Alarma a) {
        GetAlarmas();
        CFG_Alarma ret = null;
        for (CFG_Alarma alap : alarmas) {
            if (alap.getNombre().equalsIgnoreCase(a.getCodigoAlarma())) {
                ret = alap;
                break;
            }
        }
        return ret;
    }

    public void cargarConfiguracionCDEG() {
        String jsonlec = "";
        try {
            File doc = new File("./ConfiguracionCDEG/configuracion.txt");
            //System.out.println(doc.getAbsolutePath());
            String lec;
            BufferedReader text = new BufferedReader(new FileReader(doc));
            while ((lec = text.readLine()) != null) {

                jsonlec += lec + "\n";

            }
            actualizarCampos(jsonlec);
            //System.out.println(jsonlec);
        } catch (Exception ex) {
            System.out.println("Error en cargar la configuracion del CDEG" + ex.getLocalizedMessage());
        }

    }

    public void cargarConfiguracion() throws IOException {
        formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        //System.out.println(nombreEstacion);
        String jsonlec = "";
        try {
            File doc = new File("./Configuracion/configuracion.txt");
            //System.out.println(doc.getAbsolutePath());
            String lec;
            BufferedReader text = new BufferedReader(new FileReader(doc));
            while ((lec = text.readLine()) != null) {

                jsonlec += lec + "\n";

            }

            //System.out.println(jsonlec);
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage() + "");
        }
        try {
            JSONObject configuracion = new JSONObject(jsonlec);
            PrimerVagonDerecha = configuracion.getString("PrimerVagonDerecha");
            CostadoPI = configuracion.getString("CostadoPI");
            CanalCostadoPI = configuracion.getString("CanalCostadoPI");
            JSONObject confEstacion = configuracion.getJSONObject(configuracion.getString("nombreEstacion"));
            JSONObject mlan = new JSONObject(configuracion.getJSONObject("MLAN").toString());
            JSONObject manatee = new JSONObject(configuracion.getJSONObject("MQTTMANATEE").toString());
            JSONObject inicio = configuracion.getJSONObject("inicioOperacion");
            JSONObject fin = configuracion.getJSONObject("finOperacion");
            JSONArray puertasTemp = new JSONArray(confEstacion.getJSONArray("Puertas"));
            JSONArray paradasT = mlan.getJSONArray("paradas");
            brokerManatee = manatee.getString("broker");
            claveManatee = manatee.getString("clave");
            usuarioManatee = manatee.getString("usuario");
            topicManatee = manatee.getString("topic");
            JSONObject mqttcdeg = configuracion.getJSONObject("MQTTCDEG");
            horaLimite = fin.getString("HoraLimite");
            registro = mqttcdeg.getString("registro");
            region = mqttcdeg.getString("region");
            proyecto = mqttcdeg.getString("proyecto");

            activado = (int) configuracion.getNumber("ACTIVADO");
            ModoACK = (int) configuracion.getNumber("ACK");
            switch (activado) {
                case 1:
                    log("Modo 1");
                    System.exit(0);
                    break;
                
                case 2:
                    log("Modo 2");
                    System.out.println("");
                    System.out.println("");

                    Date dateIni = new Date();
                    System.out.println(formatoFecha.format(dateIni) + " - INICIO BORRADO DE TABLAS ...");
                    log("INICIO BORRADO DE TABLAS ...");
                    System.out.println("");

                    System.out.println(formatoFecha.format(new Date()) + " - Borrando...       tabla OP_Parametros: " + dataManager.parametrosSize());
                    log("Borrando...       tabla OP_Parametros: " + dataManager.parametrosSize());
                    dataManager.deletParametros(true);
                    System.out.println(formatoFecha.format(new Date()) + " - Borrado OK        tabla OP_Parametros: " + dataManager.parametrosSize());
                    log("Borrado OK        tabla OP_Parametros: " + dataManager.parametrosSize());
                    System.out.println("");

                    System.out.println(formatoFecha.format(new Date()) + " - Borrando...         tabla OP_Registro: " + dataManager.registroSize());
                    log("Borrando...         tabla OP_Registro: " + dataManager.registroSize());
                    dataManager.DeleteRegistros();
                    System.out.println(formatoFecha.format(new Date()) + " - Borrado OK          tabla OP_Registro: " + dataManager.registroSize());
                    log("Borrado OK          tabla OP_Registro: " + dataManager.registroSize());
                    System.out.println("");

                    System.out.println(formatoFecha.format(new Date()) + " - Borrando...    tabla OP_RegistroCrudo: " + dataManager.registroCrudoSize());
                    log("Borrando...    tabla OP_RegistroCrudo: " + dataManager.registroCrudoSize());
                    dataManager.DeleteRegistrosCrudos();
                    System.out.println(formatoFecha.format(new Date()) + " - Borrado OK     tabla OP_RegistroCrudo: " + dataManager.registroCrudoSize());
                    log("Borrado OK     tabla OP_RegistroCrudo: " + dataManager.registroCrudoSize());
                    System.out.println("");

                    System.out.println(formatoFecha.format(new Date()) + " - Borrando... tabla OP_RegistroTemporal: " + dataManager.registroTemporalSize());
                    log("Borrando... tabla OP_RegistroTemporal: " + dataManager.registroTemporalSize());
                    dataManager.deleteRegistrosTemporales();
                    System.out.println(formatoFecha.format(new Date()) + " - Borrado OK  tabla OP_RegistroTemporal: " + dataManager.registroTemporalSize());
                    log("Borrado OK  tabla OP_RegistroTemporal: " + dataManager.registroTemporalSize());
                    System.out.println("");

                    System.out.println(formatoFecha.format(new Date()) + " - Borrando...             tabla Puertas: " + dataManager.puertasSize());
                    log("Borrando...             tabla Puertas: " + dataManager.puertasSize());
                    dataManager.DeletePuertas();
                    System.out.println(formatoFecha.format(new Date()) + " - Borrado OK              tabla Puertas: " + dataManager.puertasSize());
                    log("Borrado OK              tabla Puertas: " + dataManager.puertasSize());
                    System.out.println("");

                    System.out.println(formatoFecha.format(new Date()) + " - Borrando...            tabla ACKVAGON: " + dataManager.ackVagonSize());
                    log("Borrando...            tabla ACKVAGON: " + dataManager.ackVagonSize());
                    dataManager.deleteAllVagonACK();
                    System.out.println(formatoFecha.format(new Date()) + " - Borrado OK             tabla ACKVAGON: " + dataManager.ackVagonSize());
                    log("Borrado OK             tabla ACKVAGON: " + dataManager.ackVagonSize());
                    System.out.println("");

                    System.out.println(formatoFecha.format(dateIni) + " - INICIO BORRADO DE TABLAS");
                    log(formatoFecha.format(dateIni) + " - INICIO BORRADO DE TABLAS");
                    System.out.println(formatoFecha.format(new Date()) + " - FIN    BORRADO DE TABLAS");
                    log(formatoFecha.format(new Date()) + " - FIN    BORRADO DE TABLAS");
                    System.out.println("");
                    System.out.println("");
                    break;
                case 3:
                    log("Modo 3");
                    dataManager.deletParametros(false);
                    dataManager.deleteAllVagonACK();
                    addParametrosSinFecha(configuracion, confEstacion, mlan, mqttcdeg);
                    break;
                default:
                    log("Modo " + activado);
                    break;

            }

            if (dataManager.GetParametros().isEmpty()) {
                OP_Parametro conftemp = new OP_Parametro();
                conftemp.setId(1);
                conftemp.setNombre("Estacion");
                conftemp.setValor(configuracion.getString("nombreEstacion"));
                dataManager.AddParametros(conftemp);
                conftemp.setId(2);
                conftemp.setNombre("servidorLocalMQTT");
                conftemp.setValor(configuracion.getString("servidoLocalMQTT"));
                dataManager.AddParametros(conftemp);

                conftemp.setId(3);
                conftemp.setNombre("servidorExternoMQTT");
                conftemp.setValor(mqttcdeg.getString("mqttServerAddress"));
                dataManager.AddParametros(conftemp);
                conftemp.setId(4);
                conftemp.setNombre("Dispositivo");
                conftemp.setValor(configuracion.getString("dispositivo"));
                dataManager.AddParametros(conftemp);
                conftemp.setId(5);
                conftemp.setNombre("MacEthernet");
                conftemp.setValor(configuracion.getString("MacEthernet"));
                dataManager.AddParametros(conftemp);
                conftemp.setId(6);
                conftemp.setNombre("MacWifi");
                conftemp.setValor(configuracion.getString("MacWifi"));
                dataManager.AddParametros(conftemp);
                conftemp.setId(7);
                conftemp.setNombre("MacBluetooth");
                conftemp.setValor(configuracion.getString("MacBluetooth"));
                dataManager.AddParametros(conftemp);
                conftemp.setId(8);
                conftemp.setNombre("IdOperador");
                conftemp.setValor(configuracion.getString("idOperador"));
                dataManager.AddParametros(conftemp);
                conftemp.setId(9);
                conftemp.setNombre("IdEstacion");
                conftemp.setValor(confEstacion.getString("idEstacion"));
                dataManager.AddParametros(conftemp);
                conftemp.setId(10);
                conftemp.setNombre("versionTrama");
                conftemp.setValor(configuracion.getString("versionTrama"));
                dataManager.AddParametros(conftemp);

                //Hora inicio operacion
                conftemp.setId(11);
                conftemp.setNombre("HoraInicioOperacion");
                conftemp.setValor(inicio.getString("HoraInicioOperacion"));
                dataManager.AddParametros(conftemp);

                conftemp.setId(12);
                conftemp.setNombre("HoraFinOperacion");
                conftemp.setValor(fin.getString("HoraFinOperacion"));
                dataManager.AddParametros(conftemp);

                conftemp.setId(13);
                conftemp.setNombre("TiempoFinOperacion");
                conftemp.setValor(fin.getString("TiempoFinOperacion"));
                dataManager.AddParametros(conftemp);

                conftemp.setId(14);
                conftemp.setNombre("FechaInicioOperacion");
                conftemp.setValor(inicio.getString("FechaInicioOperacion"));
                dataManager.AddParametros(conftemp);

                conftemp.setId(15);
                conftemp.setNombre("FechaFinOperacion");
                conftemp.setValor(fin.getString("FechaFinOperacion"));
                dataManager.AddParametros(conftemp);

                conftemp.setId(16);
                conftemp.setNombre("DireccionMLAN");
                conftemp.setValor(mlan.getString("DireccionMLAN"));
                dataManager.AddParametros(conftemp);

                conftemp.setId(17);
                conftemp.setNombre("PuertoReceptorMLAN");
                conftemp.setValor(mlan.getString("PuertoReceptorMLAN"));
                dataManager.AddParametros(conftemp);

                conftemp.setId(18);
                conftemp.setNombre("PuertoTransmisorMLAN");
                conftemp.setValor(mlan.getString("PuertoTransmisorMLAN"));
                dataManager.AddParametros(conftemp);

            }

            if (dataManager.GetPuertas().isEmpty()) {

                Puerta tp = new Puerta();
                for (int i = 0; i < puertasTemp.length(); i++) {
                    JSONObject pt = puertasTemp.getJSONObject(i);
                    tp.setIdPuerta(pt.getString("idPuerta"));
                    tp.setCanal(pt.getString("canal"));
                    tp.setDescripcion(pt.getString("descripcion"));
                    tp.setVagon(pt.getString("vagon"));
                    tp.setId(i + 1);
                    dataManager.AddPuerta(tp);

                }
            }

            paradas = new ArrayList<>();
            //System.out.println("paradasT " + paradasT);
            for (int i = 0; i < paradasT.length(); i++) {
                JSONObject pt = paradasT.getJSONObject(i);
                paradas.add(pt);

            }
            vagones.clear();
            int contador = 0;
            for (Puerta puerta : dataManager.GetPuertas()) {
                if (vagones.isEmpty()) {
                    Vagon temp = new Vagon();
                    temp.setComandos(new ArrayList<JSONObject>());
                    temp.setNombre(puerta.getVagon());
                    temp.setUltimaConexion(0);

                    int index = contador;
                    String numero = "W" + (char) ('A' + (index));
                    temp.setNombreCDEG(numero);
                    contador++;
                    vagones.add(temp);
                    crerarHiloEscuchador("vagon" + temp.getNombre());
                    crearHiloPublicador(vagones.indexOf(temp));
                } else {
                    boolean ce = false;
                    Vagon temp = new Vagon();
                    temp.setComandos(new ArrayList<JSONObject>());

                    for (Vagon vagont : vagones) {
                        if (puerta.getVagon().equalsIgnoreCase(vagont.getNombre())) {
                            ce = true;
                        }
                    }
                    if (!ce) {
                        temp.setNombre(puerta.getVagon());
                        temp.setUltimaConexion(0);

                        int index = contador;
                        String numero = "W" + (char) ('A' + (index));
                        temp.setNombreCDEG(numero);
                        contador++;
                        vagones.add(temp);
                        crerarHiloEscuchador("vagon" + temp.getNombre());
                        crearHiloPublicador(vagones.indexOf(temp));
                    }

                }

                //System.out.println("Vagones " + "\n" + new Gson().toJson(vagones));
            }
            cargarDatosACK();
            try {
                publicadorManatee = new PublicadorMANATEEMQTT1(brokerManatee, claveManatee,
                        usuarioManatee);
            } catch (Exception e) {
                System.out.println("error publicador manatee");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el json");
        }

    }

    public void cargarDatosACK() {
        for (ACKVagon aCKVagon : dataManager.GetAllVagonACK()) {
            for (Vagon vagone : vagones) {
                if (vagone.getNombre().equalsIgnoreCase(aCKVagon.getVagon())) {
                    if (aCKVagon.getCanal() != 0) {
                        vagone.processMessage(aCKVagon.getIdDispositivo(), aCKVagon.getRegistro(), aCKVagon.getCanal());
                        break;
                    } else if (aCKVagon.getCanal() == 0) {
                        vagone.processMessageEmergency(aCKVagon.getIdDispositivo(), aCKVagon.getRegistro());
                        break;
                    }
                    //System.out.println(re);
                }
            }
            if (aCKVagon.getCanal() == -1) {
                moduloPi = (aCKVagon.getRegistro() == 1);
            }

        }

    }

    public void addParametrosSinFecha(JSONObject configuracion, JSONObject confEstacion, JSONObject mlan, JSONObject mqttcdeg) {
        OP_Parametro conftemp = new OP_Parametro();
        conftemp.setId(1);
        conftemp.setNombre("Estacion");
        conftemp.setValor(configuracion.getString("nombreEstacion"));
        dataManager.AddParametros(conftemp);
        conftemp.setId(2);
        conftemp.setNombre("servidorLocalMQTT");
        conftemp.setValor(configuracion.getString("servidoLocalMQTT"));
        dataManager.AddParametros(conftemp);

        conftemp.setId(3);
        conftemp.setNombre("servidorExternoMQTT");
        conftemp.setValor(mqttcdeg.getString("mqttServerAddress"));
        dataManager.AddParametros(conftemp);
        conftemp.setId(4);
        conftemp.setNombre("Dispositivo");
        conftemp.setValor(configuracion.getString("dispositivo"));
        dataManager.AddParametros(conftemp);
        conftemp.setId(5);
        conftemp.setNombre("MacEthernet");
        conftemp.setValor(configuracion.getString("MacEthernet"));
        dataManager.AddParametros(conftemp);
        conftemp.setId(6);
        conftemp.setNombre("MacWifi");
        conftemp.setValor(configuracion.getString("MacWifi"));
        dataManager.AddParametros(conftemp);
        conftemp.setId(7);
        conftemp.setNombre("MacBluetooth");
        conftemp.setValor(configuracion.getString("MacBluetooth"));
        dataManager.AddParametros(conftemp);
        conftemp.setId(8);
        conftemp.setNombre("IdOperador");
        conftemp.setValor(configuracion.getString("idOperador"));
        dataManager.AddParametros(conftemp);
        conftemp.setId(9);
        conftemp.setNombre("IdEstacion");
        conftemp.setValor(confEstacion.getString("idEstacion"));
        dataManager.AddParametros(conftemp);
        conftemp.setId(10);
        conftemp.setNombre("versionTrama");
        conftemp.setValor(configuracion.getString("versionTrama"));
        dataManager.AddParametros(conftemp);

        conftemp.setId(16);
        conftemp.setNombre("DireccionMLAN");
        conftemp.setValor(mlan.getString("DireccionMLAN"));
        dataManager.AddParametros(conftemp);

        conftemp.setId(17);
        conftemp.setNombre("PuertoReceptorMLAN");
        conftemp.setValor(mlan.getString("PuertoReceptorMLAN"));
        dataManager.AddParametros(conftemp);

        conftemp.setId(18);
        conftemp.setNombre("PuertoTransmisorMLAN");
        conftemp.setValor(mlan.getString("PuertoTransmisorMLAN"));
        dataManager.AddParametros(conftemp);
    }

    private void InterrogarRed() {
        ComandoInterfazVisual comandoInterfazVisual = new ComandoInterfazVisual();
        comandoInterfazVisual.setComando("escanear");
        publisherMQTTServiceInterno.Publisher(new Gson().toJson(comandoInterfazVisual).getBytes(), "Broadcast");
    }

    public void StartThreads() {
        new Thread() {
            @Override
            public void run() {
                String[] topic = {"Estacion", "CDEG", "InterfazVisual", "MCV485"};
                SuscriptorLocalMQTT subscriberMQTTServiceLocal = new SuscriptorLocalMQTT(topic, "tcp://localhost:1883", "PILocal");
                subscriberMQTTServiceLocal.Subscribe();

                while (true) {
                    if (!subscriberMQTTServiceLocal.isConnected()) {
                        subscriberMQTTServiceLocal.reconect();
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException ex) {
                            System.err.println(ex.getLocalizedMessage() + "");
                        }
                    } else if (subscriberMQTTServiceLocal.isMessageArrived()) {

                        try {

                            subscriberMQTTServiceLocal.setMessageArrived(false);
                            OP_RegistroCrudo registroCrudo = new OP_RegistroCrudo();
                            switch (subscriberMQTTServiceLocal.getMessageTopicArrived()) {
                                case "CDEG":

                                    datoCDEGString = subscriberMQTTServiceLocal.getData();
                                    System.out.println("DATO A REVISAR " + datoCDEGString + "");
                                    registroCrudo.setTrama(datoCDEGString);
                                    registroCrudo.setFechaOcurrencia(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                                    registroCrudo.setOrigen(subscriberMQTTServiceLocal.getMessageTopicArrived());
                                    JSONObject envioManatee = new JSONObject();
                                    envioManatee.put("trama", datoCDEGString);
                                    envioManatee.put("estadoEnvioManatee", true);
                                    Long idF = Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
                                    String id = idEstacion + idF;
                                    envioManatee.put("IDManatee", id);
                                    envioManatee.put("fechaHoraEnvio", formatoFecha.format(new Date()));
                                    ComandoCDEG comandoRecibido = new ComandoCDEG();
                                    comandoRecibido.setTrama(datoCDEGString);
                                    comandoRecibido.setFechaHoraOcurrencia(registroCrudo.getFechaOcurrencia());
                                    dataManager.saveComando(comandoRecibido);
                                    publicadorManatee.Publisher(envioManatee.toString().getBytes(), topicManatee);
                                    dataManager.AddRegistroCrudo(registroCrudo);
                                    break;
                                case "InterfazVisual":
                                    ComandoInterfazVisual comandoInterfazVisual = new ComandoInterfazVisual();
                                    comandoInterfazVisual = cast.JSONtoComandoInterfazVisual(subscriberMQTTServiceLocal.getData());
                                    //System.out.println("InterfazVisual: " + subscriberMQTTServiceLocal.getData());

                                    try {
                                        for (String puerta : comandoInterfazVisual.getPuertas()) {

                                            Puerta puertaTemp = dataManager.GetPuerta(puerta);

                                            JSONObject dato = auxService.JsonProcesarComandoIV(comandoInterfazVisual, puerta, puertaTemp);

                                            System.out.println("Dato Interfaz VIsual: " + dato);
                                            AddComando(puertaTemp.getVagon(), dato);
                                            //publisherMQTTServiceInterno.Publisher(dato.toString().getBytes(), puertaTemp.getVagon());
                                            Thread.sleep(100);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                //System.out.println(new Gson().toJson(comandoInterfazVisual));
                                case "MCV485":
                                    System.out.println("Dato: " + subscriberMQTTServiceLocal.getData());
                                    break;

                            }
                        } catch (Exception ex) {
                            System.err.println(ex.getLocalizedMessage() + "");
                        }
                    }
                    try {
                        Thread.sleep(1);
                    } catch (Exception ie) {
                    }
                }
            }
        }.start();
        if (activado != 0) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        clavePrivada = new GenerarClave().Generar(nombreEstacion);
                        while (publicadorExternoMQTT.conect()) {
                            if (publicadorExternoMQTT.isEntroDato()) {
                                if (!publicadorExternoMQTT.getDato().equalsIgnoreCase("")) {
                                    try {
                                        datoCDEGString = publicadorExternoMQTT.getDato();
                                        System.out.println("\nDato Plataforma: " + datoCDEGString + " - " + "Topico: " + publicadorExternoMQTT.getTopicoEntro() + "\n");
                                        publicadorExternoMQTT.setEntroDato(false);
                                        OP_RegistroCrudo registroCrudo = new OP_RegistroCrudo();
                                        registroCrudo.setOrigen("CDEG");
                                        registroCrudo.setTrama(datoCDEGString);
                                        registroCrudo.setFechaOcurrencia(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));

                                        if (!datoCDEGString.equalsIgnoreCase(" ")) {
                                            dataManager.AddRegistroCrudo(registroCrudo);
                                        }
                                        ComandoCDEG comandoRecibido = new ComandoCDEG();
                                        comandoRecibido.setTrama(datoCDEGString);
                                        comandoRecibido.setFechaHoraOcurrencia(registroCrudo.getFechaOcurrencia());
                                        dataManager.saveComando(comandoRecibido);
                                        publicadorManatee.Publisher(auxService.comandoCDEGMTE(datoCDEGString, idEstacion, formatoFecha).toString().getBytes(), topicManatee);
                                    } catch (Exception ex) {
                                        System.err.println(ex.getLocalizedMessage() + "");
                                    }
                                }
                            }
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ie) {
                            }

                        }

                        try {
                            Thread.sleep(5000);

                        } catch (InterruptedException ex) {
                            System.err.println(ex.getLocalizedMessage() + "");
                        }

                    }

                }
            }.start();
        }

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        InicioOperacion();
                        FinOperacion();

//                        guardarRegistroCrudo();
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (moduloPi) {
                            Thread.sleep(2 * 60 * 60 * 1000);
                            moduloPi = false;
                            dataManager.UpdateVagonACK("moduloPi", -1, 0, "-1");
                        }

//                        guardarRegistroCrudo();
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                
                while (true) {
                    try {
                        EscucharMLANS();
                    } catch (Exception ie) {
                        ie.printStackTrace();
                        log("ERROR EN UDP: " + ie.getLocalizedMessage());
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                String[] datos = (direccionMLAN.split(","));
                MLans.clear();
                for (String dato : datos) {
                    MLan mlanT = new MLan();
                    mlanT.setIp(dato);
                    MLans.add(mlanT);

                }
                //System.out.println(new Gson().toJson(MLans));
                while (true) {
                    try {
                        for (String dato : datos) {
                            TransmisorUDP transmisorUDP = new TransmisorUDP(dato, Integer.parseInt(puertoTransmisorMLAN));
                            Date date = new Date();
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                            String formattedDate = formatter.format(date);
                            String dwl = ">DWL1<" + ">TIME " + formattedDate + "<";
                            transmisorUDP.EnviarDato(dwl.getBytes());
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {

                while (true) {
                    try {

                        for (Vagon conjunto : vagones) {

                            try {
                                for (ModuloConcentradorVagon moduloConcentradorVagon : conjunto.getMVCS()) {
                                    if (moduloConcentradorVagon.getConexionPuertas() != null) {
                                        JSONArray puertasVivas = new JSONArray(moduloConcentradorVagon.getConexionPuertas());
                                        String vagonid = conjunto.getNombre();
                                        for (int i = 0; i < puertasVivas.length(); i++) {
                                            JSONObject tempPuerta = puertasVivas.getJSONObject(i);
                                            if (!tempPuerta.getString("idPuerta").equalsIgnoreCase("0")) {
                                                Puerta temp = dataManager.GetPuerta(tempPuerta.getString("canal"), vagonid, tempPuerta.getString("idPuerta"));
                                                if (!tempPuerta.getBoolean("conectada")) {

                                                    if (temp != null) {

                                                        temp.setEstado("SIN CONEXION");
//                                                            
                                                        dataManager.UpdatePuerta(temp);

                                                    }

                                                } else {
                                                    if (temp != null) {
                                                        temp.setUltimaConexion(System.currentTimeMillis());
                                                        dataManager.UpdatePuerta(temp);

                                                    }

                                                }
                                            }

                                        }
                                        moduloConcentradorVagon.setConexionPuertas(null);
                                    }
                                    for (Canal canale : moduloConcentradorVagon.getCanales()) {
                                        if (canale.getConexionPuertas() != null) {
                                            JSONArray puertasVivas = new JSONArray(canale.getConexionPuertas());
                                            String vagonid = conjunto.getNombre();
                                            for (int i = 0; i < puertasVivas.length(); i++) {
                                                JSONObject tempPuerta = puertasVivas.getJSONObject(i);
                                                if (!tempPuerta.getString("idPuerta").equalsIgnoreCase("0")) {
                                                    Puerta temp = dataManager.GetPuerta(tempPuerta.getString("canal"), vagonid, tempPuerta.getString("idPuerta"));
                                                    if (!tempPuerta.getBoolean("conectada")) {

                                                        if (temp != null) {

                                                            temp.setEstado("SIN CONEXION");
//                                                            
                                                            dataManager.UpdatePuerta(temp);

                                                        }

                                                    } else {
                                                        if (temp != null) {
                                                            temp.setUltimaConexion(System.currentTimeMillis());
                                                            dataManager.UpdatePuerta(temp);

                                                        }

                                                    }
                                                }

                                            }

                                        }
                                        canale.setConexionPuertas(null);
                                    }

                                }

                            } catch (JSONException e) {

                            }
                            Thread.sleep(100);
                        }

                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ProcesarRegistrosCrudos();

                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

//        new Thread() {
//            @Override
//            public void run() {
//                String topics[] = new String[0];
//                PublicadorLocalMQTT subscriberMQTTServiceLocal = new PublicadorLocalMQTT("tcp://localhost:1883");
//                while (true) {
//                    try {
//                        for (Vagon vagone : vagones) {
//                            
//                        }
//
//                        Thread.sleep(10);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();

        new Thread() {
            @Override
            public void run() {
                publicadorManatee = new PublicadorMANATEEMQTT1(brokerManatee, claveManatee,
                        usuarioManatee);
                while (true) {
                    try {
                        //ProcesarRegistrosCrudos();
                        if (activado != 0) {
                            EnviarRegistro();
                            enviarManatee();
                        }
                        borrarRegistros();
                        Thread.sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        boolean ce = true;
//                        while (ce) {
//                            for (OP_Registro dato : dataManager.GetRegistro()) {
//                                try {
//                                    Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date()));
//                                    long restaFechas = ((date.getTime() - dato.getfechaHoraOcurrencia().getTime()));
//                                    long dias = restaFechas / (60 * 60 * 1000 * 24);
//                                    if (dias >= 60) {
//                                        dataManager.DeleteRegistro(dato.getId());
//                                    } else { 
//                                        ce = false;
//                                        break;
//                                    }
//                                } catch (ParseException ex) {
//                                    System.err.println(ex.getLocalizedMessage() + "");
//                                }
//
//                            }
//                            Thread.sleep(1000);
//                        }
//
//                        //cada 8 horas
//                        Thread.sleep(24 * 60 * 60 * 1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 60 * 2);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getLocalizedMessage() + "");
                }
                while (true) {
                    try {
                        for (Puerta puertaTemp : dataManager.GetPuertas()) {
                            if (!puertaTemp.conexion()) {
                                puertaTemp.setEstado("SIN CONEXION");
                                dataManager.UpdatePuerta(puertaTemp);
                            }
                        }

                        //30 segundos
                        Thread.sleep(30 * 1000);
                    } catch (Exception e) {
                        System.out.println("Error en el hilo conexionPuertas " + e.getLocalizedMessage());
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                boolean SCDEG = false;
                boolean SCC = false;
                
                while (true) {
                    try {

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

                        JSONObject puertas = new JSONObject();
                        JSONArray conexiones = new JSONArray();
                        JSONObject conexion = new JSONObject();
                        if (moduloPi) {
                            conexion = new JSONObject();
                            conexion.put("nombre", "ModuloPi");
                            conexion.put("CONECTADO", false);
                            conexion.put("fechaConexion", "--/--/-- --:--:--");
                            conexiones.put(conexion);
                        }
                        for (MLan MLanT : MLans) {
                            conexion = new JSONObject();
                            conexion.put("nombre", "MLAN" + (MLans.indexOf(MLanT) + 1));
                            conexion.put("CONECTADO", MLanT.conectado());
                            if (MLanT.getUltimaconexion() != 0) {
                                conexion.put("fechaConexion", formatter.format(new Date(MLanT.getUltimaconexion())));
                            } else {
                                conexion.put("fechaConexion", "--/--/-- --:--:--");
                            }
                            conexiones.put(conexion);
                        }

                        conexion = new JSONObject();
                        conexion.put("nombre", "CDEG");
                        if(publicadorExternoMQTT.conect()!=SCDEG){
                            SCDEG=publicadorExternoMQTT.conect();
                            log("Cambio conexion Centro de Gestion a " + (SCDEG ? "conectado" : "desconectado"));
                        }
                        conexion.put("CONECTADO", publicadorExternoMQTT.conect());
                        conexion.put("fechaConexion", formatter.format(conexionCDEG));
                        conexiones.put(conexion);

                        conexion = new JSONObject();
                        conexion.put("nombre", "CENTRO CONTROL");
                        if(publicadorManatee.isConected()!=SCC){
                            SCC=publicadorManatee.isConected();
                            log("Cambio conexion Centro de Control a " + (SCC ? "conectado" : "desconectado"));
                        }
                        conexion.put("CONECTADO", publicadorManatee.isConected());
                        conexion.put("fechaConexion", formatter.format(conexionMTE));
                        conexiones.put(conexion);

                        for (Vagon vagone : vagones) {
                            for (ModuloConcentradorVagon mvc : vagone.getMVCS()) {
                                for (Canal canal : mvc.getCanales()) {
                                    JSONObject mvcJson = new JSONObject();
                                    mvcJson.put("nombre", "MCV-" + vagone.getNombreCDEG() + "-" + canal.getCanal() + "/" + mvc.getIdDispositivo());
                                    mvcJson.put("CONECTADO", canal.conectado());
                                    if(canal.isCambio()){
                                        log("Cambio conexion " + "MCV-" + vagone.getNombreCDEG() + "-" + canal.getCanal() + "/" + mvc.getIdDispositivo()+(canal.conectado() ? " conectado" : " desconectado"));
                                    }
                                    mvcJson.put("fechaConexion", formatter.format(new Date(canal.getUltimaConexcion())));
                                    conexiones.put(mvcJson);
                                }

                            }
                        }

                        puertas.put("CanalCostadoPI", CanalCostadoPI);
                        puertas.put("PrimerVagonDerecha", PrimerVagonDerecha);
                        puertas.put("CostadoPI", CostadoPI);

                        puertas.put("puertas", dataManager.GetPuertas());
                        puertas.put("Estacion", nombreEstacion);
                        puertas.put("version", VERSION);
                        puertas.put("conexiones", conexiones);
                        publisherMQTTServiceInterno.Publisher(puertas.toString().getBytes(), "STATUSIV");
                        Thread.sleep(250);
                    } catch (Exception ex) {
                        System.err.println(ex.getLocalizedMessage() + "");
                    }
                }

            }

        }.start();
        new Thread() {
            @Override
            public void run() {
                long tiempoAnterior = 0;

                while (true) {
                    // Espera durante 1 segundo antes de realizar la siguiente verificación
                    try {
                        Thread.sleep(1000); // espera 1000 milisegundos (1 segundo)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Obtén el tiempo actual
                    // Obtén el tiempo actual en milisegundos
                    long tiempoActual = System.currentTimeMillis();

                    // Si es la primera vez, simplemente almacena el tiempo actual
                    if (tiempoAnterior == 0) {
                        tiempoAnterior = tiempoActual;
                        System.out.println("Primera vez que se ejecuta el programa. Se almacenó el tiempo actual.");
                    } else {
                        // Calcula la diferencia entre el tiempo actual y el tiempo anterior
                        long diferenciaEnMilisegundos = tiempoActual - tiempoAnterior;

                        // Comprueba si la diferencia es mayor a 2 minutos (en milisegundos)
                        if (Math.abs(diferenciaEnMilisegundos) >= 2 * 60 * 1000) {
                            moduloPi = true;
                            dataManager.UpdateVagonACK("moduloPi", -1, 1, "-1");
                            Date fechaAntes = new Date(tiempoAnterior);
                            Date fechaDespues = new Date(tiempoActual);
                            if (diferenciaEnMilisegundos < 0) {
                                log(" El reloj se ha atrasado. La diferencia es de " + Math.abs(diferenciaEnMilisegundos) / 1000 / 60 + " minutos." + " Fecha anterior " + formatoFecha.format(fechaAntes) + " Fecha actual " + formatoFecha.format(fechaDespues));
                            } else {
                                log(" El reloj se ha adelantado. La diferencia es de " + diferenciaEnMilisegundos / 1000 / 60 + " minutos." + " Fecha anterior " + formatoFecha.format(fechaAntes) + " Fecha actual " + formatoFecha.format(fechaDespues));
                            }
                        }

                        // Actualiza el tiempo anterior con el tiempo actual para la próxima verificación
                        tiempoAnterior = tiempoActual;
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Obtener el uso de la CPU
                        double cpuUsage = getCPUUsage();

                        // Obtener la temperatura de la CPU en sistemas Linux
                        double cpuTemperature = getCpuTemperature();

                        // Obtener la fecha actual
                        String currentDate = formatoFecha.format(new Date());

                        // Registrar en el archivo de log
                        log("CPU: " + cpuUsage + "% - Temp: " + cpuTemperature + "°C");

                        // Esperar 5 minutos antes de la próxima verificación
                        Thread.sleep(5 * 60 * 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    private double getCPUUsage() {
        try {
            // Ejecutar el comando 'top' y capturar la salida
            Process process = Runtime.getRuntime().exec("top -b -n 1");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Leer las líneas de salida y buscar la línea que contiene la información de la CPU
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Cpu(s)")) {
                    String[] tokens = line.split(",");
                    String cpuUsageStr = tokens[0].split(":")[1].trim();
                    return Double.parseDouble(cpuUsageStr);
                }
            }

            // Si no se encuentra información de uso de la CPU, devuelve -1
            return -1.0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0;
        }
    }

    private double getCpuTemperature() {
        try {
            // Ejecutar el comando 'sensors' y capturar la salida
            Process process = Runtime.getRuntime().exec("sensors");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Leer las líneas de salida y buscar la línea que contiene la información de la temperatura
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Package id 0:")) { // Ajusta esto según el formato de salida de 'sensors'
                    // Buscar el índice del primer dígito en la línea para extraer la temperatura
                    int startIndex = line.indexOf("+");
                    int endIndex = line.indexOf(".", startIndex);

                    // Verificar si se encontraron los índices válidos
                    if (startIndex >= 0 && endIndex > startIndex) {
                        String temperatureStr = line.substring(startIndex, endIndex + 3); // Incluir el punto y dos dígitos decimales

                        // Eliminar cualquier carácter no numérico al final de la cadena
                        temperatureStr = temperatureStr.replaceAll("[^\\d.]", "");

                        return Double.parseDouble(temperatureStr);
                    } else {
                        System.err.println("No se pudo encontrar la temperatura en el formato esperado.");
                        return -1.0;
                    }
                }
            }

            // Si no se encuentra información de temperatura, devuelve -1
            return -1.0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0;
        }
    }




    public void conexionVagon(Vagon conect) {

        for (Vagon vagonT : vagones) {
            if (vagonT.getNombre().equalsIgnoreCase(conect.getNombre())) {
                vagonT.setUltimaConexion(System.currentTimeMillis());
                vagonT.setDesconectado(false);

                //System.out.println(new Gson().toJson(conect));
            }
        }
        //System.out.println(new Gson().toJson(vagones));
    }

    public void envio(OP_RegistroTemporal registroTemporal) {
//        new Thread() {
//            @Override
//            public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        Date now = new Date();
        registroTemporal.setTrama(registroTemporal.getTrama().replaceFirst("\"fechaHoraEnvioDato\":\"[^\"]+\"", "\"fechaHoraEnvioDato\":\"" + sdf.format(now) + "\""));
        if (!registroTemporal.isestadoEnvio()) {
            try {
                registroTemporal.setfechaHoraEnvio(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(registroTemporal.getfechaHoraEnvio());
                calendar.add(Calendar.HOUR, -5);
                registroTemporal.setfechaHoraEnvio(calendar.getTime());
            } catch (ParseException ex) {
                System.err.println(ex.getLocalizedMessage() + "");
            }
            if (publicadorExternoMQTT.Publicar(registroTemporal.getTrama().getBytes(), "events")) {

                try {
                    conexionCDEG = new Date();
                    registroTemporal.setestadoEnvio(true);

                    System.out.println("\n" + registroTemporal.getTrama() + "\n" + "Envio CORRECTO");
                    OP_Registro registro = new OP_Registro();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(registroTemporal.getfechaHoraOcurrencia());
                    registro.setfechaHoraOcurrencia(calendar.getTime());
                    calendar.setTime(registroTemporal.getfechaHoraEnvio());
                    registro.setfechaHoraEnvio(calendar.getTime());
                    registro.setTrama(registroTemporal.getTrama());
                    registroTemporal.setEstadoEnvioManatee(false);
                    dataManager.UpdateRegistroTemporal(registroTemporal);
                    dataManager.AddRegistro(registro);
                    //publicadorManatee.Publisher(new Gson().toJson(registroTemporal).getBytes(), "S1", "tcp://4.236.168.64:1883", macWifi);

                } catch (Exception ex) {
                    System.err.println(ex.getLocalizedMessage() + "");
                }
            } else {
                JSONObject retransmitida = new JSONObject(registroTemporal.getTrama());
                retransmitida.put("tramaRetransmitida", true);
                registroTemporal.setTrama(retransmitida.toString());
                try {
                    clavePrivada = new GenerarClave().Generar(nombreEstacion);
                    publicadorExternoMQTT = new PublicadorExternoMQTT(clavePrivada, dispositivo, servidorExternoMQTT, proyecto, region, registro);

                } catch (Exception ex) {
                    System.err.println(ex.getLocalizedMessage() + "");
                }
            }
        }

    }

    public void enviarManatee() {
        List<OP_RegistroTemporal> registros = dataManager.GetRegistroTemporalByMTE();

        for (OP_RegistroTemporal registroTemporal : registros) {
            if (registroTemporal.isestadoEnvio()) {
                try {
                    conexionMTE = new Date();
                    registroTemporal.setEstadoEnvioManatee(true);
                    JSONObject temp = new JSONObject(registroTemporal);
                    temp.put("fechaHoraEnvio", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date()));
                    publicadorManatee.Publisher(temp.toString().getBytes(), topicManatee);
                    dataManager.UpdateRegistroTemporal(registroTemporal);
                    //System.out.println("Envio a manatee cc " + temp);
                } catch (Exception ex) {
                    System.err.println(ex.getLocalizedMessage() + "");
                }
            } else if (!registroTemporal.isEstadoEnvioManatee()) {
                conexionMTE = new Date();
                registroTemporal.setEstadoEnvioManatee(true);
                dataManager.UpdateRegistroTemporal(registroTemporal);
                registroTemporal.setfechaHoraEnvio(null);
                JSONObject temp = new JSONObject(registroTemporal);
                temp.put("fechaHoraEnvio", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date()));
                publicadorManatee.Publisher(temp.toString().getBytes(), topicManatee);

                //System.out.println("Envio a manatee cc " + temp);
            }

        }

    }

    public void borrarRegistros() {
        List<OP_RegistroTemporal> registros = dataManager.GetRegistroTemporal();

        for (OP_RegistroTemporal registroTemporal : registros) {

            try {
                 if (registroTemporal.isestadoEnvio() && registroTemporal.isEstadoEnvioManatee()) {

                    try {
                        dataManager.DeleteRegistroTemporal(registroTemporal.getId());
                    } catch (Exception ex) {
                        System.err.println(ex.getLocalizedMessage() + "");
                    }

                }
            } catch (Exception ex) {
                System.err.println(ex.getLocalizedMessage() + "");
            }

        }

    }

    public void crerarHiloEscuchador(String topic) {
        new Thread() {
            @Override
            public void run() {

                String topics[] = {topic};
                SuscriptorLocalMQTT subscriberMQTTServiceLocal = new SuscriptorLocalMQTT(topics, "tcp://localhost:1883", "PILocal");
                subscriberMQTTServiceLocal.Subscribe();
                while (true) {
                    try {
                        if (!subscriberMQTTServiceLocal.isConnected()) {
                            subscriberMQTTServiceLocal.reconect();
                            Thread.sleep(100);
                        } else {
                            JSONObject re;
                            if (subscriberMQTTServiceLocal.isMessageArrived()) {
                                subscriberMQTTServiceLocal.setMessageArrived(false);
                                OP_RegistroCrudo registroCrudo = new OP_RegistroCrudo();
                                String registroCrudoStringTEMP = subscriberMQTTServiceLocal.getData();
                                re = new JSONObject(registroCrudoStringTEMP);
                                registroCrudo = cast.JSONtoRegistroEventoCrudo(registroCrudoStringTEMP);
                                Vagon vagonT = EncontrarVagon(registroCrudo.getIdVagon());

                                if (!re.isNull("fechaHoraOcurrencia")) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                            "dd/MM/yyyy HH:mm:ss.SSS");
                                    try {
                                        Date date = simpleDateFormat.parse(re.getString("fechaHoraOcurrencia"));
                                        registroCrudo.setFechaOcurrencia(date);
                                        //System.out.println(registroCrudo.getFechaOcurrencia());
                                    } catch (ParseException e) {
                                        System.out.println("Error en convertir fecha");
                                    }
                                } else {
                                    try {
                                        registroCrudo.setFechaOcurrencia(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                                    } catch (ParseException ex) {
                                        System.err.println(ex.getLocalizedMessage() + "");
                                    }
                                }

                                registroCrudo.setOrigen("Estacion");
                                if (!re.isNull("idRegistro")
                                        && (registroCrudo.getFuncion().equalsIgnoreCase("EVENTO")
                                        || registroCrudo.getFuncion().equalsIgnoreCase("BOTON_EMERGENCIA"))
                                        && !re.isNull("ActivacionAck")
                                        && re.getString("ActivacionAck").equalsIgnoreCase("1")
                                        && !re.isNull("dispositivoMCV")) {

                                    String idDispositivo = re.getString("dispositivoMCV");
                                    JSONObject ack = new JSONObject();
                                    ack.put("origen", "PI");
                                    ack.put("funcion", "ACK");
                                    ack.put("dispositivoMCV", idDispositivo);
                                    ack.put("idRegistro", re.getInt("idRegistro"));
                                    ack.put("canal", registroCrudo.getCanal());

                                    if (registroCrudo.getFuncion().equalsIgnoreCase("BOTON_EMERGENCIA")) {
                                        if (vagonT.processMessageEmergency(idDispositivo, re.getInt("idRegistro"))) {
                                            dataManager.UpdateVagonACK(vagonT.getNombre(), Integer.parseInt(registroCrudo.getCanal()), re.getInt("idRegistro"), idDispositivo);
                                            dataManager.AddRegistroCrudo(registroCrudo);
                                        }
                                    } else if (vagonT.processMessage(idDispositivo, re.getInt("idRegistro"), Integer.parseInt(registroCrudo.getCanal()))) {
                                        dataManager.UpdateVagonACK(vagonT.getNombre(), Integer.parseInt(registroCrudo.getCanal()), re.getInt("idRegistro"), idDispositivo);
                                        dataManager.AddRegistroCrudo(registroCrudo);
                                    }
                                    AddComando(registroCrudo.getIdVagon(), ack);
                                    //subscriberMQTTServiceLocal.Publisher(ack.toString().getBytes(), registroCrudo.getIdVagon());
                                } else if (registroCrudo.getFuncion().equalsIgnoreCase("CONEXION_PUERTAS")) {
                                    if (!re.isNull("dispositivoMCV") && (!re.isNull("canal1Habilitado") && !re.isNull("canal2Habilitado"))) {
                                        vagonT.actualizarConexionPuertas(re.getString("dispositivoMCV"), registroCrudo.getTrama(), re.getBoolean("canal1Habilitado"), re.getBoolean("canal2Habilitado"));
                                    } else {
                                        vagonT.actualizarConexionPuertas("0", registroCrudo.getTrama(), true, true);

                                    }

                                } else {
                                    if (registroCrudo.getCanal() != null) {
                                        vagonT.processMessageSinAck(Integer.parseInt(registroCrudo.getCanal()));
                                    } else if (registroCrudo.getFuncion().equalsIgnoreCase("CONEXION_PUERTAS")) {
                                        vagonT.actualizarConexionPuertas("0", registroCrudo.getTrama(), true, true);
                                    } else {
                                        vagonT.actualizaConexionVagon();
                                    }

                                    dataManager.AddRegistroCrudo(registroCrudo);
                                }
//                            System.out.println("Julian:  " + new Gson().toJson(subscriberMQTTServiceLocal.getData()));

                            }

                            Thread.sleep(1);
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR EN TRAMA DEL MVC: " + e.getMessage());
                    }
                }

            }
        }.start();

    }
    public void crearHiloPublicador(int indexVagon) {
        new Thread() {
            @Override
            public void run() {

                
                PublicadorLocalMQTT publicador = new PublicadorLocalMQTT("tcp://localhost:1883");
                
                while (true) {
                    try {
                        List<JSONObject> comandos = new ArrayList<>();
                            comandos.addAll(vagones.get(indexVagon).getComandos());
                            for (JSONObject comando : comandos) {
                                publicador.Publisher(comando.toString().getBytes(), vagones.get(indexVagon).getNombre());
                                vagones.get(indexVagon).getComandos().remove(comando);
                                Thread.sleep(50);
                            }
                            
                        Thread.sleep(10);
                    } catch (Exception e) {
                        System.out.println("ERROR EN TRAMA DEL MVC: " + e.getMessage());
                    }
                }

            }
        }.start();

    }

    public Vagon EncontrarVagon(String vagon) {
        Vagon re = new Vagon();

        for (Vagon vagone : vagones) {
            if (vagone.getNombre().equalsIgnoreCase(vagon)) {
                re = vagone;
                //System.out.println(re);
            }
        }
        return re;
    }

    public void AddComando(String vagon, JSONObject comando) {
        Vagon re = new Vagon();

        for (Vagon vagone : vagones) {
            if (vagone.getNombre().equalsIgnoreCase(vagon)) {
                vagone.getComandos().add(comando);
                break;
                //System.out.println(re);
            }
        }
    }

    public String nombreVagon(String nombre) {
        String re = null;

        ArrayList<Vagon> vagonesT = new ArrayList<>();
        vagonesT.addAll(vagones);
        for (Vagon vagone : vagonesT) {
            if (vagone.getNombre().equalsIgnoreCase(nombre)) {
                re = vagone.getNombreCDEG();
                //System.out.println(re);
            }
        }
        return re;
    }

    public String numeroVagon(String nombre) {
        String re = null;

        ArrayList<Vagon> vagonesT = new ArrayList<>();
        vagonesT.addAll(vagones);
        for (Vagon vagone : vagonesT) {
            if (vagone.getNombreCDEG().equalsIgnoreCase(nombre)) {
                re = vagone.getNombre();
                //System.out.println(re);
            }
        }
        return re;
    }

    public String nombreParada(String vagon, String parada) {
        String nombre = "";
        for (JSONObject parada1 : paradas) {
            if (parada1.getString("vagon").equalsIgnoreCase(vagon) && parada1.getString("parada").equalsIgnoreCase(parada)) {
                nombre = parada1.getString("nombre");
            }
        }
        return nombre;

    }
    int cont = -1;

    public void mlanVivo(String ip) {
        for (MLan MLanT : MLans) {
            if (MLanT.getIp().equalsIgnoreCase(ip)) {
                //System.out.println("entro" + ip);
                MLanT.setUltimaconexion(new Date().getTime());
            }
        }
    }
    int consecutivoT = 0;

    private String consecutivo() {

        DecimalFormat formatter = new DecimalFormat("0000");
        String formattedNumber = formatter.format(consecutivoT);
        consecutivoT++;
        if (consecutivoT > 9999) {
            consecutivoT = 0;
        }
        return formattedNumber;
    }

    public void log(String message) {
        ensureLogFileExists();
        nameFile = formatoFechaMM_yyyy.format(new Date()) + "_logPI.txt";   //formatoFechaMM_yyyy = new SimpleDateFormat("MM_yyyy");
        
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH + nameFile, true))) {
            String currentDate = formatoFecha.format(new Date());
            String logEntry = currentDate + " - " + message;
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }

    public void ensureLogFileExists() {
        nameFile = formatoFechaMM_yyyy.format(new Date()) + "_logPI.txt";   //formatoFechaMM_yyyy = new SimpleDateFormat("MM_yyyy");
        
        Path logFilePath = Paths.get(LOG_FILE_PATH + nameFile);

        if (!Files.exists(logFilePath)) {
            try {
                Files.createDirectories(logFilePath.getParent());
                Files.createFile(logFilePath);
            } catch (IOException e) {
                System.err.println("Error al crear el archivo de log: " + e.getMessage());
            }
        }
    }
    public void EscucharMLANS() {
        ReceptorUDP receptorUDP = new ReceptorUDP(direccionMLAN, Integer.parseInt(puertoReceptorMLAN));
        while (true) {
            try {
                receptorUDP.RecibirDato();
                if (receptorUDP.isEntroDato()) {
                    //System.out.println("MLAN: " + receptorUDP.getDato());
                    receptorUDP.setEntroDato(false);
                    OP_RegistroCrudo registroCrudo = new OP_RegistroCrudo();
                    registroCrudo.setTrama(receptorUDP.getDato());
                    registroCrudo.setOrigen("MLAN");
                    //System.out.println(receptorUDP.getIp());
                    mlanVivo(receptorUDP.getIp());
                    try {
                        registroCrudo.setFechaOcurrencia(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date())));
                    } catch (ParseException ex) {
                        System.err.println(ex.getLocalizedMessage() + "");
                    }
                    //System.out.println(new Gson().toJson(registroCrudo));
                    dataManager.AddRegistroCrudo(registroCrudo);

                }
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
