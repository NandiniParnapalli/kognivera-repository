package com.kog.cm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.kog.cm.entity.Faculty;
import com.kog.cm.repository.FacultyRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FacultyService {

   private FacultyRepository facultyRepository;
	
     public FacultyService(FacultyRepository facultyRepository)
    {
	
	      this.facultyRepository = facultyRepository;
    }

	public Faculty saveFaculty(Faculty faculty) {
		
	      log.info("inserting faculties");
		return facultyRepository.save(faculty);
	}
    
	public List<Faculty> getFaculties() {
	      
		log.info("get all faculties");
		return facultyRepository.findAll();
	}
	
	public Faculty getFacultyById(Integer facultyId)
	{
		log.info("get faculty data by id");
		Faculty faculty=facultyRepository.findById(facultyId).orElse(null);
		
		return faculty;
	}
	
	public Faculty getById(Long facultyId)
	{
		return facultyRepository.findByOne(facultyId);
	}
	
	
	
	public String  deleteFaculty(Integer facultyId)
	{
		
		Faculty faculty=facultyRepository.findById(facultyId).orElse(null);
		facultyRepository.delete(faculty);
		return "Faculty removed";
	}
	
	public String updateFaculty(Integer facultyId,Faculty faculty)
	{
		Faculty faculty1=facultyRepository.findById(facultyId).orElse(null);
		if(faculty1 !=null)
		{
		facultyRepository.save(faculty);
		return "Faculty updated";
		}
		return "Faculty not found";
	}
	
	public Page<Faculty> getPageSort(int offset,int size,String field)
	 {
		 log.info("paginate and sorting");
		 return facultyRepository.findAll(PageRequest.of(offset, size).withSort(Sort.by(Sort.Direction.DESC,field)));
	 }

}
