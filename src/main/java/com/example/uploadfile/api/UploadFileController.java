package com.example.uploadfile.api;
import com.example.uploadfile.dto.Human;
import com.example.uploadfile.service.UploadFileService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// YES
@RestController
@RequestMapping(value = "/upload")
@Slf4j
public class UploadFileController {


    private UploadFileService uploadFileService;

    @Autowired
    public UploadFileController(UploadFileService uploadFileService){
        this.uploadFileService=uploadFileService;
    }


    @SneakyThrows
    @PostMapping(value = "/files", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity uploadFiles(@RequestPart(value = "files") MultipartFile[] files) {
        try {
            uploadFileService.saveFilesToHardDrive(files);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            log.error("Failed to save files",e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @SneakyThrows
    @PostMapping(value = "/addHuman",  produces = "application/json")
    public ResponseEntity addHuman(@RequestBody Human human) {
        try {
            uploadFileService.addHuman(human);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            log.error("Failed to save files",e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @SneakyThrows
    @PostMapping(value = "/jsonWithFile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
    public ResponseEntity uploadJsonWithFile(@RequestPart(value = "human") Human human , @RequestPart(value = "file") MultipartFile file) {
        try {
            uploadFileService.persistHumanAndSaveFileToHardDrive(human,file);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            log.error("Failed to save json with file",e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
