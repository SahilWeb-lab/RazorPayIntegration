package com.example.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.model.Orders;
import com.example.repository.OrdersRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.annotation.PostConstruct;

@Service
public class OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Value("${razorpay.key.id}")
	private String razorPayId;
	
	@Value("${razorpay.key.secret}")
	private String razorPaySecretKey;
	
	private RazorpayClient razorpayCLient;
	
	@PostConstruct
	public void init() throws RazorpayException {
		this.razorpayCLient = new RazorpayClient(razorPayId, razorPaySecretKey);
	}
	
	public Orders createOrders(Orders orders) throws RazorpayException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", (orders.getAmount() * 100));
		jsonObject.put("currency", "INR");
		jsonObject.put("receipt", orders.getEmail());
		Order razorPayOrder = razorpayCLient.orders.create(jsonObject);
		
		if(!ObjectUtils.isEmpty(razorPayOrder)) {			
			orders.setRazorPayOrderId(razorPayOrder.get("id"));
			orders.setOrderStatus(razorPayOrder.get("status"));;
		}
		
		Orders saveOrders = ordersRepository.save(orders);
		return saveOrders;
	}
	
//	Create a method to update the status of payment:
	public Orders updateStatus(String razorpay_order_id) {
		System.out.println("update status method running...");
		Orders order = ordersRepository.findByRazorPayOrderId(razorpay_order_id);
		order.setOrderStatus("PAYMENT DONE");
		return ordersRepository.save(order);
	}
	
}
