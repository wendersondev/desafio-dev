package com.desafio.dev.utils;

import com.desafio.dev.domain.exceptions.SanitizerFileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sanitizer {

    public static List<String> sanitizeFile(MultipartFile multipartFile){
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 80) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new SanitizerFileException("Erro ao limpar o arquivo", e);
        }

        return lines;
    }

    public static String sanitize(MultipartFile multipartFile){
        String lines = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 80) {
                    String s = lines + line + ",";
                    lines += s;
                }
            }
        } catch (IOException e) {
            throw new SanitizerFileException("Erro ao limpar o arquivo", e);
        }

        return lines;
    }

}
