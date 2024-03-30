package com.example.examen1.service.impl;

import com.example.examen1.entity.Categoria;
import com.example.examen1.entity.CodigoBarra;
import com.example.examen1.entity.Producto;
import com.example.examen1.repository.CodigoBarraRepository;
import com.example.examen1.service.CodigoBarraInt;
import com.example.examen1.util.constants.StatusPeticion;
import com.example.examen1.util.web.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CodigoBarraImpl implements CodigoBarraInt {
    @Autowired
    private CodigoBarraRepository codigoBarraRepository;
    public ContentResponse<List<CodigoBarra>> consulta(){
        ContentResponse<List<CodigoBarra>> codigos = new ContentResponse<>();
        List<CodigoBarra> listaCat = (List<CodigoBarra>) codigoBarraRepository.findAll();
        codigos.setData(listaCat);
        codigos.setCodigo(1);
        codigos.setError(false);
        codigos.setStatus(StatusPeticion.EXITO);
        return codigos;
    }

    public ContentResponse<String> agregar(CodigoBarra codigoBarra){
        ContentResponse<String> response = new ContentResponse<>();
        try {
            CodigoBarra resultado = codigoBarraRepository.save(codigoBarra);
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
            response.setCodigo(1);
            response.setStatus(StatusPeticion.EXITO);
            if (codigoBarraRepository.existsById(idItem)){
                codigoBarraRepository.deleteById(idItem);
                response.setData("Eliminado");

            }else{
                response.setDescripcion("No se encontró el registro");
            }

        }
        catch (Exception e){
            response.setDescripcion(e.getMessage());
            System.out.println("Ha ocurrido un error " + e);
        }

        return response;
    }

    public ContentResponse<String> actualizar(CodigoBarra codigoBarra){
        ContentResponse<String> respuesta = new ContentResponse<>();
        respuesta.setStatus(StatusPeticion.ERROR);
        respuesta.setCodigo(0);
        try {
            if (codigoBarraRepository.existsById(codigoBarra.getId())){
                CodigoBarra res = codigoBarraRepository.save(codigoBarra);
                respuesta.setData(res.toString());
                respuesta.setCodigo(1);
                respuesta.setStatus(StatusPeticion.EXITO);
            }else{
                respuesta.setDescripcion("No se encontró el registro");
            }

        }catch (Exception e){
            respuesta.setDescripcion(e.getMessage());
        }

        return respuesta;
    }
}
