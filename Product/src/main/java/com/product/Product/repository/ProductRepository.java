package com.product.Product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.Product.beans.Product;
@Repository
public interface ProductRepository extends MongoRepository<Product, Integer>{
@Query("{'name':?0}")
Product getByName(String name);
}
