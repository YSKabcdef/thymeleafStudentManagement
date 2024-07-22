package portfolio.java.sms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import portfolio.java.sms.dto.StudentDto;
import portfolio.java.sms.service.StudentService;

@Controller
@AllArgsConstructor
public class StudentController {
    
    private StudentService studentService;

    @GetMapping("/students")
    private String listAllStudent(Model model){
        List<StudentDto> students = studentService.listAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/students/new")
    private String newStudent(Model model){
            StudentDto student = new StudentDto();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    private String addStudent(@Valid @ModelAttribute("student") StudentDto student,
    BindingResult result, Model model ){
        if(result.hasErrors()){
            model.addAttribute("student", student);
            return "create_student";
        }
            
        studentService.createStudent(student);
      
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/edit")
    public String editStudent(@PathVariable("id") Long id,Model model){
        StudentDto student = studentService.findStudent(id);
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String saveStudent(@PathVariable("id") Long id,@Valid @ModelAttribute StudentDto student, BindingResult result , Model model ){
        if(result.hasErrors()){
            model.addAttribute("student", student);
            return "edit_student";
        }
        student.setId(id);
        studentService.updateStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id){
        StudentDto student = studentService.findStudent(id);
        studentService.deleteStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/students/{id}/view")
    public String viewStudent(@PathVariable("id") Long id,  Model model){
        StudentDto student = studentService.findStudent(id);
        model.addAttribute("student", student);
        return "view_student";
    }
}   
