package portfolio.java.sms.service;

import java.util.List;

import portfolio.java.sms.dto.StudentDto;

public interface StudentService {
    List<StudentDto> listAllStudents();
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(StudentDto studentDto);
    StudentDto findStudent(Long id);
    void deleteStudent(StudentDto studentDto);
}
