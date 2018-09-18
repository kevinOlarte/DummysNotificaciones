package co.com.datatools.c2.controladores;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Clase Aplicacion para entrada de los servicios
 *
 * @author Cipriano Armando Gonzalez Ramirez - 20180723
 * @version 20180723 - 01
 */

@javax.ws.rs.ApplicationPath("/rest")
public class Aplicacion extends Application{
     @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
     private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(co.com.datatools.c2.controladores.AuthenticacionResource.class);
//        resources.add(co.com.datatools.c2.controladores.CatalogosWS.class);
        //resources.add(co.com.datatools.c2.controladores.ConfiguracionWS.class);
        resources.add(co.com.datatools.c2.controladores.ConsultasWS.class);
//        resources.add(co.com.datatools.c2.controladores.ProcesosWS.class);
//        resources.add(co.com.datatools.c2.controladores.ReportWS.class);
//        resources.add(co.com.datatools.c2.servicio.AuthenticationFilter.class);
//        resources.add(co.com.datatools.c2.servicio.CorsFilter.class);
    }
    
}
