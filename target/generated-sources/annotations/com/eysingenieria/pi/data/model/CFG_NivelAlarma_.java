package com.eysingenieria.pi.data.model;

import com.eysingenieria.pi.data.model.CFG_Alarma;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-03-08T10:06:43")
@StaticMetamodel(CFG_NivelAlarma.class)
public class CFG_NivelAlarma_ { 

    public static volatile SingularAttribute<CFG_NivelAlarma, Integer> nivelInferior;
    public static volatile SingularAttribute<CFG_NivelAlarma, CFG_Alarma> alarma;
    public static volatile SingularAttribute<CFG_NivelAlarma, String> valor;
    public static volatile SingularAttribute<CFG_NivelAlarma, Integer> secuencia;
    public static volatile SingularAttribute<CFG_NivelAlarma, Integer> nivelSuperior;
    public static volatile SingularAttribute<CFG_NivelAlarma, Integer> id;

}