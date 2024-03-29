package com.example.examen1.controller;

import com.example.examen1.entity.Producto;
import com.example.examen1.service.ProductoInt;
import com.example.examen1.util.web.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("producto")
public class ProductosController {
    @Autowired
    private ProductoInt productoInt;

    @GetMapping(path = "/consultar")
    public ContentResponse<List<Producto>> consultar(){
        return productoInt.consulta();
    }

    @GetMapping(path = "/consultarCodigo/{codigo}")
    public ContentResponse<List<Producto>> consultarCodigo(@PathVariable String codigo){
        return productoInt.consultarPorCodigo(codigo);
    }

    @GetMapping(path = "/consultarDescripcion/{data}")
    public ContentResponse<List<Producto>> consultarDescripcion(@PathVariable String data){

        return productoInt.consultarPorDescripcion(data);
    }

    @PostMapping(path = "/agregar")
    public ContentResponse<String> agregar(@RequestBody Producto producto){
        return productoInt.agregar(producto);
    }

    @PostMapping(path = "/actualizar")
    public ContentResponse<String> actualizar(@RequestBody Producto producto){

        return productoInt.actualizar(producto);
    }

    @GetMapping(path = "/eliminar/{idItem}")
    public ContentResponse<String> eliminar(@PathVariable Long idItem){

        return productoInt.eliminar(idItem);
    }
}
