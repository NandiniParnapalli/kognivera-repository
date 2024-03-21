package com.product.Product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.Product.beans.Product;
import com.product.Product.repository.ProductRepository;

@Service
public class ProductService {

@Autowired
ProductRepository repo;
 
public Product insert(Product p)
{
	return repo.save(p);
}
public List<Product> getAll()
{
	return repo.findAll();
}
public Product getById(int id)
{
	Optional<Product> op=repo.findById(id);
	if(op.isPresent())
	{
		return op.get();
	}
	return null;
}
public Product getByName(String name)
{
	Product list=repo.getByName(name);
	return list;
	
}
public String delete(int id) 
{
	Product p=getById(id);
	repo.delete(p);
	return "deleted";
}
public String update(int id,Product product)
{
	Product p=getById(id);
	if(p!=null)
	{
		 repo.save(product);
		 return "product updated";
	}
	return "product not found"; 
	  
}

}
