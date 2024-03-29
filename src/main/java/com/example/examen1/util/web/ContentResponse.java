package com.example.examen1.util.web;

import com.example.examen1.util.constants.StatusPeticion;

import java.io.Serializable;

public class ContentResponse <T> implements Serializable {
    private int codigo;
    private String descripcion;
    private boolean isError;
    private T data;
    private StatusPeticion status;

    public ContentResponse() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public StatusPeticion getStatus() {
        return status;
    }

    public void setStatus(StatusPeticion status) {
        this.status = status;
    }
}
