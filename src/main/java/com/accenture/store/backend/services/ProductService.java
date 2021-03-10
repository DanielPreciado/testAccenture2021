package com.accenture.store.backend.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.store.backend.dtos.Order;
import com.accenture.store.backend.dtos.Product;
import com.accenture.store.backend.dtos.User;
import com.accenture.store.backend.repositories.OrderRepository;
import com.accenture.store.backend.repositories.ProductRepository;
import com.accenture.store.backend.repositories.UserRepository;

@RestController
@RequestMapping("/store/api")
public class ProductService {

	//____________________________________________
	// Attributes
	//____________________________________________

	/**
	 * representacion de un productor
	 */
	@Autowired
	private ProductRepository productRepo;
	
	/**
	 * representacion de una orden
	 */
	@Autowired
	  private OrderRepository orderRepo;


	//____________________________________________
	// Attributes
	//____________________________________________


	@GetMapping ("/products")
	public List <Product> getAllProducts()
	{
		return productRepo.findAll();
	}
	
	  /**
	   * Create an product.
	   *
	   * @param product the product
	   * @return the product
	   */
	  @PostMapping("/products")
	  public Product createProudct( @RequestBody Product product) {

		  Optional<Order> order = orderRepo.findById(product.getOrder().getIdOrder());
		  product.setOrder(order.get());
	    return productRepo.save(product);
	  }

}
