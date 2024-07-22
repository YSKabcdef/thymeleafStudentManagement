package portfolio.java.sms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import portfolio.java.sms.dto.StudentDto;
import portfolio.java.sms.entity.Student;
import portfolio.java.sms.repo.StudentRepo;
import portfolio.java.sms.service.StudentService;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    private StudentRepo studentRepo;
    private ModelMapper modelMapper;

    @Override
    public List<StudentDto> listAllStudents() {
        
        List<Student> students = studentRepo.findAll();
        return students.stream().map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        Student savedStudent = studentRepo.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        Student savedStudent = studentRepo.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);

    }

    @Override
    public StudentDto findStudent(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(()->new RuntimeException("Not found Student with id of: " + id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto,Student.class);
        studentRepo.delete(student);
    }
    
}
