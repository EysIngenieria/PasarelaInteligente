package com.eysingenieria.pi.data.model;

import com.eysingenieria.pi.data.model.CFG_Alarma;
import com.eysingenieria.pi.data.model.CFG_CamposValidos;
import com.eysingenieria.pi.data.model.CFG_Configuracion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-03-08T10:06:43")
@StaticMetamodel(CFG_CamposAlarma.class)
public class CFG_CamposAlarma_ { 

    public static volatile SingularAttribute<CFG_CamposAlarma, CFG_Alarma> alarma;
    public static volatile SingularAttribute<CFG_CamposAlarma, CFG_CamposValidos> camposValidos;
    public static volatile SingularAttribute<CFG_CamposAlarma, Integer> id;
    public static volatile SingularAttribute<CFG_CamposAlarma, CFG_Configuracion> configuracion;

}