package com.example.examen1.controller;

import com.example.examen1.entity.Categoria;
import com.example.examen1.entity.CodigoBarra;
import com.example.examen1.service.CodigoBarraInt;
import com.example.examen1.util.web.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("codigoBarra")
public class CodigoBarraController {
    @Autowired
    private CodigoBarraInt codigoBarraInt;
    @GetMapping(path = "/consultar")
    public ContentResponse<List<CodigoBarra>> consultar(){
        return codigoBarraInt.consulta();
    }

    @PostMapping(path = "/agregar")
    public ContentResponse<String> agregar(@RequestBody CodigoBarra codigoBarra){
        return codigoBarraInt.agregar(codigoBarra);
    }

    @PostMapping(path = "/actualizar")
    public ContentResponse<String> actualizar(@RequestBody CodigoBarra codigoBarra){

        return codigoBarraInt.actualizar(codigoBarra);
    }

    @GetMapping(path = "/eliminar/{idItem}")
    public ContentResponse<String> eliminar(@PathVariable Long idItem){

        return codigoBarraInt.eliminar(idItem);
    }
}
