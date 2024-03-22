package com.student.Student.beans;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Document(collection= "students")
public class Student {
private Integer id;
private String name;
private Integer age;
private String subject;
private Integer marks;


}
