package com.example.xalqaro.students;

import com.example.xalqaro.config.TableName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = TableName.STUDENTS)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phone;
    private String whatsapp;
    private String birthDate;
    private String gender;
    private String country;
    private String studyType;
    private String studyDirection;
    private String imageLink;
    private String idCardSeries;
    private String idCardNumber;
    private String idCardFileLink;
    private String diplomaOrCertificateLink ;
    private String testimonyFileLink;
    private Integer status;

}
