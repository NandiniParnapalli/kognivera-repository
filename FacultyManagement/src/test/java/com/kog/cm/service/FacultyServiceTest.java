package com.kog.cm.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.kog.cm.entity.Faculty;
import com.kog.cm.repository.FacultyRepository;

class FacultyServiceTest {

	
	@InjectMocks
	FacultyService facultyService;
	@Mock
	FacultyRepository facultyRepository;
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void testSaveFaculty() {
		Faculty faculty=new Faculty(1,"usha","cs");
		when(facultyRepository.save(faculty)).thenReturn(faculty);
		assertEquals(faculty,  facultyService.saveFaculty(faculty));
	}

	@Test
	void testGetFacultyById() {
		Faculty faculty=new Faculty(1,"usha","cs");
		when(facultyRepository.findById(1)).thenReturn(Optional.of(faculty));
		assertEquals(faculty,facultyService.getFacultyById(1));
	}

	@Test
	void testDeleteFaculty() {
		Faculty faculty=new Faculty(1,"usha","cs");
		when(facultyRepository.findById(faculty.getFacultyId())).thenReturn(Optional.of(faculty));
		facultyService.deleteFaculty(faculty.getFacultyId());
		verify(facultyRepository,times(1)).delete(faculty);
	}

}
