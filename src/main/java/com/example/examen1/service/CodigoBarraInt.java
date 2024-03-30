package com.example.examen1.service;

import com.example.examen1.entity.Categoria;
import com.example.examen1.entity.CodigoBarra;
import com.example.examen1.entity.Producto;
import com.example.examen1.util.web.ContentResponse;

import java.util.List;

public interface CodigoBarraInt {
    ContentResponse<List<CodigoBarra>> consulta();
    ContentResponse<String> agregar(CodigoBarra codigoBarra);
    ContentResponse<String> actualizar(CodigoBarra codigoBarra);
    ContentResponse<String> eliminar(Long idItem);
}
