package com.java.code.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.code.model.Student;
import com.java.code.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
    private StudentService studentService;

    // display list of students
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "index";
    }
    
    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        // create model attribute to bind form data
    	Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }
    
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        // save student to database
    	studentService.saveStudent(student);
        return "redirect:/";
    }
    
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
     
     // get student from the service
    	Student student = studentService.getStudentById(id);
     
     // set student as a model attribute to pre-populate the form
     model.addAttribute("student", student);
     return "update_student";
    }
    
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable (value = "id") long id) {
     
     // call delete employee method 
     this.studentService.deleteStudentById(id);
     return "redirect:/";
    }
    
    @RequestMapping("/search")
    public String search(Model model) {
    	Student student = new Student();
        model.addAttribute("student", student);
        return "search_student";
    }
    
    @RequestMapping("/searchStudent")
    public String searchStudent(@RequestParam(name = "id", defaultValue = "1") int id,Model model) {
    	Student student=studentService.find(id);
        List<Student> students =new ArrayList<>();
        Optional<Student> studentExists = Optional.ofNullable(student);
        if(studentExists.isPresent()) {
        	students.add(student);
        	model.addAttribute("listStudents", students);
        	return "view_student";
        }
        else {
        	Student student1 = new Student();
            model.addAttribute("student", student1);
        	model.addAttribute("errorMessage","Student does not exist !! ");
        	return "search_student";
        }
    }
}
