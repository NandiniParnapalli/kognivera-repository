package com.student.Student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.Student.Repository.StudentRepo;
import com.student.Student.beans.Student;

@RestController
//@RequestMapping("/api/students")
public class StudentController {
@Autowired
private StudentRepo rep;

@GetMapping("/all")
public List<Student> getAll() {
	return rep.findAll();
}
@PostMapping("/insert")
public ResponseEntity<Student> insert(@RequestBody Student s)
{
	Student st=rep.save(s);
	return ResponseEntity.ok(st);
}
@GetMapping("/get")
public ResponseEntity<Student> getById(@RequestParam Integer id)
{
	Optional<Student> op=rep.findById(id);
	return op.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
}
@DeleteMapping("/delete")
public ResponseEntity<?> deleteObject(@RequestParam Integer id)
{
	if(rep.existsById(id))
	{
		rep.deleteById(id);
	
		return ResponseEntity.noContent().build();
	}
	else
	{
		return ResponseEntity.notFound().build();
	}
}
@PutMapping("/{id}")
public ResponseEntity<Student> update(@PathVariable Integer id,@RequestBody Student s)
{
	Optional<Student> op=rep.findById(id);
	if(op.isPresent())
	{
		Student s1=op.get();
		s1.setName(s.getName());
		s1.setAge(s.getAge());
		s1.setSubject(s.getSubject());
		s1.setMarks(s.getMarks());
		Student st=rep.save(s1);
		return ResponseEntity.ok(st);
	}
	else
	{
		return ResponseEntity.notFound().build();
	}
}
}
