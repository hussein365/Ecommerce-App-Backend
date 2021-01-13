package ecommerce.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecommerce.dao.CustomerRepository;
import ecommerce.dto.Purchase;
import ecommerce.dto.PurchaseResponse;
import ecommerce.entity.Customer;
import ecommerce.entity.Order;
import ecommerce.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	
	private CustomerRepository customerRepository;
	
	
	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}
	
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		Order order=purchase.getOrder();
		
		String orderTrackingNumber=generateOrderTrackingNumber();
		
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		
		Set<OrderItem> orderItems=purchase.getOrderItems();
		orderItems.forEach(item-> order.add(item));
		
		
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		
		Customer customer=purchase.getCustomer();
		customer.add(order);
		
		customerRepository.save(customer);
		
		return new PurchaseResponse(orderTrackingNumber);
		
	}


	private String generateOrderTrackingNumber() {
		
		return UUID.randomUUID().toString();
	}

}
