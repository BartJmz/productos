package com.example.examen1.controller;

import com.example.examen1.service.impl.FileService;
import org.apache.catalina.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mhosein-abbasi 11/9/21
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
    private final FileService fileService;

    public FileUploadController(FileService service) {
        this.fileService = service;
    }

    @PostMapping("/upload")
    public String upload(
            @RequestParam MultipartFile file,
            @RequestParam Integer numberOfSheet)
            throws Exception {
        return fileService.upload(file, numberOfSheet);
    }
}