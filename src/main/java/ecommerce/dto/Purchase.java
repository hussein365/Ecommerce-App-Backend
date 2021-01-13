package ecommerce.dto;

import java.util.Set;

import ecommerce.entity.Address;

import ecommerce.entity.Customer;
import ecommerce.entity.Order;
import ecommerce.entity.OrderItem;

public class Purchase {
	
	
	private Customer customer;
	
	
	private Address shippingAddress;
	
	
	private Address billingAddress;
	
	private Order order;
	
	private Set<OrderItem> orderItems;
	
	public Purchase() {
		
	}

	public Purchase(Customer customer, Address shippingaddress, Address billingaddress, Order order,
			Set<OrderItem> orderItems) {
		this.customer = customer;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.order = order;
		this.orderItems = orderItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	

}
