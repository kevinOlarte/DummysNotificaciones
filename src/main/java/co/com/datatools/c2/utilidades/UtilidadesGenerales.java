package co.com.datatools.c2.utilidades;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import sun.misc.BASE64Encoder;

/**
 * Clase para el registro de metodos comúnes para procesamiento de datos,
 * validaciones, conversiones etc.
 *
 * @author Cipriano Armando Gonzalez Ramirez - 20180723
 * @version 20180723 - 01
 *
 */
public class UtilidadesGenerales implements Serializable {

    private static final long serialVersionUID = 3957991515722461947L;
    private static final Logger logger = Logger.getLogger(Parameters.class.toString());

    public static boolean isVacio(String cadena) {
        return cadena == null || cadena.trim().equals("");
    }

    /**
     * 20180726 - Método para convertir una fecha tipo String a tipo Date
     *
     * @param fecha
     * @param patron
     * @return Date fecha
     * @throws java.text.ParseException
     */
    public static Date getFechaStringAFechaDate(String fecha, String patron) throws ParseException {
        return new SimpleDateFormat(patron).parse(fecha);
    }

    /**
     * 20180726 - Método para convertir una fecha tipo Date a tipo String
     *
     * @param fecha
     * @param patron
     * @return String fecha
     * @throws java.text.ParseException
     */
    public static String getFechaDateAFechaTexto(Date fecha, String patron) {
        return new SimpleDateFormat(patron).format(fecha);
    }

    /**
     * 20180726 - Método para validar si un rango de fechas se solapa con otro
     *
     * @param fechaInidialRangoExistente
     * @param fechaFinalRangoExistente
     * @param fechaInicialNuevoRango
     * @param fechaFinalNuevoRango
     * @return boolean
     * @throws java.text.ParseException
     */
    public static boolean isRangoFechaSolapado(Date fechaInidialRangoExistente, Date fechaFinalRangoExistente, Date fechaInicialNuevoRango, Date fechaFinalNuevoRango) {
        String patron = Patrones.FECHA_YYYYMMDD;
        DateTimeFormatter formatter = DateTimeFormat.forPattern(patron);
        DateTime fecha1 = formatter.parseDateTime(getFechaDateAFechaTexto(fechaInidialRangoExistente, patron));
        DateTime fecha2 = formatter.parseDateTime(getFechaDateAFechaTexto(fechaFinalRangoExistente, patron));
        DateTime fecha3 = formatter.parseDateTime(getFechaDateAFechaTexto(fechaInicialNuevoRango, patron));
        DateTime fecha4 = formatter.parseDateTime(getFechaDateAFechaTexto(fechaFinalNuevoRango, patron));

        Interval rango1 = new Interval(fecha1, fecha2);
        Interval rango2 = new Interval(fecha3, fecha4);

        return rango1.overlaps(rango2);
    }

    /**
     * 20180726 - Método para validar si una fecha1 es anterior a fecha2
     *
     * @param fecha1
     * @param fecha2
     * @return boolean
     * @throws java.text.ParseException
     */
    public static boolean isFechaAnterior(Date fecha1, Date fecha2) throws ParseException {
        String patron = Patrones.FECHA_YYYYMMDD;
        fecha1 = getFechaStringAFechaDate(getFechaDateAFechaTexto(fecha1, patron), patron);
        fecha2 = getFechaStringAFechaDate(getFechaDateAFechaTexto(fecha2, patron), patron);
        return fecha1.before(fecha2);
    }

    /**
     * 20180726 - Método para validar si una fecha1 es posterior a fecha2
     *
     * @param fecha1
     * @param fecha2
     * @return boolean
     * @throws java.text.ParseException
     */
    public static boolean isFechaPosterior(Date fecha1, Date fecha2) throws ParseException {
        String patron = Patrones.FECHA_YYYYMMDD;
        fecha1 = getFechaStringAFechaDate(getFechaDateAFechaTexto(fecha1, patron), patron);
        fecha2 = getFechaStringAFechaDate(getFechaDateAFechaTexto(fecha2, patron), patron);
        return fecha1.after(fecha2);
    }

    /**
     * Devuelve la diferencia entre dos fechas; valor= 1, devuelve diferencia
     * entre dias, 2 devuelve diferencia en meses, 3 devuelve diferencia en años
     *
     * @param fecha1
     * @param fecha2
     * @param valor
     * @return int
     */
    public static int getDiferenciaEntreFechas(Date fecha1, Date fecha2, int valor) {
        int retorno = 0;
        java.util.Date date1 = fecha1;
        java.util.Date date2 = fecha2;
        try {
            Calendar cal1 = null;
            cal1 = Calendar.getInstance();

            Calendar cal2 = null;
            cal2 = Calendar.getInstance();

            cal1.setTime(date1);
            long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);

            cal2.setTime(date2);
            long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);

            int hr1 = (int) (ldate1 / 3600000); //60*60*1000 
            int hr2 = (int) (ldate2 / 3600000);

            int days1 = (int) hr1 / 24;
            int days2 = (int) hr2 / 24;

            int dateDiff = days2 - days1;
            int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
            int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

            switch (valor) {
                case 1:
                    if (dateDiff < 0) {
                        dateDiff = dateDiff * (-1);
                    }
                    retorno = dateDiff;
                    break;
                case 2:
                    if (monthDiff < 0) {
                        monthDiff = monthDiff * (-1);
                    }
                    retorno = monthDiff;
                    break;
                case 3:
                    if (yearDiff < 0) {
                        yearDiff = yearDiff * (-1);
                    }
                    retorno = yearDiff;
                    break;
                default:
                    if (dateDiff < 0) {
                        dateDiff = dateDiff * (-1);
                    }
                    retorno = dateDiff;
            }

        } catch (Exception pe) {
            logger.log(Level.SEVERE, pe.getMessage());
        }
        return retorno;
    }

    /**
     * Mónica Janeth Blanco Díaz 20180824 Recibe un String, la procesa en base64
     * y devuelve un array de bytes
     *
     *
     * @param cadena
     * @return byte Array
     */
    public static byte[] getStringAByte(String cadena) {

        byte[] base64Decode = DatatypeConverter.parseBase64Binary(cadena);

        return base64Decode;
    }

    /**
     * Mónica Janeth Blanco Díaz 20180824 Recibe un arreglo de bytes, la ruta y
     * nombre del archivo, con la extensión que define su formato. Devuelve un
     * devuelve un File
     *
     *
     * @param array
     * @param extension
     * @param rutaNombre
     * @return Flie
     */
    public static File getByteAFile(byte[] array, String extension, String rutaNombre) throws FileNotFoundException, IOException {

        File file = new File(rutaNombre + "." + extension);
        try (
                OutputStream os = new FileOutputStream(file)) {
            os.write(array);
        } catch (Exception e) {
            System.out.println("Error en conversión de Array de bytes a File ");
        }

        return file;
    }

    /**
     * Mónica Janeth Blanco Díaz 20180824 Recibe un String, la procesa en base64
     * y devuelve un array de bytes
     *
     *
     * @param fileXLSX
     * @param extension
     * @param nombreRutaArchivo
     *
     */
    public static void getProcesarArchivoXLSX(File fileXLSX, String extension, String nombreRutaArchivo) throws FileNotFoundException, IOException {

        File outputFile = crearFileCsvVacio(nombreRutaArchivo.trim() + "." + extension.trim());
        StringBuffer data = new StringBuffer();
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileXLSX));
            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell;
            Row row;

            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
//                    cell = row.getCell(cell, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            data.append(cell.getNumericCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_STRING:
                            data.append(cell.getStringCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_BLANK:
                            data.append("" + ",");
                            break;

                        default:
                            data.append(cell + ",");
                    }

                    data.append('\n');
                }
            }

            fos.write(data.toString().getBytes());
            fos.close();

            getListaArchivoCSV(";", "\"", nombreRutaArchivo.trim() + "." + extension.trim());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static File crearFileCsvVacio(String rutaNombreExtension) {

        File file = new File(rutaNombreExtension);

        boolean fileCreated = false;

        try {

            fileCreated = file.createNewFile();

        } catch (IOException ioe) {
            System.out.println("Error while creating empty file: " + ioe);

        }

        if (fileCreated) {

            System.out.println("Created empty file: " + file.getPath());

        } else {

            System.out.println("Failed to create empty file: " + file.getPath());

        }

        return file;

    }

    private static void getListaArchivoCSV(String separador, String quote, String rutaNombreExtencion) throws IOException {

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader("C:\\Users\\MONICA\\Documents\\LibroPruebacsv.csv"));
            String line = br.readLine();
            while (null != line) {
                String[] fields = line.split(separador);
                System.out.println(Arrays.toString(fields));

                fields = removeTrailingQuotes(fields, quote);
                System.out.println(Arrays.toString(fields));

                line = br.readLine();
            }

        } catch (Exception e) {

        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    private static String[] removeTrailingQuotes(String[] fields, String quote) {

        String result[] = new String[fields.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = fields[i].replaceAll("^" + quote, "").replaceAll(quote + "$", "");
        }
        return result;
    }

    public static Response addResponseOK(Object catalogoList) {
        System.out.println("Agregando headers ok");
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Origin")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
                .entity(catalogoList)
                .build();
    }

    public static Response addResponseError(Response.Status status, Object message) {
        System.out.println("Agregando headers error");
        return Response.status(status)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Origin")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
                .entity(message)
                .build();
    }

    /**
     * Devuelve File de excel
     *
     * @param listaExporter
     * @param listaNombreCampos
     * @param nombreHojaExportar
     * @param tituloEnHoja
     * @param nombreArchivo
     * @param rutaFinal
     * @return int
     */
    public static File getExcel(List<Object[]> listaExporter, List<String> listaNombreCampos, String nombreHojaExportar, String tituloEnHoja, String nombreArchivo, String rutaFinal, DatosEncabezadoExcel datosEncabezado) {
        CrearExcel excel;
        int filasEncabezado = 0 + 1;
        int filas = listaExporter.size() + filasEncabezado + 1;
        int col = listaNombreCampos.size();

        // seteamos los titulos
        Object lista[][] = new Object[filas][col];
        int anchoCol[] = new int[col];

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j <= col - 1; j++) {
                lista[i][j] = String.valueOf(listaNombreCampos.get(j));
            }
        }

        // enviamos la data
        for (int i = 0; i < listaExporter.size(); i++) {
            for (int j = 0; j <= col - 1; j++) {
                lista[i + 1][j] = listaExporter.get(i)[j];
            }
        }

        for (int i = 0; i < listaNombreCampos.size(); i++) {
            anchoCol[i] = 250;
        }

        try {
            String nombreHoja = nombreHojaExportar;
            crearDirectorio(rutaFinal, true);

            excel = new CrearExcel(nombreArchivo, nombreHoja, lista, listaExporter.size(), col, anchoCol, tituloEnHoja, rutaFinal, datosEncabezado);
            return excel.getArchivoFile();
        } catch (Exception e) {
            return null;
        }
    }

    public static void crearDirectorio(String directorio, boolean todaLaRuta) throws IOException {
        File folder = new File(directorio);
        if (todaLaRuta) {
            folder.mkdirs();
        } else {
            folder.mkdir();
        }
    }

    /**
     * Devuelve texto aleatorio segun la cantidad de digitod
     *
     * @param digitos
     * @return int
     */
    public static String getTextoAleatorio(int digitos) {
        String texto = "";

        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random random = new Random(milis);
        int iterador = 0;
        while (iterador < digitos) {
            char c = (char) random.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                texto += c;
                iterador++;
            }
        }

        return texto;
    }

    /**
     * Verifica si existe un archivo
     *
     * @param rutaArchivo
     * @return
     */
    public static boolean isExisteArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        return archivo.exists();
    }

    /**
     * Convierte un arreglo de byte a base64
     *
     * @param rutaArchivo
     * @return
     */
    public static String convertirRutaArchivoStringAABse64(String rutaArchivo) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            byte[] base64EncodedImage = convertirArchivoAByte(rutaArchivo);
            if (base64EncodedImage != null) {
                String imageBase64 = encoder.encodeBuffer(base64EncodedImage);
                if (imageBase64 != null && !imageBase64.trim().equals("")) {
                    return imageBase64;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convierte un archivo de una ruta a un arreglo de byte
     *
     * @param rutaArchivo
     * @return
     */
    public static byte[] convertirArchivoAByte(String rutaArchivo) throws Exception {
        File file = new File(rutaArchivo.toString());
        if (file.exists()) {
            int lenght = (int) file.length();
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[lenght];
            reader.read(bytes, 0, lenght);
            reader.close();
            return bytes;
        } else {
            return null;
        }
    }

    public static String getRutasPrincipalEvidencias() {
        return Parameters.getParameter(Parameters.RUTA_ARCHIVOS) + "/" + Parameters.getParameter(Parameters.RUTA_ADICIONAL_EVIDENCIAS_COURIER) + "/";
    }

    public static boolean eliminarArchivoFisico(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        return archivo.delete();
    }

    public static void escribirArchivoBase64AArchivoEnDisco(String archivoBAse64, String ruraArchivo) throws FileNotFoundException, IOException {
        byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(archivoBAse64);
        File outputFile = new File(ruraArchivo);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(bytes);
        outputStream.close();
    }

}
