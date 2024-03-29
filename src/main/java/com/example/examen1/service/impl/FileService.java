package com.example.examen1.service.impl;

import com.example.examen1.entity.Categoria;
import com.example.examen1.entity.Producto;
import com.example.examen1.repository.CategoriaRepository;
import com.example.examen1.repository.ProductoRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Mhosein-abbasi 11/9/21
 */
@org.springframework.stereotype.Service
public class FileService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    public String upload(MultipartFile file, Integer numberOfSheet) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());



        if (numberOfSheet == null || numberOfSheet < 0) {
            numberOfSheet = workbook.getNumberOfSheets();
        }
        for (int i = 0; i < numberOfSheet; i++) {
            // Getting the Sheet at index i
            Sheet sheet = workbook.getSheetAt(i);
            System.out.println("=> " + sheet.getSheetName());
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();
            // 1. You can obtain a rowIterator and columnIterator and iterate over them
            System.out.println("Iterating over Rows and Columns using Iterator");
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell codigo = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell descripcion = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell categoria = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                Categoria categoriaConsulta = categoriaRepository.findByCodigo(categoria.getStringCellValue());
                if (!codigo.getStringCellValue().equals("") && row.getRowNum() > 0){
                    Producto producto = new Producto();
                    Categoria categoriaprod = new Categoria();
                    producto.setCodigo(codigo.getStringCellValue());
                    producto.setDescripcion(descripcion.getStringCellValue());

                    categoriaprod.setCodigo(categoria.getStringCellValue());
                    categoriaprod.setId(categoriaConsulta.getId());
                    producto.setCategoria(categoriaprod);

                    productoRepository.save(producto);

                }




                // Now let's iterate over the columns of the current row
                /*Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    System.out.print(cellValue + "\t");
                }
                System.out.println();*/
            }
        }
        return "OK";
    }
}