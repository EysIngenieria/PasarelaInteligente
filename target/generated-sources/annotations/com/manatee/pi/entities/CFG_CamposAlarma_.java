package com.manatee.pi.entities;

import com.manatee.pi.entities.CFG_Alarma;
import com.manatee.pi.entities.CFG_CamposValidos;
import com.manatee.pi.entities.CFG_Configuracion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-02-08T07:52:17")
@StaticMetamodel(CFG_CamposAlarma.class)
public class CFG_CamposAlarma_ { 

    public static volatile SingularAttribute<CFG_CamposAlarma, CFG_Alarma> alarma;
    public static volatile SingularAttribute<CFG_CamposAlarma, CFG_CamposValidos> camposValidos;
    public static volatile SingularAttribute<CFG_CamposAlarma, Integer> id;
    public static volatile SingularAttribute<CFG_CamposAlarma, CFG_Configuracion> configuracion;

}