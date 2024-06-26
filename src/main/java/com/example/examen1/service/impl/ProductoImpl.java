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

    public ContentResponse<List<Producto>> consultarPorCodigo(String codigo){
        ContentResponse<List<Producto>> ventas = new ContentResponse<>();
        List<Producto> listaProd = productoRepository.buscarPorCodigo(codigo);
        ventas.setData(listaProd);
        ventas.setCodigo(1);
        ventas.setError(false);
        ventas.setStatus(StatusPeticion.EXITO);
        return ventas;
    }

    public ContentResponse<List<Producto>> consultarPorDescripcion(String codigo){
        ContentResponse<List<Producto>> ventas = new ContentResponse<>();
        List<Producto> listaProd = productoRepository.buscarPorDescripcion(codigo);
        ventas.setData(listaProd);
        ventas.setCodigo(1);
        ventas.setError(false);
        ventas.setStatus(StatusPeticion.EXITO);
        return ventas;
    }

    public ContentResponse<List<Producto>> consultarPorCodigoBarra(String codigo){
        ContentResponse<List<Producto>> ventas = new ContentResponse<>();
        List<Producto> listaProd = productoRepository.consultaProductosPorCodigo(codigo);
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
            response.setCodigo(1);
            response.setStatus(StatusPeticion.EXITO);
            if (productoRepository.existsById(idItem)){
                productoRepository.deleteById(idItem);
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

    public ContentResponse<String> actualizar(Producto producto){
        ContentResponse<String> respuesta = new ContentResponse<>();
        respuesta.setStatus(StatusPeticion.ERROR);
        respuesta.setCodigo(0);

        try {
            if (productoRepository.existsById(producto.getId())){
                Producto res = productoRepository.save(producto);
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
