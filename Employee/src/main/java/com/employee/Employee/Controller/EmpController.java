package com.employee.Employee.Controller;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Employee.DTO.Employee;
import com.employee.Employee.Repository.EmployeeRepo;

@RestController
public class EmpController {
	@Autowired
	EmployeeRepo rep;
@PostMapping("/insert")
public Employee insert(@RequestBody Employee e)
{
	return rep.save(e);
}

@GetMapping("/get")
public List<Employee> display()
{
	return rep.findAll();
}
@GetMapping("/find")
public Employee find(@RequestParam int id)
{
	java.util.Optional<Employee> op=rep.findById(id);
	if(op.isPresent())
	{
		return op.get();
	}
	return null;
}
@DeleteMapping("/delete")
public String delete(@RequestParam int id)
{
	Employee e=find(id);
	if(e!=null)
	{
		rep.deleteById(id);
	return "deleted";
	}
	else
		
	{
		return "data not found";
	}
}
@PutMapping("/update")
public String update(@RequestParam int id)
{
	Employee e=find(id);
	if(e!=null)
	{
		e.setName("nandu");
		e.setAge(23);
		rep.save(e);
	return "updated";
	}
	else
		
	{
		return "data not found";
	}
}
}
