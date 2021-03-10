package com.accenture.store.backend.dtos;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {
	
	//____________________________________________
	// Attributes
	//____________________________________________
	
	/**
	 * id de la orden
	 */
	@Id
	@GeneratedValue
	private Integer idOrder;
	
	/**
	 * estado de una orden
	 */
	@Column(name = "state")
	private String state;
	
	/**
	 * fecha exacta en la que se comenzo la orden
	 */
	@Column(name = "date_order", nullable = false)
	private Date dateOrder;

	/**
	 * precio total de la orden
	 */
	@Column(name = "total")
	private Double total;
	
	/**
	 * usuario al cual pertenece la orden
	 */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
    

    /**
     * lista de productos pedidos en una orden
     */
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> products;
	
	/**
	 * factura asociada a una orden
	 */
	 @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private Invoice invoice;

	
	
	//____________________________________________
	// Constructors
	//____________________________________________
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param dateOrder != null fecha en la que se comienza la orden
	 */
	public Order( Date dateOrder) {
		super();
		this.dateOrder = dateOrder;

	}

	
	//____________________________________________
	// Methods
	//____________________________________________
	
	/**
	 * @return the idOrder
	 */
	public Integer getIdOrder() {
		return idOrder;
	}

	/**
	 * @param idOrder the idOrder to set
	 */
	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the dateOrder
	 */
	public Date getDateOrder() {
		return dateOrder;
	}

	/**
	 * @param dateOrder the dateOrder to set
	 */
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	
	

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", state=" + state + ", dateOrder=" + dateOrder + ", total=" + total
				+ ", user=" + user + ", products=" + products + "]";
	}


	
	
	
	
	
}
