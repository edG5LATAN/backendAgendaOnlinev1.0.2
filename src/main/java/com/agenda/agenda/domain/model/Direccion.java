package com.agenda.agenda.domain.model;


import jakarta.persistence.Embeddable;

@Embeddable
public class Direccion {

    private String ciudad;
    private String colonia;

//    constructores
     public Direccion() {
     }
     public Direccion(String ciudad, String colonia) {
          this.ciudad = ciudad;
          this.colonia = colonia;
     }

     //    getter and setter
     public String getCiudad() {
          return ciudad;
     }

     public void setCiudad(String ciudad) {
          this.ciudad = ciudad;
     }

     public String getColonia() {
          return colonia;
     }

     public void setColonia(String colonia) {
          this.colonia = colonia;
     }
}
