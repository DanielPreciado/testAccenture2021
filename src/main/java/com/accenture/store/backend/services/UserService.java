package com.accenture.store.backend.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.store.backend.dtos.Invoice;
import com.accenture.store.backend.dtos.Order;
import com.accenture.store.backend.dtos.Product;
import com.accenture.store.backend.dtos.User;
import com.accenture.store.backend.repositories.InvoiceRepository;
import com.accenture.store.backend.repositories.OrderRepository;
import com.accenture.store.backend.repositories.ProductRepository;
import com.accenture.store.backend.repositories.UserRepository;


@RestController
@RequestMapping("/store/api")
public class UserService {
	
	
	//____________________________________________
	// Attributes
	//____________________________________________
	
	/**
	 * representacion de un usuario
	 */
	@Autowired
	  private UserRepository userRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	/**
	 * representacion de un productor
	 */
	@Autowired
	private ProductRepository productRepo;
	
	/**
	 * representacion de una factura
	 */
	@Autowired
	private InvoiceRepository InvoiceRepo;
	

	
	//____________________________________________
	// Methods
	//____________________________________________
	
	  /**
	   * Get all users list.
	   *
	   * @return the list
	   */
	  @GetMapping("/users")
	  public List<User> getAllUsers() {
	    return userRepo.findAll();
	  }
	  
	  /**
	   * Create an user 
	   *
	   * @param user the user
	   * @return the user
	   */
	  @PostMapping("/users")
	  public User createUser( @RequestBody User user) {
	    return userRepo.save(user);
	  }
	  
	  /**
	   * Gets users by id.
	   *
	   * @param userId the user id
	   * @return the users by id
	   */
	  @GetMapping("/users/{id}")
	  public ResponseEntity<Optional<User>> getUsersById(@PathVariable(value = "id") Integer userId){
		  
	    Optional<User> user = userRepo.findById(userId);
	    
	    return ResponseEntity.ok().body(user);
	    
	  }
	  
	  /**
	   * Get all  orders of user.
	   *
	   * @return the list of orders 
	   */
	  @GetMapping("/users/{id}/orders")
	  public List<Order> getAllOrdersOfUser(@PathVariable(value = "id") Integer userId) {
		  List<Order> orders = null;
		  
		  Optional<User> user = userRepo.findById(userId);
		  if(user != null) 
		  {
			  User tempUser = user.get();
			  orders = tempUser.getOrders();
		  }
		  
		  return orders;
	  }
	  
	  /**
	   * Create an order .
	   *
	   * @param user the user
	   * @return the user
	   */
	  @PostMapping("/users/{id}/orders")
	  public Order createOrderOfUser( @PathVariable(value = "id")Integer userId, @RequestBody Order order) {
		  Date date = new Date(); 
		  Optional<User> user = userRepo.findById(userId);
		  order.setUser(user.get());
		  order.setDateOrder(date);
	    return orderRepo.save(order);
	  }
	  
	  /**
	   * Get all  products of order of an user.
	   *
	   * @return the list of products 
	   */
	  @GetMapping("/users/{id}/orders/{orderId}/products")
	  public List<Product> getAllProductsOfOrderOfUser(@PathVariable(value = "orderId") Integer orderId) {
		
		  List<Product> products = null;
		  
		  Optional<Order> order = orderRepo.findById(orderId);
		  if(order != null) 
		  {
			  Order tempOrder = order.get();
			  products = tempOrder.getProducts();
		  }
		  
		  return products;
	  }
	  
	  /**
	   * Create an product in an order of user.
	   *
	   * @param product the product
	   * @return the product
	   */
	  @PostMapping("/users/{id}/orders/{order}/products")
	  public Product addProduct( @PathVariable(value = "order")Integer orderId, @RequestBody Product product) {

		  Date date = new Date();
		  Optional<Order> order = orderRepo.findById(orderId);
		  Order tempOrder = order.get();
		  if(tempOrder.getDateOrder().getTime()- date.getTime() <=18000000) 
		  {
			  tempOrder.setTotal(tempOrder.getTotal()+product.getPrice());
			  
			  orderRepo.save(tempOrder);
			  product.setOrder(order.get());
		    return productRepo.save(product);
		  }
		  else
			  return null;
		  
		 
	  }
	  
	  /**
	   * delete an product in an order of user.
	   *
	   * @param product the product
	   * @return the product
	   */
	  @DeleteMapping("/users/{id}/orders/{order}/products")
	  public void deleteProduct( @PathVariable(value = "order")Integer orderId, @RequestBody Integer idProduct) {

		  Optional<Order> order = orderRepo.findById(orderId);
		  Order tempOrder = order.get();
		  Product product = productRepo.getOne(idProduct);
		  tempOrder.setTotal(tempOrder.getTotal()-product.getPrice());
		  
		  orderRepo.save(tempOrder);
		  product.setOrder(order.get());
	      productRepo.delete(product);
	  }
	  
	  
	  
	  /**
	   * generate an invoice of a order of user.
	   *
	   * @param product the product
	   * @return the product
	   */
	  @PostMapping("/users/{id}/orders/{order}/invoice")
	  public Invoice generateInvoice( @PathVariable(value = "order")Integer orderId ) {

		  Optional<Order> order = orderRepo.findById(orderId);
		  Order tempOrder = order.get();
		  Date date = new Date();
		  Invoice invoice = null;
		  
		  if(tempOrder.getInvoice() == null)
		  {
			   invoice = new Invoice(date);
			   invoice.setOrder(tempOrder);
				  invoice.setIva(tempOrder.getTotal()* 0.19);
				  if(tempOrder.getTotal() >100000) 
				  {
					  invoice.setPrice(tempOrder.getTotal()+invoice.getIva());
				  }
				  else 
				  {
					  invoice.setPrice(tempOrder.getTotal()+invoice.getIva()+tempOrder.getTotal()*0.25);
				  }						  
		  }
		  else 
		  {
			  invoice = tempOrder.getInvoice();
			  invoice.setOrder(tempOrder);
			  invoice.setIva(tempOrder.getTotal()* 0.19);
			  if(tempOrder.getTotal() >100000) 
			  {
				  invoice.setPrice(tempOrder.getTotal()+invoice.getIva());
			  }
			  else 
			  {
				  invoice.setPrice(tempOrder.getTotal()+invoice.getIva()+tempOrder.getTotal()*0.25);
			  }						  
			  
		  }	  

		  
		  orderRepo.save(tempOrder);
	    return InvoiceRepo.save(invoice);
	  }
	  
	  
	  

	  
	  

}
