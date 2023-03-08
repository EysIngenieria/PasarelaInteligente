package com.eysingenieria.pi.data.model;

import com.eysingenieria.pi.data.model.CFG_CamposValidos;
import com.eysingenieria.pi.data.model.CFG_Configuracion;
import com.eysingenieria.pi.data.model.CFG_Evento;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-03-08T10:06:43")
@StaticMetamodel(CFG_CamposEvento.class)
public class CFG_CamposEvento_ { 

    public static volatile SingularAttribute<CFG_CamposEvento, CFG_Evento> evento;
    public static volatile SingularAttribute<CFG_CamposEvento, CFG_CamposValidos> camposValidos;
    public static volatile SingularAttribute<CFG_CamposEvento, Integer> id;
    public static volatile SingularAttribute<CFG_CamposEvento, CFG_Configuracion> configuracion;

}