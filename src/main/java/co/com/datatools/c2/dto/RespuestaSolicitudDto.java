package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase RespuestaSolicitudDto para la transferencia de datos de respuesta de
 las solicitudes
 *
 * @author Cipriano Armando Gonzalez Ramirez - 20180723
 * @version 20180723 - 01
 */
public class RespuestaSolicitudDto implements Serializable {

    //private static final long serialVersionUID = 1050120778724148147L;

    //private long idSolicitud;
    private String tipoIidentificacion;
    private String numeroIdentificacion;
    private List<String> correo = new ArrayList<>();
    //private String correo;
    private List<direccionCompuesta> direccion = new ArrayList<>();
    //private String direccion;

    public RespuestaSolicitudDto() {
        // NO requiere inicializar atributos
    }

    public RespuestaSolicitudDto(String tipoIidentificacion, String numeroIdentificacion) {
        this.tipoIidentificacion = tipoIidentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
    }

    

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getTipoIidentificacion() {
        return tipoIidentificacion;
    }

    public void setTipoIidentificacion(String tipoIidentificacion) {
        this.tipoIidentificacion = tipoIidentificacion;
    }

    public List<String> getCorreo() {
        return correo;
    }

    public void setCorreo(List<String> correo) {
        this.correo = correo;
    }

    public List<direccionCompuesta> getDireccion() {
        return direccion;
    }

    public void setDireccion(List<direccionCompuesta> direccion) {
        this.direccion = direccion;
    }


    

}
