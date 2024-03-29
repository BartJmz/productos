package com.example.examen1.service;

import com.example.examen1.entity.Producto;
import com.example.examen1.util.web.ContentResponse;

import java.util.List;

public interface ProductoInt {
    ContentResponse<List<Producto>> consulta();
    ContentResponse<String> agregar(Producto producto);
    ContentResponse<String> actualizar(Producto producto);
    ContentResponse<String> eliminar(Long idItem);
}
