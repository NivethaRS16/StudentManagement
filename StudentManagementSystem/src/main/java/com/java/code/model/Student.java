package com.java.code.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 20)
    @NotBlank(message = "Enter the first name")
    @Size(min = 3, max = 40, message = "First name should be between 3 - 40 characters" )
    private String firstName;

    @Column(name = "last_name", length = 40)
    @NotBlank(message = "Enter the last name")
    @Size(min = 3, max = 40, message = "Last name should be between 3 - 40 characters" )
    private String lastName;

    @Column(name = "email")
	@NotBlank(message = "Enter your email id")
	@Email(message = "Enter a proper email id")
    private String email;
    
    @Column(name = "grade")
	@NotBlank(message = "Enter grades")
    private String grade;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
    
}