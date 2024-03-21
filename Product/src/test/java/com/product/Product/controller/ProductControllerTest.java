package com.product.Product.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.Product.beans.Product;
import com.product.Product.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
@MockBean
ProductService service;
@Autowired
MockMvc mockMvc;
	@Test
	void testInsert() throws  Exception {
		Product product=new Product(1,"nandu","des",5000,3);
		when(service.insert(product)).thenReturn(product);
		mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(product)))
		.andExpect(status().isOk());
	}

	@Test
	void testGetAll() throws Exception {
		List<Product> products=Arrays.asList(new Product(1,"nandu","des",5000,3),new Product(2,"naveen","pro",1000,9));
		when(service.getAll()).thenReturn(products);
		mockMvc.perform(get("/getall").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id").value(1))
		.andExpect(jsonPath("$[0].name").value("nandu"))
		.andExpect(jsonPath("$[0].description").value("des"))
		.andExpect(jsonPath("$[0].price").value(5000))
		.andExpect(jsonPath("$[0].stock").value(3))
		.andExpect(jsonPath("$[1].id").value(2))
		.andExpect(jsonPath("$[1].name").value("naveen"))
		.andExpect(jsonPath("$[1].description").value("pro"))
		.andExpect(jsonPath("$[1].price").value(1000))
		.andExpect(jsonPath("$[1].stock").value(9));
		
	}

	@Test
	void testGetById() throws Exception {
		Product product=new Product(1,"nandu","des",5000,3);
		when(service.getById(1)).thenReturn(product);
		mockMvc.perform(get("/{id}",1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.name").value("nandu"))
		.andExpect(jsonPath("$.description").value("des"))
		.andExpect(jsonPath("$.price").value(5000))
		.andExpect(jsonPath("$.stock").value(3));
		
	}

	@Test
	void testDelete() throws Exception {
		Product product=new Product(1,"nandu","des",5000,3);
		when(service.delete(1)).thenReturn("deleted");
		mockMvc.perform(delete("/delete/{id}",1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string("deleted"));
		verify(service,times(1)).delete(1);
	}

	@Test
	void testUpdate() throws Exception {
       Product product=new Product(1,"nandu","des",5000,3);
		when(service.update(1, product)).thenReturn("product updated");
        mockMvc.perform(put("/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(product)))
		.andExpect(status().isOk())
		.andExpect(content().string("product updated"));
		
		 verify(service, times(1)).update(1,product);
	}

}
