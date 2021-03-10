package com.accenture.store.backend.dtos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "invoices")
@EntityListeners(AuditingEntityListener.class)
public class Invoice {

	//____________________________________________
	// Attributes
	//____________________________________________

	/**
	 * id de la factura
	 */
	@Id
	@GeneratedValue
	private Integer idInvoice;

	/**
	 * iva de una factura
	 */
	@Column(name = "iva")
	private Double iva;

	/**
	 * precio total de una factura
	 */
	@Column(name = "price")
	private Double price;

	/**
	 * fecha de una factura
	 */
	@Column(name = "date_invoice")
	private Date dateInvoice;

	/**
	 * order a la cual esta asociada la factura
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Order order;



	//____________________________________________
	// Constructors
	//____________________________________________
	
	/**
	 * 
	 */
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param dateInvoice
	 */
	public Invoice(Date dateInvoice) {
		super();
		this.dateInvoice = dateInvoice;
	}


	//____________________________________________
	// Methods
	//____________________________________________


	/**
	 * @return the idInvoice
	 */
	public Integer getIdInvoice() {
		return idInvoice;
	}



	/**
	 * @param idInvoice the idInvoice to set
	 */
	public void setIdInvoice(Integer idInvoice) {
		this.idInvoice = idInvoice;
	}



	/**
	 * @return the iva
	 */
	public Double getIva() {
		return iva;
	}



	/**
	 * @param iva the iva to set
	 */
	public void setIva(Double iva) {
		this.iva = iva;
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
	 * @return the dateInvoice
	 */
	public Date getDateInvoice() {
		return dateInvoice;
	}



	/**
	 * @param dateInvoice the dateInvoice to set
	 */
	public void setDateInvoice(Date dateInvoice) {
		this.dateInvoice = dateInvoice;
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


	

}
