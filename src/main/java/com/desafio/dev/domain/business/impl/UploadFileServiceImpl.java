package com.desafio.dev.domain.business.impl;

import com.desafio.dev.domain.business.UploadFileService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.desafio.dev.utils.ConstantsUtils.PATH_UPLOAD;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public void upload(MultipartFile multipartFile, String fileName) throws IOException {
        File targetFile = new File(PATH_UPLOAD.concat(fileName));
        FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(targetFile));
    }
}
