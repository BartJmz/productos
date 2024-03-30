package com.example.examen1.repository;

import com.example.examen1.entity.CodigoBarra;
import com.example.examen1.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CodigoBarraRepository extends CrudRepository<CodigoBarra,Long> {

    @Query(value = "SELECT p.id, p.activo, p.codigo, p.descripcion, categoria_id " +
            "FROM producto p JOIN producto_codigo cb ON cb.producto_id = p.id " +
            "where cb.codigo =?1", nativeQuery = true)
    List<Producto> consultaProductosPorCodigo(String codigo);
}
