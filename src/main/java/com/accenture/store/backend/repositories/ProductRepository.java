package com.accenture.store.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.store.backend.dtos.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
