package com.example.examen1.controller;

import com.example.examen1.entity.Categoria;
import com.example.examen1.service.CategoriaInt;
import com.example.examen1.util.web.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("categoria")
public class CategoriaController {
    @Autowired
    private CategoriaInt categoriaInt;
    @GetMapping(path = "/consultar")
    public ContentResponse<List<Categoria>> consultar(){
        return categoriaInt.consulta();
    }

    @GetMapping(path = "/consultarCodigo/{codigo}")
    public ContentResponse<List<Categoria>> consultarPorCodigo(@PathVariable String codigo){
        return categoriaInt.consultaPorCodigo(codigo);
    }

    @GetMapping(path = "/consultarDescripcion/{data}")
    public ContentResponse<List<Categoria>> consultarPorDescripcion(@PathVariable String data){
        return categoriaInt.consultaPorDescripcion(data);
    }

    @PostMapping(path = "/agregar")
    public ContentResponse<String> agregar(@RequestBody Categoria categoria){
        return categoriaInt.agregar(categoria);
    }

    @PutMapping(path = "/actualizar")
    public ContentResponse<String> actualizar(@RequestBody Categoria categoria){

        return categoriaInt.actualizar(categoria);
    }

    @DeleteMapping(path = "/eliminar/{idItem}")
    public ContentResponse<String> eliminar(@PathVariable Long idItem){

        return categoriaInt.eliminar(idItem);
    }
}
