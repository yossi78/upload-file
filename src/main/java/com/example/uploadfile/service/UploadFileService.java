package com.example.uploadfile.service;
import java.io.File;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
public class UploadFileService {



    @SneakyThrows
    public void saveFileToHardDrive(MultipartFile file)  {
        log.info("Save file to hardDrive");
        file.transferTo(new File("/home/swm/SWM_ALL/apache-tomcat/temp/TARGET-FOLDER/" + file.getOriginalFilename()));
    }

    @SneakyThrows
    public void saveFilesToHardDrive(MultipartFile[] files) {
        for (MultipartFile file : files) {
            saveFileToHardDrive(file);
        }
    }


}
