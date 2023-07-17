package com.desafio.dev.domain.business.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class UploadFileServiceImplTest {

    @Mock
    private FileCopyUtils fileCopyUtils;

    @InjectMocks
    private UploadFileServiceImpl uploadFileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpload() throws IOException {
        String fileName = "test-file.txt";
        byte[] fileContent = "Test file content".getBytes();

        MockMultipartFile multipartFile = new MockMultipartFile("file", fileName, "text/plain", fileContent);
        File targetFile = mock(File.class);
        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        uploadFileService.upload(multipartFile, fileName);

        verify(fileCopyUtils, times(1)).copy(multipartFile.getInputStream(), fileOutputStream);
    }

}
