package com.accenture.store.backend.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.store.backend.dtos.Order;
import com.accenture.store.backend.dtos.User;
import com.accenture.store.backend.repositories.OrderRepository;
import com.accenture.store.backend.repositories.UserRepository;


@RestController
@RequestMapping("/store/api")
public class OrderService {
	
	//____________________________________________
	// Attributes
	//____________________________________________
	
	/**
	 * representacion de una orden
	 */
	@Autowired
	  private OrderRepository orderRepo;
	
	/**
	 * representacion de un usuario
	 */
	@Autowired
	  private UserRepository userRepo;
	
	//____________________________________________
	// Methods
	//____________________________________________
	
	  /**
	   * Get all orders list.
	   *
	   * @return the list
	   */
	  @GetMapping("/orders")
	  public List<Order> getAllOrders() {
	    return orderRepo.findAll();
	  }
	  
	  /**
	   * Create an order.
	   *
	   * @param user the user
	   * @return the user
	   */
	  @PostMapping("/orders")
	  public Order createOrder( @RequestBody Order order) {
		  Date date = new Date(); 
		  Optional<User> user = userRepo.findById(order.getUser().getIdUser());
		  order.setUser(user.get());
		  order.setDateOrder(date);
	    return orderRepo.save(order);
	  }
	  

}
