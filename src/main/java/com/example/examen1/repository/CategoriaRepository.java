package com.example.examen1.repository;

import com.example.examen1.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    @Query(value="select * from categoria where codigo like %?1%", nativeQuery = true)
    List<Categoria> buscarPorCodigo(String codigo);
    @Query(value="select * from categoria where descripcion like %?1% ",nativeQuery = true)
    List<Categoria> buscarPorDescripcion(String descripcion);

    Categoria findByCodigo(String codigo);
}
