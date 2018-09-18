package co.com.datatools.c2.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Componente de seguridad DataTools
 *
 * @author Leonardo Machado Jaimes
 */
public class Parameters {

    private static final Logger logger = Logger.getLogger(Parameters.class.toString());
    private static Properties properties;
    public static final String EXPIRATION_TIME = "EXPIRATION_TIME";
    public static final String AUTHENTICATION_SIGNATURE = "AUTHENTICATION_SIGNATURE";

    public static final int PROYECTO = 1;
    public static final int PROCESO = 2;
    public static final int SUBPROCESO = 3;
    public static final int TIPO_DOCUMENTO_NOTIFICAR = 4;
    public static final int PRIORIDAD = 5;
    public static final int PERIODICIDAD_VALOR = 6;
    public static final int TIPO_PERIODICIDAD = 7;
    public static final int PERMANENCIA_MEDIO_VALOR = 8;
    public static final int TIPO_PERMANENCIA_MEDIO = 9;
    public static final int MAXIMO_INTENTOS = 10;
    public static final int TIPO_IDENTIFICACION = 11;
    public static final int ESTADOS_SOLICITUD = 12;
    public static final int MOTIVOS = 13;
    public static final int MEDIO_ENVIO_SIMPLE = 14;
    public static final int PROVINCIA = 15;
    public static final int CANTON = 16;
    public static final int PARROQUIA = 17;
    public static final int TIPO_VALIDACION = 18;
    public static final int PROVEEDOR_CORREO = 19;
    public static final int TIPO_ARCHIVO = 20;
    public static final String REPORTS_DIR="REPORTS_DIR";
    public static final String RUTA_ARCHIVOS="RUTA_ARCHIVOS";
    public static final String RUTA_ADICIONAL_ARCHIVOS_TEMPORALES="RUTA_ADICIONAL_ARCHIVOS_TEMPORALES";
    public static final String RUTA_ADICIONAL_EVIDENCIAS_COURIER="RUTA_ADICIONAL_EVIDENCIAS_COURIER";
    public static final String CANTIDAD_IMAGENES_PERMITIDAS_EVIDENCIAS_COURIER="CANTIDAD_IMAGENES_PERMITIDAS_EVIDENCIAS_COURIER";
    public static final String RUTA_IMAGENES="RUTA_IMAGENES";

    static {
        getPropertiesFile();
    }

    private static Properties getPropertiesFile() {
        properties = new Properties();
        InputStream inputStream;
        inputStream = Parameters.class.getClassLoader().getResourceAsStream("notificaciones.properties");
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error", e);
        }

        return properties;
    }

    public static String getParameter(String param) {
        return properties.getProperty(param);
    }

    public static long getLongParameter(String param) {
        try {
            return Long.parseLong(getParameter(param));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public static class Strings{
        public static final String DEBE_SELECCIONAR_SOLICITUD = "Debe seleccionar una solicitud";
        public static final String DEBE_ESPECIFICAR_FECHA_FINAL = "Debe especificar la fecha final";
        public static final String DEBE_ESPECIFICAR_FECHA_INICIAL = "Debe especificar la fecha inicial";
        public static final String DEBE_SELECCIONAR_PROYECTO = "Debe seleccionar un proyecto";
        public static final String DEBE_SELECCIONAR_PROCESO = "Debe seleccionar un proceso";
        public static final String INCONSISTENCIAS_CONVERTIR_FECHAS = "Se presentaron inconsistencias al convertir fechas";
        public static final String A_FECHA_SOLICITUD = "a.fechaSolicitud";
        public static final String EXCEPTION = "Exception";
        public static final String ACCES_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
        public static final String ACCES_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
        public static final String GET_POST_DELETE_PUT_OPTIONS_HEAD = "GET, POST, DELETE, PUT, OPTIONS, HEAD";
        public static final String ACCES_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
        public static final String CONTENT_TYPE_ACCEPT_X_REQUESTED_WITH = "Content-Type, Accept, X-Requested-With";
        public static final String ACCES_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
        public static final String OPTIONS = "OPTIONS";
        public static final String EXCEPTION_CAGHT = "exception caught";
        public static final String TABLA_SOLICITUD = "Solicitud";
        public static final String NUMERO_IDENTIFICACION = "numeroIdentificacion";
        public static final String AND_A_NUMERO_IDENTIFICACION = " AND a.numeroIdentificacion = :";
        public static final String SELECT_A_FROM = " SELECT a FROM ";
        public static final String WHERE_1_1 = " WHERE 1 = 1 ";
        public static final String AND_ACTIVO_TRUE = " AND a.activo = true ";
        public static final String ORDER_BY = " ORDER BY ";
        public static final String AND_IDPROYECTO_IGUAL_PARAMETRO = " AND p.idProyecto = :idProyecto ";
        public static final String PARAMETRO_IDPROYECTO = "idProyecto";
        public static final String PARAMETRO_IDPROCESO = "idProceso";
        public static final String PARAMETRO_IDSPROCESO = "idSProceso";
    }
}





