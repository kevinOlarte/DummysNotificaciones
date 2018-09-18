/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.datatools.c2.utilidades;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import static javafx.scene.input.DataFormat.URL;
import javax.imageio.ImageIO;
import static javax.servlet.SessionTrackingMode.URL;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CrearExcel implements Serializable {

    private static final long serialVersionUID = 4634280093323967443L;

    private String nombreLibro;
    private String nombreHoja;
    private String[][] datos;
    private Object[][] datosObj;
    private int filas;
    private int columnas;
    private int[] anchoCol;
    private String titulo;
    private File archivoFile;
    private String rutaFinal;
    DatosEncabezadoExcel datosEncabezado;

    public CrearExcel(String nombreLibro, String nombreHoja, Object[][] datos, int filas, int columnas, int[] anchoCol, String titulo, String rutaFinal, DatosEncabezadoExcel datosEncabezado) throws Exception {
        this.nombreLibro = nombreLibro;
        this.nombreHoja = nombreHoja;
        this.datosObj = datos;
        this.filas = filas;
        this.columnas = columnas;
        this.anchoCol = anchoCol;
        this.titulo = titulo;
        this.rutaFinal = rutaFinal;
        this.datosEncabezado = datosEncabezado;
        escribirExcelObjReporte();
    }

    private void escribirExcelObjReporte() throws Exception {

        try {
            XSSFWorkbook objLibro = new XSSFWorkbook();
            XSSFSheet hoja1 = objLibro.createSheet(nombreHoja);

            XSSFFont fuenteNombreFiltro = (XSSFFont) objLibro.createFont();
            fuenteNombreFiltro.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fuenteNombreFiltro.setColor(new XSSFColor(Color.decode("#F8F9F9")));

            XSSFCellStyle estiloFiltro = objLibro.createCellStyle();
            estiloFiltro.setFillForegroundColor(new XSSFColor(Color.decode("#3498DB")));
            estiloFiltro.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            estiloFiltro.setAlignment(estiloFiltro.ALIGN_LEFT);
            estiloFiltro.setFont(fuenteNombreFiltro);
            estiloFiltro.setBorderTop(BorderStyle.DOUBLE);
            estiloFiltro.setBorderBottom(BorderStyle.DOUBLE);
            estiloFiltro.setBorderLeft(BorderStyle.DOUBLE);
            estiloFiltro.setBorderRight(BorderStyle.DOUBLE);

            XSSFFont fuenteEncabezado = (XSSFFont) objLibro.createFont();
            fuenteEncabezado.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fuenteEncabezado.setColor(new XSSFColor(Color.decode("#F8F9F9")));

            XSSFCellStyle estiloEncabezado = objLibro.createCellStyle();
            estiloEncabezado.setFillForegroundColor(new XSSFColor(Color.decode("#2471A3")));
            estiloEncabezado.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            estiloEncabezado.setAlignment(estiloFiltro.ALIGN_CENTER);
            estiloEncabezado.setFont(fuenteEncabezado);
            estiloEncabezado.setBorderTop(BorderStyle.THIN);
            estiloEncabezado.setBorderBottom(BorderStyle.THIN);
            estiloEncabezado.setBorderLeft(BorderStyle.THIN);
            estiloEncabezado.setBorderRight(BorderStyle.THIN);

            if (!UtilidadesGenerales.isVacio(datosEncabezado.getTituloEncabezado())) {
                XSSFRow fila = hoja1.createRow(0);
                XSSFCell celda = fila.createCell(0);
                celda.setCellStyle(estiloEncabezado);
                celda.setCellValue(datosEncabezado.getTituloEncabezado());
                for (int j = 1; j < datosEncabezado.getColumnas(); j++) {
                    XSSFCell celda1 = fila.createCell(j);
                    celda1.setCellStyle(estiloEncabezado);
                    celda1.setCellValue("");
                }
                combinarCeldas(hoja1, 0, 0, 0, datosEncabezado.getColumnas() - 1);
                // inmovilizar paneles
                hoja1.createFreezePane(0, datosEncabezado.getFilas() + 2, 0, 3);
            }

            for (int x = 1; x <= datosEncabezado.getFilas(); x++) {
                XSSFRow fila = hoja1.createRow(x);

                for (int j = 0; j < datosEncabezado.getColumnas(); j++) {
                    XSSFCell celda = fila.createCell(j);
                    if (datosEncabezado.getDatosEncabezado().get(x + "-" + j) == null) {
                        celda.setCellValue("");
                    } else {
                        celda.setCellValue(datosEncabezado.getDatosEncabezado().get(x + "-" + j));
                    }
                }
            }

            if(datosEncabezado.getColumnas() > 0 && datosEncabezado.getFilas() > 0){
                if(!UtilidadesGenerales.isVacio(datosEncabezado.getRutaNombreImagen1())){
                    InputStream is = new FileInputStream(datosEncabezado.getRutaNombreImagen1());
                    setimagen(objLibro, hoja1, is, datosEncabezado.getRowImagen1(), datosEncabezado.getColImagen1());                    
                }
                if(!UtilidadesGenerales.isVacio(datosEncabezado.getRutaNombreImagen2())){
                    InputStream is = new FileInputStream(datosEncabezado.getRutaNombreImagen2());
                    setimagen(objLibro, hoja1, is, datosEncabezado.getRowImagen2(), datosEncabezado.getColImagen2());                    
                }
            }

            int i = 0;
            int inicio = datosEncabezado.getFilas() + 1;

            for (int x = inicio; x < datosObj.length + inicio; x++) {
                XSSFRow fila = hoja1.createRow(x);
                for (int j = 0; j < datosObj[i].length; j++) {
                    XSSFCell celda = fila.createCell(j);
                    if (inicio == x) {
                        celda.setCellStyle(estiloEncabezado);
                    }

                    if (datosObj[i][j] instanceof Time) {
                        celda.setCellValue(datosObj[i][j].toString());
                    } else if (datosObj[i][j] instanceof Date) {
                        celda.setCellValue((Date) datosObj[i][j]);
                    } else if (datosObj[i][j] instanceof Double) {
                        celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        celda.setCellValue((double) datosObj[i][j]);
                    } else if (datosObj[i][j] instanceof Long) {
                        celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        celda.setCellValue((long) datosObj[i][j]);
                    } else if (datosObj[i][j] instanceof Integer) {
                        celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        celda.setCellValue((int) datosObj[i][j]);
                    } else if (datosObj[i][j] instanceof Boolean) {
                        celda.setCellValue((boolean) datosObj[i][j]);
                    } else if (datosObj[i][j] instanceof BigDecimal) {
                        celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        celda.setCellValue(((BigDecimal) datosObj[i][j]).doubleValue());
                    } else {
                        celda.setCellValue(datosObj[i][j] == null ? "" : datosObj[i][j].toString());
                    }

                }
                i++;
            }

            //ajustamos las columnas
            for (int z = 0; z < anchoCol.length; z++) {
                hoja1.autoSizeColumn(z);
            }

            String strNombreArchivo = rutaFinal + nombreLibro;
            archivoFile = new File(strNombreArchivo);
            FileOutputStream archivoSalida = new FileOutputStream(archivoFile);
            objLibro.write(new FileOutputStream(archivoFile));
            archivoSalida.close();
        } catch (Exception e) {
            System.out.println("Error al escribir el fichero.");
        }
    }

    public File getArchivoFile() {
        return archivoFile;
    }

    private void combinarCeldas(XSSFSheet sheet, int numRow, int untilRow, int numCol, int untilCol) {
        CellRangeAddress cellMerge = new CellRangeAddress(numRow, untilRow, numCol, untilCol);
        sheet.addMergedRegion(cellMerge);
    }

    public void setimagen(XSSFWorkbook workbook, XSSFSheet sheet, InputStream file, int row, int col) {
        try {

            //Get the contents of an InputStream as a byte[].
            byte[] bytes = IOUtils.toByteArray(file);
            //Adds a picture to the workbook
            int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            //close the input stream
            //Returns an object that handles instantiating concrete classes
            CreationHelper helper = workbook.getCreationHelper();
            //Creates the top-level drawing patriarch.
            Drawing drawing = sheet.createDrawingPatriarch();
            //Create an anchor that is attached to the worksheet
            ClientAnchor anchor = helper.createClientAnchor();
            //set top-left corner for the image
            anchor.setDx1(0);
            anchor.setDy1(0);
            anchor.setDx2(50);
            anchor.setDy2(0);
            anchor.setCol1(col);
            anchor.setRow1(row);
            anchor.setCol2(1);
            anchor.setRow2(1);
            //Creates a picture
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            //Reset the image to the original size
            pict.resize();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
