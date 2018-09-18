package co.com.datatools.c2.controladores;


import co.com.datatools.c2.dto.PeticionDto;
import co.com.datatools.c2.dto.PeticionListaDto;
import co.com.datatools.c2.dto.RespuestaSolicitudDto;
import co.com.datatools.c2.util.Util;
import co.com.datatools.c2.utilidades.Parameters;
import com.google.gson.TypeAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

/**
 * Clase ConsultasWS de servicios para la consulta de informes generales
 *
 * @author Cipriano Armando Gonzalez Ramirez - 20180723
 * @version 20180723 - 01
 */
@Path("/consultas")
public class ConsultasWS implements Serializable {

    //private final IValidacionesImpl validaciones = lookupValidacionesImplLocal();

    //private final IConfiguracionImpl servicio = lookupConfiguracionImplLocal();

    //private static final long serialVersionUID = -9108532776082175965L;

    @Context
    SecurityContext securityContext;

    /**
     * Servicio Rest para la consulta Dummy ubicabilidad
     *
     * @author CAGR - 20180827
     * @param quienSolicita
     * @param json
     * @return Response Con la lista de datos solicitados
     */
//    @Seguridad
    @POST
    @Path("/consultarUbicabilidad")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response consultarUbicabilidad(@Context HttpServletRequest quienSolicita, String json) {
//        final Gson gson = new Gson();
//	final Type tipoListaPeticionDto = new TypeToken<List<PeticionDto>>(){}.getType();
//	final List<PeticionDto > dtoParametros = gson.fromJson(json, tipoListaPeticionDto);
        //PeticionDto[] dtoParametros = new Gson().fromJson(json, PeticionDto[].class);
        List<PeticionDto> dtoParametros = new Gson().fromJson(json, new TypeToken<List<PeticionDto>>(){}.getType());
        List<RespuestaSolicitudDto> lista = new ArrayList<RespuestaSolicitudDto>();
        List<RespuestaSolicitudDto> listaRespuesta = new ArrayList<RespuestaSolicitudDto>();
        List<String> listaValidacion = new ArrayList<>();



        // Consulta al servicio
        if (true) {
            try {
                        //set Datos DUMMYS
                        lista = Util.setListDummy(listaRespuesta);
//                List<SolicitudDto> listaDto = servicio.getDao().getListaSolicitudesConsultaNotificaciones(dto);
//
//                for (SolicitudDto iterador : listaDto) {
//                    SolicitudDtoEstadosWS objeto = new SolicitudDtoEstadosWS();
//
//                    objeto.setActivo(iterador.isActivo());
//                    objeto.setEstadoSolicitud(iterador.getEstadoSolicitud());
//                    objeto.setFechaFinalSuspendida(iterador.getFechaFinalSuspendida());
//                    if (iterador.getFechaFinalSuspendida() != null) {
//                        objeto.setFechaFinalSuspendidaTexto(UtilidadesGenerales.getFechaDateAFechaTexto(iterador.getFechaFinalSuspendida(), Patrones.FECHA_YYYYMMDD_PARA_JSON));
//                    }
//                    objeto.setFechaInicialSuspendida(iterador.getFechaInicialSuspendida());
//                    if (iterador.getFechaInicialSuspendida() != null) {
//                        objeto.setFechaInicialSuspendidaTexto(UtilidadesGenerales.getFechaDateAFechaTexto(iterador.getFechaInicialSuspendida(), Patrones.FECHA_YYYYMMDD_PARA_JSON));
//                    }
//                    objeto.setFechaRegistroSogitTexto(UtilidadesGenerales.getFechaDateAFechaTexto(iterador.getFechaRegistroSogit(), Patrones.FECHA_YYYYMMDD_PARA_JSON));
//                    objeto.setIdCanton(iterador.getIdCanton());
//                    objeto.setIdProvincia(iterador.getIdProvincia());
//                    objeto.setIdSolicitud(iterador.getIdSolicitud());
//                    objeto.setIdTipoDocumentoNotificar(iterador.getTipoDocumentoNotificar().getIdTipoDocumentoNotificar());
//                    objeto.setIdTipoIdentificacion(iterador.getTercero().getTipoIdentificacion().getIdTipoIdentificacion());
//                    objeto.setMotivo(iterador.getMotivo());
//                    objeto.setNombreCompleto(iterador.getTercero().getNombreCompleto());
//                    objeto.setNombreEstadoSolicitud(EEstadosSolicitud.getNombrePorId(iterador.getEstadoSolicitud()));
//                    objeto.setNombreMotivo(EMotivoSolicitud.getNombrePorId(iterador.getMotivo()));
//                    objeto.setNombreResponsable(EResponsableSolicitud.getNombrePorId(iterador.getResponsable()));
//                    objeto.setNombreTipoDocumentoNotificar(iterador.getTipoDocumentoNotificar().getDescripcion());
//                    objeto.setNombreTipoIdentificacion(iterador.getTercero().getTipoIdentificacion().getDescripcion());
//                    objeto.setNumeroFactura(iterador.getNumeroFactura());
//                    objeto.setNumeroIdentificacion(iterador.getTercero().getNumeroIdentificacion());
//                    objeto.setParroquia(iterador.getParroquia());
//                    objeto.setResponsable(iterador.getResponsable());
//                    objeto.setValorMulta(iterador.getValorMulta());
//                    objeto.setUsuarioRegistro(iterador.getUsuarioRegistro());
//                    objeto.setLote(iterador.getLote());
//                    objeto.setFechaSolicitudTexto(UtilidadesGenerales.getFechaDateAFechaTexto(iterador.getFechaSolicitud(), Patrones.FECHA_YYYYMMDD_PARA_JSON));
//                    objeto.setDiasTranscurridos(iterador.getDiasTranscurridosNotifiacion());
//                    objeto.setFechaImposicionTexto(UtilidadesGenerales.getFechaDateAFechaTexto(iterador.getFechaImposicion(), Patrones.FECHA_YYYYMMDD_PARA_JSON));
//                    objeto.setFechaMultaTexto(UtilidadesGenerales.getFechaDateAFechaTexto(iterador.getFechaMulta(), Patrones.FECHA_YYYYMMDD_PARA_JSON));
//                    objeto.setNumeroCitacion(iterador.getNumeroCitacion());
//
//                    //Datos Dummy
//                    objeto.setNombreMedioEnvio("Courier");
//                    objeto.setFechaEnvioTexto("2018-08-21");
//                    objeto.setFechaNotificacionTexto("2018-08-31");
//
//                    lista.add(objeto);
//                }
                            // respuesta
                    if (lista.isEmpty()) {
                        return Response.status(Response.Status.NOT_FOUND).build();
                    } else {
                        
                        //return Response.status(Response.Status.CREATED).entity(lista).build();
                    }
                } catch (Exception e) {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }
        } else {
//                //hay problemas en 1 o mas validaciones
//            for (String iterador : listaValidacion) {
//                RespuestaProcesoDtoWS dtoRta = new RespuestaProcesoDtoWS();
//                dtoRta.setCorrecto(false);
//                dtoRta.setRespuesta(iterador);
//                listaRespuesta.add(dtoRta);
//            }
        }
        //Object lista;
        return Response.status(Response.Status.CREATED).entity(true ? lista : listaRespuesta).build();
        //return Response.status(Response.Status.CREATED).entity(lista).build();
    }

    
//    private IConfiguracionImpl lookupConfiguracionImplLocal() {
//        try {
//            javax.naming.Context c = new InitialContext();
//            return (IConfiguracionImpl) c.lookup(
//                    "java:global/Notificaciones2EAR-1.0-SNAPSHOT/NotificacionesEJB-1.0/ConfiguracionImpl!com.co.datatools.c2.interfaz.IConfiguracionImpl");
//        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, Parameters.Strings.EXCEPTION_CAGHT, ne);
//            throw new RuntimeException(ne);
//        }
//    }
//
//    private IValidacionesImpl lookupValidacionesImplLocal() {
//        try {
//            javax.naming.Context c = new InitialContext();
//            return (IValidacionesImpl) c.lookup(
//                    "java:global/Notificaciones2EAR-1.0-SNAPSHOT/NotificacionesEJB-1.0/ValidacionesImpl!com.co.datatools.c2.interfaz.IValidacionesImpl");
//        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, Parameters.Strings.EXCEPTION_CAGHT, ne);
//            throw new RuntimeException(ne);
//        }
//    }
}
