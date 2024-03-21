package com.kog.cm.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kog.cm.entity.Faculty;
import com.kog.cm.service.FacultyService;

@SpringBootTest
@AutoConfigureMockMvc
class FacultyControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FacultyService facultyService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testSaveFaculty() throws Exception {
		Faculty faculty=new Faculty(1,"usha","cs");  
		when(facultyService.saveFaculty(faculty)).thenReturn(faculty);        
		// Act & Assert
		        mockMvc.perform(MockMvcRequestBuilders.post("/admin/insert").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(faculty)))
		        .andExpect(MockMvcResultMatchers.status().isOk());     
	}

	@Test
	void testGetFacultyById() throws  Exception {
		Faculty faculty=new Faculty(1,"usha","cs");  
		when(facultyService.getFacultyById(1)).thenReturn(faculty);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/user/get/{facultyId}", 1).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(faculty)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.facultyId").value(1))
		
		.andExpect(MockMvcResultMatchers.jsonPath("$.facultyName").value("usha"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subject").value("cs"));
		
	 
				verify(facultyService,times(1)).getFacultyById(1);
	}

	@Test
	void testDeleteFaculty() throws  Exception {
		Faculty faculty=new Faculty(1,"usha","cs"); 
		doNothing().when(facultyService).deleteFaculty(1);
		mockMvc.perform(delete("/admin/delete/{facultyId}",1)
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(faculty)))
		.andExpect(status().isOk());
	}

}
