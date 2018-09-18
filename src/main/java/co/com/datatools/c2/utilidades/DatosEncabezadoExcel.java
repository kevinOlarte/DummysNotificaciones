package co.com.datatools.c2.utilidades;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author GONZALEZ
 */
public class DatosEncabezadoExcel implements Serializable{
    
    private static final long serialVersionUID = -7534715438282160150L;
    
    private int columnas;
    private int filas;
    private String tituloEncabezado = "";
    private Map<String,String> datosEncabezado = new HashMap<>();
    private String rutaNombreImagen1="";
    private String rutaNombreImagen2="";
    private int rowImagen1;
    private int colImagen1;
    private int rowImagen2;
    private int colImagen2;

    public DatosEncabezadoExcel() {
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public String getTituloEncabezado() {
        return tituloEncabezado;
    }

    public void setTituloEncabezado(String tituloEncabezado) {
        this.tituloEncabezado = tituloEncabezado;
    }

    public Map<String,String> getDatosEncabezado() {
        return datosEncabezado;
    }

    public void setDatosEncabezado(Map<String,String> datosEncabezado) {
        this.datosEncabezado = datosEncabezado;
    }

    public String getRutaNombreImagen1() {
        return rutaNombreImagen1;
    }

    public void setRutaNombreImagen1(String rutaNombreImagen1) {
        this.rutaNombreImagen1 = rutaNombreImagen1;
    }

    public String getRutaNombreImagen2() {
        return rutaNombreImagen2;
    }

    public void setRutaNombreImagen2(String rutaNombreImagen2) {
        this.rutaNombreImagen2 = rutaNombreImagen2;
    }

    public int getRowImagen1() {
        return rowImagen1;
    }

    public void setRowImagen1(int rowImagen1) {
        this.rowImagen1 = rowImagen1;
    }

    public int getColImagen1() {
        return colImagen1;
    }

    public void setColImagen1(int colImagen1) {
        this.colImagen1 = colImagen1;
    }

    public int getRowImagen2() {
        return rowImagen2;
    }

    public void setRowImagen2(int rowImagen2) {
        this.rowImagen2 = rowImagen2;
    }

    public int getColImagen2() {
        return colImagen2;
    }

    public void setColImagen2(int colImagen2) {
        this.colImagen2 = colImagen2;
    }
    
    
}
