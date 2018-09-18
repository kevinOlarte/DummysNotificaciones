/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.datatools.c2.util;

import co.com.datatools.c2.dto.RespuestaSolicitudDto;
import co.com.datatools.c2.dto.direccionCompuesta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CGIngenieria
 */
public class Util {
    
    public final static List<RespuestaSolicitudDto> setListDummy(List<RespuestaSolicitudDto> lista){ 
        
        //objecto 0
        RespuestaSolicitudDto respuestaSolicitudDto = new RespuestaSolicitudDto("RUC", "1792413176001");
        List<String> listaCorreo = new ArrayList<>();
        List<direccionCompuesta> listaDireccion = new ArrayList<>();
        direccionCompuesta compuesta = new direccionCompuesta();
        
        listaCorreo.add("correo1@foo.com");
        listaCorreo.add("correo2@foo.com");
        
        
        
        compuesta.setDireccion("SALSAS 8 MZ 496 V 4");
        compuesta.setProvincia("Guayas");
        compuesta.setCanton("Guayaquil");
        compuesta.setParroquia("");
        compuesta.setValidacion("valida");
        compuesta.setCalificacion("1");
        listaDireccion.add(compuesta);
        
        compuesta.setDireccion("AV. FRANCISCO DE ORELLANA PISCINA JORGE DELGADO SUR / NORTE");
        compuesta.setProvincia("Guayas");
        compuesta.setCanton("Guayaquil");
        compuesta.setParroquia("");
        compuesta.setValidacion("valida");
        compuesta.setCalificacion("1");
        listaDireccion.add(compuesta);
        
        
        respuestaSolicitudDto.setCorreo(listaCorreo);
        respuestaSolicitudDto.setDireccion(listaDireccion);
        lista.add(respuestaSolicitudDto);
        
        //objecto 1
        RespuestaSolicitudDto respuestaSolicitudDto1 = new RespuestaSolicitudDto("RUC", "0603022534");
        List<String> listaCorreo1 = new ArrayList<>();
        List<direccionCompuesta> listaDireccion1 = new ArrayList<>();
        direccionCompuesta compuesta1 = new direccionCompuesta();
        
        listaCorreo1.add("correo1@foo.com");
        listaCorreo1.add("correo2@foo.com");
        
        
        
        compuesta1.setDireccion("SALSAS 8 MZ 496 V 4");
        compuesta1.setProvincia("Guayas");
        compuesta1.setCanton("Guayaquil");
        compuesta1.setParroquia("");
        compuesta1.setValidacion("valida");
        compuesta1.setCalificacion("1");
        listaDireccion1.add(compuesta1);
        
        compuesta1.setDireccion("SALSAS 8 MZ 496 V 40");
        compuesta1.setProvincia("Guayas");
        compuesta1.setCanton("Guayaquil");
        compuesta1.setParroquia("");
        compuesta1.setValidacion("valida");
        compuesta1.setCalificacion("1");
        listaDireccion1.add(compuesta1);
        
        
        respuestaSolicitudDto1.setCorreo(listaCorreo1);
        respuestaSolicitudDto1.setDireccion(listaDireccion1);
        lista.add(respuestaSolicitudDto1);
        return lista;
    }
    
}
