package com.example.examen1.util.constants;

public enum StatusPeticion {
    EXITO("OK"),
    ERROR("ERROR");

    private String descripcion= "";

    public String getDescripcion(){
        return this.descripcion;
    }
    private StatusPeticion(String descripcion){
        this.descripcion = descripcion;
    }
}
