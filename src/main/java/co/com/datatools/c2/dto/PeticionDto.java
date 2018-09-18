package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * Clase PeticionDto para la transferencia de datos de respuesta de
 las solicitudes
 *
 * @author Cipriano Armando Gonzalez Ramirez - 20180723
 * @version 20180723 - 01
 */
public class PeticionDto implements Serializable {

    //private static final long serialVersionUID = 7961679898876874326L;

    
    private String tipoIidentificacion; 
    private String numeroIdentificacion;

    public PeticionDto() {
        // NO requiere inicializar atributos
    }

    public String getTipoIidentificacion() {
        return tipoIidentificacion;
    }

    public void setTipoIidentificacion(String tipoIidentificacion) {
        this.tipoIidentificacion = tipoIidentificacion;
    }




    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

}
