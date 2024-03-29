package com.example.examen1.repository;

import com.example.examen1.entity.Categoria;
import com.example.examen1.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    @Query(value="select * from producto where codigo = ?1", nativeQuery = true)
    List<Producto> buscarPorCodigo(String codigo);
    @Query(value="select * from producto where descripcion like %?1% ",nativeQuery = true)
    List<Producto> buscarPorDescripcion(String descripcion);

    @Query(value = "SELECT p.id, p.activo, p.codigo, p.descripcion, categoria_id, codigo_barra_id FROM producto p JOIN codigo_barra cb ON cb.id = p.codigo_barra_id " +
            "where cb.codigo =?1", nativeQuery = true)
    List<Producto> consultaProductosPorCodigo(String codigo);
}
