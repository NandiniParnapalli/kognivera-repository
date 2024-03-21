package com.product.Product.service;

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


import com.product.Product.beans.Product;
import com.product.Product.repository.ProductRepository;

class ProductServiceTest {
	@Mock
ProductRepository repository;
	@InjectMocks
ProductService service;
	@Test
	void testInsert() {
		
	}
@BeforeEach
public void init() {
	MockitoAnnotations.openMocks(this);
}
	@Test
	void testGetAll() {
		Product product=new Product(1,"nandu","des",5000,3);
		when(repository.save(product)).thenReturn(product);
		assertEquals(product, service.insert(product));
	}

	@Test
	void testGetById() {
		Product product=new Product(1,"nandu","des",5000,3);
		when(repository.findById(1)).thenReturn(Optional.of(product));
		Product p=service.getById(1);
		
		assertEquals(product, p);
	}

	@Test
	void testDelete() {
		Product product=new Product(1,"nandu","des",5000,3);
		when(repository.findById(1)).thenReturn(Optional.of(product));
		String result=service.delete(1);
		verify(repository,times(1)).delete(product);
		assertEquals("deleted", result);
	}

	@Test
	void testUpdate() {
		Product product=new Product(1,"nandu","des",5000,3);
		when(repository.findById(1)).thenReturn(Optional.of(product));
		 when(repository.save(product)).thenReturn(product);
		 String p1=service.update(1, product);
		 verify(repository, times(1)).findById(product.getId());
	        verify(repository, times(1)).save(product);
		assertEquals("product updated", p1);
	}

}
