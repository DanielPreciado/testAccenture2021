package com.accenture.store.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.store.backend.dtos.Invoice;
import com.accenture.store.backend.dtos.Order;
import com.accenture.store.backend.repositories.InvoiceRepository;
import com.accenture.store.backend.repositories.OrderRepository;

@RestController
@RequestMapping("/store/api")
public class InvoiceService {

	//____________________________________________
	// Attributes
	//____________________________________________

	/**
	 * representacion de una factura
	 */
	@Autowired
	private InvoiceRepository InvoiceRepo;

	/**
	 * representacion de una orden
	 */
	@Autowired
	private OrderRepository orderRepo;

	//____________________________________________
	// Methods
	//____________________________________________

	/**
	 * Get all orders list.
	 *
	 * @return the list
	 */
	@GetMapping("/invoices")
	public List<Invoice> getAllInvoices() {
		return InvoiceRepo.findAll();
	}

}
