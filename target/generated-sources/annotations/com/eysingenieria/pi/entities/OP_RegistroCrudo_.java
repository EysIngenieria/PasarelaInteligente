package com.eysingenieria.pi.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-08-15T08:36:24")
@StaticMetamodel(OP_RegistroCrudo.class)
public class OP_RegistroCrudo_ { 

    public static volatile SingularAttribute<OP_RegistroCrudo, String> trama;
    public static volatile SingularAttribute<OP_RegistroCrudo, Boolean> estadoProcesado;
    public static volatile SingularAttribute<OP_RegistroCrudo, Date> fechaHoraOcurrencia;
    public static volatile SingularAttribute<OP_RegistroCrudo, String> idPuerta;
    public static volatile SingularAttribute<OP_RegistroCrudo, String> codigoPuerta;
    public static volatile SingularAttribute<OP_RegistroCrudo, Integer> id;
    public static volatile SingularAttribute<OP_RegistroCrudo, String> origen;
    public static volatile SingularAttribute<OP_RegistroCrudo, String> canal;
    public static volatile SingularAttribute<OP_RegistroCrudo, String> funcion;
    public static volatile SingularAttribute<OP_RegistroCrudo, String> idVagon;

}