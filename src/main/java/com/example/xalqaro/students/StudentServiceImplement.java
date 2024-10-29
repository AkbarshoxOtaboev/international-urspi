package com.example.xalqaro.students;

import com.example.xalqaro.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImplement implements StudentService {
    public final StudentRepository studentRepository;
    public final StorageService storageService;
    @Override
    public void addStudent(StudentDTO studentDTO) {
        String imageLink = storageService.store(studentDTO.getImageLink());
        String idCardFileLink = storageService.store(studentDTO.getIdCardFileLink());
        String diplomaOrCertificateLink = storageService.store(studentDTO.getDiplomaOrCertificateLink());
        String testimonyFileLink = storageService.store(studentDTO.getTestimonyFileLink());
        var student = Student.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
                .phone(studentDTO.getPhone())
                .birthDate(studentDTO.getBirthDate())
                .gender(studentDTO.getGender())
                .country(studentDTO.getCountry())
                .studyType(studentDTO.getStudyType())
                .studyDirection(studentDTO.getStudyDirection())
                .imageLink(imageLink)
                .idCardSeries(studentDTO.getIdCardSeries())
                .idCardNumber(studentDTO.getIdCardNumber())
                .idCardFileLink(idCardFileLink)
                .diplomaOrCertificateLink(diplomaOrCertificateLink)
                .testimonyFileLink(testimonyFileLink)
                .status(1)
                .build();
        studentRepository.save(student);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return studentRepository.existsByPhone(phone);
    }
}
