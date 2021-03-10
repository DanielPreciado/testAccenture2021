package com.accenture.store.backend.dtos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

	//____________________________________________
	// Attributes
	//____________________________________________

	/**
	 * id de un usuario
	 */
	@Id
	@GeneratedValue
	private Integer idUser ;

	/**
	 * nombre de un usuario
	 */
	@Column(name = "name", nullable = false)
	private String name;


	/**
	 * numero de identificacion de un usuario
	 */
	@Column(name = "id_card", nullable = false)
	private String idCard;

	/**
	 *  direccion de un usuario
	 */
	@Column(name = "address", nullable = false)
	private String  address;


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

	//____________________________________________
	// Constructors
	//____________________________________________

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param name  != null nombre del usuario
	 * @param idCard != null numero de identificacion del usuario 
	 * @param address != null direccion del usuario 
	 */
	public User(String name, String idCard, String address) {
		super();
		this.name = name;
		this.idCard = idCard;
		this.address = address;
	}



	//____________________________________________
	// Methods
	//____________________________________________
	
	/**
	 * @return the idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
    /**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", idCard=" + idCard + ", address=" + address + ", orders="
				+ orders + "]";
	}


	



}
