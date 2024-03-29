package com.example.examen1.service.impl;

import com.example.examen1.entity.Producto;
import com.example.examen1.repository.ProductoRepository;
import com.example.examen1.service.ProductoInt;
import com.example.examen1.util.constants.StatusPeticion;
import com.example.examen1.util.web.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoImpl implements ProductoInt {

    @Autowired
    private ProductoRepository productoRepository;
    public ContentResponse<List<Producto>> consulta(){
        ContentResponse<List<Producto>> ventas = new ContentResponse<>();
        List<Producto> listaProd = (List<Producto>) productoRepository.findAll();
        ventas.setData(listaProd);
        ventas.setCodigo(1);
        ventas.setError(false);
        ventas.setStatus(StatusPeticion.EXITO);
        return ventas;
    }

    public ContentResponse<String> agregar(Producto producto){
        ContentResponse<String> response = new ContentResponse<>();
        try {
            Producto resultado = productoRepository.save(producto);
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
            productoRepository.deleteById(idItem);
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

    public ContentResponse<String> actualizar(Producto student){
        ContentResponse<String> respuesta = new ContentResponse<>();
        respuesta.setStatus(StatusPeticion.ERROR);
        respuesta.setCodigo(0);
        Producto studentFinded = productoRepository.findById(student.getId()).orElse(new Producto());
        if (studentFinded.getId()!= null){
            Producto res = productoRepository.save(student);
            respuesta.setData(res.toString());
            respuesta.setCodigo(1);
            respuesta.setStatus(StatusPeticion.EXITO);
        }
        return respuesta;
    }

}
