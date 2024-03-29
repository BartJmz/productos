package com.example.examen1.service;

import com.example.examen1.entity.Categoria;
import com.example.examen1.entity.Producto;
import com.example.examen1.util.web.ContentResponse;

import java.util.List;

public interface CategoriaInt {
    ContentResponse<List<Categoria>> consulta();
    ContentResponse<List<Categoria>> consultaPorCodigo(String codigo);
    ContentResponse<List<Categoria>> consultaPorDescripcion(String codigo);
    ContentResponse<String> agregar(Categoria categoria);
    ContentResponse<String> actualizar(Categoria categoria);
    ContentResponse<String> eliminar(Long idItem);

}
