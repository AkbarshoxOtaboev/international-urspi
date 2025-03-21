package com.example.xalqaro.students;

import com.example.xalqaro.storage.StorageService;
import jakarta.persistence.Tuple;
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
        String diplomaOrCertificateLink = storageService.store(studentDTO.getDiplomaOrCertificateLink());
        String testimonyFileLink = storageService.store(studentDTO.getTestimonyFileLink());
        var student = Student.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
                .phone(studentDTO.getPhone())
                .birthDate(studentDTO.getBirthDate())
                .whatsapp(studentDTO.getWhatsapp())
                .gender(studentDTO.getGender())
                .country(studentDTO.getCountry())
                .studyType(studentDTO.getStudyType())
                .studyDirection(studentDTO.getStudyDirection())
                .imageLink(imageLink)
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

    @Override
    public void changeStudentStatus(Long id, Integer status) {
        Student student = getStudent(id);
        student.setStatus(status);
        studentRepository.saveAndFlush(student);
    }

    @Override
    public Integer fetchActiveApplication() {
        return studentRepository.countByActiveStatus();
    }

    @Override
    public StudentStatusDTO fetchStudentCountStatus() {
        Object[] result = studentRepository.countsStudentStatusRaw().get(0);
        return new StudentStatusDTO(
                ((Number) result[0]).intValue(),
                ((Number) result[1]).intValue(),
                ((Number) result[2]).intValue(),
                ((Number) result[3]).intValue()
        );
    }
}
