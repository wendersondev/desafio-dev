package com.desafio.dev.domain.business;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {

    public void upload(MultipartFile multipartFile, String fileName) throws IOException;
}
