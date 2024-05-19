package com.StudentManager.StudentManagerProject;

import com.StudentManager.StudentManagerProject.dao.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagerProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagerProjectApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;
	

	@Override
	public void run(String... args) throws Exception {
//		Student student1 = new Student("Ayoub", "Anafal", "Ayoub@gmail.com");
//		studentRepository.save(student1);
//
//		Student student2 = new Student("Emily ", "Johnson", "emily.johnson@example.com");
//		studentRepository.save(student2);
//
//		Student student3 = new Student("Michael ", "Smith", "m.smith@example.com");
//
//		Admin admin1 = new Admin("ayoub", "ayoubanafal2200@gmail.com", "123");
//		adminRepository.save(admin1);
	}

}
