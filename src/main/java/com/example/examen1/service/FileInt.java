package com.example.examen1.service;

import com.example.examen1.util.web.ContentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileInt {
    ContentResponse<List<String>> upload(MultipartFile file, Integer numberOfSheet);
}
