package com.eysingenieria.pi.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-03-09T14:06:40")
@StaticMetamodel(OP_RegistroTemporal.class)
public class OP_RegistroTemporal_ { 

    public static volatile SingularAttribute<OP_RegistroTemporal, String> trama;
    public static volatile SingularAttribute<OP_RegistroTemporal, Boolean> estadoEnvioManatee;
    public static volatile SingularAttribute<OP_RegistroTemporal, Date> fechaHoraOcurrencia;
    public static volatile SingularAttribute<OP_RegistroTemporal, String> IDManatee;
    public static volatile SingularAttribute<OP_RegistroTemporal, Boolean> estadoEnvio;
    public static volatile SingularAttribute<OP_RegistroTemporal, Integer> id;
    public static volatile SingularAttribute<OP_RegistroTemporal, Date> fechaHoraEnvio;
    public static volatile SingularAttribute<OP_RegistroTemporal, Date> fechaPrimerIntento;

}