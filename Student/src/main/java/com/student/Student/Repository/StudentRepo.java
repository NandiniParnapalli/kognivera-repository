package com.student.Student.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.student.Student.beans.Student;

public interface StudentRepo extends MongoRepository<Student, Integer> {

}
