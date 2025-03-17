package com.example.xalqaro.students;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StudentDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String whatsapp;
    private String birthDate;
    private String gender;
    private String country;
    private String studyType;
    private String studyDirection;
    private MultipartFile imageLink;
    private String idCardSeries;
    private String idCardNumber;
    private MultipartFile idCardFileLink;
    private MultipartFile diplomaOrCertificateLink ;
    private MultipartFile testimonyFileLink;
}
