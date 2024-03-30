package com.example.examen1.service.impl;

import com.example.examen1.entity.Categoria;
import com.example.examen1.entity.Producto;
import com.example.examen1.repository.CategoriaRepository;
import com.example.examen1.repository.ProductoRepository;
import com.example.examen1.service.FileInt;
import com.example.examen1.util.constants.StatusPeticion;
import com.example.examen1.util.web.ContentResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FileImpl implements FileInt {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Metodo para lectura del archivo de excel y recorrido de las filas, por cada
     * fila se almacena un registro en la BD
     * @param file archivo que viene en el formulario
     * @param numberOfSheet campo que se recibe desde el formulario en donde se especifican
     *                      la cantidad de hojas con las que cuenta el archivo de excel
     * @return String cadena con mensaje de respuesta del procesamiento
     */
    public ContentResponse<List<String>> upload(MultipartFile file, Integer numberOfSheet) {
        ContentResponse<List<String>> respuesta = new ContentResponse<>();
        List<String> listadoDetalles = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

            if (numberOfSheet == null || numberOfSheet < 0) {
                numberOfSheet = workbook.getNumberOfSheets();
            }

            int cantidadFilasInsertadas = 0;
            for (int i = 0; i < numberOfSheet; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                System.out.println("Recorriendo hoja " + sheet.getSheetName());
                Iterator<Row> rowIterator = sheet.rowIterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    if (row.getRowNum() > 0){
                        String resultadoGuardado = guardarProducto(row);
                        if(resultadoGuardado.isEmpty()){
                            cantidadFilasInsertadas++;
                        }else{
                            listadoDetalles.add(resultadoGuardado);
                        }
                    }
                }
            }
            respuesta.setDescripcion("Cantidad de filas insertadas " + cantidadFilasInsertadas);
            respuesta.setData(listadoDetalles);
            respuesta.setError(false);
            respuesta.setCodigo(1);
            respuesta.setStatus(StatusPeticion.EXITO);
        }
        catch (Exception e){
            respuesta.setError(true);
            respuesta.setCodigo(0);
            respuesta.setStatus(StatusPeticion.ERROR);
            respuesta.setDescripcion(e.getMessage());
        }


        return respuesta;
    }

    private String guardarProducto(Row row){
        String resultado = "";
        try {
            /*
            Se almacenan en una variable los valores de las celdas, estos se accesan por la posicion
            del indice
             */
            Cell codigo = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            Cell descripcion = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            Cell categoria = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            //Se consultan la categoria por codigo para sacar el id, ya que con este se relaciona como clave primaria
            Categoria categoriaConsulta = categoriaRepository.findByCodigo(categoria.getStringCellValue());
            if (categoriaConsulta == null){
                resultado = "El producto " + row.getRowNum() + " tiene una categoria desconocida" ;
            }
            if(codigo.getStringCellValue().length() > 6){
                resultado = "El codigo del producto " + row.getRowNum() + " excede los 6 caracteres" ;
            }
            if(descripcion.getStringCellValue().length() > 6){
                resultado = "La descripcion del producto " + row.getRowNum() + " excede los 30 caracteres" ;
            }
            if(descripcion.getStringCellValue().isEmpty()){
                resultado = "La descripcion del producto " + row.getRowNum() + " está vacio" ;
            }
            if(categoria.getStringCellValue().length() > 6){
                resultado = "La categoria del producto " + row.getRowNum() + " excede los 6 caracteres" ;
            }
            if(categoria.getStringCellValue().isEmpty()){
                resultado = "La categoria del producto " + row.getRowNum() + " está vacio" ;
            }


            List<Producto> prods = new ArrayList<>();
            //Si la fila tiene datos y no es la fila de cabecera se crea una entidad y se registra en la bd
            if (!codigo.getStringCellValue().isEmpty() && resultado.isEmpty()){
                Producto producto = new Producto();
                Categoria categoriaprod = new Categoria();
                producto.setCodigo(codigo.getStringCellValue());
                producto.setDescripcion(descripcion.getStringCellValue());

                categoriaprod.setCodigo(categoria.getStringCellValue());
                categoriaprod.setId(categoriaConsulta.getId());
                producto.setCategoria(categoriaprod);
                producto.setActivo(true);
                prods.add(producto);
            }
            productoRepository.saveAll(prods);

        }catch (Exception e){
            System.out.println(e.getMessage());
            if (e.getMessage().contains("Duplicate entry")){
                resultado = "El producto " + row.getRowNum() + " tiene un codigo duplicado";
            }
        }
        return resultado;
    }
}