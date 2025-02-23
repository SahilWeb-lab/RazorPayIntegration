package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Orders;
import com.example.service.OrdersService;
import com.razorpay.RazorpayException;

@Controller
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;

	@GetMapping("/orders")
	public String ordersPage() {
		return "orders";
	}
	
//	Create a handler to create orders:
	@PostMapping(value = "/createOrder", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> createOrders(@RequestBody Orders orders) throws RazorpayException {
		Orders resultOrder = ordersService.createOrders(orders);
		return new ResponseEntity<>(resultOrder, HttpStatus.CREATED);
	}
	
	@PostMapping("/paymentCallback")
	public String paymentCallBack(@RequestParam String razorpay_order_id) {
		System.out.println(razorpay_order_id);
		ordersService.updateStatus(razorpay_order_id);
		return "success";
	}
}
