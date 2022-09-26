package com.viksingh.springdbencryption;

import com.viksingh.springdbencryption.model.Student;
import com.viksingh.springdbencryption.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDbEncryptionApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void insertData() {
		Student student = new Student();
		student.setFirstName("Vikash");
		student.setLastName("Singh");
		student.setMobile("9716484616");
		studentRepository.save(student);
	}

}
