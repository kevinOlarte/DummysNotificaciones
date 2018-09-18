package co.com.datatools.c2.utilidades;

import java.io.Serializable;

/**
 * Clase para el registro de los patrones de conversión que se requieran
 *
 * @author Cipriano Armando Gonzalez Ramirez - 20180723
 * @version 20180723 - 01
 *
 */
public class Patrones implements Serializable {

    private static final long serialVersionUID = 1946727715520357852L;

    private static final String YYYYMMDD_1 = "yyyy-MM-dd";
    private static final String YYYYMMDD_2 = "yyyyMMdd";

    public static final String FECHA_YYYYMMDD = YYYYMMDD_2;
    public static final String FECHA_YYYYMMDD_SEPARADOR_GUION = YYYYMMDD_1;
    public static final String FECHA_YYYYMMDD_PARA_JSON = YYYYMMDD_1;
}
