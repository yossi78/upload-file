package com.example.uploadfile.service;
import com.example.uploadfile.dto.Human;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
public class UploadFileService {

    private List<Human> humanList;
    public final String targetPath="/home/swm/SWM_ALL/apache-tomcat/temp/TARGET-FOLDER/";

    public UploadFileService(){
        this.humanList=new ArrayList<>();
    }


    @SneakyThrows
    public void saveFilesToHardDrive(MultipartFile[] files) {
        for (MultipartFile file : files) {
            saveFileToHardDrive(file, targetPath+ file.getOriginalFilename());
        }
    }

    @SneakyThrows
    public void saveFileToHardDrive(MultipartFile file,String path)  {
        log.info("Save file to hardDrive");
        file.transferTo(new File(path));
    }


    @SneakyThrows
    public void addHuman(Human human) {
        this.humanList.add(human);
    }


    @SneakyThrows
    public void persistHumanAndSaveFileToHardDrive(Human human,MultipartFile file) {
        this.humanList.add(human);
        if(file==null){
            return;
        }
        saveFileToHardDrive(file, targetPath+ file.getOriginalFilename());
    }

}
