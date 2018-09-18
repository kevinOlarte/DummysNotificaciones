package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase PeticionDto para la transferencia de datos de respuesta de
 las solicitudes
 *
 * @author Cipriano Armando Gonzalez Ramirez - 20180723
 * @version 20180723 - 01
 */
public class PeticionListaDto implements Serializable {

    //private static final long serialVersionUID = 7961679898876874326L;

    private List<PeticionDto> ids = new ArrayList<>();

    public List<PeticionDto> getIds() {
        return ids;
    }

    public void setIds(List<PeticionDto> ids) {
        this.ids = ids;
    }

   

}
