/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.datatools.c2.dto;

/**
 *
 * @author CGIngenieria
 */
public class direccionCompuesta {
            private String direccion;
            private String provincia;
            private String canton;
            private String parroquia;
            private String validacion;
            private String calificacion;
            
        public direccionCompuesta(){
            
        }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }
            
         
        
        

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }

        public String getParroquia() {
            return parroquia;
        }

        public void setParroquia(String parroquia) {
            this.parroquia = parroquia;
        }

        public String getValidacion() {
            return validacion;
        }

        public void setValidacion(String validacion) {
            this.validacion = validacion;
        }

        public String getCalificacion() {
            return calificacion;
        }

        public void setCalificacion(String calificacion) {
            this.calificacion = calificacion;
        }
}
