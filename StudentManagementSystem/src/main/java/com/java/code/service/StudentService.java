package com.java.code.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.code.model.Student;
import com.java.code.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public void saveStudent(Student student) {
        this.studentRepository.save(student);
    }

    public Student getStudentById(long id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;
        if (optional.isPresent()) {
        	student = optional.get();
        } else {
            throw new RuntimeException(" Student not found for id :: " + id);
        }
        return student;
    }

    public void deleteStudentById(long id) {
    	  this.studentRepository.deleteById(id);
    	 }
    
    public Student find(long id) {
		// TODO Auto-generated method stub
		if(studentRepository.findById(id).isPresent()) {
			return studentRepository.findById(id).get();
		}
		else {
			return null;
		}
	}
    
   //Added for pagination
    public Page<Student> findPaginated(int pageNo, int pageSize)
    {
    	Pageable pageable = PageRequest.of( pageNo-1,pageSize );
    	return this.studentRepository.findAll(pageable);
    }
}
