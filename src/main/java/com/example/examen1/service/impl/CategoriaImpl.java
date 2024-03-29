package com.example.examen1.service.impl;

import com.example.examen1.entity.Categoria;
import com.example.examen1.repository.CategoriaRepository;
import com.example.examen1.service.CategoriaInt;
import com.example.examen1.util.constants.StatusPeticion;
import com.example.examen1.util.web.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriaImpl implements CategoriaInt {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public ContentResponse<List<Categoria>> consulta(){
        ContentResponse<List<Categoria>> ventas = new ContentResponse<>();
        List<Categoria> listaCat = (List<Categoria>) categoriaRepository.findAll();
        ventas.setData(listaCat);
        ventas.setCodigo(1);
        ventas.setError(false);
        ventas.setStatus(StatusPeticion.EXITO);
        return ventas;
    }

    public ContentResponse<List<Categoria>> consultaPorCodigo(String codigo){
        ContentResponse<List<Categoria>> cats = new ContentResponse<>();
        List<Categoria> listaCat = categoriaRepository.buscarPorCodigo(codigo);
        cats.setData(listaCat);
        cats.setCodigo(1);
        cats.setError(false);
        cats.setStatus(StatusPeticion.EXITO);
        return cats;
    }

    public ContentResponse<List<Categoria>> consultaPorDescripcion(String codigo){
        ContentResponse<List<Categoria>> cats = new ContentResponse<>();
        List<Categoria> listaCat = categoriaRepository.buscarPorDescripcion(codigo);
        cats.setData(listaCat);
        cats.setCodigo(1);
        cats.setError(false);
        cats.setStatus(StatusPeticion.EXITO);
        return cats;
    }

    public ContentResponse<String> agregar(Categoria categoria){
        ContentResponse<String> response = new ContentResponse<>();
        try {
            Categoria resultado = categoriaRepository.save(categoria);
            response.setCodigo(1);
            response.setData("Insertado");
            response.setStatus(StatusPeticion.EXITO);
        }
        catch (Exception e){
            response.setDescripcion(e.getMessage());
            System.out.println("Ha ocurrido un error " + e);
        }

        return response;
    }

    public ContentResponse<String> eliminar(Long idItem){
        ContentResponse<String> response = new ContentResponse<>();
        try {
            categoriaRepository.deleteById(idItem);
            response.setCodigo(1);
            response.setData("Eliminado");
            response.setStatus(StatusPeticion.EXITO);
        }
        catch (Exception e){
            response.setDescripcion(e.getMessage());
            System.out.println("Ha ocurrido un error " + e);
        }

        return response;
    }

    public ContentResponse<String> actualizar(Categoria categoria){
        ContentResponse<String> respuesta = new ContentResponse<>();
        respuesta.setStatus(StatusPeticion.ERROR);
        respuesta.setCodigo(0);
        Categoria catFinded = categoriaRepository.findById(categoria.getId()).orElse(new Categoria());
        if (catFinded.getId()!= null){
            Categoria res = categoriaRepository.save(categoria);
            respuesta.setData(res.toString());
            respuesta.setCodigo(1);
            respuesta.setStatus(StatusPeticion.EXITO);
        }
        return respuesta;
    }

}
