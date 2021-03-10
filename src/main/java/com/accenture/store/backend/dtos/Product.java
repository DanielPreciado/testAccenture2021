package com.accenture.store.backend.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product {
	

	//____________________________________________
	// Attributes
	//____________________________________________

	/**
	 * id del producto
	 */
	@Id
	@GeneratedValue
	private Integer idProduct;
	
	/**
	 * nombre de un producto
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * descripcion de un producto
	 */
	@Column(name = "description")
	private String description;
	
	/**
	 * precio de un producto
	 */
	@Column(name = "price")
	private Double price;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="order_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;


  //____________________________________________
  // Constructors
  //____________________________________________
    
	/**
	 * 
	 */
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

    /**
     * @param idProduct
     * @param name
     * @param description
     * @param price
     * @param order
     */
    public Product( String name, String description, Double price) {
    	super();
    	this.name = name;
    	this.description = description;
    	this.price = price;

    }


    
    //____________________________________________
    // Methods
    //____________________________________________
    
	/**
	 * @return the idProduct
	 */
	public Integer getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct the idProduct to set
	 */
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", name=" + name + ", description=" + description + ", price="
				+ price + ", order=" + order + "]";
	}
    
	
	




    
    
    
    
    
    
    
}
