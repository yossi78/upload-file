package com.example.uploadfile.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Human {

    private Long id;
    private Integer age;
    private String name;
    private MultipartFile file;
}
