package com.product.Product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.Product.beans.Product;
import com.product.Product.service.ProductService;

@RestController

public class ProductController {
@Autowired
ProductService prod;

@PostMapping("/add")
public Product insert(@RequestBody Product p)
{
	return prod.insert(p);
}

@GetMapping("/getall")
public List<Product> getAll(){
	return prod.getAll();
}

@GetMapping("/{id}")
public Product getById(@PathVariable ("id") int id)
{
	return prod.getById(id);
}

@GetMapping("/name/{name}")
public Product getByName(@PathVariable ("name") String name)
{
	return prod.getByName(name);
}

@DeleteMapping("/delete/{id}")
public String delete(@PathVariable int id)
{
	return prod.delete(id);
}

@PutMapping("/{id}")
public String update(@PathVariable int id,@RequestBody Product product)
{
	return prod.update(id, product);
}
}
