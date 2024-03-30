package com.example.examen1.controller;

import com.example.examen1.service.FileInt;
import com.example.examen1.util.web.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private FileInt fileInt;

    @PostMapping("/upload")
    public ContentResponse<List<String>> upload(
            @RequestParam MultipartFile file,
            @RequestParam Integer numberOfSheet)
            throws Exception {
        return fileInt.upload(file, numberOfSheet);
    }
}