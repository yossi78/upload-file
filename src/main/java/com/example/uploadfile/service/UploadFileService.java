package com.example.uploadfile.service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
public class UploadFileService {



    public void saveFileToHardDrive(MultipartFile file) throws IOException {
        log.info("Save file to hardDrive");
        byte[] bytes = file.getBytes();
        Path path = Paths.get("/home/swm/SWM_ALL/apache-tomcat/temp/TARGET-FOLDER/" + file.getOriginalFilename());
        Files.write(path, bytes);
    }

    @SneakyThrows
    public void saveFilesToHardDrive(MultipartFile[] files) {
        for (MultipartFile file : files) {
            saveFileToHardDrive(file);
        }
    }


}
